package com.megah.crudmysql2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.megah.crudmysql2.model.Person;

//@Component
@Transactional
@Repository
public class MysqlDao {
	
	private static final Logger log = LogManager.getLogger(MysqlDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getAllPerson() {
		
		List<Map<String, Object>> listPerson = new ArrayList<>();
		
		String query = "SELECT * FROM person";
		
//		log.info("QUERY -> " + query);
		
		try {
			
			listPerson = jdbcTemplate.queryForList(query);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		return listPerson;
	}
	
	public List<Person> getAllPerson2() {
		
		List<Person> listPerson = new ArrayList<>();
		
		String query = "SELECT * FROM person";
		
		try {
			
			listPerson = jdbcTemplate.query(query, 
					(rs, rowNum) -> {
						Person person = new Person();
						person.setId(rs.getInt("id"));
						person.setName(rs.getString("name"));
						person.setAge(rs.getInt("age"));
						person.setAddress(rs.getString("address"));
						return person;
					});
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		return listPerson;
	}
	
	public List<Map<String, Object>> getAllPerson3() {
		
		List<Map<String, Object>> listPerson = new ArrayList<>();
		
		String query = "SELECT * FROM person";
		
		try {
			
			listPerson = jdbcTemplate.query(query, new MyRowMapper());
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		return listPerson;
	}
	
	public List<Map<String, Object>> getPersonByNameAndAge(String name, Integer age) {
		
		List<Map<String, Object>> listPerson = new ArrayList<>();
		
		String query = "SELECT * FROM person WHERE name = ? and age >= ?";
		
		try {
			
			listPerson = jdbcTemplate.query(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, name);
					ps.setInt(2, age);
					return ps;
				}
			}, new MyRowMapper());
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		return listPerson;
	}
	
	public List<Map<String, Object>> getPersonByName(String name) {
		
		List<Map<String, Object>> listPerson = new ArrayList<>();
		
		String query = "SELECT * FROM person WHERE name = '" + name + "'";
		
		try {
			
			listPerson = jdbcTemplate.query(query, new MyRowMapper());
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		return listPerson;
	}
	
	public boolean addPerson(String name, Integer age, String address) {
		
		String query = "INSERT INTO person(name, age, address) VALUES('"+ name +"', "+ age +", '"+ address +"')";
		
		try {
			
			jdbcTemplate.update(query);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean addPerson2(String name, Integer age, String address) {
		
		String query = "INSERT INTO person(name, age, address) VALUES(?, ?, ?)";
		
		try {
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, name);
					ps.setInt(2, age);
					ps.setString(3, address);
					return ps;
				}
			});
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean updatePersonByName(String name, Integer age, String address) {
		
		String query = "UPDATE person SET age = "+ age +", address = '"+ address +"' WHERE name = '"+ name +"'";
		
		try {
			
			jdbcTemplate.update(query);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean deletePersonById(Integer id) {
		
		String query = "DELETE FROM person WHERE id = " + id;
		
		try {
			
			jdbcTemplate.update(query);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error -> " + e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

}

class MyRowMapper implements RowMapper<Map<String, Object>> {

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> person = new HashMap<>();
		person.put("id", rs.getInt("id"));
		person.put("name", rs.getString("name"));
		person.put("age", rs.getInt("age"));
		person.put("address", rs.getString("address"));

		return person;
	}

}