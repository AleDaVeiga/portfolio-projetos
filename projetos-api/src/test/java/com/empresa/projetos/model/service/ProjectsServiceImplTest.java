package com.empresa.projetos.model.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.empresa.projetos.exception.NotAllowedException;
import com.empresa.projetos.model.entity.Person;
import com.empresa.projetos.model.entity.PersonBuilder;
import com.empresa.projetos.model.entity.Project;
import com.empresa.projetos.model.entity.ProjectBuilder;
import com.empresa.projetos.model.repository.PersonRepository;
import com.empresa.projetos.model.repository.ProjectRepository;

@RunWith(SpringRunner.class)
public class ProjectsServiceImplTest {
	@TestConfiguration
    static class ProjectsServiceImplTestContextConfiguration {
        @Bean
        public ProjectsService employeeService() {
            return new ProjectsServiceImpl();
        }
    }
	
	@Autowired
	private ProjectsService projectsService;
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private PersonRepository personRepository;
	
	@Test
	public void mustSaveProject() {
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").build();
		configMockProjectSave(project);
		
		projectsService.create(project);
		
		verify(projectRepository).save(any(Project.class));
	}
	
	@Test
	public void mustSaveProjectWithMember() {
		Long employeeId = 33L;
		Person employee = newEmployee(employeeId, "Alessandro Employee").build();
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").addMember(employee).build();
		
		configMockPersonFindById(employeeId, employee);
		configMockProjectSave(project);
		
		Project createdProject = projectsService.create(project);
		
		assertTrue(createdProject.getMembers().contains(employee));
		verify(projectRepository).save(any(Project.class));
		verify(personRepository).findById(employeeId);
	}
	
	@Test(expected=NotAllowedException.class)
	public void mustFailOnSaveProjectWithMemberAsManager() {
		Long employeeId = 33L;
		Person employee = newEmployee(employeeId, "Alessandro Employee").build();
		Person employeeChanged = newEmployee(employeeId, "Alessandro Manager").asManager().build();
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").addMember(employee).build();
		
		configMockPersonFindById(employeeId, employeeChanged);
		configMockProjectSave(project);
		
		projectsService.create(project);
	}
	
	@Test
	public void mustUpdateProject() {
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").build();
		configMockProjectSave(project);
		
		projectsService.update(project);
		
		verify(projectRepository).save(any(Project.class));
	}
	
	@Test
	public void mustUpdateProjectWithMember() {
		Long employeeId = 33L;
		Person employee = newEmployee(employeeId, "Alessandro Employee").build();
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").addMember(employee).build();
		
		configMockPersonFindById(employeeId, employee);
		configMockProjectSave(project);
		
		Project updatedProject = projectsService.update(project);
		
		assertTrue(updatedProject.getMembers().contains(employee));
		verify(projectRepository).save(any(Project.class));
		verify(personRepository).findById(employeeId);
	}
	
	@Test(expected=NotAllowedException.class)
	public void mustFailOnUpdateProjectWithMemberAsManager() {
		Long employeeId = 33L;
		Person employee = newEmployee(employeeId, "Alessandro Employee").build();
		Person employeeChanged = newEmployee(employeeId, "Alessandro Manager").asManager().build();
		Project project = new ProjectBuilder().withId(22L).withName("Projeto de portfolio").addMember(employee).build();
		
		configMockPersonFindById(employeeId, employeeChanged);
		configMockProjectSave(project);
		
		projectsService.update(project);
	}
	
	@Test
	public void mustRemoveProject() {
		Long projectId = 22L;
		configMockProjectExistsById(projectId, true);
		configMockProjectDelete(projectId);
		
		projectsService.remove(projectId);
		
		verify(projectRepository).deleteById(projectId);
	}
	
	@Test
	public void mustNotRemoveProjectAbsent() {
		Long projectId = 22L;
		configMockProjectExistsById(projectId, false);
		configMockProjectDelete(projectId);
		
		projectsService.remove(projectId);
		
		verify(projectRepository, never()).deleteById(projectId);
	}

	private void configMockProjectSave(Project project) {
		when(projectRepository.save(project)).thenReturn(project);
	}
	
	private PersonBuilder newEmployee(Long id, String name) {
		return new PersonBuilder().withId(id).withName(name);
	}

	private void configMockPersonFindById(Long employeeId, Person employee) {
		when(personRepository.findById(employeeId)).thenReturn(Optional.of(employee));
	}

	private void configMockProjectDelete(Long projectId) {
		doNothing().when(projectRepository).deleteById(projectId);
	}

	private void configMockProjectExistsById(Long projectId, boolean exists) {
		when(projectRepository.existsById(projectId)).thenReturn(exists);
	}
}
