package com.empresa.projetos.model.entity;

public enum Status {
	UNDER_REVIEW("Em análise"), //
	ANALYSIS_PERFORMED("Análise realizada"), //
	ANALYSIS_APPROVED("Análise aprovada"), //
	STARTED("Iniciado"), //
	PLANNED("Planejado"), //
	UNDERWAY("Em andamento"), //
	CLOSED("Encerrado"), //
	CANCELED("Cancelado");

	String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Status ofValue(String value) {
		for (Status s : values()) {
			if (s.getValue().equalsIgnoreCase(value)) {
				return s;
			}
		}
		return null;
	}
}
