package com.empresa.projetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.projetos.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
