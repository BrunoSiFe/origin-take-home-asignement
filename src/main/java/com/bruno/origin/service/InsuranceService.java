package com.bruno.origin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruno.origin.mapper.InsuranceResponseMapper;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.insurance.request.InsuranceRequestDTO;
import com.bruno.origin.model.dto.insurance.response.InsuranceResponseDTO;
import com.bruno.origin.utils.IncomeInsuranceUtils;
import com.bruno.origin.utils.InsuranceScoreUtils;
import com.bruno.origin.utils.insurance.house.HouseInsuranceUtils;

@Service
public class InsuranceService {

	public InsuranceResponseDTO calculateInsuranceScore(InsuranceRequestDTO insuranceCostumerData) {

		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		Map<String, InsuranceResult> insuranceResultPerLine = insuranceScoreUtils
				.calculateBaseRiskAnswers(insuranceCostumerData.getRiskQuestions());

		HouseInsuranceUtils houseInsurance = new HouseInsuranceUtils();
		IncomeInsuranceUtils incomeInsuranceUtils = new IncomeInsuranceUtils();
		
		insuranceResultPerLine = incomeInsuranceUtils.evaluateNoIncome(insuranceCostumerData.getIncome(), insuranceResultPerLine);

		insuranceResultPerLine = houseInsurance.validateHomeInsuranceNotIneligible(insuranceCostumerData.getHouse(),
				insuranceResultPerLine);

		InsuranceResponseMapper insuranceMapper = new InsuranceResponseMapper();
		return insuranceMapper.createInsuranceResponseDTO(insuranceResultPerLine);
	}

}
