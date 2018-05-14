package com.empresa.projetos.model.entity;

public enum Risk {
	LOW("Baixo risco"), //
	MID("Médio risco"), //
	HIGH("Alto risco");

	String value;

	Risk(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Risk ofValue(String value) {
		for (Risk s : values()) {
			if (s.getValue().equalsIgnoreCase(value)) {
				return s;
			}
		}
		return null;
	}
}
