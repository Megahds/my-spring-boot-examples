package com.megah.crudmysql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.megah.crudmysql.model.PersonModel;
import com.megah.crudmysql.service.PersonService;

@RestController
public class PersonControllerRest {
	
	private static final Logger log = LogManager.getLogger(PersonControllerRest.class);
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "list-person")
	public ResponseEntity<Object> listPerson() {
		
		List<PersonModel> listPerson = new ArrayList<>();
		
		log.info("Before" + listPerson);
		
		try {
			
			listPerson = personService.listPerson();
			
			log.info("Berhasil -> " + listPerson);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error -> " + e.getMessage());
			e.printStackTrace();
			
			log.info("Gagal -> " + listPerson);
			
			return ResponseEntity.ok("Gagal hehe");
		}
		
		return ResponseEntity.ok(listPerson);
	}
	
	@PostMapping(value = "add-person")
	public ResponseEntity<Object> addPerson(@RequestBody Map<String, Object> person) {
		
		try {
			
			log.info("New Person -> " + person);
			
			personService.addPerson(person.get("name").toString(), 
					Integer.parseInt(person.get("age").toString()), 
					person.get("address").toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error -> " + e.getMessage());
			e.printStackTrace();
			
			log.info("Gagal add new person -> " + person);
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping(value = "update-person-by-id/{id}")
	public ResponseEntity<Object> updatePersonById(@PathVariable("id") int id, @RequestBody Map<String, Object> person) {
		
		try {
			
			log.info("Person -> " + person);
			
			personService.updatePerson(person.get("name").toString(), 
					Integer.parseInt(person.get("age").toString()), 
					person.get("address").toString(), 
					id);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "delete-person-by-id/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable("id") int id) {
		
		try {
			
			log.info("Id -> " + id);
			
			personService.deletePerson(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping(value = "get-person-by-id/{id}")
	public ResponseEntity<Object> getPersonById(@PathVariable("id") int id) {
		
		PersonModel person = new PersonModel();
		
		log.info("Before" + person);
		
		try {
			
			person = personService.getPersonById(id);
			
			log.info("Berhasil -> " + person);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}

}
