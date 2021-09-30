package com.bruno.origin.test.utils.insurance.house;

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
import com.bruno.origin.model.dto.HouseDTO;
import com.bruno.origin.utils.insurance.house.HouseInsuranceUtils;

@ExtendWith(MockitoExtension.class)
class HouseInsuranceUtilsTest {

	@InjectMocks
	private HouseInsuranceUtils houseInsuranceUtils;
	
	private static final String OWNERSHIP_STATUS_OWNED="owned";
	private static final String OWNERSHIP_STATUS_MORTGAGED="mortgaged";

	@Test
	void validate_InsuranceHomeIneligible() {

		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();

		insuranceResultPerLine = houseInsuranceUtils.validateHomeInsuranceNotIneligible(null, insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.INELIGIBLE,
				insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(-1, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}
	
	@Test
	void validate_InsuranceHomeNotIneligible() {

		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();
		
		HouseDTO house = new HouseDTO();
		house.setOwnershipStatus(OWNERSHIP_STATUS_OWNED);

		insuranceResultPerLine = houseInsuranceUtils.validateHomeInsuranceNotIneligible(house, insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}
	
	@Test
	void evaluate_IsHouseMortgagedHouseNull() {

		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();

		insuranceResultPerLine = houseInsuranceUtils.isHouseMortgaged(null, insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}
	
	@Test
	void evaluate_IsHouseMortgagedOwned() {

		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();

		HouseDTO house = new HouseDTO();
		house.setOwnershipStatus(OWNERSHIP_STATUS_OWNED);
		
		insuranceResultPerLine = houseInsuranceUtils.isHouseMortgaged(house, insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());

	}
	
	@Test
	void evaluate_HouseMortgaged() {

		Map<String, InsuranceResult> insuranceResultPerLine = createInsuranceResultMap();

		HouseDTO house = new HouseDTO();
		house.setOwnershipStatus(OWNERSHIP_STATUS_MORTGAGED);
		
		insuranceResultPerLine = houseInsuranceUtils.isHouseMortgaged(house, insuranceResultPerLine);

		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getFinalScore());
		assertEquals(InsuranceScoreResult.ECONOMIC,
				insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getFinalScore());

		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
		assertEquals(3, insuranceResultPerLine.get(InsuranceLines.DISABILITY_LINE).getNumericalScore());
		assertEquals(3, insuranceResultPerLine.get(InsuranceLines.HOUSE_LINE).getNumericalScore());
		assertEquals(2, insuranceResultPerLine.get(InsuranceLines.LIFE_LINE).getNumericalScore());

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
