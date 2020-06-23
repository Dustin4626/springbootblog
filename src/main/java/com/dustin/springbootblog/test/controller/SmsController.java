package com.dustin.springbootblog.test.controller;

import javax.validation.metadata.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class SmsController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/sms")
	@ResponseBody
	public String callApiExample() {
		final String uri = "http://api.message.net.tw/send.php?id={id}&password={password}&tel={tel}&msg={msg}&mtype={mtype}&encoding={encoding}";
		String result = restTemplate.getForObject(uri, String.class);
//		String result = restTemplate.getForObject(uri, String.class,"","","","test 發票","G","utf8");
		System.out.println(result);
		return result;
//		restTemplate.exchange(uri, HttpMethod.GET, null, String.class, ) 
//		ResponseEntity<List<POJO>> responseEntity = restTemplate.exchange(
//			    URL, 
//			    HttpMethod.GET, 
//			    null, 
//			    new ParameterizedTypeReference<List<POJO>>() {
//			    });
//		List<POJO> pojoObjList = responseEntity.getBody();
	}
}
