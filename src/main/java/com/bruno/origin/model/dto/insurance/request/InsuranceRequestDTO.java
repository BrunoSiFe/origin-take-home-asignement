package com.bruno.origin.model.dto.insurance.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bruno.origin.model.dto.HouseDTO;
import com.bruno.origin.model.dto.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InsuranceRequestDTO {

	@NotNull
	@Min(0)
	private Integer age;

	@NotNull
	@Min(0)
	private Integer dependents;

	private HouseDTO house;

	@NotNull
	@Min(0)
	private Integer income;

	@NotNull
	@JsonProperty("marital_status")
	private String maritalStatus;

	@NotNull
	@JsonProperty("risk_questions")
	private List<Boolean> riskQuestions;

	private VehicleDTO vehicle;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getDependents() {
		return dependents;
	}

	public void setDependents(Integer dependents) {
		this.dependents = dependents;
	}

	public HouseDTO getHouse() {
		return house;
	}

	public void setHouse(HouseDTO house) {
		this.house = house;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<Boolean> getRiskQuestions() {
		return riskQuestions;
	}

	public void setRiskQuestions(List<Boolean> riskQuestions) {
		this.riskQuestions = riskQuestions;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

}
