package com.aurionpro.creation.builder.model;

public interface ISmartphoneBuilder {
	void buildOperatingSystem();

	void buildScreenSize(String screenSize);

	void buildStorage(String storage);

	void buildBattery(String battery);

	Smartphone getSmartphone();
}