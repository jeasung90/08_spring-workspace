package com.kh.spring.member.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

/*
 * 2번과 4번 주로 씀
 */


@Controller // Controller 타입의 어노테이션을 붙여주면 빈스캐닝 통해 자동으로 빈으로 등록
public class MemberController {
	
	
//	private MemberServiceImpl mService = new MemberServiceImpl();
	
	@Autowired // DI(Dependency Injection) 특징
	private MemberServiceImpl mService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptpasswordEncoder;
	
	/*
	 *  * 요청 처리 후 응답페이지로 포워딩 또는 url 재요청, 응답데이터 담는 방법
	 *  
	 *  1. 스프링에서 제공하는 Model객체를 사용하는 방법
	 *  	포워딩 할 뷰로 전달하고자 하는 데이터를 맵형식(key-value)로 담을 수 있는 영역
	 *  	Model 객체는 requestScope 이다
	 *  	단, setAttribute가 아닌 addAttribute 메소드 이용
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m, Model model,HttpSession session) {
		
		
		Member loginMember = mService.loginMember(m);
		
		if(loginMember == null) { // 로그인 실패 => 에러페이지(/WEB-INF/views/common/errorPage.jsp)로 포워딩
			model.addAttribute("errorMsg","로긴실패 앙");
			return "common/errorPage";
		}else {// 로그인 성공 => loginMember를 sessionScope에 담고 메인페이지 url 재용청
			session.setAttribute("loginMember", loginMember);
			return "redirect:/";
		}
	}
	*/
	/*
	@RequestMapping(value = "login.me") // RequestMapping 타입의 어노테이션을 붙여줌으로써 HandleMapping 등록 
	// 중복되면 앙댐
	public void loginMember() {
		
	}
	
	public void insertMember() {
		
	}
	
	public void updateMember() {
		
	}
	*/
	
	/*
	 * * 파라미터(요청시 전달값)를 받는 방법 (요청시 전달되는 값들 처리방법) 
	 * 
	 * 1. HttpServletReques를 이용해서 전달받기 (기존의 jsp/servlet방식)
	 * 	 해당 메소드의 매개변수로 HttpServletRequest를 작성해두면
	 * 	 스프링컨테이너가 해당 메소드 호출시(실행시) 자동으로 해당객체를 생성해서 인자로 주입해줌
	 * 
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(HttpServletRequest reques) {
		String userId = reques.getParameter("id");
		String userPwd = reques.getParameter("pwd");
		
		System.out.println("id : "+userId);
		System.out.println("pwd : "+userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 2. @RequstParam 어노테이션을 이용하는 방법
	 * 		requst.getParameter("키") : 벨류의 영괗을 대신해주는 어노테이션
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember( @RequestParam(value = "id" ,defaultValue = "aa") String userId,
								@RequestParam(value = "pwd") String userPwd) {
		
		System.out.println("id : "+userId);
		System.out.println("pwd : "+userPwd);
		
		return "main";
	}
	*/
	/*
	 * 3. @RequsetParam 어노테이션을 생략하는 방법
	 * 			** 단, 매개변수명 name값(요청시 전달값의 키값)과 동일하게 세팅해둬야 자동으로 값이 주입됨
	 * 	
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(String id, String pwd) {

		
		
		System.out.println("id : "+id);
		System.out.println("pwd : "+pwd);
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		
		// Service쪽 메소드에 m을 전달하며 조회
		
		return "main";
	}
	*/
	/*
	 *  4. 커맨드 객체 방식
	 *  해당 메소드 매개변수로
	 *  요청시 전달값을 담고자 하는 vo클래스 타입을 셋팅 후
	 *  요청시 전달값의 키값 (name값)을 vo클래스에 담고자 하는 필드명으로 작성
	 *  
	 *  스프링컨테이너가 해당 객체를 기본생성자로 생ㅅ어 후 setter 메소드 찾아서
	 *  요청시 전달밗을 해당 필드에 담아주는 내부적인 원리
	 *  
	 *  ** 반드시 name속성값(키값)과 담고자 하는 필드명 동일해야됨!!
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m) {

		System.out.println("id : "+m.getUserId());
		System.out.println("pwd : "+m.getUserPwd());
		return "main";
	}
	*/
	
	
	
