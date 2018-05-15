package com.empresa.projetos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.projetos.exception.NotAllowedException;
import com.empresa.projetos.exception.NotFoundException;
import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.service.ProjectsService;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
	@Autowired
	private ProjectsService projectsService;
	
	public ProjectsController(ProjectsService projectsService) {
		this.projectsService = projectsService;
	}

	@PostMapping
	@ResponseBody
	@Transactional
	public Project create(@RequestBody @Valid Project project) {
		return projectsService.create(project);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public Project update(@PathVariable(value = "id") Long id, @RequestBody @Valid Project project) {
		projectsService.findById(id).orElseThrow(() -> new NotFoundException("Project", "id", id));
		return projectsService.update(project);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public Project remove(@PathVariable(value = "id") Long id) {
		Optional<Project> projectRemoved = projectsService.findById(id);
		projectRemoved.orElseThrow(() -> new NotFoundException("Project", "id", id));
		projectRemoved.filter(p -> p.isAllowedRemove()).orElseThrow(() -> new NotAllowedException("Project", "id", id));
		projectsService.remove(id);
		return projectRemoved.get();
	}

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return projectsService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly=true)
	public Project findById(@PathVariable(value = "id") Long id) {
		return projectsService.findById(id).orElseThrow(() -> new NotFoundException("Project", "id", id));
	}
}
