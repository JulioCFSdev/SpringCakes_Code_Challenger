package com.marketplace.cake.repositories;

import com.marketplace.cake.domain.user.User;
import com.marketplace.cake.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
    User findUserByLogin(String login);
    UserDetails findByCpf(String cpf);
    UserDetails findByEmail(String email);
    List<User> findAllByStatus(UserStatus status);
}
