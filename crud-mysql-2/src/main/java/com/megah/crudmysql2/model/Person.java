package com.megah.crudmysql2.model;

import org.springframework.data.annotation.Id;

public class Person {
	
	@Id
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "{id = " + this.id 
				+ ", name = " + this.name 
				+ ", age = " + this.age 
				+ ", address = " + this.address + "}";
	}

}
