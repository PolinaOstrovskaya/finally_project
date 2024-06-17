package com.bookstore.repository;

import com.bookstore.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM sellers")
    List<Seller> findAll();

}
