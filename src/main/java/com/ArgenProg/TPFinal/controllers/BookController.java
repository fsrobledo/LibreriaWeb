package com.ArgenProg.TPFinal.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.ArgenProg.TPFinal.entities.Book;
import com.ArgenProg.TPFinal.services.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookServiceImpl bookService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createBook(@RequestBody Book book) {

		try {
			bookService.saveBook(book);
			return new ResponseEntity<>("Book added", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Book>> getAllBooks(){
		
		List<Book> allBooks = bookService.getAllBooks();
		
		if (!allBooks.isEmpty())
			return new ResponseEntity<>(allBooks, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
	@GetMapping("/available")
	public ResponseEntity<List<Book>> getFilteredBooks(){
		
		List<Book> allBooks = bookService.getAllBooks();
		List<Book> filteredBooks = new ArrayList<>();
		
		for (Book book: allBooks) {
			if (book.getRegistered() == true)
				filteredBooks.add(book);
		}
			
		if (!filteredBooks.isEmpty())
			return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{book_id}")
	public ResponseEntity<Book> getBookById(@PathVariable("book_id") Integer bookId) {
		Book book = bookService.getBookById(bookId);

		if (book != null)
			return new ResponseEntity<>(book, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		try {
			bookService.updateBook(book);
			Book bookUpdated = bookService.getBookById(book.getId());

			if (bookUpdated != null) {
				return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{book_id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null) {
				bookService.deleteBook(book_id);
				return new ResponseEntity<>("Book deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No book found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/remove/{book_id}")
	public ResponseEntity<String> removeBookById(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null) {
				book.setRegistered(false);
				return new ResponseEntity<>("Book removed", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No book found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
}
