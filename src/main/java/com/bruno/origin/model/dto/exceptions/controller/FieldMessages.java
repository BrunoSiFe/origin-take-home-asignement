package com.bruno.origin.model.dto.exceptions.controller;

import java.io.Serializable;

public class FieldMessages implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	
	private String fieldMessage;
	
	public FieldMessages() {
		
	}

	public FieldMessages(String fieldName, String fieldMessage) {
		this.fieldName = fieldName;
		this.fieldMessage = fieldMessage;
	}



	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}

	public void setFieldMessage(String fieldMessage) {
		this.fieldMessage = fieldMessage;
	}
}

