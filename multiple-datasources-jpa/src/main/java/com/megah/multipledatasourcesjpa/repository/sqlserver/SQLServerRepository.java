package com.megah.multipledatasourcesjpa.repository.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megah.multipledatasourcesjpa.model.sqlserver.Person;

public interface SQLServerRepository extends JpaRepository<Person, Integer> {

}
