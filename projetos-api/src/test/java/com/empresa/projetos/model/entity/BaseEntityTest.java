package com.empresa.projetos.model.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BaseEntityTest {
	@Test
	public void testEquals_Symmetric() {
		BaseEntity x = new BaseEntityBuilder().withId(33L).build();
		BaseEntity y = new BaseEntityBuilder().withId(33L).build();
		
	    assertTrue(x.equals(y) && y.equals(x));
	    assertTrue(x.hashCode() == y.hashCode());
	}
}
