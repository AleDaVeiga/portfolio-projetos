package com.empresa.projetos.model;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "projeto")
public class Project {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 200)
	private String name;
	
	@Column(name="data_inicio")
	@Temporal(DATE)
	private Date start;
	
	@Column(name="data_previsao_fim")
	@Temporal(DATE)
	private Date scheduledEnd;
	
	@Column(name="data_fim")
	@Temporal(DATE)
	private Date end;
	
	@Column(name = "descricao", length = 5000)
	private String description;
	
	@Column(name = "status", length = 45)
	private String status;
	
	@Column(name = "orcamento")
	private BigDecimal budget;
	
	@Column(name = "risco", length = 45)
	private String risk;
	
	@ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
	private Person manager;
	
	@ManyToMany
	@JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
	private Set<Person> members;
}
