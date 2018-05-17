package com.empresa.projetos.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.empresa.projetos.model.entity.Person;
import com.empresa.projetos.model.entity.PersonBuilder;
import com.empresa.projetos.model.service.PeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PeopleController.class)
public class PeopleControllerTest {
	@Autowired
	private MockMvc mock;

	@MockBean
	private PeopleService peopleService;

	@Test
	public void mustFindAllPeople() throws Exception {
		Person mockPerson = new PersonBuilder().withId(22L).withName("Alessandro da Veiga").build();
		List<Person> mockPeople = asList(mockPerson);
		String mockPeopleJSON = toObjectJSON(mockPeople);
		configMockFindAllPeople(mockPeople);
		
		mock.perform(get("/people")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().json(mockPeopleJSON));
		
		verify(peopleService).findAll();
	}
	
	@Test
	public void mustFindPersonById() throws Exception {
		Long idToFind = 22L;
		Person mockPerson = new PersonBuilder().withId(idToFind).withName("Alessandro da Veiga").build();
		String mockPersonJSON = toObjectJSON(mockPerson);
		configMockFindByIdPerson(idToFind, mockPerson);
		
		mock.perform(get("/people" + "/{id}", idToFind)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().json(mockPersonJSON));
		
		verify(peopleService).findById(idToFind);
	}
	
	@Test
	public void mustFailOnFindPersonById() throws Exception {
		Long idToFind = 22L;
		configMockFindByIdPerson(idToFind, null);
		
		mock.perform(get("/people" + "/{id}", idToFind)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(peopleService).findById(idToFind);
	}

	private String toObjectJSON(Object obj) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);
	}

	private void configMockFindByIdPerson(Long id, Person person) {
		when(peopleService.findById(id)).thenReturn(Optional.ofNullable(person));
	}

	private void configMockFindAllPeople(List<Person> people) {
		when(peopleService.findAll()).thenReturn(people);
	}
}
