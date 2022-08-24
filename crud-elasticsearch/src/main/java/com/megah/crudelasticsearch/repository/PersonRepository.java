package com.megah.crudelasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.megah.crudelasticsearch.model.Person;

public interface PersonRepository extends ElasticsearchRepository<Person, Integer> {
	
	List<Person> findByName(String name);

}
