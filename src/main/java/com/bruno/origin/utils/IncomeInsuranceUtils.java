package com.bruno.origin.utils;

import java.util.Map;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.model.InsuranceResult;

public class IncomeInsuranceUtils {

	public Map<String, InsuranceResult> evaluateNoIncome(Integer income, Map<String, InsuranceResult> insuranceResultPerLine){
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if(income==0)
			insuranceResultPerLine.replace(InsuranceLines.DISABILITY_LINE, insuranceScoreUtils.createIneligibleScoreResult());
		
		return insuranceResultPerLine;
	}
}
