package com.megah.crudmysql2.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.crudmysql2.model.Person;
import com.megah.crudmysql2.repository.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger log = LogManager.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepo;
	
	public List<Person> findAll() {
		return personRepo.findAll();
	}
	
	public Optional<Person> findById(Integer id) {
		return personRepo.findById(id);
	}
	
	public Person save(Person person) {
		return personRepo.save(person);
	}
	
	public void updateNameById(Integer id, String name) {
		personRepo.updateNameById(id, name);
	}
	
	public void deleteById(Integer id) {
		personRepo.deleteById(id);
	}
	
	public void deleteByName(String name) {
		personRepo.deleteByName(name);
	}

}
