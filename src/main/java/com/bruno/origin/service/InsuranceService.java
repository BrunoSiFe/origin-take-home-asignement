package com.bruno.origin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruno.origin.mapper.InsuranceResponseMapper;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.insurance.request.InsuranceRequestDTO;
import com.bruno.origin.model.dto.insurance.response.InsuranceResponseDTO;
import com.bruno.origin.utils.insurance.AgeInsuranceUtils;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;
import com.bruno.origin.utils.insurance.dependants.InsuranceDependantsUtils;
import com.bruno.origin.utils.insurance.house.HouseInsuranceUtils;
import com.bruno.origin.utils.insurance.income.IncomeInsuranceUtils;
import com.bruno.origin.utils.insurance.married.MarriedInsuranceUtils;
import com.bruno.origin.utils.insurance.vehicle.VehicleInsuranceUtils;
import com.bruno.origin.utils.validator.HouseValidatorUtils;
import com.bruno.origin.utils.validator.MarriedStatusValidatorUtils;

@Service
public class InsuranceService {

	public InsuranceResponseDTO calculateInsuranceScore(InsuranceRequestDTO insuranceCostumerData) {

		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		Map<String, InsuranceResult> insuranceResultPerLine = insuranceScoreUtils
				.calculateBaseRiskAnswers(insuranceCostumerData.getRiskQuestions());

		HouseInsuranceUtils houseInsurance = new HouseInsuranceUtils();
		VehicleInsuranceUtils vehicleInsurance = new VehicleInsuranceUtils();
		IncomeInsuranceUtils incomeInsurance = new IncomeInsuranceUtils();
		AgeInsuranceUtils ageInsurance = new AgeInsuranceUtils();
		InsuranceDependantsUtils dependantsInsurance = new InsuranceDependantsUtils();
		MarriedInsuranceUtils marriedInsurance = new MarriedInsuranceUtils();
		
		HouseValidatorUtils houseValidator = new HouseValidatorUtils();
		MarriedStatusValidatorUtils marriedStatusValidator = new MarriedStatusValidatorUtils();
		
		houseValidator.validateHouseValues(insuranceCostumerData.getHouse());
		
		marriedStatusValidator.validateMarriedStatus(insuranceCostumerData.getMaritalStatus());

		insuranceResultPerLine = incomeInsurance.evaluateNoIncome(insuranceCostumerData.getIncome(),
				insuranceResultPerLine);

		insuranceResultPerLine = vehicleInsurance
				.validateVehicleInsuranceNotIneligible(insuranceCostumerData.getVehicle(), insuranceResultPerLine);

		insuranceResultPerLine = houseInsurance.validateHomeInsuranceNotIneligible(insuranceCostumerData.getHouse(),
				insuranceResultPerLine);

		insuranceResultPerLine = ageInsurance.validateAgeIneligible(insuranceCostumerData.getAge(),
				insuranceResultPerLine);

		insuranceResultPerLine = ageInsurance.evaluateClientAge(insuranceCostumerData.getAge(), insuranceResultPerLine);

		insuranceResultPerLine = incomeInsurance.validateIncomeOver200Thousand(insuranceCostumerData.getIncome(),
				insuranceResultPerLine);

		insuranceResultPerLine = houseInsurance.isHouseMortgaged(insuranceCostumerData.getHouse(), insuranceResultPerLine);
		
		insuranceResultPerLine = dependantsInsurance.validateClientHasDependants(insuranceCostumerData.getDependents(), insuranceResultPerLine);
		
		insuranceResultPerLine = marriedInsurance.validateClientIsMarried(insuranceCostumerData.getMaritalStatus(), insuranceResultPerLine);
		
		insuranceResultPerLine = vehicleInsurance.validateVehicleProductionDate(insuranceCostumerData.getVehicle(), insuranceResultPerLine);
		
		InsuranceResponseMapper insuranceMapper = new InsuranceResponseMapper();
		return insuranceMapper.createInsuranceResponseDTO(insuranceResultPerLine);
	}

}
