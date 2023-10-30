package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Publisher;

@Service
public interface IPublisherService {

	public void savePublisher(Publisher publisher);

	public List<Publisher> getAllPublishers();
	
	public Publisher getPublisherById(Integer id);
	
	public void updatePublisher(Publisher publisher);
	
	public void deletePublisher(Integer id);
}
