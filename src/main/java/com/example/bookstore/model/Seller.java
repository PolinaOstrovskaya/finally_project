package com.example.bookstore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sellers")
public class Seller {

    @Id
    @SequenceGenerator(name = "sellerSeqGen", sequenceName = "sellers_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "sellerSeqGen")
    private Long id;

    @Column(name = "surname", unique = true)
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "number_telephone")
    private String numberTelephone;

}

