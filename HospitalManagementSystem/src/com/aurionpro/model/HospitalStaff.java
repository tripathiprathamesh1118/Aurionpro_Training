package com.aurionpro.model;

abstract public class HospitalStaff {
	private int staffId;
	private String name;
	private String department;

	public HospitalStaff(int staffId, String name, String department) {
		this.staffId = staffId;
		this.name = name;
		this.department = department;
	}

	public abstract void work();

	public int getStaffId() {
		return staffId;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		return "\nStaff ID: " + staffId + "\nName: " + name + "\nDepartment: " + department;
	}
}