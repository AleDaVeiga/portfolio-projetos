package com.empresa.projetos.controller;

import static java.time.Month.JUNE;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
	public void mustCreatePerson() throws Exception {
		Person mockPerson = new PersonBuilder().withId(22L).withName("Alessandro da Veiga").withBirth(1989, JUNE, 12).withCpf("185.689.865-28").asEmprolyee().build();
		String mockPersonJSON = toObjectJSON(mockPerson);
		configMockCreatePerson(mockPerson);

		mock.perform(post("/people")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8)
									.content(mockPersonJSON))
		.andExpect(status().isOk())
		.andExpect(content().json(mockPersonJSON));
		
		verify(peopleService).create(any(Person.class));
	}
	
	@Test
	public void mustFailOnCreatePerson() throws Exception {
		Person mockPerson = new PersonBuilder().withId(22L).withName(null).build();
		String mockPersonJSON = toObjectJSON(mockPerson);

		mock.perform(post("/people")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8)
									.content(mockPersonJSON))
		.andExpect(status().is(BAD_REQUEST.value()));
	}

	@Test
	public void mustUpdatePerson() throws Exception {
		Long idToUpdate = 22L;
		Person mockPersonOld = new PersonBuilder().withId(idToUpdate).withName("Alessandro da Veiga").build();
		Person mockPersonChanged = new PersonBuilder().withId(idToUpdate).withName("Alessandro da Veiga").withBirth(1989, JUNE, 12).withCpf("185.689.865-28").asEmprolyee().build();
		configMockFindByIdPerson(idToUpdate, mockPersonOld);
		configMockUpdatePerson(mockPersonChanged);

		mock.perform(put("/people" + "/{id}", idToUpdate)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8)
														.content(toObjectJSON(mockPersonOld)))
		.andExpect(status().isOk())
		.andExpect(content().json(toObjectJSON(mockPersonChanged)));
		
		verify(peopleService).findById(idToUpdate);
		verify(peopleService).update(mockPersonChanged);
	}
	
	@Test
	public void mustFailOnUpdatePerson() throws Exception {
		Long idToUpdate = 22L;
		Person mockPerson = new PersonBuilder().withId(idToUpdate).withName("Alessandro da Veiga").withBirth(1989, JUNE, 12).withCpf("185.689.865-28").asEmprolyee().build();
		String mockPersonJSON = toObjectJSON(mockPerson);
		configMockFindByIdPerson(idToUpdate, null);

		mock.perform(put("/people" + "/{id}", idToUpdate)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8)
														.content(mockPersonJSON))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(peopleService).findById(idToUpdate);
	}
	
	@Test
	public void mustRemovePerson() throws Exception {
		Long idToRemove = 22L;
		Person mockPerson = new PersonBuilder().withId(idToRemove).withName("Alessandro da Veiga").build();
		String mockPersonJSON = toObjectJSON(mockPerson);
		configMockFindByIdPerson(idToRemove, mockPerson);
		
		mock.perform(delete("/people" + "/{id}", idToRemove))
		.andExpect(status().isOk())
		.andExpect(content().json(mockPersonJSON));
		
		verify(peopleService).findById(idToRemove);
	}
	
	@Test
	public void mustFailOnRemovePerson() throws Exception {
		Long idToRemove = 22L;
		configMockFindByIdPerson(idToRemove, null);
		
		mock.perform(delete("/people" + "/{id}", idToRemove))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(peopleService).findById(idToRemove);
	}
	
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

	private void configMockCreatePerson(Person person) {
		when(peopleService.create(any(Person.class))).thenReturn(person);
	}

	private void configMockUpdatePerson(Person person) {
		when(peopleService.update(person)).thenReturn(person);
	}

	private void configMockFindByIdPerson(Long id, Person person) {
		when(peopleService.findById(id)).thenReturn(Optional.ofNullable(person));
	}

	private void configMockFindAllPeople(List<Person> people) {
		when(peopleService.findAll()).thenReturn(people);
	}
}
