package com.bruno.origin.mapper;

import java.util.Map;
import java.util.Objects;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.insurance.response.InsuranceResponseDTO;

public class InsuranceResponseMapper {

	public InsuranceResponseDTO createInsuranceResponseDTO(Map<String, InsuranceResult> insuranceResultPerLine) {

		InsuranceResponseDTO insuranceResponse = new InsuranceResponseDTO();

		if (isInsuranceScoreIneligible(insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore())) {
			calculateAutoFinalScore(insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore(),
					insuranceResponse);
		}else {
			insuranceResponse.setAuto(InsuranceScoreResult.INELIGIBLE);
		}

		if (isInsuranceScoreIneligible(insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore())) {
			calculateDisabilityFinalScore(
					insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore(), insuranceResponse);
		}else {
			insuranceResponse.setDisability(InsuranceScoreResult.INELIGIBLE);
		}

		if (isInsuranceScoreIneligible(insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore())) {
			calculateHomeFinalScore(insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore(),
					insuranceResponse);
		}else {
			insuranceResponse.setHome(InsuranceScoreResult.INELIGIBLE);
		}
		
		if (isInsuranceScoreIneligible(insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore())) {
			calculateLifeFinalScore(insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore(),
					insuranceResponse);
		}else {
			insuranceResponse.setLife(InsuranceScoreResult.INELIGIBLE);
		}
		
		return insuranceResponse;
	}

	private InsuranceResponseDTO calculateAutoFinalScore(Integer numericalScore,
			InsuranceResponseDTO insuranceResponse) {
		if (numericalScore < 1) {
			insuranceResponse.setAuto(InsuranceScoreResult.ECONOMIC);
		} else if (numericalScore <= 2 && numericalScore >= 1) {
			insuranceResponse.setAuto(InsuranceScoreResult.REGULAR);
		} else {
			insuranceResponse.setAuto(InsuranceScoreResult.RESPONSIBLE);
		}

		return insuranceResponse;
	}

	private InsuranceResponseDTO calculateDisabilityFinalScore(Integer numericalScore,
			InsuranceResponseDTO insuranceResponse) {
		if (numericalScore < 1) {
			insuranceResponse.setDisability(InsuranceScoreResult.ECONOMIC);
		} else if (numericalScore <= 2 && numericalScore >= 1) {
			insuranceResponse.setDisability(InsuranceScoreResult.REGULAR);
		} else {
			insuranceResponse.setDisability(InsuranceScoreResult.RESPONSIBLE);
		}

		return insuranceResponse;
	}

	private InsuranceResponseDTO calculateHomeFinalScore(Integer numericalScore,
			InsuranceResponseDTO insuranceResponse) {
		if (numericalScore < 1) {
			insuranceResponse.setHome(InsuranceScoreResult.ECONOMIC);
		} else if (numericalScore <= 2 && numericalScore >= 1) {
			insuranceResponse.setHome(InsuranceScoreResult.REGULAR);
		} else {
			insuranceResponse.setHome(InsuranceScoreResult.RESPONSIBLE);
		}

		return insuranceResponse;
	}

	private InsuranceResponseDTO calculateLifeFinalScore(Integer numericalScore,
			InsuranceResponseDTO insuranceResponse) {
		if (numericalScore < 1) {
			insuranceResponse.setLife(InsuranceScoreResult.ECONOMIC);
		} else if (numericalScore <= 2 && numericalScore >= 1) {
			insuranceResponse.setLife(InsuranceScoreResult.REGULAR);
		} else {
			insuranceResponse.setLife(InsuranceScoreResult.RESPONSIBLE);
		}

		return insuranceResponse;
	}

	private boolean isInsuranceScoreIneligible(String finalScore) {
		return !Objects.isNull(finalScore) && !finalScore.equals(InsuranceScoreResult.INELIGIBLE);
	}

}
