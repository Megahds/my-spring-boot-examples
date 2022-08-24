package com.megah.crudelasticsearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.megah.crudelasticsearch.model.Person;
import com.megah.crudelasticsearch.repository.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger log = LogManager.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepo;
	
	// Create/add doc
	public Person addPerson(Map<String, Object> person) {
		
		Person _person = new Person();
		_person.setId(Integer.parseInt(person.get("id").toString()));
		_person.setName(person.get("name").toString());
		if (!person.get("age").equals("")) {
			_person.setAge(Integer.parseInt(person.get("age").toString()));
		}
		_person.setAddress(person.get("address").toString());
		
		return personRepo.save(_person);
	}
	
	// Create/add multiple docs
	public List<Person> addPerson(List<Map<String, Object>> listPerson) {
		
		List<Person> _listPerson = new ArrayList<>();
		
		listPerson.forEach(person -> {
			Person _person = new Person();
			_person.setId(Integer.parseInt(person.get("id").toString()));
			_person.setName(person.get("name").toString());
			_person.setAge(Integer.parseInt(person.get("age").toString()));
			_person.setAddress(person.get("address").toString());
			
			_listPerson.add(_person);
		});
		
		return (List<Person>) personRepo.saveAll(_listPerson);
	}
	
	// Get all docs
	public Iterable<Person> findAllPerson() {
		return personRepo.findAll(Sort.by("id"));
	}
	
	// Get doc by id
	public Optional<Person> findPersonById(Integer id) {
		return personRepo.findById(id);
	}
	
	// Get doc by name
	public List<Person> findPersonByName(String name) {
		return personRepo.findByName(name);
	}
	
	// Delete doc by id
	public void deletePersonById(Integer id) {
		personRepo.deleteById(id);
	}
	
	// Delete docs
	public void deletePerson(List<Person> listPerson) {
		personRepo.deleteAll(listPerson);
	}
	
	// Delete all docs
	public void deleteAll() {
		personRepo.deleteAll();
	}
}
