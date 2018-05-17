package com.empresa.projetos.model.entity;

public class PersonBuilder {
	private Long id;
	private String name;
	private boolean manager;
	
	public Person build() {
		Person person = new Person();
		person.setId(id);
		person.changeName(name);
		if(manager) {
			person.asManager();
		}
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
	
	public PersonBuilder asManager() {
		this.manager = true;
		return this;
	}
}
