package com.bruno.origin.enums;

public enum MarriageValidationEnum {
	
	MARRIED("married"), SINGLE("single");

	private String maritalStatus = "";

	public String getMaritalStatus() {
		return maritalStatus;
	}

	MarriageValidationEnum(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

}
