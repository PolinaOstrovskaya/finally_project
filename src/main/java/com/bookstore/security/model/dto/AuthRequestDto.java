package com.bookstore.security.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthRequestDto {
    private String login;
    private String password;
}
