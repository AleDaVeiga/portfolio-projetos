package com.empresa.projetos.model.entity.converter;

import static com.empresa.projetos.model.entity.Status.ANALYSIS_APPROVED;
import static com.empresa.projetos.model.entity.Status.ANALYSIS_PERFORMED;
import static com.empresa.projetos.model.entity.Status.CANCELED;
import static com.empresa.projetos.model.entity.Status.CLOSED;
import static com.empresa.projetos.model.entity.Status.PLANNED;
import static com.empresa.projetos.model.entity.Status.STARTED;
import static com.empresa.projetos.model.entity.Status.UNDERWAY;
import static com.empresa.projetos.model.entity.Status.UNDER_REVIEW;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.empresa.projetos.model.entity.converter.StatusConverter;

public class StatusConverterTest {
	private StatusConverter converter;

	@Before
	public void setUp() {
		this.converter = new StatusConverter();
	}

	@Test
	public void convertToDatabaseColumn() {
		assertNull(converter.convertToDatabaseColumn(null));
		assertEquals(UNDER_REVIEW.getValue(), converter.convertToDatabaseColumn(UNDER_REVIEW));
		assertEquals(ANALYSIS_PERFORMED.getValue(), converter.convertToDatabaseColumn(ANALYSIS_PERFORMED));
		assertEquals(ANALYSIS_APPROVED.getValue(), converter.convertToDatabaseColumn(ANALYSIS_APPROVED));
		assertEquals(STARTED.getValue(), converter.convertToDatabaseColumn(STARTED));
		assertEquals(PLANNED.getValue(), converter.convertToDatabaseColumn(PLANNED));
		assertEquals(UNDERWAY.getValue(), converter.convertToDatabaseColumn(UNDERWAY));
		assertEquals(CLOSED.getValue(), converter.convertToDatabaseColumn(CLOSED));
		assertEquals(CANCELED.getValue(), converter.convertToDatabaseColumn(CANCELED));
	}

	@Test
	public void convertToEntityAttribute() {
		assertNull(converter.convertToEntityAttribute(null));
		assertEquals(UNDER_REVIEW, converter.convertToEntityAttribute(UNDER_REVIEW.getValue()));
		assertEquals(UNDER_REVIEW, converter.convertToEntityAttribute(UNDER_REVIEW.getValue().toLowerCase()));
		assertEquals(ANALYSIS_PERFORMED, converter.convertToEntityAttribute(ANALYSIS_PERFORMED.getValue()));
		assertEquals(ANALYSIS_PERFORMED, converter.convertToEntityAttribute(ANALYSIS_PERFORMED.getValue().toUpperCase()));
		assertEquals(ANALYSIS_APPROVED, converter.convertToEntityAttribute(ANALYSIS_APPROVED.getValue()));
		assertEquals(STARTED, converter.convertToEntityAttribute(STARTED.getValue()));
		assertEquals(PLANNED, converter.convertToEntityAttribute(PLANNED.getValue()));
		assertEquals(UNDERWAY, converter.convertToEntityAttribute(UNDERWAY.getValue()));
		assertEquals(CLOSED, converter.convertToEntityAttribute(CLOSED.getValue()));
		assertEquals(CANCELED, converter.convertToEntityAttribute(CANCELED.getValue()));
	}
}
