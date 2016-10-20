package com.cognizant.vehiclerental.service;

import java.math.BigDecimal;

import com.cognizant.vehiclerental.model.Vehicle;

public interface VehicleRentalService {

	public BigDecimal calculateCost(Vehicle vehicle);
	
	public void setVehicleInterface(VehicleInterface vehicleInterface);
	
	public void setRouteService(RouteService routeService); 
	
}
