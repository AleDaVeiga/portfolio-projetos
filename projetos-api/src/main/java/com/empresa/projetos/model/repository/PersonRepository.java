package com.empresa.projetos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.projetos.model.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
