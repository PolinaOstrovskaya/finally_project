package com.bookstore.security.service;

import com.bookstore.exception.SameUserInDatabase;
import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.security.model.Roles;
import com.bookstore.security.model.UserSecurity;
import com.bookstore.security.model.dto.AuthRequestDto;
import com.bookstore.security.model.dto.RegistrationDto;
import com.bookstore.security.repository.UserSecurityRepository;
import com.bookstore.security.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSecurityService {
    private final PasswordEncoder passwordEncoder;
    private final UserSecurityRepository userSecurityRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserSecurityService(PasswordEncoder passwordEncoder, UserSecurityRepository userSecurityRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userSecurityRepository = userSecurityRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Transactional(rollbackFor = Exception.class)
    public void registration(RegistrationDto registrationDto) throws SameUserInDatabase {
        Optional<UserSecurity> security = userSecurityRepository.findByUserLogin(registrationDto.getLogin());
        if (security.isPresent()) {
            throw new SameUserInDatabase(registrationDto.getLogin());
        }
        User user = new User();
        user.setAge(registrationDto.getAge());
        user.setUsername(registrationDto.getUsername());
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        User savedUser = userRepository.save(user);

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUserLogin(registrationDto.getLogin());
        userSecurity.setUserPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userSecurity.setRole(Roles.USER);
        userSecurity.setUserId(savedUser.getId());
        userSecurityRepository.save(userSecurity);
    }

    public Optional<String> generateToken(AuthRequestDto authRequestDto) {
        Optional<UserSecurity> security = userSecurityRepository.findByUserLogin(authRequestDto.getLogin());
        if (security.isPresent()
                && passwordEncoder.matches(authRequestDto.getPassword(), security.get().getUserPassword())) {
            return Optional.of(jwtUtils.generateJwtToken(authRequestDto.getLogin()));
        }
        return Optional.empty();
    }
}
