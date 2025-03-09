package com.aurionpro.behavioral.observer.model;

public class Car implements IVehicle {

	private String carName;

	public Car(String carName) {
		this.carName = carName;
	}

	public String getCarName() {
		return carName;
	}

	@Override
	public void update(String lightColor) {
		if (lightColor.equalsIgnoreCase("Red")) {
			System.out.println(carName + " stops at red signal");
		}
			else if (lightColor.equalsIgnoreCase("Yellow")){
				System.out.println(carName + " slows down at yellow signal");
			}
			else if(lightColor.equalsIgnoreCase("Green")){
				System.out.println(carName + " moves forward at green signal");
			}
			else {
				System.out.println("Error");
			}
		
	}

}
