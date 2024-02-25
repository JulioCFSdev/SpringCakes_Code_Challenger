package com.julio.web.controller;

import com.julio.web.domain.User;
import com.julio.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping()
    public List<User> getUsers(){
        return repository.findAll();
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email){
        return repository.findByEmail(email);
    }

    @PostMapping("/{email}")
    public void postUser(@RequestBody User user){
        repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }
}
