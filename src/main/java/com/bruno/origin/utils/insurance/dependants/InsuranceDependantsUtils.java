package com.bruno.origin.utils.insurance.dependants;

import java.util.Map;

import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

public class InsuranceDependantsUtils {
	
	public Map<String, InsuranceResult> validateClientHasDependants(Integer numberDependants,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if (hasDependants(numberDependants)) {
			return insuranceScoreUtils.addRiskPointDisabilityLife(insuranceResultPerLine, 1);
		}
		
		return insuranceResultPerLine;
	}
	
	private boolean hasDependants(Integer numberDependants) {
		return numberDependants > 0;
	}

}
