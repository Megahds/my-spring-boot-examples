package com.megah.multipledatasourcesjpa.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megah.multipledatasourcesjpa.model.postgresql.Person;
import com.megah.multipledatasourcesjpa.service.PostgreSQLService;

@RestController
@RequestMapping("postgresql")
public class PostgreSQLController {
	
	private static final Logger log = LogManager.getLogger(PostgreSQLController.class);
	
	@Autowired
	private PostgreSQLService postgreSQLService;

	@GetMapping("find-all")
	public ResponseEntity<Object> findAll() {
		
		try {
			
			List<Person> listPerson = postgreSQLService.findAll();
			
			log.info("List Person -> " + listPerson);
			
			return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("add")
	public ResponseEntity<Object> add(@RequestBody Person person) {
		
		try {
			
			Person _person = postgreSQLService.add(person);
			
			log.info("Added Person -> " + _person);
			
			return new ResponseEntity<Object>(_person, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		
		try {
			
			postgreSQLService.delete(id);
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
