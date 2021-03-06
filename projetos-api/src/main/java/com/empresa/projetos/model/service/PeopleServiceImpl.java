package com.empresa.projetos.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.projetos.model.entity.Person;
import com.empresa.projetos.model.repository.PersonRepository;

@Service
public class PeopleServiceImpl implements PeopleService {
	@Autowired
	private PersonRepository personRepository;
	
	public PeopleServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}
}
