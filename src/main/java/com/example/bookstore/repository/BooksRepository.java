package com.example.bookstore.repository;

import com.example.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users")
    static List<Books> customGetAllBooks();
}
