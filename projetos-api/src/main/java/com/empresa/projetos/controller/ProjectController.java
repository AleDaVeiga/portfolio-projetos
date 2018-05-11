package com.empresa.projetos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.empresa.projetos.exception.NotFoundException;
import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return projectService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly=true)
	public Project findById(@PathVariable(value = "id") Long id) {
		return projectService.findById(id).orElseThrow(() -> new NotFoundException("Project", "id", id));
	}

	@PostMapping
	@ResponseBody
	@Transactional
	public Project create(@RequestBody @Valid Project project) {
		return projectService.create(project);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public Project update(@PathVariable(value = "id") Long id, @RequestBody @Valid Project project) {
		projectService.findById(id).orElseThrow(() -> new NotFoundException("Project", "id", id));
		return projectService.update(project);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {
		projectService.findById(id).orElseThrow(() -> new NotFoundException("Project", "id", id));
		projectService.remove(id);
		return ResponseEntity.ok().build();
	}
}
