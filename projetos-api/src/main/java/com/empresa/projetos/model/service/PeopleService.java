package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import com.empresa.projetos.model.entity.Person;

public interface PeopleService {
	Person create(Person person);
	
	Person update(Person person);

	void remove(Long id);

	List<Person> findAll();

	Optional<Person> findById(Long id);
}
