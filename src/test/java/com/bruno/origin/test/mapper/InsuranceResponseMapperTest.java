package com.bruno.origin.test.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.mapper.InsuranceResponseMapper;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.insurance.response.InsuranceResponseDTO;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

@ExtendWith(MockitoExtension.class)
class InsuranceResponseMapperTest {

	@InjectMocks
	private InsuranceResponseMapper insuranceResponseMapper;

	@Test
	void allLines_ineligible() {

		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();

		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResultIneligible());
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, createResultIneligible());
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResultIneligible());
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResultIneligible());

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getLife());

	}
	
	@Test
	void allLines_ineligibleNullFinalScore() {

		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();

		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResult(null,0));
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, createResult(null,0));
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResult(null,0));
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResult(null,0));

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResponse.getLife());

	}

	@Test
	void lines_Economic() {
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResult(InsuranceScoreResult.ECONOMIC,0));
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, createResult(InsuranceScoreResult.ECONOMIC,0));
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResult(InsuranceScoreResult.ECONOMIC,0));
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResult(InsuranceScoreResult.ECONOMIC,0));

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResponse.getLife());
	}

	@Test
	void lines_RegularRiskScoreOne() {
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResult(InsuranceScoreResult.REGULAR,1));
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, createResult(InsuranceScoreResult.REGULAR,1));
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResult(InsuranceScoreResult.REGULAR,1));
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResult(InsuranceScoreResult.REGULAR,1));

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getLife());
	}
	
	@Test
	void lines_RegularRiskScoreTwo() {
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResult(InsuranceScoreResult.REGULAR,2));
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE, createResult(InsuranceScoreResult.REGULAR,2));
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResult(InsuranceScoreResult.REGULAR,2));
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResult(InsuranceScoreResult.REGULAR,2));

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.REGULAR, insuranceResponse.getLife());
	}

	@Test
	void lines_Responsible() {
		Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, createResult(InsuranceScoreResult.RESPONSIBLE,3));
		insuranceResultPerLine.put(InsuranceLines.DISABILITY_LINE,
				createResult(InsuranceScoreResult.RESPONSIBLE,3));
		insuranceResultPerLine.put(InsuranceLines.HOUSE_LINE, createResult(InsuranceScoreResult.RESPONSIBLE,3));
		insuranceResultPerLine.put(InsuranceLines.LIFE_LINE, createResult(InsuranceScoreResult.RESPONSIBLE,3));

		InsuranceResponseDTO insuranceResponse = insuranceResponseMapper
				.createInsuranceResponseDTO(insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.RESPONSIBLE, insuranceResponse.getAuto());
		assertEquals(InsuranceScoreResult.RESPONSIBLE, insuranceResponse.getDisability());
		assertEquals(InsuranceScoreResult.RESPONSIBLE, insuranceResponse.getHome());
		assertEquals(InsuranceScoreResult.RESPONSIBLE, insuranceResponse.getLife());
	}

	private InsuranceResult createResultIneligible() {
		InsuranceScoreUtils insuranceUtils = new InsuranceScoreUtils();
		return insuranceUtils.createIneligibleScoreResult();
	}

	private InsuranceResult createResult(String insuranceScoreResult, Integer riskScore) {
		return new InsuranceResult(insuranceScoreResult, riskScore);
	}

}
