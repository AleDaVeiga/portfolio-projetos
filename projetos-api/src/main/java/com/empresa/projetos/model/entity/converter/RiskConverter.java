package com.empresa.projetos.model.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.empresa.projetos.model.entity.Risk;

@Converter
public class RiskConverter implements AttributeConverter<Risk, String> {
	@Override
	public String convertToDatabaseColumn(Risk status) {
		if (status != null) {
			return status.getValue();
		}
		return null;
	}

	@Override
	public Risk convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return Risk.ofValue(value);
	}
}
