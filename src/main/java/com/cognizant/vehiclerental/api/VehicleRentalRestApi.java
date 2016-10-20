package com.cognizant.vehiclerental.api;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cognizant.vehiclerental.model.Vehicle;
import com.cognizant.vehiclerental.service.VehicleRentalService;

@Path("/vehicleRental")
@Component("vehicleRentalRestApi")
public class VehicleRentalRestApi {

	@Autowired
	@Qualifier("vehicleRentalService")
	private VehicleRentalService vehicleRentalService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRentalRestApi.class);
	
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/calculateCost/{vehicleName}/{vehicleType}")
	public BigDecimal calculateCost(@PathParam("vehicleName") String vehicleName,
			@PathParam("vehicleType") String vehicleType,@QueryParam("acFlag") String acFlag,
			@QueryParam("routeDetails") String routeDetails, @QueryParam("passengerNumber") String passengerNumber) {
		
		long startTime = System.currentTimeMillis();
		
		LOGGER.info("caculateCost() with parameters vehicleName " + vehicleName + " ,vehicleType " + vehicleType
				+ " ,acFlag " + acFlag + " ,routeDetails " + routeDetails
				+ " ,passengerNumber " + passengerNumber);
		
		LOGGER.info("********************* startTime== "+startTime);
		
		Vehicle vehicle = new Vehicle(vehicleName, vehicleType, acFlag, routeDetails, passengerNumber);
		
		BigDecimal cost = vehicleRentalService.calculateCost(vehicle);		
		
		LOGGER.info("calculated Cost == "+cost);
		LOGGER.info("calculateCost TIME TAKEN== "+(System.currentTimeMillis()-startTime));
		
		return cost;
	}
	
}
