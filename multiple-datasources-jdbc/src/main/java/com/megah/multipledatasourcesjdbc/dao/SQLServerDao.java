package com.megah.multipledatasourcesjdbc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megah.multipledatasourcesjdbc.model.Person;

//@Service
@Transactional
@Repository
public class SQLServerDao {
	
	private static final Logger log = LogManager.getLogger(SQLServerDao.class);

	@Autowired
	@Qualifier("jdbcTemplateSqlserver")
	private JdbcTemplate jdbcTemplate;
	
public List<Person> findAll() {
		
		try {
			
			String query = "SELECT * FROM person";
			
			List<Person> listPerson = new ArrayList<>();
			
			listPerson = jdbcTemplate.query(query, (rs, rowNum) -> {
				return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("address"));
			});
			
			return listPerson;
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	public Integer add(Person person) {
		
		try {
			
			Integer check = 0;
			
			String query = "INSERT INTO person (name, age, address) "
					+ "VALUES('"+ person.getName() +"', "+ person.getAge() +", '"+ person.getAddress() +"');";
			
			check = jdbcTemplate.update(query);
			
			return check;
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	public Integer delete(Integer id) {
		
		try {
			
			Integer check = 0;
			
			String query = "DELETE FROM person WHERE id="+ id +";";
			
			check = jdbcTemplate.update(query);
			
			return check;
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
}
