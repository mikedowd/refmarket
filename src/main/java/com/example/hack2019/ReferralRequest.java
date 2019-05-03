package com.example.hack2019;

import java.util.Date;

public class ReferralRequest {

	private final int id; 
	private final String patient_url;
	private final String locationZip;
	private final String specialty;
	private final Date startDate;
	private final Date endDate;
	private final String network;
	private final String gender;
	private final short age;

	public ReferralRequest(int id, String patient_url, String locationZip, String specialty, Date startDate, Date endDate,
			String network, String gender, short age) {
		super();
		this.id = id;
		this.patient_url = patient_url;
		this.locationZip = locationZip;
		this.specialty = specialty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.network = network;
		this.gender = gender;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPatientURL() {
		return patient_url;
	}
	public String getLocationZip() {
		return locationZip;
	}
	public String getSpecialty() {
		return specialty;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getNetwork() {
		return network;
	}
	public String getGender() {
		return gender;
	}
	public short getAge() {
		return age;
	}
	
}
