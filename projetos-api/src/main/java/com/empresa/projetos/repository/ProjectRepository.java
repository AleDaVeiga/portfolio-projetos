package com.empresa.projetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.projetos.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
