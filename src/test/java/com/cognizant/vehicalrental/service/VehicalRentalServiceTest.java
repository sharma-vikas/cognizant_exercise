package com.cognizant.vehicalrental.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cognizant.vehiclerental.exception.VehicleRentalServiceException;
import com.cognizant.vehiclerental.model.Vehicle;
import com.cognizant.vehiclerental.service.RouteService;
import com.cognizant.vehiclerental.service.RouteServiceImpl;
import com.cognizant.vehiclerental.service.SwiftCar;
import com.cognizant.vehiclerental.service.VehicleInterface;
import com.cognizant.vehiclerental.service.VehicleRentalService;
import com.cognizant.vehiclerental.service.VehicleRentalServiceImpl;

public class VehicalRentalServiceTest {

	private VehicleRentalService vehicalRentalService;
	private Vehicle vehicle;
	private VehicleInterface vehicleInterface;
	private RouteService routeService;
	
	private static final String VEHICLE_NAME = "Swiftcar";
	private static final String VEHICLE_BUS_NAME = "Bus";
	private static final String VEHICLE_TYPE_DIESEL = "DIESEL";
	private static final String VEHICLE_TYPE_PETROL = "Petrol";
	private static final String AC_FLAG_FALSE = "false";
	private static final String AC_FLAG_TRUE = "true";
	private static final String ROUTE_DETAILS = "Pune-Bangalore-Mysore-Pune";
	private static final String PASSENGER_NUMBER = "4";
	private static final String PASSENGER_NUMBER_MAX = "6";
	
	private final static String INVALID_INPUT = "Invalid Input";
	private final static String INVALID_VEHICLE_TYPE = "Invalid vehicle type";
	private final static String INVALID_AC_FLAG = "Invalid AC Flag";
	private final static String INVALID_PASSENGER_NUMBER = "Invalid passenger numbers";
	
	@Before
	public void setUp() throws Exception {
		vehicalRentalService = new VehicleRentalServiceImpl();
		vehicleInterface = new SwiftCar();
		routeService = new RouteServiceImpl();
		vehicle = new Vehicle(VEHICLE_NAME, VEHICLE_TYPE_DIESEL, AC_FLAG_FALSE, ROUTE_DETAILS,PASSENGER_NUMBER);
		
		vehicalRentalService.setVehicleInterface(vehicleInterface);
		vehicalRentalService.setRouteService(routeService);
	}
	
	@Test
	public void testCalculateCostSuccess()  {
		
		BigDecimal cost = vehicalRentalService.calculateCost(vehicle);
		
		Assert.assertEquals(2800.0, cost.doubleValue(),0.0);
	}
	
	@Test
	public void testCalculateCostValidationFailure()  {
		
		vehicle.setVehicleType("");
		try{
			vehicalRentalService.calculateCost(vehicle);
			Assert.fail("Control shouldn't reach here");
		}catch(VehicleRentalServiceException vrse){
			Assert.assertEquals(INVALID_INPUT,vrse.getMessage());
		}
		
		vehicle.setVehicleType(INVALID_VEHICLE_TYPE);
		try{
			vehicalRentalService.calculateCost(vehicle);
			Assert.fail("Control shouldn't reach here");
		}catch(VehicleRentalServiceException vrse){
			Assert.assertEquals(INVALID_VEHICLE_TYPE,vrse.getMessage());
		}

		vehicle.setVehicleType(VEHICLE_TYPE_DIESEL);
		vehicle.setAcFlag(INVALID_AC_FLAG);
		try{
			vehicalRentalService.calculateCost(vehicle);
			Assert.fail("Control shouldn't reach here");
		}catch(VehicleRentalServiceException vrse){
			Assert.assertEquals(INVALID_AC_FLAG,vrse.getMessage());
		}
		
		vehicle.setAcFlag(AC_FLAG_FALSE);
		vehicle.setPassengerNumber(INVALID_PASSENGER_NUMBER);
		try{
			vehicalRentalService.calculateCost(vehicle);
			Assert.fail("Control shouldn't reach here");
		}catch(VehicleRentalServiceException vrse){
			Assert.assertEquals(INVALID_PASSENGER_NUMBER,vrse.getMessage());
		}
	}
	
	@Test
	public void testCalculateCostACFlag()  {
		
		vehicle.setAcFlag(AC_FLAG_TRUE);
		
		BigDecimal cost = vehicalRentalService.calculateCost(vehicle);
		
		Assert.assertEquals(3200.0, cost.doubleValue(),0.0);
	}
	
	@Test
	public void testCalculateCostBus()  {
		
		vehicle.setVehicleName(VEHICLE_BUS_NAME);
		
		BigDecimal cost = vehicalRentalService.calculateCost(vehicle);
		
		Assert.assertEquals(2744.0, cost.doubleValue(),0.0);
	}
	
	@Test
	public void testCalculateCostPassengerNumber()  {
		
		vehicle.setPassengerNumber(PASSENGER_NUMBER_MAX);
		
		BigDecimal cost = vehicalRentalService.calculateCost(vehicle);
		
		Assert.assertEquals(3200.0, cost.doubleValue(),0.0);
	}
	
	@Test
	public void testCalculateCostPetrol()  {
		
		vehicle.setVehicleType(VEHICLE_TYPE_PETROL);
		
		BigDecimal cost = vehicalRentalService.calculateCost(vehicle);
		
		Assert.assertEquals(3000.0, cost.doubleValue(),0.0);
	}
}
