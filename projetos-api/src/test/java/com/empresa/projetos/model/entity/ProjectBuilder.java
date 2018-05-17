package com.empresa.projetos.model.entity;

import static com.empresa.projetos.model.entity.Status.CLOSED;

import java.util.ArrayList;
import java.util.List;

public class ProjectBuilder {
	private Long id;
	private String name;
	private Person manager;
	private Status status;
	private List<Person> members;

	public ProjectBuilder() {
		this.members = new ArrayList<>();
	}

	public Project build() {
		Project project = new Project();
		project.setId(id);
		project.changeName(name);
		project.changeStatus(status);
		if (manager != null) {
			project.changeManager(manager);
		}
		for (Person person : members) {
			project.addEmployee(person);
		}
		return project;
	}

	public ProjectBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public ProjectBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ProjectBuilder closed() {
		this.status = CLOSED;
		return this;
	}

	public ProjectBuilder withManager(Person manager) {
		this.manager = manager;
		return this;
	}

	public ProjectBuilder addMember(Person employee) {
		this.members.add(employee);
		return this;
	}
}
