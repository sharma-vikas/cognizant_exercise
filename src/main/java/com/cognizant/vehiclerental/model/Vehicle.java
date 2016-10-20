package com.cognizant.vehiclerental.model;

public class Vehicle {

	private String vehicleName;
	private String vehicleType;
	private String acFlag;
	private String routeDetails;
	private String passengerNumber;

	public Vehicle(String vehicleName, String vehicleType, String acFlag,
			String routeDetails, String passengerNumber) {
		this.vehicleName = vehicleName;
		this.vehicleType = vehicleType;
		this.acFlag = acFlag;
		this.routeDetails = routeDetails;
		this.passengerNumber = passengerNumber;
	}
	
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getAcFlag() {
		return acFlag;
	}
	public void setAcFlag(String acFlag) {
		this.acFlag = acFlag;
	}
	public String getRouteDetails() {
		return routeDetails;
	}
	public void setRouteDetails(String routeDetails) {
		this.routeDetails = routeDetails;
	}
	public String getPassengerNumber() {
		return passengerNumber;
	}
	public void setPassengerNumber(String passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	
}
