package com.megah.multipledatasourcesjpa.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megah.multipledatasourcesjpa.model.postgresql.Person;

public interface PostgreSQLRepository extends JpaRepository<Person, Integer> {

}
