package com.aurionpro.model;

public class Nurse extends HospitalStaff {
	private String shiftTimings;

	public Nurse(int staffId, String name, String department, String shiftTimings) {
		super(staffId, name, department);
		this.shiftTimings = shiftTimings;
	}

	@Override
	public void work() {
		System.out.println("Nurse " + getName() + " serving in " + shiftTimings + " shift.");
	}

	@Override
	public String toString() {
		return super.toString() + "\nShift Timings: " + shiftTimings;
	}
}