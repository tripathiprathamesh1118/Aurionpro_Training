package com.aurionpro.behavioral.state.model;

public class YellowState  implements IState{

	@Override
	public void handleRequest(TrafficLight trafficLight) throws Exception {
	try {
		
			System.out.println("Signal is Yellow, slow your vehicle");

			trafficLight.setState(new BlinkyYellow());

			Thread.sleep(500);
		}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}