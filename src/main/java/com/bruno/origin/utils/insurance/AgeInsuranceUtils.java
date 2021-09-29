package com.bruno.origin.utils.insurance;

import java.util.Map;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.model.InsuranceResult;

public class AgeInsuranceUtils {

	public Map<String, InsuranceResult> validateAgeIneligible(Integer age,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if (age > 60) {
			InsuranceResult insuranceResult = insuranceScoreUtils.createIneligibleScoreResult();
			insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceResult);
			insuranceResultPerLine.replace(InsuranceLines.LIFE_LINE, insuranceResult);
		}

		return insuranceResultPerLine;
	}

	public Map<String, InsuranceResult> evaluateClientAge(Integer age,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new  InsuranceScoreUtils();
		if (isAgeOver30AndUnder40(age)) {

			insuranceResultPerLine = insuranceScoreUtils.deduceRiskScorePointAllInsuranceLines(insuranceResultPerLine, 1);
		} else if (age < 30) {
			insuranceResultPerLine = insuranceScoreUtils.deduceRiskScorePointAllInsuranceLines(insuranceResultPerLine, 2);
		}

		return insuranceResultPerLine;
	}

	private boolean isAgeOver30AndUnder40(Integer age) {
		return age >= 30 && age <= 40;
	}

}
