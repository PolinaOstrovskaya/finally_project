package com.bookstore.security.controller;

import com.bookstore.exception.SameUserInDatabase;
import com.bookstore.security.model.dto.AuthRequestDto;
import com.bookstore.security.model.dto.AuthResponseDto;
import com.bookstore.security.model.dto.RegistrationDto;
import com.bookstore.security.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/security")
public class SecurityController {
    private final UserSecurityService userSecurityService;

    @Autowired
    public SecurityController(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody RegistrationDto registrationDto) throws SameUserInDatabase {
        userSecurityService.registration(registrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDto> generateToken(@RequestBody AuthRequestDto authRequest) {
        Optional<String> token = userSecurityService.generateToken(authRequest);
        if (token.isPresent()) {
            return new ResponseEntity<>(new AuthResponseDto(token.get()), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
