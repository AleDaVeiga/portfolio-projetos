package com.empresa.projetos.model.entity;

import static com.empresa.projetos.model.entity.Risk.HIGH;
import static com.empresa.projetos.model.entity.Risk.LOW;
import static com.empresa.projetos.model.entity.Risk.MID;
import static com.empresa.projetos.model.entity.Status.CLOSED;
import static com.empresa.projetos.model.entity.Status.STARTED;
import static com.empresa.projetos.model.entity.Status.UNDERWAY;
import static com.empresa.projetos.model.entity.Status.UNDER_REVIEW;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	private Project project;

	@Before
	public void setUp() {
		this.project = new Project();
	}
	
	@Test
	public void mustChangeName() {
		String name = "Portfólio";
		
		this.project.changeName(name);
		
		assertEquals(name, this.project.getName());
	}
	
	@Test
	public void mustSchedule() {
		Date start = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		this.project.schedule(start, end);
		
		assertEquals(start, this.project.getStart());
		assertEquals(end, this.project.getScheduledEnd());
	}
	
	@Test
	public void mustFinish() {
		Date end = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		this.project.finish();
		
		assertEquals(end, this.project.getEnd());
	}
	
	@Test
	public void mustReturnEndWhenJustSchedule() {
		Date start = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		this.project.schedule(start, end);

		assertEquals(end, this.project.getEnd());
	}
	
	@Test
	public void mustChangeDescription() {
		String description = "Gerenciar os dados do portfólio.";
		
		this.project.changeDescription(description);
		
		assertEquals(description, this.project.getDescription());
	}
	
	@Test
	public void mustDefineStatus() {
		this.project.changeStatus(STARTED);
		
		assertEquals(STARTED, this.project.getStatus());
	}
	
	@Test
	public void mustReturnUnderReviewWhenStatusNotDefined() {
		assertEquals(UNDER_REVIEW, this.project.getStatus());
	}
	
	@Test
	public void mustChangeBudget() {
		BigDecimal budget = TEN;
		
		this.project.changeBudget(budget);
		
		assertEquals(budget, this.project.getBudget());
	}
	
	@Test
	public void mustDefineLowRisk() {
		this.project.lowRisk();
		
		assertEquals(LOW, this.project.getRisk());
	}
	
	@Test
	public void mustReturnLowRiskWhenRiskNotDefined() {
		assertEquals(LOW, this.project.getRisk());
	}
	
	@Test
	public void mustDefineMidRisk() {
		this.project.midRisk();
		
		assertEquals(MID, this.project.getRisk());
	}
	
	@Test
	public void mustDefineHighRisk() {
		this.project.highRisk();
		
		assertEquals(HIGH, this.project.getRisk());
	}
	
	@Test
	public void mustChangeManager() {
		Person manager = new PersonBuilder().withId(33L).withName("Alessandro").build();
		
		this.project.changeManager(manager);
		
		assertEquals(manager, this.project.getManager());
	}
	
	@Test
	public void mustAllowedRemove() {
		assertTrue(this.project.isAllowedRemove());
	}
	
	@Test
	public void mustDenyRemoveWhenStatusStarted() {
		this.project.changeStatus(STARTED);
		
		assertFalse(this.project.isAllowedRemove());
	}
	
	@Test
	public void mustDenyRemoveWhenStatusUnderWay() {
		this.project.changeStatus(UNDERWAY);
		
		assertFalse(this.project.isAllowedRemove());
	}
	
	@Test
	public void mustDenyRemoveWhenStatusClosed() {
		this.project.changeStatus(CLOSED);
		
		assertFalse(this.project.isAllowedRemove());
	}
}
