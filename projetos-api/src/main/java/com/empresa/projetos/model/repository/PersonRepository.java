package com.empresa.projetos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.projetos.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
