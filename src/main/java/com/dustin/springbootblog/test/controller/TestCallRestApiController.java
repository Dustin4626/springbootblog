package com.dustin.springbootblog.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * 	範例Call 3party function
 * 1.WebConfig register RestTemplate in spring container
 * 2.and call RestTemplate getForObject function
 * 
 * @author dustinxie
 *
 */
@Controller
@RequestMapping("/admin")
public class TestCallRestApiController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/testRestAPI")
	@ResponseBody
	public String testRestAPI() {
		String result = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", String.class);
		return result;
	}
	
	@GetMapping("/testRestAPI2")
	@ResponseBody
	public String testRestAPI2() {
		String result = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api", String.class);
		return result;
	}
}
