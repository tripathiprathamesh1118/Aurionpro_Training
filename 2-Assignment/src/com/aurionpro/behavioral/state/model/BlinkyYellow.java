package com.aurionpro.behavioral.state.model;

public class BlinkyYellow  implements IState{

	@Override
	public void handleRequest(TrafficLight trafficLight) throws Exception {
	try {
		
			System.out.println("Signal is BlinkYellow, meaning its about to change to red");

			trafficLight.setState(new RedState());

			Thread.sleep(100);
		}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}