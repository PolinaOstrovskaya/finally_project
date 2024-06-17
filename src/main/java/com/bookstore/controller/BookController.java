package com.bookstore.controller;

import com.bookstore.exception.CustomValidException;
import com.bookstore.model.Books;
import com.bookstore.model.dto.BookCreateDto;
import com.bookstore.service.BooksService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    private final BooksService bookService;

    public BookController(BooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable("id") @Parameter Long id) {
        log.info("IN getBooksById ");
        Optional<Books> book = bookService.getBooksById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createBook(@RequestBody @Valid BookCreateDto book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        return new ResponseEntity<>(bookService.createBook(book) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateBook(@RequestBody Books books, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        return new ResponseEntity<>(bookService.updateBook(books) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.deleteBooksById(id) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}

