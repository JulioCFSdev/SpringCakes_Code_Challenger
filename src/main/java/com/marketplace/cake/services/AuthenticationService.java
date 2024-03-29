package com.marketplace.cake.services;

import com.marketplace.cake.domain.user.User;
import com.marketplace.cake.dtos.user.*;

import java.util.List;

public interface AuthenticationService {
    User registerUser(RegisterRequestDTO data);
    boolean validateEmail(String email);
    boolean validateCpf(String cpf);
    int getDigitCpfVerification(int maxMultiplier, String[] cpfParts);
    boolean validatePhoneNumber(String phoneNumber);
    LoginResponseDTO loginUser(AuthenticationRequestDTO data);
    List<User> getActiveUsers();
    User getUserById(String id);
    User updateUser(UpdateRequestDTO data);
    User updateUserPassword(UpdatePasswordRequestDTO data);
    void deleteUser(DeleteUserRequestDTO data);
}
