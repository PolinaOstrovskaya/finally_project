package com.bookstore.repository;

import com.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users")
    List<Books> findAll();
}
