package com.bruno.origin.utils.insurance.married;

import java.util.Map;

import com.bruno.origin.enums.MarriageValidationEnum;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

public class MarriedInsuranceUtils {
	
	public Map<String, InsuranceResult> validateClientIsMarried(String maritalStatus,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if (isMarried(maritalStatus)) {
			return insuranceScoreUtils.deduceRiskPointDisabilityAddRiskPointLife(insuranceResultPerLine, 1);
		}
		
		return insuranceResultPerLine;
	}
	
	private boolean isMarried(String maritalStatus) {
		return maritalStatus.equals(MarriageValidationEnum.MARRIED.getMaritalStatus());
	}
}
