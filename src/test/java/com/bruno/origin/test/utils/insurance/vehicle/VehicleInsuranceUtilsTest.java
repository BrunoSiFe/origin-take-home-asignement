package com.bruno.origin.test.utils.insurance.vehicle;

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
import com.bruno.origin.model.dto.VehicleDTO;
import com.bruno.origin.utils.insurance.vehicle.VehicleInsuranceUtils;

@ExtendWith(MockitoExtension.class)
class VehicleInsuranceUtilsTest {
	
	@InjectMocks
	private VehicleInsuranceUtils vehicleInsuranceUtils;
	
	@Test
	void validate_VehicleIneligible() {
		Map<String, InsuranceResult> insuranceResultPerLine = vehicleInsuranceUtils.validateVehicleInsuranceNotIneligible(null, createMap());
		
		assertEquals(InsuranceScoreResult.INELIGIBLE, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(-1, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
	}
	
	
	@Test
	void validate_VehicleYearIsNull() {
		Map<String, InsuranceResult> insuranceResultPerLine = vehicleInsuranceUtils.validateVehicleProductionDate(null, createMap());
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(0, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
	}
	
	@Test
	void validate_VehicleYearIsNotNullPastFiveYears() {
		
		VehicleDTO vehicle = new VehicleDTO();
		
		vehicle.setYear(2015);
		Map<String, InsuranceResult> insuranceResultPerLine = vehicleInsuranceUtils.validateVehicleProductionDate(vehicle, createMap());
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(0, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
	}
	
	@Test
	void validate_VehicleYearIsNotNullLastFiveYears() {
		
		VehicleDTO vehicle = new VehicleDTO();
		
		vehicle.setYear(2018);
		Map<String, InsuranceResult> insuranceResultPerLine = vehicleInsuranceUtils.validateVehicleProductionDate(vehicle, createMap());
		
		assertEquals(InsuranceScoreResult.ECONOMIC, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getFinalScore());
		assertEquals(1, insuranceResultPerLine.get(InsuranceLines.AUTO_LINE).getNumericalScore());
	}
	
	private Map<String, InsuranceResult> createMap(){
		 Map<String, InsuranceResult> insuranceResultPerLine = new HashMap<>();
		 insuranceResultPerLine.put(InsuranceLines.AUTO_LINE, new InsuranceResult(InsuranceScoreResult.ECONOMIC,0));
		 
		 return insuranceResultPerLine;
		 
	}

}
