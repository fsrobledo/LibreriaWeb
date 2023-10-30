package com.ArgenProg.TPFinal.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ArgenProg.TPFinal.entities.Author;

@DataJpaTest
public class AuthorRepositoryTest {
	
	@Autowired
	private IAuthorRepository authorRepository;
	
	private Author author;
	
	@BeforeEach
	void setup() {
		author = new Author("AuthorName", true);
	}
	
	@DisplayName("Test para guardar un autor")
	@Test
	void testSaveAuthor() {
		
		Author authorBD = authorRepository.save(author);
		
		assertThat(authorBD).isNotNull();
		assertThat(authorBD.getId()).isGreaterThan(0);
	}
	
	@DisplayName("Test para listar todos los autores")
	@Test
	void testListAllAuthors() {
		Author author2 = new Author("AuthorName2", true);
		authorRepository.save(author);
		authorRepository.save(author2);

		List<Author> allAuthorsBD = authorRepository.findAll();

		assertThat(allAuthorsBD).isNotNull();
		assertThat(allAuthorsBD.size()).isEqualTo(2);
	}

	@DisplayName("Test para obtener un autor por id")
	@Test
	void testGetAuthorById() {
		authorRepository.save(author);

		Author authorBD = authorRepository.findById(author.getId()).get();

		assertThat(authorBD).isNotNull();
	}

	@DisplayName("Test para actualizar un autor")
	@Test
	void testUpdateAuthor() {
		authorRepository.save(author);

		Author authorBD = authorRepository.findById(author.getId()).get();

		authorBD.setName("TestName");
		authorBD.setRegistered(true);

		Author authorUpdate = authorRepository.save(author);

		assertThat(authorUpdate.getName()).isEqualTo("TestName");
		assertThat(authorUpdate.getRegistered()).isEqualTo(true);
	}

	@DisplayName("Test para eliminar un autor")
	@Test
	void testDeleteAuthor() {
		authorRepository.save(author);

		authorRepository.deleteById(author.getId());

		Optional<Author> authorDeleted = authorRepository.findById(author.getId());

		assertThat(authorDeleted).isEmpty();
	}
	
	@DisplayName("Test para dar de baja un autor")
	@Test
	void testRemoveAuthor() {
		authorRepository.save(author);
		
		Author authorBD = authorRepository.findById(author.getId()).get();

		authorBD.setRegistered(false);

		Author authorUpdate = authorRepository.save(author);

		assertThat(authorUpdate.getRegistered()).isEqualTo(false);
		
	}
}

