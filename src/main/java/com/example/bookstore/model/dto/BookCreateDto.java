package com.example.bookstore.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookCreateDto {
    @NotNull
    @Size(min = 6, max = 15)
    private String title;

    @NotNull
    @Size(min = 6, max = 15)
    private String author;

    @NotNull
    private Double price;
}
