package com.ArgenProg.TPFinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ArgenProg.TPFinal.entities.Author;

public interface IAuthorRepository extends JpaRepository<Author, Integer>{

}
