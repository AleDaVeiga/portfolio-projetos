package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}
	
	@Transactional
	public Project create(Project project) {
		return projectRepository.save(project);
	}

	@Transactional
	public Project update(Project project) {
		return projectRepository.save(project);
	}

	@Transactional
	public void remove(Long id) {
		if (projectRepository.existsById(id)) {
			projectRepository.deleteById(id);
		}
	}
}
