package com.cognizant.vehiclerental.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cognizant.vehiclerental.api.VehicleRentalRestApi;
import com.cognizant.vehiclerental.model.Vehicle;
import com.cognizant.vehiclerental.model.VehicleRentalValidator;

@Component("vehicleRentalService")
public class VehicleRentalServiceImpl implements VehicleRentalService{

	private static final Integer PETROL_COST = 15;
	private static final Integer DIESEL_COST = 14;
	private static final Double BUS_DISCOUNT = 0.02;
	private static final Integer AC_COST = 2;
	private static final Integer ADDITIONAL_COST_PASSENGER = 1;
	
	@Autowired
	@Qualifier("vehicle")
	private VehicleInterface vehicleInterface;
	
	@Autowired
	@Qualifier("routeService")
	private RouteService routeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRentalRestApi.class);
	
	@Override
	public BigDecimal calculateCost(Vehicle vehicle) {

		LOGGER.info("Start VehicleRentalServiceImpl:: calculateCost() ");
		
		BigDecimal totalCost; 
		
		BigDecimal costPerKm;
		
		VehicleRentalValidator.validateCalculateCost(vehicle);
		
		if(vehicle.getVehicleType().equalsIgnoreCase("Petrol")){
			costPerKm = new BigDecimal(PETROL_COST);
			
		}else{
			costPerKm = new BigDecimal(DIESEL_COST);
		}
		
		if(vehicle.getAcFlag().equalsIgnoreCase("true")){
			costPerKm = costPerKm.add(new BigDecimal(AC_COST));
		}
		
		if(vehicle.getVehicleName().equalsIgnoreCase("Bus")){
			costPerKm = costPerKm.subtract(costPerKm.multiply(new BigDecimal(BUS_DISCOUNT)));
			costPerKm = costPerKm.setScale(2,RoundingMode.HALF_UP);
		}
		
		if(Integer.valueOf(vehicle.getPassengerNumber()) > vehicleInterface.maxNumberOfPassengers()){
			int diff = Integer.valueOf(vehicle.getPassengerNumber()) - vehicleInterface.maxNumberOfPassengers();
			int additionalCost = diff * ADDITIONAL_COST_PASSENGER;
			costPerKm = costPerKm.add(new BigDecimal(additionalCost));
		}
		
		totalCost = costPerKm.multiply(new BigDecimal(routeService.numberOfKms(vehicle.getRouteDetails())));
		
		LOGGER.info("End VehicleRentalServiceImpl:: calculateCost() " + totalCost);
		
		return totalCost;
	}

	public void setVehicleInterface(VehicleInterface vehicleInterface) {
		this.vehicleInterface = vehicleInterface;
	}

	public void setRouteService(RouteService routeService) {
		this.routeService = routeService;
	}

}
