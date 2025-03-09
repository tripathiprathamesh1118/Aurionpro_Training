package com.aurionpro.behavioral.observer.model;

public class Truck implements IVehicle {

	private String truckName;

	public Truck(String truckName) {
		this.truckName = truckName;
	}

	public String getTruckName() {
		return truckName;
	}

	@Override
	public void update(String lightColor) {
		if (lightColor.equalsIgnoreCase("Red")) {
			System.out.println(truckName + " stops at red signal");
		}
			else if (lightColor.equalsIgnoreCase("Yellow")){
				System.out.println(truckName + " slows down at yellow signal");
			}
			else if(lightColor.equalsIgnoreCase("Green")){
				System.out.println(truckName + " moves forward at green signal");
			}
			else {
				System.out.println("Error");
			}
		
	}

}
