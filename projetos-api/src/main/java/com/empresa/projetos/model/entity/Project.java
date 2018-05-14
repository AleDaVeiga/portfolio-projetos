package com.empresa.projetos.model.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projeto")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	@NotEmpty
	@Size(max = 200)
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
	
	@Column(name = "descricao")
	@Size(max = 5000)
	private String description;
	
	@Column(name = "status")
	@Size(max = 45)
	private String status;
	
	@Column(name = "orcamento")
	private BigDecimal budget;
	
	@Column(name = "risco")
	@Size(max = 45)
	private String risk;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "idgerente")
	private Person manager;
	
	@ManyToMany
	@JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
	private Set<Person> members;
	
	public Project() {
		this.members = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public Date getScheduledEnd() {
		return scheduledEnd;
	}

	public Date getEnd() {
		return end;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public String getRisk() {
		return risk;
	}

	public Person getManager() {
		return manager;
	}

	public Set<Person> getMembers() {
		return Collections.unmodifiableSet(members);
	}

	// TODO: Remove
	public void setId(Long id) {
		this.id = id;
	}

	public void changeName(String name) {
		this.name = name;
	}
	
	public void changeManager(Person manager) {
		this.manager = manager;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Project) {
			return ((Project) obj).getId().equals(getId());
		}
		return false;
	}
}
