package com.empresa.projetos.model.entity;

public class BaseEntityBuilder {
	private Long id;
	
	public BaseEntity build() {
		BaseEntity base = new BaseEntity() { };
		base.setId(id);
		return base;
	}
	
	public BaseEntityBuilder withId(Long id) {
		this.id = id;
		return this;
	}
}
