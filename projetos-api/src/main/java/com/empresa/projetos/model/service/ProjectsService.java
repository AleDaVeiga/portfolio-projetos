package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import com.empresa.projetos.model.entity.Project;

public interface ProjectsService {
	Project create(Project project);

	Project update(Project project);

	void remove(Long id);

	List<Project> findAll();

	Optional<Project> findById(Long id);
}
