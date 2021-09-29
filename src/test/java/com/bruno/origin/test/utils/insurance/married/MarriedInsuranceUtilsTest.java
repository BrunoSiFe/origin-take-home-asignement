package com.bruno.origin.test.utils.insurance.married;

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
import com.bruno.origin.utils.insurance.married.MarriedInsuranceUtils;

@ExtendWith(MockitoExtension.class)
class MarriedInsuranceUtilsTest {
	
	@InjectMocks
	private MarriedInsuranceUtils marriedInsuranceUtils;
	
	@Test
	void validateClientIsMarried_IsMarried() {
		
		String marital_status = "married";
		
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.LIFE_LINE, createInsuranceResult());
		
		insuranceScoreResultPerLine = marriedInsuranceUtils.validateClientIsMarried(marital_status, insuranceScoreResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(1,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(3,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
		
	}
	
	@Test
	void validateClientIsMarried_IsSingle() {
		
		String marital_status = "single";
		
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.LIFE_LINE, createInsuranceResult());
		
		insuranceScoreResultPerLine = marriedInsuranceUtils.validateClientIsMarried(marital_status, insuranceScoreResultPerLine);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2,insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());
		
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
