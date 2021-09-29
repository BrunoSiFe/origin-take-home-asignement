package com.bruno.origin.test.utils.insurance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.AgeInsuranceUtils;

@ExtendWith(MockitoExtension.class)
class AgeInsuranceUtilsTest {

	
	@InjectMocks
	private AgeInsuranceUtils ageInsuranceUtils;
	
	@Test
	void validate_AgeIneligible() {
			Integer age = 65;
			
			Map<String, InsuranceResult> insuranceResultPerLine = createMapInsuranceResult();
			Map<String, InsuranceResult> insuranceResultPerLineExpected = ageInsuranceUtils.validateAgeIneligible(age, insuranceResultPerLine);
			
			assertEquals(InsuranceScoreResult.INELIGIBLE,insuranceResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
			assertEquals(InsuranceScoreResult.INELIGIBLE,insuranceResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getFinalScore());
			
	}
	
	@Test
	void validate_AgeEligible() {
			Integer age = 55;
			
			Map<String, InsuranceResult> insuranceResultPerLine = createMapInsuranceResult();
			Map<String, InsuranceResult> insuranceResultPerLineExpected = ageInsuranceUtils.validateAgeIneligible(age, insuranceResultPerLine);
			
			assertNotEquals(InsuranceScoreResult.INELIGIBLE,insuranceResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
			assertNotEquals(InsuranceScoreResult.INELIGIBLE,insuranceResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getFinalScore());
			
	}
	
	
	@Test
	void evaluate_ClintAgeUnder30() {

		Integer age = 29;
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();
		
		insuranceScoreResultPerLine = ageInsuranceUtils.evaluateClientAge(age, insuranceScoreResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(0,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(0,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(0,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(0,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
		
	}
	
	
	@Test
	void evaluate_ClintAgeOver30Under40() {

		Integer age = 35;
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();
		
		insuranceScoreResultPerLine = ageInsuranceUtils.evaluateClientAge(age, insuranceScoreResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(1,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(1,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(1,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(1,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
		
	}
	
	@Test
	void evaluate_ClintAgeOver40Under60() {

		Integer age = 45;
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();
		
		insuranceScoreResultPerLine = ageInsuranceUtils.evaluateClientAge(age, insuranceScoreResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
		
	}
	
	private Map<String, InsuranceResult> createMapInsuranceResult(){
		InsuranceResult insuranceResult = new InsuranceResult();
		insuranceResult.setFinalScore(InsuranceScoreResult.ECONOMIC);
		insuranceResult.setNumericalScore(0);
		
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, insuranceResult);
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, insuranceResult);
		
		return insuranceResultPerLine;
		
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
