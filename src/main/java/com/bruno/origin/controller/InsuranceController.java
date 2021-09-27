package com.bruno.origin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruno.origin.model.dto.insurance.request.InsuranceRequestDTO;
import com.bruno.origin.model.dto.insurance.response.InsuranceResponseDTO;
import com.bruno.origin.service.InsuranceService;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;

	@GetMapping(value = "/calculate")
	public ResponseEntity<InsuranceResponseDTO> calculateInsuranceScore(
			@RequestBody InsuranceRequestDTO insuranceCostumerData) {

		return ResponseEntity.ok().body(insuranceService.calculateInsuranceScore(insuranceCostumerData));
	}
}
