package com.bruno.origin.test.utils.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bruno.origin.exception.WrongParametersException;
import com.bruno.origin.model.dto.HouseDTO;
import com.bruno.origin.utils.validator.HouseValidatorUtils;

@ExtendWith(MockitoExtension.class)
class HouseValidatorUtilsTest {
	
	@InjectMocks
	private HouseValidatorUtils houseValidatorUtils;
	
	private static final String OWNERSHIP_STATUS_ANY = "any";
	private static final String OWNERSHIP_STATUS_MORTGAGED = "mortgaged";
	private static final String OWNERSHIP_STATUS_OWNED = "owned";
	
	
	@Test
	void emptyHouse_thowsException() {
		
		HouseDTO house = new HouseDTO();
		
		house.setOwnershipStatus(OWNERSHIP_STATUS_ANY);
		
		assertThrows(WrongParametersException.class,() -> houseValidatorUtils.validateHouseValues(house));
	}
	
	@Test
	void validHouse_Owned() {
		
		HouseDTO house = new HouseDTO();
		
		house.setOwnershipStatus(OWNERSHIP_STATUS_OWNED);
		
		houseValidatorUtils.validateHouseValues(house);
	}
	
	@Test
	void validHouse_Mortgaged() {
		
		HouseDTO house = new HouseDTO();
		
		house.setOwnershipStatus(OWNERSHIP_STATUS_MORTGAGED);
		
		houseValidatorUtils.validateHouseValues(house);
	}
	
	@Test
	void emptyHouse() {
		
		houseValidatorUtils.validateHouseValues(null);
	}

}
