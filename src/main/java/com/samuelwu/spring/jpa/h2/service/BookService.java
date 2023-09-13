package com.samuelwu.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuelwu.spring.jpa.h2.model.Book;
import com.samuelwu.spring.jpa.h2.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	public List<Book> getAllBooks(String title) {
		var books = new ArrayList<Book>();

		if (title == null)
			bookRepository.findAll().forEach(books::add);
		else
			bookRepository.findByTitleContainingIgnoreCase(title).forEach(books::add);
		return books;

	}

	public Optional<Book> getBookById(long id) {
		return bookRepository.findById(id);
	}

	public Book createBook(String title, String summary) {
		return bookRepository.save(new Book(title, summary, false));
	}

	public Book updateBook(long id, Book book) {
		Optional<Book> bookData = bookRepository.findById(id);

		if (bookData.isEmpty()) {
			return null;
		}
		Book _book = bookData.get();
		_book.setTitle(book.getTitle());
		_book.setDescription(book.getDescription());
		_book.setAvailable(book.isAvailable());
		return bookRepository.save(_book);
	}

	public void deleteBook(long id) {
		bookRepository.deleteById(id);
	}

	public void deleteAllBooks() {
		bookRepository.deleteAll();
	}

	public List<Book> findAvailableBooks() {
		return bookRepository.findByAvailable(true);
	}

}
