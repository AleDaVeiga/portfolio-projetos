package com.empresa.projetos.model.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	private Person person;

	@Before
	public void setUp() {
		this.person = new Person();
	}
	
	@Test
	public void mustChangeName() {
		String name = "Alessandro";
		
		this.person.changeName(name);
		
		assertEquals(name, this.person.getName());
	}
}
