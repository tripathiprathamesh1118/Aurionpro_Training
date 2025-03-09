package com.aurionpro.behavioral.state.model;

public class GreenState implements IState {

	@Override
	public void handleRequest(TrafficLight trafficLight) throws Exception {
	try {
		
			System.out.println("Signal is green, move your vehicle");

			trafficLight.setState(new YellowState());

			Thread.sleep(3000);
		}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
