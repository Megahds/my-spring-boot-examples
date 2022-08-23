package com.megah.crudmongodb.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.megah.crudmongodb.model.Person;
import com.megah.crudmongodb.service.PersonService;

@RestController
public class PersonController {
	
	private static final Logger log = LogManager.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("find-all-person")
	public ResponseEntity<Object> findAllPerson() {
		
		List<Person> listPerson = new ArrayList<>();
		
		try {
			
			listPerson = personService.findAllPerson();
			
			log.info("List Person -> " + listPerson);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		if (listPerson.isEmpty()) {
//			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
//		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@PostMapping("add-person")
	public ResponseEntity<Object> addPerson(@RequestBody Map<String, Object> person) {
		
		Person addedPerson = new Person();
		
		try {
			
			Person newPerson = new Person();
			newPerson.setId(Integer.parseInt(person.get("id").toString()));
			newPerson.setName(person.get("name").toString());
			newPerson.setAge(Integer.parseInt(person.get("age").toString()));
			newPerson.setAddress(person.get("address").toString());
			
			addedPerson = personService.addPerson(newPerson);
			
			log.info("Added Person -> " + addedPerson);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(addedPerson, HttpStatus.CREATED);
		
//		return new ResponseEntity<Object>(addedPerson, HttpStatus.OK);
	}
	
	@GetMapping("find-person-by-id/{id}")
	public ResponseEntity<Object> findPersonById(@PathVariable("id") Integer id) {
		
		try {
			
			Optional<Person> person = personService.findPersonById(id);
			
			if (person.isPresent()) {
				
				log.info("Person By Id -> " + person);
				return new ResponseEntity<Object>(person.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("update-person-by-id/{id}")
	public ResponseEntity<Object> updatePersonById(@PathVariable("id") Integer id, @RequestBody Person person) {
		
		try {
			
			Optional<Person> personOld = personService.findPersonById(id);
			if (personOld.isPresent()) {
				Person _person = personOld.get();
				_person.setName(person.getName());
				_person.setAge(person.getAge());
				_person.setAddress(person.getAddress());
				
				log.info("Updated Person -> " + _person);
				return new ResponseEntity<Object>(personService.addPerson(_person), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("delete-person-by-id/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable("id") Integer id) {
		
		try {
			
			personService.deletePersonById(id);
			
			log.info("Id -> " + id);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete-all-person")
	public ResponseEntity<Object> deleteAllPerson() {
		
		try {
			
			personService.deleteAllPerson();
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("find-person-by-name/{name}")
	public ResponseEntity<Object> findPersonByName(@PathVariable("name") String name) {
		
		try {
			
			List<Person> listPerson = personService.findPersonByName(name);
			
			log.info("Person By Name -> " + listPerson);
			
			if (listPerson.isEmpty()) {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
