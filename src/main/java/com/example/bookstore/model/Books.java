package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Books {
    @Id
    @SequenceGenerator(name = "booksSeqGen", sequenceName = "books_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "booksSeqGen")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "author")
    private String author;

}
