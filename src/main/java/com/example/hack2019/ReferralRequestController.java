package com.example.hack2019;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferralRequestController {

	@Autowired
	private DataSource dataSource;
	  
	@RequestMapping(method = RequestMethod.POST, value = "/referralrequests")
	public void processReferralRequest(@RequestBody ReferralRequest request) throws Exception{
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"insert into treatmentrequests(patient_url, location, specialty, startdate, enddate, network, gender, age) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, request.getPatientURL());
			ps.setString(2, request.getLocationZip());
			ps.setString(3, request.getSpecialty());
			ps.setDate(4, (java.sql.Date) request.getStartDate());
			ps.setDate(5, (java.sql.Date) request.getEndDate());
			ps.setString(6, request.getNetwork());
			ps.setString(7, request.getGender());
			ps.setInt(8, request.getAge());
			
			ps.execute();
			
		} catch (Exception e) {
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/referralrequests")
	public List<ReferralRequest> getReferralRequests(@RequestParam(value="zipcode") String zipcode,
			@RequestParam(value="specialty") String specialty,
			@RequestParam(value="availabledate") @DateTimeFormat(pattern="yyyy-MM-dd") Date availabledate,
			@RequestParam(value="network") String network,
			@RequestParam(value="gender") String gender,
			@RequestParam(value="ageRange") String ageRange
			){
		ArrayList<ReferralRequest> requests = new ArrayList<ReferralRequest>();
		try (Connection connection = dataSource.getConnection()) {
			
			String[] ages = ageRange.split(":");
			
			PreparedStatement ps = connection.prepareStatement(
					"select id, patient_url, location, specialty, startdate, enddate, network, gender, age "
					+ "from treatmentrequests "
					+ "where location=? AND specialty=? AND network=? and gender=? and age between ? and ? and "
					+ "? between startdate and enddate");
			ps.setString(1, zipcode);
			ps.setString(2, specialty);
			ps.setString(3, network);
			ps.setString(4, gender);
			ps.setInt(5, Integer.parseInt(ages[0]));
			ps.setInt(6, Integer.parseInt(ages[1]));
			ps.setDate(7, (java.sql.Date) availabledate);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ReferralRequest request = new ReferralRequest(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), 
						rs.getString(7), rs.getString(8), rs.getShort(9));

				requests.add(request);
			}
			
		} catch (Exception e) {
		}
		
		return requests;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/referralrequests/{id}/accept")
	public void acceptReferralRequest(@PathVariable int id, @RequestParam(value="provider_url") String provider_url) throws Exception{
		try (Connection connection = dataSource.getConnection()) {

			PreparedStatement ps = connection.prepareStatement("UPDATE treatmentrequest SET provider_url=? WHERE id=?");
			ps.setString(1, provider_url);
			ps.setInt(2, id);
			
			ps.execute();
			
		} catch (Exception e) {
		}
	}
}
