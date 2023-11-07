package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	private static final String serviceKey ="0OhBU7ZCGIobDVKDeBJDpmDRqK3IRNF6jlf%2FJB2diFAf%2FfR2czYO9A4UTGcsOwppV6W2HVUeho%2FFPwXoL6DwqA%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "busroute.do" , produces = "application/json; charset=utf-8")
	public String tsunamiShelter() throws IOException {
		
		System.out.println("controll call");
		
		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
		url += "?serviceKey="+ serviceKey;
		url += "&strSrch=3";
		url += "&resultType=json";
		
		System.out.println(url);
		
		URL reqyestUrl = new URL(url);
		HttpURLConnection urlController = (HttpURLConnection) reqyestUrl.openConnection();
		urlController.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlController.getInputStream()));
		
		String responseText = "";
		String line ="";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlController.disconnect();
		
		return responseText;
	}
	







}
