package com.empresa.projetos.model.entity;

public class ProjectBuilder {
	private Long id;
	private String name;
	private Person manager;
	
	public Project build() {
		Project project = new Project();
		project.setId(id);
		project.changeName(name);
		project.changeManager(manager);
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
	
	public ProjectBuilder withManager(Person manager) {
		this.manager = manager;
		return this;
	}
}
