package com.megah.crudelasticsearch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.megah.crudelasticsearch.model.Person;
import com.megah.crudelasticsearch.service.PersonService;

@RestController
public class PersonController {
	
	private static final Logger log = LogManager.getFormatterLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	// Create/add doc
	@PostMapping(value = "add-person")
	public ResponseEntity<Object> addPerson(@RequestBody Map<String, Object> person) {
		
		try {
			
			//validasi
			int invalid = 0;
			
			if (!person.containsKey("id") || !person.containsKey("name")) {
				invalid++;
			}
			
			if (!person.containsKey("age")) {
				person.put("age", "");
			}
			
			if (!person.containsKey("address")) {
				person.put("address", "");
			}
			
			if (invalid > 0) {
				return new ResponseEntity<Object>("Request invalid", HttpStatus.BAD_REQUEST);
			}
			
			Person _person = personService.addPerson(person);
			
			log.info("New added person -> " + _person);
			
			return new ResponseEntity<Object>(_person, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// Create/add multiple docs
	@PostMapping(value = "add-multi-person")
	public ResponseEntity<Object> addPerson(@RequestBody List<Map<String, Object>> listPerson) {
		
		try {
			
			List<Person> _listPerson = personService.addPerson(listPerson);
			
			return new ResponseEntity<Object>(_listPerson, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Get all docs
	@GetMapping(value = "find-all-person")
	public ResponseEntity<Object> findAllPerson() {
		try {
			
			List<Person> listPerson = new ArrayList<>();
			personService.findAllPerson().forEach(listPerson::add);
			
			return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Get doc by id
	@GetMapping(value = "find-person-by-id/{id}")
	public ResponseEntity<Object> findPersonById(@PathVariable("id") Integer id) {
		
		try {
			
			Optional<Person> person = personService.findPersonById(id);
			
			if (person.isPresent()) {
				return new ResponseEntity<Object>(person.get(), HttpStatus.OK);
			}
			
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// Get doc by name
	@GetMapping(value = "find-person-by-name/{name}")
	public ResponseEntity<Object> findPersonByName(@PathVariable("name") String name) {
		
		try {
			
			List<Person> listPerson = personService.findPersonByName(name);
			
			return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Update by id
	@PostMapping(value = "update-person-by-id/{id}")
	public ResponseEntity<Object> updatePersonById(@PathVariable("id") Integer id, @RequestBody Map<String, Object> personNew) {
		
		try {
			
			Optional<Person> _person = personService.findPersonById(id);
			
			if (!_person.isPresent()) {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
			
			Person person = _person.get();
			
			personNew.put("id", person.getId());
			
			if (!personNew.containsKey("name")) {
				personNew.put("name", person.getName());
			}
			
			if (!personNew.containsKey("age")) {
				personNew.put("age", person.getAge());
			}
			
			if (!personNew.containsKey("address")) {
				personNew.put("address", person.getAddress());
			}
			
			Person updatedPerson = personService.addPerson(personNew);
			
			return new ResponseEntity<Object>(updatedPerson, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete doc by id
	@DeleteMapping(value = "delete-person-by-id/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable("id") Integer id) {
		
		try {
			
			personService.deletePersonById(id);
			
			return new ResponseEntity<Object>("Deleted", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete docs by name
	@DeleteMapping(value = "delete-person-by-name/{name}")
	public ResponseEntity<Object> deletePersonByName(@PathVariable("name") String name) {
		
		try {
			
			List<Person> listPerson = personService.findPersonByName(name);
			
			if (!listPerson.isEmpty()) {
				personService.deletePerson(listPerson);
			}
			
			return new ResponseEntity<Object>("Deleted", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete all docs
	@DeleteMapping(value = "delete-all")
	public ResponseEntity<Object> deleteAll() {
		
		try {
			
			personService.deleteAll();
			
			return new ResponseEntity<Object>("Deleted", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
