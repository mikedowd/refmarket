package com.example.hack2019;

import java.util.Date;

public class ReferralRequest {

	private final long id; 
	private final String orgURL;
	private final String locationZip;
	private final String specialty;
	private final Date startDate;
	private final Date endDate;
	private final String network;
	private final String gender;
	private final short age;

	public ReferralRequest(long id, String orgURL, String locationZip, String specialty, Date startDate, Date endDate,
			String network, String gender, short age) {
		super();
		this.id = id;
		this.orgURL = orgURL;
		this.locationZip = locationZip;
		this.specialty = specialty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.network = network;
		this.gender = gender;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	
	public String getOrgURL() {
		return orgURL;
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
