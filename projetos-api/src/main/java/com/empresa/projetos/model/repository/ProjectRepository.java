package com.empresa.projetos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.projetos.model.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
