package com.bruno.origin.utils.insurance.house;

import java.util.Map;
import java.util.Objects;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.HouseDTO;
import com.bruno.origin.utils.InsuranceScoreUtils;
import com.bruno.origin.utils.validator.HouseValidatorUtils;

public class HouseInsuranceUtils {

	public Map<String, InsuranceResult> validateHomeInsuranceNotIneligible(HouseDTO houseData,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if (!Objects.isNull(houseData)) {
			validateHouseData(houseData);
		} else {
			insuranceResultPerLine.replace(InsuranceLines.HOUSE_LINE, insuranceScoreUtils.createIneligibleScoreResult());
		}
		
		return insuranceResultPerLine;
	}
	
	private void validateHouseData(HouseDTO houseData) {
		HouseValidatorUtils houseValidator = new HouseValidatorUtils();
		houseValidator.validateHouseValues(houseData);
	}
	
}
