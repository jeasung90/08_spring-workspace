package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;

@Controller
public class AjaxController {
	/*
	 *  HttpServletResponse 객체로 응답데이터 응답하기 (기존의 jsp, servlet 때 했던 Stream 이용한 방식)
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response)throws IOException {
		System.out.println(name);
		System.out.println(age);
		
		// 요청 처리를 위해 서비스 호출
		
		// 요청 처리가 다 됐다는 가정하에 요청한 그 페이지에 응답할 데이터가 있을 경우
		String responseData = "응답 문자열 : "+name + "은(는) " + age+ "살 입니다.";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
		
	}
	*/
	/*
	 * 2. 응답할 데이터를 문자열로 리턴
	 * 		=> HttpServletResponse 를 안쓸 수 있음
	 * 		단, 문자열 리턴하면 원래는 포워딩 방식이였음 => 응답뷰 인식해서 해당 뷰 페이지를 찾고 있음
	 * 		따라서 내가 리턴하는 문자열이 응답뷰가 아니라 응답데이터야 라는걸 선언하는
	 * 		어노테이션 @ResponseBody를 붙여야 함
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "ajax1.do", produces = "text/html; charset=utf-8")
	 * public String ajaxMethod1(String name, int age) { String responseData =
	 * "응답 문자열 : "+name + "은(는) " + age+ "살 입니다."; return responseData; }
	 */
	
	// 다수의 응답데이터가 있을 경우??
	
	
	/*
	@RequestMapping(value = "ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 요청처리가 다 됐다는 가정하에 데이터 응답
		
		
		  //response.setContentType("text/html; charset=UTF-8");
		  //response.getWriter().print(name); response.getWriter().print(age);
		 // 연이어 출력하는 데이터가 하나의 문자열로 연이어져 있음
		
		// JSON(JavaScript Object Notation) 형태로 담아서 응답
		// JSONArray => [값, 값, 값, ... ]   	=> 자바에서의 ArrayList와 유사 (list에 추가할때는 add)
		// JSONObejct => {키:값, 키:값,....} 	=> 자바에서의 HashMap과 유사  (map에 추가할때는 put)
		
		
		// 첫번째 방법. JSONArray로 담아서 응답
		//JSONArray jArr = new JSONArray();
		//jArr.add(name); // ["이름"]
		//jArr.add(age); // ["이름","나이"]
		
		
		// 두번째 방법. JSONObject로 담아서 응답
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name); // {name:"이름"}
		jObj.put("age", age); // {name:"이름",age:"나이"}
		
		
		response.setContentType("application/json; charset=UTF-8"); // 반드시 해줘야함
		response.getWriter().print(jObj);
	}
	*/
	
	@ResponseBody
	@RequestMapping(value = "ajax1.do",produces = "application/json; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name); // {name:"이름"}
		jObj.put("age", age); // {name:"이름",age:나이}
		
		return jObj.toJSONString(); // "{name:"이름",age:나이}"
	}
	
	/*
	@ResponseBody
	@RequestMapping(value = "ajax2.do",produces = "application/json; charset=UTF-8")
	public String ajaxMethod2() {
		
//		Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "딱대좌", 33, "010111111");
		
		// JSON형태로 만들어서 응답
		JSONObject jObj	= new JSONObject();
		
		jObj.put("userId", m.getUserId());
		jObj.put("userName", m.getUserName());
		jObj.put("age", m.getAge());
		jObj.put("phone", m.getPhone());
		
		return jObj.toJSONString();
	}
	*/
	
	@ResponseBody
	@RequestMapping(value = "ajax2.do",produces = "application/json; charset=UTF-8")
	public String ajaxMethod2() {
		
//		Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "딱대좌", 33, "010111111");
		
		return new Gson().toJson(m); // {userId: 'user01', userPwd: 'pass01', userName: '딱대좌', age: 33, phone: '010111111'}
	}
	
	@ResponseBody
	@RequestMapping(value = "ajax3.do" ,produces = "application/json; charset=UTF-8")
	public String ajaxMethod3() {
//		ArrayList<Member> list = mService.selectList();
		
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("user01", "pass01", "딱대좡", 33, "01012315462"));
		list.add(new Member("user02", "pass02", "김시연", 30, "01022315462"));
		list.add(new Member("user03", "pass03", "김시딱", 29, "01092315462"));
		
		return new Gson().toJson(list);
	}
	
	
	
	
	
	
	
	
	
}
