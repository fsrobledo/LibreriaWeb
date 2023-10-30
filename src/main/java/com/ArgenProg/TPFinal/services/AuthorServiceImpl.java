package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Author;
import com.ArgenProg.TPFinal.repositories.IAuthorRepository;

@Service
public class AuthorServiceImpl implements IAuthorService{

	@Autowired
	private IAuthorRepository authorRepository;
	
	@Override
	public void saveAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> allAuthors = authorRepository.findAll();
		return allAuthors;
	}

	@Override
	public Author getAuthorById(Integer id) {
		Author author = authorRepository.findById(id).orElse(null);
		return author;
	}

	@Override
	public void updateAuthor(Author author) {
		this.saveAuthor(author);
	}

	@Override
	public void deleteAuthor(Integer id) {
		authorRepository.deleteById(id);
	}

}
