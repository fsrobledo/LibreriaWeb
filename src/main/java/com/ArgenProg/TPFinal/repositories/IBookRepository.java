package com.ArgenProg.TPFinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArgenProg.TPFinal.entities.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer>{

}
