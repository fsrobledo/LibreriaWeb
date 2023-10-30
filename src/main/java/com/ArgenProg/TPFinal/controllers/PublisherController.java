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

import com.ArgenProg.TPFinal.entities.Publisher;
import com.ArgenProg.TPFinal.services.PublisherServiceImpl;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
	
	@Autowired
	private PublisherServiceImpl publisherService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createPublisher(@RequestBody Publisher publisher) {

		try {
			publisherService.savePublisher(publisher);
			return new ResponseEntity<>("Publisher added", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Publisher>> getAllPublishers(){
		
		List<Publisher> allPublishers = publisherService.getAllPublishers();
		
		if (!allPublishers.isEmpty())
			return new ResponseEntity<>(allPublishers, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Publisher>> getFilteredPublishers(){
		
		List<Publisher> allPublishers = publisherService.getAllPublishers();
		List<Publisher> filteredPublishers = new ArrayList<>();
		
		for (Publisher publisher: allPublishers) {
			if (publisher.getRegistered() == true)
				filteredPublishers.add(publisher);
		}
			
		if (!filteredPublishers.isEmpty())
			return new ResponseEntity<>(filteredPublishers, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{publisher_id}")
	public ResponseEntity<Publisher> getPublisherrById(@PathVariable("publisher_id") Integer publisherId) {
		Publisher publisher = publisherService.getPublisherById(publisherId);

		if (publisher != null)
			return new ResponseEntity<>(publisher, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher) {
		try {
			publisherService.updatePublisher(publisher);
			Publisher publisherUpdated = publisherService.getPublisherById(publisher.getId());

			if (publisherUpdated != null) {
				return new ResponseEntity<>(publisherUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{publisher_id}")
	public ResponseEntity<String> deletePublisherById(@PathVariable Integer publisher_id) {
		try {
			Publisher publisher = publisherService.getPublisherById(publisher_id);
			if (publisher != null) {
				publisherService.deletePublisher(publisher_id);
				return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No publisher found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/remove/{publisher_id}")
	public ResponseEntity<String> removePublisherById(@PathVariable Integer publisher_id) {
		try {
			Publisher publisher = publisherService.getPublisherById(publisher_id);
			if (publisher != null) {
				publisher.setRegistered(false);
				return new ResponseEntity<>("Publisher removed", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No publisher found for given id", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
