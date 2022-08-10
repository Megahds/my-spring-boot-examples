package com.megah.crudmysql2.controller;

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

import com.megah.crudmysql2.dao.MysqlDao;
import com.megah.crudmysql2.model.Person;

@RestController
public class PersonControllerRest2 {
	
	private static final Logger log = LogManager.getLogger(PersonControllerRest.class);
	
	@Autowired
	private MysqlDao mysqlDao;
	
	@GetMapping("get-all-person")
	public ResponseEntity<Object> getAllPerson() {
		
		List<Map<String, Object>> listPerson = mysqlDao.getAllPerson();
		
		log.info("List Person -> " + listPerson);
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@GetMapping("get-all-person2")
	public ResponseEntity<Object> getAllPerson2() {
		
		List<Person> listPerson = mysqlDao.getAllPerson2();
		
		log.info("List Person -> " + listPerson);
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@GetMapping("get-all-person3")
	public ResponseEntity<Object> getAllPerson3() {
		
		List<Map<String, Object>> listPerson = mysqlDao.getAllPerson();
		
		log.info("List Person -> " + listPerson);
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@GetMapping("get-person-by-name/{name}")
	public ResponseEntity<Object> getPersonByName(@PathVariable("name") String name) {
		
		List<Map<String, Object>> listPerson = mysqlDao.getPersonByName(name);
		
		log.info("List Person -> " + listPerson);
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@PostMapping("get-person-by-name-and-age")
	public ResponseEntity<Object> getPersonByNameAndAge(@RequestBody Map<String, Object> person) {
		
		List<Map<String, Object>> listPerson = mysqlDao.getPersonByNameAndAge(person.get("name").toString(), 
				Integer.parseInt(person.get("age").toString()));
		
		log.info("List Person -> " + listPerson);
		
		if (listPerson == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(listPerson, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Object> addPerson(@RequestBody Map<String, Object> person) {
		
		log.info("New Person -> " + person);
		
		boolean success = mysqlDao.addPerson(person.get("name").toString(), 
				Integer.parseInt(person.get("age").toString()), 
				person.get("address").toString());
		
		if (!success) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("add2")
	public ResponseEntity<Object> addPerson2(@RequestBody Map<String, Object> person) {
		
		log.info("New Person -> " + person);
		
		boolean success = mysqlDao.addPerson2(person.get("name").toString(), 
				Integer.parseInt(person.get("age").toString()), 
				person.get("address").toString());
		
		if (!success) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("update-person-by-name/{name}")
	public ResponseEntity<Object> updatePersonByName(@PathVariable("name") String name, @RequestBody Map<String, Object> person) {
		
		log.info("Update Person " + person);
		
		boolean success = mysqlDao.updatePersonByName(name, 
				Integer.parseInt(person.get("age").toString()), 
				person.get("address").toString());
		
		if (!success) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete-person-by-id/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable("id") Integer id) {
		
		log.info("Id -> " + id);
		
		boolean success = mysqlDao.deletePersonById(id);
		
		if (!success) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
