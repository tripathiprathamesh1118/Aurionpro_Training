package com.aurionpro.creation.builder.model;

public class AndroidSmartphoneBuilder implements ISmartphoneBuilder {
	private Smartphone smartphone;

	public AndroidSmartphoneBuilder() {
		this.smartphone = new Smartphone();
	}

	@Override
	public void buildOperatingSystem() {
		smartphone.setOperatingSystem("Android");
	}

	@Override
	public void buildScreenSize(String screenSize) {
		smartphone.setScreenSize(screenSize);
	}

	@Override
	public void buildStorage(String storage) {
		smartphone.setStorage(storage);
	}

	@Override
	public void buildBattery(String battery) {
		smartphone.setBattery(battery);
	}

	@Override
	public Smartphone getSmartphone() {
		return this.smartphone;
	}
}