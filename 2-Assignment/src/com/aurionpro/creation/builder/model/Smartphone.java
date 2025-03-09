package com.aurionpro.creation.builder.model;

public class Smartphone {
	private String operatingSystem;
	private String screenSize;
	private String storage;
	private String battery;

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	@Override
	public String toString() {
		return "Operating System: " + operatingSystem + "\n" + "Screen Size: " + screenSize + "\n" + "Storage: "
				+ storage + "\n" + "Battery: " + battery;
	}
}