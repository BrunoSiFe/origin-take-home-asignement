 package com.bruno.origin.utils.insurance.vehicle;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import com.bruno.origin.constants.InsuranceLines;
import com.bruno.origin.model.InsuranceResult;
import com.bruno.origin.model.dto.VehicleDTO;
import com.bruno.origin.utils.insurance.InsuranceScoreUtils;

public class VehicleInsuranceUtils {

	public Map<String, InsuranceResult> validateVehicleInsuranceNotIneligible(VehicleDTO vehicleDTO,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceScoreUtils insuranceScoreUtils = new InsuranceScoreUtils();
		if (Objects.isNull(vehicleDTO)) {
			insuranceResultPerLine.replace(InsuranceLines.AUTO_LINE, insuranceScoreUtils.createIneligibleScoreResult());
		}
		
		return insuranceResultPerLine;
	}
	
	public Map<String, InsuranceResult> validateVehicleProductionDate(VehicleDTO vehicleDTO,
			Map<String, InsuranceResult> insuranceResultPerLine) {
		InsuranceResult insuranceResult = insuranceResultPerLine.get(InsuranceLines.AUTO_LINE);
		
		if (!Objects.isNull(vehicleDTO) && isVehicleProducedLastFiveYears(vehicleDTO)) {
			Integer numericalScore = insuranceResult.getNumericalScore();
			insuranceResult.setNumericalScore(numericalScore+1);
		}
		
		return insuranceResultPerLine;
	}
	
	private boolean isVehicleProducedLastFiveYears(VehicleDTO vehicleDTO) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.setTime(date);
		
		return (cal.get(Calendar.YEAR) - vehicleDTO.getYear()) < 5;
	}
	
}
