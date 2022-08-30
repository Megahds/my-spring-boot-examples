package com.megah.multipledatasourcesjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megah.multipledatasourcesjpa.model.sqlserver.Person;
import com.megah.multipledatasourcesjpa.repository.sqlserver.SQLServerRepository;

@Service
public class SQLServerService {
	
	@Autowired
	private SQLServerRepository sqlServerRepo;

	public List<Person> findAll() {
		return sqlServerRepo.findAll();
	}
	
	public Person add(Person person) {
		return sqlServerRepo.save(person);
	}
	
	public void delete(Integer id) {
		sqlServerRepo.deleteById(id);
	}
}
