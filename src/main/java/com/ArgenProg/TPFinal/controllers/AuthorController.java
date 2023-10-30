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

import com.ArgenProg.TPFinal.entities.Author;
import com.ArgenProg.TPFinal.services.AuthorServiceImpl;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private AuthorServiceImpl authorService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAuthor(@RequestBody Author author) {

		try {
			authorService.saveAuthor(author);
			return new ResponseEntity<>("Author added", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Author>> getAllAuthors(){
		
		List<Author> allAuthors = authorService.getAllAuthors();
		
		if (!allAuthors.isEmpty())
			return new ResponseEntity<>(allAuthors, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Author>> getFilteredAuthors(){
		
		List<Author> allAuthors = authorService.getAllAuthors();
		List<Author> filteredAuthors = new ArrayList<>();
		
		for (Author author: allAuthors) {
			if (author.getRegistered() == true)
				filteredAuthors.add(author);
		}
			
		if (!filteredAuthors.isEmpty())
			return new ResponseEntity<>(filteredAuthors, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{author_id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("author_id") Integer authorId) {
		Author author = authorService.getAuthorById(authorId);

		if (author != null)
			return new ResponseEntity<>(author, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
		try {
			authorService.updateAuthor(author);
			Author authorUpdated = authorService.getAuthorById(author.getId());

			if (authorUpdated != null) {
				return new ResponseEntity<>(authorUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{author_id}")
	public ResponseEntity<String> deleteAuthorById(@PathVariable Integer author_id) {
		try {
			Author author = authorService.getAuthorById(author_id);
			if (author != null) {
				authorService.deleteAuthor(author_id);
				return new ResponseEntity<>("Author deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No author found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/remove/{author_id}")
	public ResponseEntity<String> removeAuthorById(@PathVariable Integer author_id) {
		try {
			Author author = authorService.getAuthorById(author_id);
			if (author != null) {
				author.setRegistered(false);
				return new ResponseEntity<>("Author removed", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No author found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