	/*
	 *  2. 스프링에서 제공하는 MdelAndView 객체를 이용하는 방법
	 *  
	 *  Model은 데이터를 key-value 세트로 담을 수 있는 공간이라고 한다면
	 *  View는 응답뷰에 대한 정보를 담을 수 있는 공산
	 */
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m,HttpSession session, ModelAndView mv) {
		
		
//		암호화 작업 전
//		Member loginMember = mService.loginMember(m);
//		
//		if(loginMember == null) { // 로그인 실패 => 에러페이지(/WEB-INF/views/common/errorPage.jsp)로 포워딩
//			mv.addObject("errorMsg","로긴실패 앙");
//			mv.setViewName("common/errorPage");
//		}else {// 로그인 성공 => loginMember를 sessionScope에 담고 메인페이지 url 재용청
//			session.setAttribute("loginMember", loginMember);
//			mv.setViewName("redirect:/");
//		}
//		
//		return mv;
		
		// 암호화 작업 후
		// Member m userId 필드 : 사용자가 입력한 아이디
		// 		    userPwd 필드 : 사용자가 입력한 비번(평문)
		
		Member loginMember = mService.loginMember(m);
		// loginMember : 오로지 아이디만을 가지고 조회된 회원
		// loginMember userPwd 필드 : DB에 기록된 비번(암호문)
		
		if(loginMember != null && bcryptpasswordEncoder.matches(m.getUserPwd(), loginMember.getUserPwd())) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		}else {
			mv.addObject("errorMsg","로긴실패 앙");
			mv.setViewName("common/errorPage");
		}
		return mv;
		
		
		
		
		
	}
	
	@RequestMapping("logout.me")
	public String logoutMember (HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		// WEB-INF/views/member/memberEnrollForm.jsp로 포워딩
		// 앞뒤는 설정해논 게있어서 member/memberEnrollForm만 넣으면 됨
		return "member/memberEnrollForm";
	}
	
	@RequestMapping("insert.me")
	public String insertMember(Member m, Model model, HttpSession session) {
		System.out.println(m);
		// 1. 한글깨짐 => 스프링에서 제공하는 인코딩 필터 등록
		// 2. 나이를 입력하지 않았을 경우 "" 빈문자열이 넘어오는데 int형 필드에 담을 수 없어서 400에러 발생
		//		=> Member 클래스에 age 필드를 int형 --> String형으로 변경
		// 3. 비밀번호가 사용자가 입력한 있는 그대로 평문
		//		=> Bcrypt 방식의 암호화를 통해서 암호문으로 변경
		// 		 => 1) 스프링 시큐리티 모듈에서 제공(라이브러리 추가 pom.xml)
		//		 => 2) BcryptPassWordEncoder 라는 클래스를 빈으로 등록 (xml 방식 : spring-security.xml 파일에)
		//		 => 3) web.xml에 spring-security.xml 파일을 pre-loading(읽는다) 할 수 있도록 작성
//		System.out.println("평문 : "+m.getUserPwd());
		
		// 암호화 작업(암호문을 만들어내는 과정)
		String encPwd = bcryptpasswordEncoder.encode(m.getUserPwd());
//		System.out.println("암호문: "+ encPwd);
		
		m.setUserPwd(encPwd); // Member 객체의 userPwd에 평문이 아닌 암호문으로 변경
		
		int result = mService.insertMember(m);
		
		if(result > 0) { // 성공 => 메인페이지 url 재요청
			session.setAttribute("alertMsg", "회원가입 추카추카");
			return "redirect:/";
		}else { // 실패 => 에러문구 담아서 에러페이지 포워딩
			model.addAttribute("errorMsg","회원가입 실패 앙");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("mypage.me")
	private String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMember(Member m, Model model, HttpSession session) {
		
		int result = mService.updateMember(m);
		
		if(result > 0) { // 성공 => 메인페이지 url 재요청
			// db로 부터 수정된 회원정보를 다시 조회해와서
			// session에 loginMember 키값으로 덮어씌워야함 *****
			
			Member updateMem =  mService.loginMember(m);
			session.setAttribute("loginMember", updateMem);
			
			// alert 띄어줄 문구 담기
			session.setAttribute("alertMsg", "성공적으로 회원정보 변경되었습니다.");
			
			// 마이페이지로 url 재요청
			return "redirect:mypage.me";
			
		}else { // 실패 => 에러문구 담아서 에러페이지 포워딩
			model.addAttribute("errorMsg","회원정보 수정 실패!");
			
			return "common/errorPage";
		}
	}
	
	@RequestMapping("delete.me")
	public String deleteMember(String userPwd, String userId, HttpSession session,  Model model) {
		// userPwd : 회원탈퇴시 사용자가 입력한 비밀번호 평문
		// session : loginMember Member 객체의 userPwd 필드 : db로 부터 조회된 비번(암호문)
		String encPwd = ((Member)session.getAttribute("loginMember")).getUserPwd();
		
		if(bcryptpasswordEncoder.matches(userPwd, encPwd)) { // 비번 일치
			int result = mService.deleteMember(userId);
			
			if(result > 0) { // 탈퇴처리 성공 => session에 loginMember지우고 , alert문구 담기 => 메인보냄
				session.removeAttribute("loginMember");
				session.setAttribute("alertMsg", "성공적으로 회원탈퇴 되었습니다.");
				return "redirect:/";
			}else { // 탈퇴처리 실패 => 에러문구 담아서 에러페이지 포워딩
				model.addAttribute("errorMsg","회원탈퇴 실패 앙");
				return "common/errorPage";
			}
			
		}else { // 비번 불일치 => 비번틀림 알리고 마이페이지 보여지게
			session.setAttribute("alertMsg", "비밀번호를 잘못 입력하셨습니다. 확인해라");
			return "redirect:mypage.me";
		}
		
		
		
	}
	
	
	
}
