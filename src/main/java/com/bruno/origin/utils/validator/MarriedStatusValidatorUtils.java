package com.bruno.origin.utils.validator;

import com.bruno.origin.enums.MarriageValidationEnum;
import com.bruno.origin.exception.WrongParametersException;

public class MarriedStatusValidatorUtils {

	public void validateMarriedStatus(String marriedStatus) {
		if (checkMarriedStatus(marriedStatus)) {
			throw new WrongParametersException("Married status invalid.");
		}
	}

	private boolean checkMarriedStatus(String marriedStatus) {
		return !marriedStatus.equals(MarriageValidationEnum.SINGLE.getMaritalStatus())
				&& !marriedStatus.equals(MarriageValidationEnum.MARRIED.getMaritalStatus());
	}
}
