package com.jermainem.bookhive.book.controller;

import com.jermainem.bookhive.book.model.Book;
import com.jermainem.bookhive.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> ResponseEntity.ok()
                .body(value))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails){
        Optional<Book> book = bookService.getBookById(id);

        if(book.isPresent()){
            Book updateBook = book.get();
            updateBook.setTitle(bookDetails.getTitle());
            updateBook.setAuthor(bookDetails.getAuthor());
            updateBook.setIsbn(bookDetails.getIsbn());
            updateBook.setPublisher(bookDetails.getPublisher());
            updateBook.setPublishedDate(bookDetails.getPublishedDate());

            return ResponseEntity.ok(bookService.saveBook(updateBook));

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
