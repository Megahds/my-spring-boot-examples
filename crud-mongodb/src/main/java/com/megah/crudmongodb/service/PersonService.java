package com.megah.crudmongodb.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.crudmongodb.model.Person;
import com.megah.crudmongodb.repository.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger log = LogManager.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepo;
	
	public List<Person> findAllPerson() {
		return personRepo.findAll();
	}
	
	public Person addPerson(Person person) {
		return personRepo.save(person);
	}
	
	public Optional<Person> findPersonById(Integer id) {
		return personRepo.findById(id);
	}
	
	public void deletePersonById(Integer id) {
		personRepo.deleteById(id);
	}
	
	public void deleteAllPerson() {
		personRepo.deleteAll();
	}
	
	public List<Person> findPersonByName(String name) {
		return personRepo.findByName(name);
	}

}
