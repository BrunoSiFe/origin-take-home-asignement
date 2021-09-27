package com.bruno.origin.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;

public class InsuranceScoreUtils {
	
	public InsuranceResult createIneligibleScoreResult() {
		return new InsuranceResult(InsuranceScoreResult.INELIGIBLE,InsuranceScoreResult.NUMERICAL_SCORE_INELIGIBLE );
	}
	
	public Map<String, InsuranceResult> calculateBaseRiskAnswers(List<Boolean> riskAnswers) {
		Integer totalRiskAnswersFactor = 0;
		for (boolean answer : riskAnswers) {
			if (answer) {
				totalRiskAnswersFactor += 1;
			}
		}

		Map<String, InsuranceResult> baseInsuranceScoreResult = new HashMap<>();

		baseInsuranceScoreResult.put(InsuranceLines.AUTO_LINE, createBaseInsuranceResult(totalRiskAnswersFactor));
		baseInsuranceScoreResult.put(InsuranceLines.DISABILITY_LINE, createBaseInsuranceResult(totalRiskAnswersFactor));
		baseInsuranceScoreResult.put(InsuranceLines.HOUSE_LINE, createBaseInsuranceResult(totalRiskAnswersFactor));
		baseInsuranceScoreResult.put(InsuranceLines.LIFE_LINE, createBaseInsuranceResult(totalRiskAnswersFactor));

		return baseInsuranceScoreResult;
	}

	private InsuranceResult createBaseInsuranceResult(Integer riskScore) {
		return new InsuranceResult(InsuranceScoreResult.REGULAR,riskScore);
	}

}
