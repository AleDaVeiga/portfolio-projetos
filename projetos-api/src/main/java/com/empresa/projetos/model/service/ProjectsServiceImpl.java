package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.projetos.exception.NotAllowedException;
import com.empresa.projetos.model.entity.Person;
import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.repository.PersonRepository;
import com.empresa.projetos.model.repository.ProjectRepository;

@Service
public class ProjectsServiceImpl implements ProjectsService {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Transactional
	public Project create(Project project) {
		return save(project);
	}

	private Project save(Project project) {
		for (Person member : project.getMembers()) {
			Optional<Person> person = personRepository.findById(member.getId());
			person.filter(p -> p.isEmployee()).orElseThrow(() -> new NotAllowedException("Person", "id", member.getId()));
		}
		return projectRepository.save(project);
	}

	@Transactional
	public Project update(Project project) {
		return save(project);
	}

	@Transactional
	public void remove(Long id) {
		if (projectRepository.existsById(id)) {
			projectRepository.deleteById(id);
		}
	}

	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}
}
