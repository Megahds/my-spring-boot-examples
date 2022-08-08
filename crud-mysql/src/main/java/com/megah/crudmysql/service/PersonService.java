package com.megah.crudmysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.crudmysql.model.PersonModel;
import com.megah.crudmysql.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepo;
	
	public List<PersonModel> listPerson() {
		return personRepo.listPerson();
	}
	
	public int addPerson(String name, Integer age, String address) {
		return personRepo.addPerson(name, age, address);
	}
	
	public int updatePerson(String name, Integer age, String address, int id) {
		return personRepo.updatePerson(name, age, address, id);
	}
	
	public int deletePerson(Integer id) {
		return personRepo.deletePerson(id);
	}
	
	public PersonModel getPersonById(Integer id) {
		return personRepo.getPersonById(id);
	}

}
