package com.empresa.projetos.model.entity;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pessoa")
public class Person extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
}
