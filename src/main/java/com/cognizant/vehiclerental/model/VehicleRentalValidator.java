package com.cognizant.vehiclerental.model;

import com.cognizant.vehiclerental.exception.VehicleRentalServiceException;


public class VehicleRentalValidator {

	private final static String INVALID_INPUT = "Invalid Input";
	private final static String INVALID_VEHICLE_TYPE = "Invalid vehicle type";
	private final static String INVALID_AC_FLAG = "Invalid AC Flag";
	private final static String INVALID_PASSENGER_NUMBER = "Invalid passenger numbers";
	
	public static boolean validateCalculateCost(Vehicle vehicle) {
		if (isBlank(vehicle.getVehicleName()) || isBlank(vehicle.getVehicleType()) || isBlank(vehicle.getAcFlag())
				|| isBlank(vehicle.getRouteDetails()) || isBlank(vehicle.getPassengerNumber())) {
			throw new VehicleRentalServiceException(INVALID_INPUT);
		}

		if(!vehicle.getVehicleType().equalsIgnoreCase("Diesel") && !vehicle.getVehicleType().equalsIgnoreCase("Petrol")){
			throw new VehicleRentalServiceException(INVALID_VEHICLE_TYPE);
		}
		
		if(!vehicle.getAcFlag().equalsIgnoreCase("true") && !vehicle.getAcFlag().equalsIgnoreCase("false")){
			throw new VehicleRentalServiceException(INVALID_AC_FLAG);
		}
		try{
				new Integer(vehicle.getPassengerNumber());
		}catch(Exception e){
			throw new VehicleRentalServiceException(INVALID_PASSENGER_NUMBER);
		}
		return true;
	}
	
	private static boolean isBlank(String arg){
		if(null!= arg && !arg.isEmpty()){
			return false;
		}
		return true;
	}
}
