package com.example.bookstore.service;

import com.example.bookstore.model.Books;
import com.example.bookstore.model.dto.BookCreateDto;
import com.example.bookstore.repository.BooksRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> getAllBooks() {
        return booksRepository.customGetAllBooks();
    }

    public Optional<Books> getBooksById(Long id) {
        return booksRepository.findById(id);
    }

    public Boolean deleteBooksById(Long id) {
        booksRepository.deleteById(id);
        return getBooksById(id).isEmpty();
    }

    public Boolean createBook(@Valid BookCreateDto booksFromDto) {
        Books books = new Books();
        books.setTitle(booksFromDto.getTitle());
        books.setPrice(BigDecimal.valueOf(booksFromDto.getPrice()));
        books.setAuthor(booksFromDto.getAuthor());
        Books createdbook = booksRepository.save(books);
        return getBooksById(createdbook.getId()).isPresent();
    }

    public Boolean updateBook(Books books) {
        Optional<Books> booksFromDBOptional = booksRepository.findById(books.getId());
        if (booksFromDBOptional.isPresent()) {
            Books booksFromDB = booksFromDBOptional.get();
            if (booksFromDB.getTitle() != null) {
                booksFromDB.setTitle(books.getTitle());
            }

            if (books.getPrice() != null) {
                booksFromDB.setPrice(books.getPrice());
            }

            if (books.getAuthor() != null) {
                booksFromDB.setAuthor(books.getAuthor());
            }
            Books updatedBooks = booksRepository.saveAndFlush(booksFromDB);
            return booksFromDB.equals(updatedBooks);
        }
        return false;
    }

}

