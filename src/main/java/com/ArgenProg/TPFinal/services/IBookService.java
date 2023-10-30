package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Book;

@Service
public interface IBookService {
	
	public void saveBook(Book book);

	public List<Book> getAllBooks();
	
	public Book getBookById(Integer id);
	
	public void updateBook(Book book);
	
	public void deleteBook(Integer id);
	
}
