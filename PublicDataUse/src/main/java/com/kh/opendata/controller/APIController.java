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

	private static final String serviceKey ="EdAsSEjn2mlpNlX3lSjBPBCoRMo8ppubGcMSJbgiq94s2aBKpFUyw7I5GmqhCBCzQ%2Fq%2BMu455Jq0b8vLJRz3Cg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "busrote.do" , produces = "application/json; charset=utf-8")
	public String tsunamiShelter() throws IOException {
		
		System.out.println("controll call");
		
		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
		url += "?serviceKey="+ serviceKey;
		url += "&strSrch=3";
		url += "resultType=json";
		
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
