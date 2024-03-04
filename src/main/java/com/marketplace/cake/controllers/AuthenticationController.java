package com.marketplace.cake.controllers;

import com.marketplace.cake.domain.user.User;
import com.marketplace.cake.dtos.user.*;
import com.marketplace.cake.exceptions.*;
import com.marketplace.cake.services.AuthenticationServiceImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImplement authenticationService;

    @PostMapping("v1/login")
    public ResponseEntity<?> login (@RequestBody @Valid AuthenticationRequestDTO data){
        try{
            return ResponseEntity.ok(authenticationService.loginUser(data));
        } catch (UserNotFoundException | UserDeletedException | BadCredentialsException exception){
            return ResponseEntity.status(403).body(exception.getMessage());
        }
    }

    @PostMapping("v1/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO data){
        try {
            User newUser = authenticationService.registerUser(data);
            return ResponseEntity.ok(newUser);
        } catch (DuplicateResourceException | PasswordConfirmationException | ArgumentNotValidException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("v1/list")
    public ResponseEntity<?> getUserCredentials(){
        List<User> users = authenticationService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("v1/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateRequestDTO data){
        try {
            User updatedUser = authenticationService.updateUser(data);
            return ResponseEntity.ok(updatedUser);
        } catch (DuplicateResourceException | UserNotFoundException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("v1/updatePassword")
    public ResponseEntity<?> updateUserPassword(@RequestBody @Valid UpdatePasswordRequestDTO data){
        try {
            User updatedUser = authenticationService.updateUserPassword(data);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException | PasswordConfirmationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("v1/delete")
    public ResponseEntity<?> deleteUser(@RequestBody @Valid DeleteUserRequestDTO data){
        try{
            authenticationService.deleteUser(data);
            return ResponseEntity.ok("User was successfully deleted.");
        } catch (UserNotFoundException | BadCredentialsException  exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
