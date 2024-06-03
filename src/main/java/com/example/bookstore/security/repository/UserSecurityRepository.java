package com.example.bookstore.security.repository;

import com.example.bookstore.security.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUserLogin(String userLogin);
}
