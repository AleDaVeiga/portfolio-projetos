package com.empresa.projetos.model.entity;

import static java.lang.Boolean.TRUE;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class PersonBuilder {
	private Long id;
	private String name;
	private Date birth;
	private String cpf;
	private Boolean employee;
	
	public Person build() {
		Person person = new Person();
		person.setId(id);
		person.changeName(name);
		person.setBirth(birth);
		person.setCpf(cpf);
		person.setEmployee(employee);
		return person;
	}
	
	public PersonBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public PersonBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public PersonBuilder withBirth(int year, Month month, int dayOfMonth) {
		this.birth = Date.from(LocalDate.of(year, month, dayOfMonth).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		return this;
	}
	
	public PersonBuilder withCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public PersonBuilder asEmprolyee() {
		this.employee = TRUE;
		return this;
	}
}
