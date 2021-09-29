package com.bruno.origin.utils.insurance.income;

import java.util.Map;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

public class IncomeInsuranceUtils {

	public Map<String, InsuranceResult> evaluateNoIncome(Integer income, Map<String, InsuranceResult> insuranceResultPerLine){
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if(income==0)
			insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceScoreUtils.createIneligibleScoreResult());
		
		return insuranceResultPerLine;
	}
	
	public Map<String, InsuranceResult> validateIncomeOver200Thousand(Integer income, Map<String, InsuranceResult> insuranceResultPerLine){
		if(income>200000) {
			InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
			return insuranceScoreUtils.deduceRiskScorePointAllInsuranceLines(insuranceResultPerLine, 1);
		}
		return insuranceResultPerLine;
	}
}
