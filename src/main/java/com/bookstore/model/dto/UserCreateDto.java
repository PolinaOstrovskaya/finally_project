package com.bookstore.model.dto;


import com.bookstore.annotation.ContactNumberConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserCreateDto {
    @NotNull
    @Size(min = 6, max = 15)
    private String username;

    @NotNull
    private Integer age;

    @NotNull
    @ContactNumberConstraint
    @Size(max = 15)
    private String telephone;
}