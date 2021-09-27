package com.bruno.origin.enums;

public enum HouseValidationEnum {
	
	OWNED("owned"), MORTGAGED("mortgaged");

	private String ownershipStatus = "";

	public String getOwnershipStatus() {
		return ownershipStatus;
	}

	HouseValidationEnum(String ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

}
