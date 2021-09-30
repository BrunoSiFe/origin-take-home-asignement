package com.bruno.origin.test.utils.insurance.income;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.income.IncomeInsuranceUtils;

@ExtendWith(MockitoExtension.class)
class IncomeInsuranceUtilsTest {

	
	@InjectMocks
	private IncomeInsuranceUtils incomeInsuranceUtils;
	
	@Test
	void evaluate_ClienDoesntHasIncome() {
		Integer income = 0;
		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();
		insuranceResultPerLine = incomeInsuranceUtils.evaluateNoIncome(income, insuranceResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(-1, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	@Test
	void evaluate_ClientHasIncome() {
		Integer income = 1;
		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();
		insuranceResultPerLine = incomeInsuranceUtils.evaluateNoIncome(income, insuranceResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	@Test
	void evaluate_IncomeUnderTwoHundredThousand() {
		Integer income = 1;
		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();
		insuranceResultPerLine = incomeInsuranceUtils.validateIncomeOver200Thousand(income, insuranceResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	@Test
	void evaluate_IncomeOverTwoHundredThousand() {
		Integer income = 2000000;
		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();
		insuranceResultPerLine = incomeInsuranceUtils.validateIncomeOver200Thousand(income, insuranceResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(1, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(1, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(1, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(1, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	private Map<String, InsuranceResult> createInsuranceResultMap() {
		Map<String, InsuranceResult> insuranceResultMap = new HashMap<>();

		insuranceResultMap.put(InsuranceLines.AUTO_LINE, createInsuranceResult());
		insuranceResultMap.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceResultMap.put(InsuranceLines.HOUSE_LINE, createInsuranceResult());
		insuranceResultMap.put(InsuranceLines.LIFE_LINE, createInsuranceResult());

		return insuranceResultMap;
	}

	private InsuranceResult createInsuranceResult() {
		return new InsuranceResult(InsuranceScoreResult.ECONOMIC, 2);
	}
}
