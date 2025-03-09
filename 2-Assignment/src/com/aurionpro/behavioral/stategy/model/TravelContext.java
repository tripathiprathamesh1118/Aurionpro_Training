package com.aurionpro.behavioral.stategy.model;

public class TravelContext {
	private ITravelStrategy travelStrategy;

	public void setTravelStrategy(ITravelStrategy travelStrategy) {
		this.travelStrategy = travelStrategy;
	}

	public void executeTravel(String source, String destination, double fare) {
		if (travelStrategy == null) {
			System.out.println("Error: Travel mode not selected.");
			return;
		}
		travelStrategy.travel(source, destination, fare);
	}
}