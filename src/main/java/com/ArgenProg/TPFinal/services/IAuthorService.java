package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Author;

@Service
public interface IAuthorService {
	
	public void saveAuthor(Author author);

	public List<Author> getAllAuthors();
	
	public Author getAuthorById(Integer id);
	
	public void updateAuthor(Author author);
	
	public void deleteAuthor(Integer id);

}
