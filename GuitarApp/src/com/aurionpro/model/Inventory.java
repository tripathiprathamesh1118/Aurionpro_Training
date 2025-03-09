package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
	private List<Guitar> guitars = new ArrayList<>();

	public void addGuitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood,
			Wood topWood) {
		guitars.add(new Guitar(serialNumber, price, builder, model, type, backWood, topWood));
	}

	public Guitar getGuitar(String serialNumber) {
		return guitars.stream().filter(g -> g.getSerialNumber().equals(serialNumber)).findFirst().orElse(null);
	}

	public List<Guitar> search(Guitar searchGuitar) {
		return guitars.stream()
				.filter(g -> searchGuitar.getBuilder() == Builder.ANY || g.getBuilder() == searchGuitar.getBuilder())
				.filter(g -> searchGuitar.getModel().isEmpty()
						|| g.getModel().equalsIgnoreCase(searchGuitar.getModel()))
				.filter(g -> searchGuitar.getType() == Type.ANY || g.getType() == searchGuitar.getType())
				.filter(g -> searchGuitar.getBackWood() == Wood.ANY || g.getBackWood() == searchGuitar.getBackWood())
				.filter(g -> searchGuitar.getTopWood() == Wood.ANY || g.getTopWood() == searchGuitar.getTopWood())
				.collect(Collectors.toList());
	}

}