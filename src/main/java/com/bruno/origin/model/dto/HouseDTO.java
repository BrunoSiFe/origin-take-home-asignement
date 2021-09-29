package com.bruno.origin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseDTO {
	
	@JsonProperty("ownership_status")
	private String ownershipStatus;

	public String getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(String ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

}
