package com.megah.multipledatasourcesjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.multipledatasourcesjpa.model.mysql.Person;
import com.megah.multipledatasourcesjpa.repository.mysql.MySQLRepository;

@Service
public class MySQLService {
	
	@Autowired
	private MySQLRepository mySQLRepo;
	
	public List<Person> findAll() {
		return mySQLRepo.findAll();
	}
	
	public Person add(Person person) {
		return mySQLRepo.save(person);
	}
	
	public void delete(Integer id) {
		mySQLRepo.deleteById(id);
	}
}
