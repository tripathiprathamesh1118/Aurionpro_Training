package com.aurionpro.behavioral.observer.model;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {

	private String color;
	private List<IVehicle> vehicles = new ArrayList<>();

	public void addObserver(IVehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void removeObserver(IVehicle vehicle) {
		vehicles.remove(vehicle);
	}

	public void notifyObserver() {
		for (IVehicle vehicle : vehicles) {
			vehicle.update(color);
		}

	}
	
	public void changeLight(String color) {
		this.color = color;
		System.out.println("\nTraffic light changes to: "+ color);
		notifyObserver();
	}
}
