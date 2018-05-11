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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoa")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	@NotBlank
	@Size(max = 100)
	private String name;

	@Column(name = "datanascimento")
	@Temporal(DATE)
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
}
