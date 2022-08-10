package com.megah.crudmysql2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.megah.crudmysql2.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	List<Person> findAll();
	
	Optional<Person> findById(Integer id);
	
	@Modifying
	@Query("UPDATE person SET name = :name WHERE id = :id")
    void updateNameById(@Param("id") Integer id, @Param("name") String name);
	
	@Modifying
	@Query("DELETE FROM person WHERE name = :name")
    void deleteByName(@Param("name") String name);
	
}
