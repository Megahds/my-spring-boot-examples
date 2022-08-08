package com.megah.crudmysql.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megah.crudmysql.model.PersonModel;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<PersonModel, Integer> {
	
	@Query(value = "SELECT * FROM person", nativeQuery = true)
	List<PersonModel> listPerson();
//	List<Map<String, Object>> listPerson();
	
	@Modifying
	@Query(value = "INSERT INTO person(name, age, address) VALUES(?1, ?2, ?3)", nativeQuery = true)
	int addPerson(String name, Integer age, String address);
	
	@Modifying
	@Query(value = "UPDATE person SET name = ?1, age = ?2, address = ?3 WHERE id = ?4", nativeQuery = true)
	int updatePerson(String name, Integer age, String address, Integer id);
	
	@Modifying
	@Query(value = "DELETE FROM person WHERE id = ?1", nativeQuery = true)
	int deletePerson(Integer id);
	
	@Query(value = "SELECT * FROM person WHERE id = ?1", nativeQuery = true)
	PersonModel getPersonById(Integer id);
//	Map<String, Object> getPersonById(int id);
	
}
