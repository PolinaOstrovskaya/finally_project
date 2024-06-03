package com.example.bookstore.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,15}(\\s*)")
    @Size(max = 15)
    private String telephone;
}