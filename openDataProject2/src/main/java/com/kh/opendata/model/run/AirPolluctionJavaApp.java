package com.kh.opendata.model.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class AirPolluctionJavaApp {
	
	public static final String serviceKey = "EdAsSEjn2mlpNlX3lSjBPBCoRMo8ppubGcMSJbgiq94s2aBKpFUyw7I5GmqhCBCzQ%2Fq%2BMu455Jq0b8vLJRz3Cg%3D%3D";

	public static void main(String[] args) throws IOException {
		
		// 발급받은 인증키 정보 변수에 담아두기
		
		
		// OpenAPI 서버로 요청하고자 하는 URL만들기
		
		String url ="https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		//url += "?serviceKey="; // 서비스키가 제대로 부여되지 않았을 경우 => service_key_is_not_어쩌구 뜸!
		url += "?serviceKey="+serviceKey;
		url += "&sidoName=" + URLEncoder.encode("서울","utf-8"); // 요청시 전달값 중 한글이 있을 경우 encoding 해야함!!
		url += "&returnType=json";
		
//		System.out.println(url);
		
		// ** HttpURLConnection 객체를 활용해서 Open API 요청 절차 **
		// 1. 요청하고자 하는 url 전달하면서 java.net.URL 객체 생성
		URL requestUrl = new URL(url);
		
		// 2. 1번 과정으로 생성된 URL객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		// 3. 요청에 필요한 Header 설정하기
		urlConnection.setRequestMethod("GET");
		
		// 4. 해당 OpenAPI 서버로 요청 보낸 후 입력 스트림을 통해서 응답데이터를 읽어드리기
		BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()) );
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
//			System.out.println(line);
			responseText += line;
		}
		
		
//		System.out.println(responseText);
		
		// JSONObject, JSONArry, JSONElement 이용해서 파싱 할 수 있음(gson 라이브러리) => 내가 원하는 데이터만 추출 가능
		// 각각의 item 정보를 => AirVO 객체에 담고 => ArrayList에 차곡차곡 쌓기 작업
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response 속성 접근 => {} JsonObject
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body 속성 접근 => {} JsonObect
		
		
		int totalCount = bodyObj.get("totalCount").getAsInt(); // totalCount 속성 접근 => 40
		JsonArray itemArr = bodyObj.getAsJsonArray("items"); // items 속성 접근 => [] JsonArray
		
		ArrayList<AirVO> list = new ArrayList<AirVO>(); // []
		
		for(int i=0; i<itemArr.size();i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			AirVO air = new AirVO();
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setkhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
		}
		
//		System.out.println(itemArr); // totalCount 속성 접근 => 40 int
		
		for(AirVO a : list) {
			System.out.println(a);
		}
		
		// 5. 다 사용한 스트림 반납
		br.close();
		urlConnection.disconnect();
		
		
		
		
		
		
		
		
	}

}
