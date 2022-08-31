package com.megah.multipledatasourcesjdbc.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	@Id
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	@Override
	public String toString() {
		return "{id = " + this.id 
				+ ", name = " + this.name 
				+ ", age = " + this.age 
				+ ", address = " + this.address + "}";
	}
}
