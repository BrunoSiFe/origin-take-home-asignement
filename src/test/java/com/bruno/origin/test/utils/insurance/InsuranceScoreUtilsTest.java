package com.bruno.origin.test.utils.insurance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.constants.InsuranceScoreResult;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

@ExtendWith(MockitoExtension.class)
class InsuranceScoreUtilsTest {

	@InjectMocks
	private InsuranceScoreUtils insuraceScoreUtils;

	@Test
	void create_IneligibleScore() {
		InsuranceResult insuranceResult = insuraceScoreUtils.createIneligibleScoreResult();

		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResult.getFinalScore());
		assertEquals(InsuranceScoreResult.NUMERICAL_SCORE_INELIGIBLE, insuranceResult.getNumericalScore());
	}

	@Test
	void calculate_BaseRisk() {
		Map<String, InsuranceResult> insuranceScoreResultPerLine = insuraceScoreUtils
				.calculateBaseRiskAnswers(createListRiskAnswers());

		InsuranceResult insuranceResult = insuranceScoreResultPerLine.get(InsuranceLines.AUTO_LINE);

		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResult.getFinalScore());
		assertEquals(2, insuranceResult.getNumericalScore());

		insuranceResult = insuranceScoreResultPerLine.get(InsuranceLines.DISABILITY_LINE);
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResult.getFinalScore());
		assertEquals(2, insuranceResult.getNumericalScore());

		insuranceResult = insuranceScoreResultPerLine.get(InsuranceLines.HOUSE_LINE);
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResult.getFinalScore());
		assertEquals(2, insuranceResult.getNumericalScore());

		insuranceResult = insuranceScoreResultPerLine.get(InsuranceLines.LIFE_LINE);
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResult.getFinalScore());
		assertEquals(2, insuranceResult.getNumericalScore());

	}

	@Test
	void addPoints_AllRiskLine() {
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.AUTO_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.HOUSE_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.LIFE_LINE, createInsuranceResult());

		Map<String, InsuranceResult> insuranceScoreResultPerLineExpected = insuraceScoreUtils
				.deduceRiskScorePointAllInsuranceLines(insuranceScoreResultPerLine, 1);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(1, insuranceScoreResultPerLineExpected.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(1, insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(1, insuranceScoreResultPerLineExpected.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(1, insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}

	@Test
	void addPoints_AddRiskPointHomeDisability() {
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.HOUSE_LINE, createInsuranceResult());

		Map<String, InsuranceResult> insuranceScoreResultPerLineExpected = insuraceScoreUtils
				.addRiskPointHomeDisability(insuranceScoreResultPerLine, 1);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.HOUSE_LINE).getFinalScore());

		assertEquals(3, insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(3, insuranceScoreResultPerLineExpected.get(InsuranceLines.HOUSE_LINE).getNumericalScore());

	}

	@Test
	void deducePoints_AddRiskPointDisabilityLife() {
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.LIFE_LINE, createInsuranceResult());

		Map<String, InsuranceResult> insuranceScoreResultPerLineExpected = insuraceScoreUtils
				.addRiskPointDisabilityLife(insuranceScoreResultPerLine, 1);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getFinalScore());
		assertEquals(3, insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(3, insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}
	
	@Test
	void addAndDeducePoints_DeduceRiskPointDisabilityAddRiskPointLife() {
		Map<String, InsuranceResult> insuranceScoreResultPerLine = createInsuranceResultMap();

		insuranceScoreResultPerLine.put(InsuranceLines.DISABILITY_LINE, createInsuranceResult());
		insuranceScoreResultPerLine.put(InsuranceLines.LIFE_LINE, createInsuranceResult());

		Map<String, InsuranceResult> insuranceScoreResultPerLineExpected = insuraceScoreUtils
				.deduceRiskPointDisabilityAddRiskPointLife(insuranceScoreResultPerLine, 1);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getFinalScore());
		assertEquals(1, insuranceScoreResultPerLineExpected.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(3, insuranceScoreResultPerLineExpected.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}

	private List<Boolean> createListRiskAnswers() {
		List<Boolean> listRiskAnswers = new ArrayList<>();

		listRiskAnswers.add(Boolean.TRUE);
		listRiskAnswers.add(Boolean.FALSE);
		listRiskAnswers.add(Boolean.TRUE);

		return listRiskAnswers;
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
