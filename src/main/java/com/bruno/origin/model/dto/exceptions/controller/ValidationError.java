package com.bruno.origin.model.dto.exceptions.controller;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartErrorDTO {

	private List<FieldMessages> erros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessages> getErrors() {
		return erros;
	}

	public void addError(String fieldName, String fieldErro) {
		var fieldsMessages = new FieldMessages(fieldName, fieldErro);
		this.erros.add(fieldsMessages);
	}
}

