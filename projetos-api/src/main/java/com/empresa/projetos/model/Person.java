package com.empresa.projetos.model;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "pessoa")
public class Person {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String name;
	
	@Column(name="datanascimento")
	@Temporal(DATE)
	private Date birth;
	
	@Column(name = "cpf", length = 14)
	private String cpf;
	
	@Column(name = "funcionario")
	private Boolean employee;
}
