package com.bruno.origin.utils.validator;

import java.util.Objects;

import com.bruno.origin.enums.HouseValidationEnum;
import com.bruno.origin.exception.WrongParametersException;
import com.bruno.origin.model.dto.HouseDTO;

public class HouseValidatorUtils {

	public void validateHouseValues(HouseDTO houseData) {
		if (!Objects.isNull(houseData)
				&& validateOwnershipStatus(houseData.getOwnershipStatus())) {
			throw new WrongParametersException("House ownership status invalid.");
		}
	}

	private boolean validateOwnershipStatus(String ownershipStatus) {
		return !ownershipStatus.equals(HouseValidationEnum.OWNED.getOwnershipStatus()) && !ownershipStatus.equals(HouseValidationEnum.MORTGAGED.getOwnershipStatus());
	}
}
