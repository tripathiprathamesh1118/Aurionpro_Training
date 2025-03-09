package com.aurionpro.behavioral.state.test;

import com.aurionpro.behavioral.state.model.TrafficLight;

public class IStateMain {

	public static void main(String[] args) throws Exception {
		TrafficLight trafficLight = new TrafficLight();
		
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
		trafficLight.changeLight();
	}
}
