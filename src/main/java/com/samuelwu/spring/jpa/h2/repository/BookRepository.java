package com.samuelwu.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuelwu.spring.jpa.h2.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByAvailable(boolean available);

  List<Book> findByTitleContainingIgnoreCase(String title);
}
