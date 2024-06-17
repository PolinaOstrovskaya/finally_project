package com.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sales")
public class Order {
    @Id
    @SequenceGenerator(name = "salesSeqGen", sequenceName = "sales_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "salesSeqGen")
    private Long id;

    @Column(name = "date_of_sale")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateSale;

    @Column(name = "last_changed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastChangedDate;

    @Column(name = "status")
    private String status;

}
