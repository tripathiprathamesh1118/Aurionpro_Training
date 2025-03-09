package com.aurionpro.behavioral.stategy.model;

public class FlightTravel implements ITravelStrategy {
	@Override
	public void travel(String source, String destination, double fare) {
		System.out.println("Travelling by Flight from " + source + " to " + destination );
		System.out.println("Fare: " + fare);
	}
}