package com.samuelwu.spring.jpa.h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samuelwu.spring.jpa.h2.model.Book;
import com.samuelwu.spring.jpa.h2.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			var books = bookService.getAllBooks(title);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		var bookData = bookService.getBookById(id);

		if (bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			var bookCreated = bookService.createBook(book.getTitle(), book.getDescription());
			return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		var bookUpdated = bookService.updateBook(id, book);

		if (bookUpdated == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/books")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		try {
			bookService.deleteAllBooks();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/books/available")
	public ResponseEntity<List<Book>> findAvailableBooks() {
		try {
			var books = bookService.findAvailableBooks();

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
