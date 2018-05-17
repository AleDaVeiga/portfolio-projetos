package com.empresa.projetos.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void mustCreateAsEmployee() {
		this.person = new Person();
		
		assertTrue(this.person.isEmployee());
	}
	
	@Test
	public void mustDefineAsManager() {
		this.person = new Person();
		
		this.person.asManager();
		
		assertFalse(this.person.isEmployee());
	}
}
