package com.empresa.projetos.controller;

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
import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.entity.ProjectBuilder;
import com.empresa.projetos.model.service.ProjectsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectsController.class)
public class ProjectsControllerTest {
	@Autowired
	private MockMvc mock;

	@MockBean
	private ProjectsService projectsService;

	@Test
	public void mustCreateProject() throws Exception {
		Project mockProject = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").withManager(defaultManager()).build();
		String mockProjectJSON = toObjectJSON(mockProject);
		configMockCreateProject(mockProject);

		mock.perform(post("/projects")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8)
									.content(mockProjectJSON))
		.andExpect(status().isOk())
		.andExpect(content().json(mockProjectJSON));
		
		verify(projectsService).create(any(Project.class));
	}
	
	@Test
	public void mustFailOnCreateProject() throws Exception {
		Project mockProject = new ProjectBuilder().withId(22L).withName(null).build();
		String mockProjectJSON = toObjectJSON(mockProject);

		mock.perform(post("/projects")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8)
									.content(mockProjectJSON))
		.andExpect(status().is(BAD_REQUEST.value()));
	}

	@Test
	public void mustUpdateProject() throws Exception {
		Long idToUpdate = 22L;
		Project mockProjectOld = new ProjectBuilder().withId(idToUpdate).withName("Projeto").withManager(defaultManager()).build();
		Project mockProjectChanged = new ProjectBuilder().withId(idToUpdate).withName("Projeto de portfolio").withManager(defaultManager()).build();
		configMockFindByIdProject(idToUpdate, mockProjectOld);
		configMockUpdateProject(mockProjectChanged);

		mock.perform(put("/projects" + "/{id}", idToUpdate)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8)
														.content(toObjectJSON(mockProjectOld)))
		.andExpect(status().isOk())
		.andExpect(content().json(toObjectJSON(mockProjectChanged)));
		
		verify(projectsService).findById(idToUpdate);
		verify(projectsService).update(mockProjectChanged);
	}
	
	@Test
	public void mustFailOnUpdateProject() throws Exception {
		Long idToUpdate = 22L;
		Project mockProject = new ProjectBuilder().withId(idToUpdate).withName("Projeto de portfolio").withManager(defaultManager()).build();
		String mockProjectJSON = toObjectJSON(mockProject);
		configMockFindByIdProject(idToUpdate, null);

		mock.perform(put("/projects" + "/{id}", idToUpdate)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8)
														.content(mockProjectJSON))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(projectsService).findById(idToUpdate);
	}
	
	@Test
	public void mustRemoveProject() throws Exception {
		Long idToRemove = 22L;
		Project mockProject = new ProjectBuilder().withId(idToRemove).withName("Projeto de portfolio").build();
		String mockProjectJSON = toObjectJSON(mockProject);
		configMockFindByIdProject(idToRemove, mockProject);
		
		mock.perform(delete("/projects" + "/{id}", idToRemove))
		.andExpect(status().isOk())
		.andExpect(content().json(mockProjectJSON));
		
		verify(projectsService).findById(idToRemove);
	}
	
	@Test
	public void mustFailOnRemoveProject() throws Exception {
		Long idToRemove = 22L;
		configMockFindByIdProject(idToRemove, null);
		
		mock.perform(delete("/projects" + "/{id}", idToRemove))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(projectsService).findById(idToRemove);
	}
	
	@Test
	public void mustDenyToRemoveProject() throws Exception {
		Long idToRemove = 22L;
		Project mockProject = new ProjectBuilder().withId(idToRemove).withName("Projeto de portfolio").closed().build();
		configMockFindByIdProject(idToRemove, mockProject);
		
		mock.perform(delete("/projects" + "/{id}", idToRemove))
		.andExpect(status().is(BAD_REQUEST.value()));
		
		verify(projectsService).findById(idToRemove);
	}
	
	@Test
	public void mustFindAllProjects() throws Exception {
		Project mockProject = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").build();
		List<Project> mockProjects = asList(mockProject);
		String mockProjectsJSON = toObjectJSON(mockProjects);
		configMockFindAllProjects(mockProjects);
		
		mock.perform(get("/projects")
									.contentType(APPLICATION_JSON_UTF8)
									.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().json(mockProjectsJSON));
		
		verify(projectsService).findAll();
	}
	
	@Test
	public void mustFindByIdProject() throws Exception {
		Long idToFind = 22L;
		Project mockProject = new ProjectBuilder().withId(idToFind).withName("Projeto de portfolio").build();
		String mockProjectJSON = toObjectJSON(mockProject);
		configMockFindByIdProject(idToFind, mockProject);
		
		mock.perform(get("/projects" + "/{id}", idToFind)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().json(mockProjectJSON));
		
		verify(projectsService).findById(idToFind);
	}
	
	@Test
	public void mustFailOnFindByIdProject() throws Exception {
		Long idToFind = 22L;
		configMockFindByIdProject(idToFind, null);
		
		mock.perform(get("/projects" + "/{id}", idToFind)
														.contentType(APPLICATION_JSON_UTF8)
														.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().is(NOT_FOUND.value()));
		
		verify(projectsService).findById(idToFind);
	}
	
	private Person defaultManager() {
		return new PersonBuilder().withId(22L).withName("Alessandro da Veiga").asManager().build();
	}

	private String toObjectJSON(Object obj) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);
	}

	private void configMockCreateProject(Project project) {
		when(projectsService.create(any(Project.class))).thenReturn(project);
	}

	private void configMockUpdateProject(Project project) {
		when(projectsService.update(project)).thenReturn(project);
	}

	private void configMockFindByIdProject(Long id, Project project) {
		when(projectsService.findById(id)).thenReturn(Optional.ofNullable(project));
	}

	private void configMockFindAllProjects(List<Project> projects) {
		when(projectsService.findAll()).thenReturn(projects);
	}
}
