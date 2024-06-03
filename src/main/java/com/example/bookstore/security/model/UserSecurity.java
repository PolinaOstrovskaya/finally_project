package com.example.bookstore.security.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity(name = "user_security")
public class UserSecurity {
    @jakarta.persistence.Id
    @Id
    @SequenceGenerator(name = "secSeqGen", sequenceName = "user_security_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "secSeqGen")
    private Long id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "user_id")
    private Long userId;

}
