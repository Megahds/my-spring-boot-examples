package com.megah.crudmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.megah.crudmongodb.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {

	List<Person> findByName(String name);
	
}
