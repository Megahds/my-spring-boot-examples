package com.megah.multipledatasourcesjdbc.controller;

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

import com.megah.multipledatasourcesjdbc.dao.MySQLDao;
import com.megah.multipledatasourcesjdbc.model.Person;

@RestController
@RequestMapping("mysql")
public class MySQLController {
	
	private static final Logger log = LogManager.getLogger(MySQLController.class);
	
	@Autowired
	private MySQLDao mySQLDao;
	
	@GetMapping("find-all")
	public ResponseEntity<Object> findAll() {
		
		List<Person> listPerson = mySQLDao.findAll();
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("List Person -> " + listPerson);
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Object> add(@RequestBody Person person) {
		
		Integer check = mySQLDao.add(person);
		
		log.info("Check -> " + check);
		
		if (check == 1) {
			
			return new ResponseEntity<Object>(person, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		
		Integer check = mySQLDao.delete(id);
		
		log.info("Check -> " + check);
		
		switch (check) {
		case 0:
			return new ResponseEntity<Object>("Person with id = " + id + " not found", HttpStatus.NOT_FOUND);
			
		case 1:
			return new ResponseEntity<Object>(HttpStatus.OK);

		default:
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
