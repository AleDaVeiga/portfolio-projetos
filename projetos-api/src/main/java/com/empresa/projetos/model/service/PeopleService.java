package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import com.empresa.projetos.model.entity.Person;

public interface PeopleService {
	List<Person> findAll();

	Optional<Person> findById(Long id);
}
