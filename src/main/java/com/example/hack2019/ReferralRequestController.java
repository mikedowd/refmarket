package com.example.hack2019;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferralRequestController {

	@RequestMapping(method = RequestMethod.POST, value = "/referralrequest")
	public void processReferralRequest(@RequestBody ReferralRequest request) throws Exception{
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/referralrequest")
	public List<ReferralRequest> getReferralRequest(){
		
		ReferralRequest rr = new ReferralRequest(1, "what", "94110", "exit", new Date(2019, 3, 2), new Date(2019, 3, 6), "network", "gender", (short) 18);
		ReferralRequest rr2 = new ReferralRequest(2, "what", "94110", "exit", new Date(2019, 3, 2), new Date(2019, 3, 6), "network", "gender", (short) 18);

		ArrayList<ReferralRequest> rrs = new ArrayList<ReferralRequest>();
		
		rrs.add(rr);
		rrs.add(rr2);
		
		return rrs;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/referralrequest/accept")
	public void acceptReferralRequest() throws Exception{
		
	}
}
