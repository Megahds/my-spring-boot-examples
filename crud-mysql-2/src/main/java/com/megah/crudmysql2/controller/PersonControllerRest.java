package com.megah.crudmysql2.controller;

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

import com.megah.crudmysql2.model.Person;
import com.megah.crudmysql2.service.PersonService;

@RestController
public class PersonControllerRest {
	
	private static final Logger log = LogManager.getLogger(PersonControllerRest.class);
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("find-all")
	public ResponseEntity<Object> findAll() {
		
		List<Person> listPerson = new ArrayList<>();
		
		try {
			
			listPerson = personService.findAll();
			
			log.info("List Person -> " + listPerson);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@GetMapping("find-by-id/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Integer id) {
		
		Person person = new Person();
		
		try {
			
			Optional<Person> rs = personService.findById(id);
			
			if (rs.isPresent()) {
				person = rs.get();
			} else {
				person = null;
			}
			
			log.info("Person -> " + person);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}
	
	@PostMapping("add-person")
	public ResponseEntity<Object> addPerson(@RequestBody Map<String, Object> person) {
		
		Person rs = new Person();
		
		Person newPerson = new Person();
		newPerson.setName(person.get("name").toString());
		newPerson.setAge(Integer.parseInt(person.get("age").toString()));
		newPerson.setAddress(person.get("address").toString());
		
		try {
			
			rs = personService.save(newPerson);
			
			log.info("New Person -> " + rs);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}
	
	@PostMapping("update-name-by-id/{id}")
	public ResponseEntity<Object> updateNameById(@PathVariable("id") Integer id, @RequestBody String name) {
		
		try {
			
			personService.updateNameById(id, name);
			
			log.info("New Name -> " + name);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete-by-id/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) {
		
		try {
			
			personService.deleteById(id);
			
			log.info("Id -> " + id);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete-by-name/{name}")
	public ResponseEntity<Object> deleteByName(@PathVariable("name") String name) {
		
		try {
			
			personService.deleteByName(name);
			
			log.info("Name -> " + name);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
