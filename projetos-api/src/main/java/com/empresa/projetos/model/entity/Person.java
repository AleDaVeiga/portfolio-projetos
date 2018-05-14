package com.empresa.projetos.model.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pessoa")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	@NotEmpty
	@Size(max = 100)
	private String name;

	@Column(name = "datanascimento")
	@Temporal(DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	@Column(name = "cpf")
	@Size(max = 14)
	private String cpf;

	@Column(name = "funcionario")
	private Boolean employee;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getBirth() {
		return birth;
	}

	public String getCpf() {
		return cpf;
	}

	public Boolean getEmployee() {
		return employee;
	}

	// TODO: Remove
	public void setId(Long id) {
		this.id = id;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmployee(Boolean employee) {
		this.employee = employee;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			return ((Person) obj).getId().equals(getId());
		}
		return false;
	}
}
