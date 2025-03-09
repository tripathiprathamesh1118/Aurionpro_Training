package com.aurionpro.behavioral.state.model;

public class TrafficLight {

	private IState currentState;

	public TrafficLight() {
		currentState = new YellowState();
	}

	public void setState(IState state) {
		this.currentState = state;
	}
	
	public void changeLight() throws Exception {
		currentState.handleRequest(this);
	}

}
