package com.megah.multipledatasourcesjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.multipledatasourcesjpa.model.postgresql.Person;
import com.megah.multipledatasourcesjpa.repository.postgresql.PostgreSQLRepository;

@Service
public class PostgreSQLService {
	
	@Autowired
	private PostgreSQLRepository postgreSQLRepo;
	
	public List<Person> findAll() {
		return postgreSQLRepo.findAll();
	}
	
	public Person add(Person person) {
		return postgreSQLRepo.save(person);
	}
	
	public void delete(Integer id) {
		postgreSQLRepo.deleteById(id);
	}

}
