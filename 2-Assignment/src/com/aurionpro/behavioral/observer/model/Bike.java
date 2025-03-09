package com.aurionpro.behavioral.observer.model;

public class Bike implements IVehicle {

	private String bikeName;

	public Bike(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getBikeName() {
		return bikeName;
	}

	@Override
	public void update(String lightColor) {
		if (lightColor.equalsIgnoreCase("Yellow")){
			System.out.println(bikeName + " slows down at yellow signal");
		}
		else if (lightColor.equalsIgnoreCase("Red")) {
			System.out.println(bikeName + " stops at red signal");
		}
			else if(lightColor.equalsIgnoreCase("Green")){
				System.out.println(bikeName + " moves forward at green signal");
			}
			else {
				System.out.println("Error");
			}
		
	}

}
