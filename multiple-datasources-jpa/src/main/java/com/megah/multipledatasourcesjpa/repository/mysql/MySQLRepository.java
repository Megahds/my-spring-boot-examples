package com.megah.multipledatasourcesjpa.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megah.multipledatasourcesjpa.model.mysql.Person;

public interface MySQLRepository extends JpaRepository<Person, Integer> {

}
