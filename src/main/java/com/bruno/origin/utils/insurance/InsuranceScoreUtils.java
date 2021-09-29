package com.bruno.origin.utils.insurance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;

public class InsuranceScoreUtils {

	public InsuranceResult createIneligibleScoreResult() {
		return new InsuranceResult(InsuranceScoreResult.INELIGIBLE, InsuranceScoreResult.NUMERICAL_SCORE_INELIGIBLE);
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
		return new InsuranceResult(InsuranceScoreResult.ECONOMIC, riskScore);
	}

	public Map<String, InsuranceResult> deduceRiskScorePointAllInsuranceLines(
			Map<String, InsuranceResult> insuranceResultPerLine, Integer scorePointQuantityToBeDeduced) {
		InsuranceResult insuranceResultAuto = insuranceResultPerLine.get(InsuranceLines.AUTO_LINE);
		InsuranceResult insuranceResultDisability = insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE);
		InsuranceResult insuranceResultHome = insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE);
		InsuranceResult insuranceResultLife = insuranceResultPerLine.get(InsuranceLines.LIFE_LINE);

		Integer numericalInsuranceScore = insuranceResultAuto.getNumericalScore();
		numericalInsuranceScore -= scorePointQuantityToBeDeduced;
		insuranceResultAuto.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultDisability.getNumericalScore();
		numericalInsuranceScore -= scorePointQuantityToBeDeduced;
		insuranceResultDisability.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultHome.getNumericalScore();
		numericalInsuranceScore -= scorePointQuantityToBeDeduced;
		insuranceResultHome.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultLife.getNumericalScore();
		numericalInsuranceScore -= scorePointQuantityToBeDeduced;
		insuranceResultLife.setNumericalScore(numericalInsuranceScore);

		insuranceResultPerLine.replace(InsuranceLines.AUTO_LINE, insuranceResultAuto);
		insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceResultDisability);
		insuranceResultPerLine.replace(InsuranceLines.HOUSE_LINE, insuranceResultHome);
		insuranceResultPerLine.replace(InsuranceLines.LIFE_LINE, insuranceResultLife);

		return insuranceResultPerLine;
	}

	public Map<String, InsuranceResult> addRiskPointHomeDisability(
			Map<String, InsuranceResult> insuranceResultPerLine, Integer scorePointQuantityToBeDeduced) {

		InsuranceResult insuranceResultDisability = insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE);
		InsuranceResult insuranceResultHome = insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE);

		Integer numericalInsuranceScore = insuranceResultDisability.getNumericalScore();
		numericalInsuranceScore += scorePointQuantityToBeDeduced;
		insuranceResultDisability.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultHome.getNumericalScore();
		numericalInsuranceScore += scorePointQuantityToBeDeduced;
		insuranceResultHome.setNumericalScore(numericalInsuranceScore);

		insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceResultDisability);
		insuranceResultPerLine.replace(InsuranceLines.HOUSE_LINE, insuranceResultHome);

		return insuranceResultPerLine;
	}

	public Map<String, InsuranceResult> addRiskPointDisabilityLife(
			Map<String, InsuranceResult> insuranceResultPerLine, Integer scorePointQuantityToBeDeduced) {

		InsuranceResult insuranceResultDisability = insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE);
		InsuranceResult insuranceResultLife = insuranceResultPerLine.get(InsuranceLines.LIFE_LINE);

		Integer numericalInsuranceScore = insuranceResultDisability.getNumericalScore();
		numericalInsuranceScore += scorePointQuantityToBeDeduced;
		insuranceResultDisability.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultLife.getNumericalScore();
		numericalInsuranceScore += scorePointQuantityToBeDeduced;
		insuranceResultLife.setNumericalScore(numericalInsuranceScore);
		
		insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceResultDisability);
		insuranceResultPerLine.replace(InsuranceLines.LIFE_LINE, insuranceResultLife);

		return insuranceResultPerLine;
	}
	
	public Map<String, InsuranceResult> deduceRiskPointDisabilityAddRiskPointLife(
			Map<String, InsuranceResult> insuranceResultPerLine, Integer scorePointQuantityToBeDeduced) {

		InsuranceResult insuranceResultDisability = insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE);
		InsuranceResult insuranceResultLife = insuranceResultPerLine.get(InsuranceLines.LIFE_LINE);

		Integer numericalInsuranceScore = insuranceResultDisability.getNumericalScore();
		numericalInsuranceScore -= scorePointQuantityToBeDeduced;
		insuranceResultDisability.setNumericalScore(numericalInsuranceScore);

		numericalInsuranceScore = insuranceResultLife.getNumericalScore();
		numericalInsuranceScore += scorePointQuantityToBeDeduced;
		insuranceResultLife.setNumericalScore(numericalInsuranceScore);
		
		insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceResultDisability);
		insuranceResultPerLine.replace(InsuranceLines.LIFE_LINE, insuranceResultLife);

		return insuranceResultPerLine;
	}
}
