package com.empresa.projetos.model.entity.converter;

import static com.empresa.projetos.model.entity.Risk.HIGH;
import static com.empresa.projetos.model.entity.Risk.LOW;
import static com.empresa.projetos.model.entity.Risk.MID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.empresa.projetos.model.entity.converter.RiskConverter;

public class RiskConverterTest {
	private RiskConverter converter;

	@Before
	public void setUp() {
		this.converter = new RiskConverter();
	}

	@Test
	public void convertToDatabaseColumn() {
		assertNull(converter.convertToDatabaseColumn(null));
		assertEquals(LOW.getValue(), converter.convertToDatabaseColumn(LOW));
		assertEquals(MID.getValue(), converter.convertToDatabaseColumn(MID));
		assertEquals(HIGH.getValue(), converter.convertToDatabaseColumn(HIGH));
	}

	@Test
	public void convertToEntityAttribute() {
		assertNull(converter.convertToEntityAttribute(null));
		assertEquals(LOW, converter.convertToEntityAttribute(LOW.getValue()));
		assertEquals(LOW, converter.convertToEntityAttribute(LOW.getValue().toLowerCase()));
		assertEquals(MID, converter.convertToEntityAttribute(MID.getValue()));
		assertEquals(MID, converter.convertToEntityAttribute(MID.getValue().toUpperCase()));
		assertEquals(HIGH, converter.convertToEntityAttribute(HIGH.getValue()));
	}
}
