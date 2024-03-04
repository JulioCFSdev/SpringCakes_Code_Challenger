package com.marketplace.cake.services;

import com.marketplace.cake.domain.user.User;
import com.marketplace.cake.enums.UserStatus;
import com.marketplace.cake.dtos.user.*;
import com.marketplace.cake.exceptions.*;
import com.marketplace.cake.infra.security.TokenService;
import com.marketplace.cake.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImplement implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;
    @Override
    public User registerUser(RegisterRequestDTO data) throws
            DuplicateResourceException,
            PasswordConfirmationException,
            EmailNotValidException{

        if(repository.findUserByLogin(data.login()) != null){
            throw new DuplicateResourceException("Account has be active");
        }
        if(!data.password1().equals(data.password2())){
            throw new PasswordConfirmationException("Password and confirmation password do not match.");
        }
        if(repository.findByEmail(data.email()) != null){
            throw new DuplicateResourceException("Email has already been registered");
        }
        if(repository.findByCpf(data.cpf()) != null){
            throw new DuplicateResourceException("CPF has already been registered");
        }
        if(!validateEmail(data.email())){
            throw new EmailNotValidException("Email not is valid.");
        }


        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password1());
        User newUser = new User(
                data.login(),
                encryptedPassword,
                data.firstName(),
                data.lastName(),
                data.email(),
                data.cpf(),
                data.phoneNumber(),
                data.role()
        );


        return repository.save(newUser);
    }

    @Override
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public LoginResponseDTO loginUser(AuthenticationRequestDTO data) throws UserNotFoundException, BadCredentialsException, UserDeletedException {
        User currentUser = repository.findUserByLogin(data.login());
        if(currentUser == null){
            throw new UserNotFoundException("User not found with login: " + data.login());
        }
        if(currentUser.getStatus() == UserStatus.DELETED) {
            throw new UserDeletedException("This account has been deleted.");
        }

        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            return new LoginResponseDTO(
                    repository.findUserByLogin(data.login()).getId(),
                    tokenService.generateToken((User) auth.getPrincipal())
            );
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Invalid login or password.");
        }

    }

    @Override
    public List<User> getActiveUsers() {
        return repository.findAllByStatus(UserStatus.ACTIVE);
    }

    @Override
    public User getUserById(String id) throws UserNotFoundException{
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User updateUser(UpdateRequestDTO data) throws DuplicateResourceException, UserNotFoundException {
        if(repository.findByEmail(data.email()) != null) {
            throw new DuplicateResourceException("Email already in use");
        }
        if(repository.findByCpf(data.cpf()) != null){
            throw new DuplicateResourceException("CPF already in use");
        }

        User currentUser = getUserById(data.id());

        if (currentUser == null){
            throw new UserNotFoundException("User not found with ID: " + data.id());
        }

        currentUser.setFirstName(data.firstName());
        currentUser.setLastName(data.lastName());
        currentUser.setEmail(data.email());
        currentUser.setCpf(data.cpf());
        currentUser.setPhoneNumber(data.phoneNumber());
        return repository.save(currentUser);
    }

    @Override
    public User updateUserPassword(UpdatePasswordRequestDTO data) throws BadCredentialsException, PasswordConfirmationException, UserNotFoundException{
        if(!data.newPassword1().equals(data.newPassword2())){
            throw new PasswordConfirmationException("Password and confirmation password do not match.");
        }

        User currentUser = getUserById(data.id());
        if(currentUser == null){
            throw new UserNotFoundException("User not found with ID: " + data.id());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(data.oldPassword(), currentUser.getPassword())){
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.newPassword1());
            currentUser.setPassword(encryptedPassword);
            return repository.save(currentUser);
        } else {
            throw new BadCredentialsException("Invalid old password.");
        }

    }

    @Override
    public void deleteUser(DeleteUserRequestDTO data) throws BadCredentialsException {
        User currentUser = getUserById(data.id());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(data.password(), currentUser.getPassword())){
            currentUser.setStatus(UserStatus.DELETED);
            repository.save(currentUser);
        } else {
            throw new BadCredentialsException("Invalid password.");
        }
    }
}
