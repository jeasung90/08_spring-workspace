package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	
	private static final String serviceKey ="EdAsSEjn2mlpNlX3lSjBPBCoRMo8ppubGcMSJbgiq94s2aBKpFUyw7I5GmqhCBCzQ%2Fq%2BMu455Jq0b8vLJRz3Cg%3D%3D";
	
	/* json으로 할 때
	@ResponseBody
	@RequestMapping(value = "air.do", produces = "application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
		
		
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+ serviceKey;
		url += "&sidoName="+URLEncoder.encode(location , "UTF-8");
		url += "&returnType=json";
		url += "&numOfRows=50";
		
		// 1.
		URL requestUrl = new URL(url);
		// 2.
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		// 3.
		urlConnection.setRequestMethod("GET");
		// 4.
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText="";
		String line="";
		
		while((line = br.readLine()) != null) {
			responseText+= line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		
		return responseText;
	}
	*/
	
	// xml 할 떄
	@ResponseBody
	@RequestMapping(value = "air.do", produces = "text/xml; charset=utf-8")
	public String airPollution(String location) throws IOException {
		
		
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+ serviceKey;
		url += "&sidoName="+URLEncoder.encode(location , "UTF-8");
		url += "&returnType=xml";
		url += "&numOfRows=50";
		
		// 1.
		URL requestUrl = new URL(url);
		// 2.
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		// 3.
		urlConnection.setRequestMethod("GET");
		// 4.
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText="";
		String line="";
		
		while((line = br.readLine()) != null) {
			responseText+= line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value = "disater.do", produces = "text/xml; charset=utf-8")
	public String disasterShelter() throws IOException {
		
		String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		url += "?serviceKey="+serviceKey;
		url += "&numOfRows=20";
		url += "&type=xml";
		
		// 1.
		URL requestUrl = new URL(url);
		// 2.
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		// 3. 
		urlConnection.setRequestMethod("GET");
		// 4.
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText="";
		String line="";
		
		while((line = br.readLine()) != null) {
			responseText+= line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value = "info.do", produces = "application/json; charset=utf-8")
	public String graundInfo() throws IOException {
		
		String url = "https://apis.data.go.kr/1611000/nsdi/PossessionService/attr/getPossessionAttr";
		url += "?serviceKey="+ serviceKey;
		url += "&pnu=1111011000100010001";
		url += "&format=json";
		
		System.out.println(url);
		
		// 1. URL만들기
		URL requestUrl = new URL(url);
		// 2. HttpURLConnection 만들기 
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		// 3. 메소드 방식 정하기
		urlConnection.setRequestMethod("GET");
		// 4.  BufferedReader 맹글기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line = "";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value = "info.bo",produces = "text/xml; charset=utf-8" )
	public String ddangInfo(String text1) throws IOException {
		
		String url = "https://apis.data.go.kr/1611000/nsdi/PossessionService/attr/getPossessionAttr";
		url += "?serviceKey="+ serviceKey;
		url += "&pnu="+text1;
		url += "&format=xml";

		// 1. URL
		URL requestUrl = new URL(url);
		// 2. urlConnection
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		// 3. 메소드방식
		urlConnection.setRequestMethod("GET");
		// 4. buffer 어쩌구 
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText ="";
		String line ="";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		return responseText;
	}
	
	
}
