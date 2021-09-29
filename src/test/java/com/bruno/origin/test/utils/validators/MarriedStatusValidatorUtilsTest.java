package com.bruno.origin.test.utils.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.exception.WrongParametersException;
import com.bruno.origin.utils.validator.MarriedStatusValidatorUtils;

@ExtendWith(MockitoExtension.class)
class MarriedStatusValidatorUtilsTest {

	@InjectMocks
	private MarriedStatusValidatorUtils marriedStatusValidatorUtils;
	
	private static final String MARRIED_STATUS_ANY = "any";
	private static final String MARRIED_STATUS_SINGLE = "single";
	private static final String MARRIED_STATUS_MARRIED = "married";
	
	
	@Test
	void invalid_Married_Status() {
		
		assertThrows(WrongParametersException.class,() -> marriedStatusValidatorUtils.validateMarriedStatus(MARRIED_STATUS_ANY));
	}
	
	@Test
	void valid_Married_Status_Single() {
		marriedStatusValidatorUtils.validateMarriedStatus(MARRIED_STATUS_SINGLE);
	}
	
	@Test
	void valid_Married_Status_Married() {
		marriedStatusValidatorUtils.validateMarriedStatus(MARRIED_STATUS_MARRIED);
	}
	
}
