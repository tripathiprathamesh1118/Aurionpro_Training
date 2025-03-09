package com.aurionpro.behavioral.observer.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.behavioral.observer.model.Bike;
import com.aurionpro.behavioral.observer.model.Car;
import com.aurionpro.behavioral.observer.model.IVehicle;
import com.aurionpro.behavioral.observer.model.TrafficLight;
import com.aurionpro.behavioral.observer.model.Truck;

public class IVehicleMain {
	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		TrafficLight trafficLight = new TrafficLight();

		List<IVehicle> vehicles = new ArrayList<>();

		while (true) {
			System.out.println("\nEnter vehicle type: ");
			System.out.println("enter Exit to stop: ");

			String vehicleType = userInput.nextLine();

			if (vehicleType.equalsIgnoreCase("exit")) {
				break;
			}
			if (!vehicleType.equalsIgnoreCase("car") && !vehicleType.equalsIgnoreCase("bike")
					&& !vehicleType.equalsIgnoreCase("truck")) {
				System.out.println("Invalid Vehicle");
				continue;
			}

			System.out.println("Enter " + vehicleType + " name: ");
			String vehicleName = userInput.nextLine();

			if (vehicleName.isEmpty()) {
				System.out.println(vehicleType + " should have a brand");
				continue;
			}

			IVehicle vehicle = null;
			if (vehicleType.equalsIgnoreCase("Car")) {
				vehicle = new Car(vehicleName);
			} else if (vehicleType.equalsIgnoreCase("Bike")) {
				vehicle = new Bike(vehicleName);
			} else if (vehicleType.equalsIgnoreCase("Truck")) {
				vehicle = new Truck(vehicleName);
			} else {
				System.out.println("Error");
			}

			vehicles.add(vehicle);
			trafficLight.addObserver(vehicle);
			System.out.println(vehicleName + " entered in traffic");

		}
		trafficLight.changeLight("Yellow");
		trafficLight.changeLight("Red");
		trafficLight.changeLight("Green");

		userInput.close();
	}
}
