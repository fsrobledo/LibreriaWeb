package com.ArgenProg.TPFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArgenProg.TPFinal.entities.Publisher;
import com.ArgenProg.TPFinal.repositories.IPublisherRepository;

@Service
public class PublisherServiceImpl implements IPublisherService{

	@Autowired
	private IPublisherRepository publisherRepository;
	
	@Override
	public void savePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}

	@Override
	public List<Publisher> getAllPublishers() {
		List<Publisher> allPublishers = publisherRepository.findAll();
		return allPublishers;
	}

	@Override
	public Publisher getPublisherById(Integer id) {
		Publisher publisher = publisherRepository.findById(id).orElse(null);
		return publisher;
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		this.savePublisher(publisher);
	}

	@Override
	public void deletePublisher(Integer id) {
		publisherRepository.deleteById(id);
	}

}
