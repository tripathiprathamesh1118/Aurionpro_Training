package com.aurionpro.model;


public class Guitar {
	private String serialNumber;
	private double price;
	private Builder builder;
	private String model;
	private com.aurionpro.model.Type type;
	private Wood backWood;
	private Wood topWood;

	public Guitar(String serialNumber, double price, Builder builder, String model, com.aurionpro.model.Type type2, Wood backWood,
			Wood topWood) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public Builder getBuilder() {
		return builder;
	}

	public String getModel() {
		return model;
	}

	public Type getType() {
		return type;
	}

	public Wood getBackWood() {
		return backWood;
	}

	public Wood getTopWood() {
		return topWood;
	}

}