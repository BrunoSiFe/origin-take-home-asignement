package com.bruno.origin.test.utils.insurance.dependants;

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
import com.bruno.origin.utils.insurance.dependants.InsuranceDependantsUtils;

@ExtendWith(MockitoExtension.class)
class InsuranceDependantsUtilsTest {
	
	@InjectMocks
	private InsuranceDependantsUtils insuranceDependantsUtisl;
	
	@Test
	void validate_ClientDoesntHasDependants() {
		
		Integer dependants = 0;
		
		Map<String, InsuranceResult> insuranceResult = createMapInsuranceResult();
		
		insuranceResult = insuranceDependantsUtisl.validateClientHasDependants(dependants, insuranceResult);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceResult.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceResult.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(0,insuranceResult.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(0,insuranceResult.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	@Test
	void validate_ClientHasDependants() {
		
		Integer dependants = 1;
		
		Map<String, InsuranceResult> insuranceResult = createMapInsuranceResult();
		
		insuranceResult = insuranceDependantsUtisl.validateClientHasDependants(dependants, insuranceResult);
		
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceResult.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,insuranceResult.get(InsuranceLines.LIFE_LINE).getFinalScore());
		
		assertEquals(1,insuranceResult.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(1,insuranceResult.get(InsuranceLines.LIFE_LINE).getNumericalScore());
	}
	
	private Map<String, InsuranceResult> createMapInsuranceResult(){
		InsuranceResult insuranceResultLife = new InsuranceResult();
		insuranceResultLife.setFinalScore(InsuranceScoreResult.ECONOMIC);
		insuranceResultLife.setNumericalScore(0);
		
		InsuranceResult insuranceResultDisability = new InsuranceResult();
		insuranceResultDisability.setFinalScore(InsuranceScoreResult.ECONOMIC);
		insuranceResultDisability.setNumericalScore(0);
		
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, insuranceResultDisability);
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, insuranceResultLife);
		
		return insuranceResultPerLine;
		
	}

}
