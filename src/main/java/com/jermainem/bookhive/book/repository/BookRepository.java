package com.jermainem.bookhive.book.repository;

import com.jermainem.bookhive.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{ }
