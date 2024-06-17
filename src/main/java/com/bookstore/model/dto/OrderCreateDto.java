package com.bookstore.model.dto;

import com.bookstore.model.Seller;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderCreateDto {
    @NotNull
    @Size(min = 6, max = 15)
    private String status;

    @NotNull
    private Seller id;
}
