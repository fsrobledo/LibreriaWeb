package com.ArgenProg.TPFinal.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Long isbn;
	private String title;
	private Integer year;
	private Integer copies;
	private Integer lentCopies;
	private Integer remainingCopies;
	private Boolean registered;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_id")
	private Author author;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="publisher_id")
	private Publisher publisher;
	
}
