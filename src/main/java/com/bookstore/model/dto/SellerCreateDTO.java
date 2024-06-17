package com.bookstore.model.dto;


import com.bookstore.annotation.ContactNumberConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class SellerCreateDTO {
    @NotNull
    @Size(min = 6, max = 15)
    private String surname;

    @NotNull
    @Size(min = 6, max = 15)
    private String name;

    @NotNull
    @Size(min = 6, max = 15)
    private String address;

    @NotNull
    @ContactNumberConstraint
    @Size(max = 15)
    private String numberTelephone;
}

