package com.empresa.projetos.model.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.empresa.projetos.model.entity.Status;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {
	@Override
	public String convertToDatabaseColumn(Status status) {
		if (status != null) {
			return status.getValue();
		}
		return null;
	}

	@Override
	public Status convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return Status.ofValue(value);
	}
}
