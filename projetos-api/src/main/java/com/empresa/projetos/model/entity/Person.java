package com.empresa.projetos.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoa")
public class Person extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
	@NotEmpty
	@Size(max = 100)
	private String name;

	@Column(name = "funcionario")
	private boolean employee;

	public Person() {
		this.employee = true;
	}

	public String getName() {
		return name;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void asManager() {
		this.employee = false;
	}
}
