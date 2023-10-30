package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Book;
import com.ArgenProg.TPFinal.repositories.IBookRepository;

@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	private IBookRepository bookRepository;
	
	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = bookRepository.findAll();
		return allBooks;
	}

	@Override
	public Book getBookById(Integer id) {
		Book book = bookRepository.findById(id).orElse(null);
		return book;
	}

	@Override
	public void updateBook(Book book) {
		this.saveBook(book);
	}

	@Override
	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);	
	}


}
