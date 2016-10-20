package com.cognizant.vehiclerental.service;

import org.springframework.stereotype.Component;

@Component("vehicle")
public class SwiftCar implements VehicleInterface {

	private static final Integer MAX_PASSENGERS = 4;
	private static final String VEHICLE_NAME = "SwiftCar";
	
	@Override
	public int maxNumberOfPassengers() {
		return MAX_PASSENGERS;
	}

	@Override
	public String vehicleName() {
		return VEHICLE_NAME;
	}

}
