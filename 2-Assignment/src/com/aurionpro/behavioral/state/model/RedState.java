package com.aurionpro.behavioral.state.model;

public class RedState implements IState{

	@Override
	public void handleRequest(TrafficLight trafficLight) throws Exception {
	try {
		
			System.out.println("Signal is Red, Stop your vehicle");

			trafficLight.setState(new GreenState());

			Thread.sleep(5000);
		}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}