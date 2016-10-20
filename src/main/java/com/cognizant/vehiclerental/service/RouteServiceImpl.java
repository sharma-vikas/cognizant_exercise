package com.cognizant.vehiclerental.service;

import org.springframework.stereotype.Component;

@Component("routeService")
public class RouteServiceImpl implements RouteService {

	private static final Integer KMS_NUMBER = 200;
	
	@Override
	public int numberOfKms(String routeDetails) {
		
		//Logic to calculate Kms based on provided routeDetails
		
		return KMS_NUMBER;
	}

}
