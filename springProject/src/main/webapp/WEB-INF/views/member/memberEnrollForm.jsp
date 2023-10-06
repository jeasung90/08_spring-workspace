<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 이쪽에 메뉴바 포함 할꺼임 -->
	<jsp:include page="../common/header.jsp" />

	 <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="enrollForm">
            
                <div class="form-group">
                    <label for="userId">* ID :</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Please Enter ID" required>
                    <div id="checkResult" style="font-size:0.8em; display: none;"></div>
                    
                    <br>
                    <label for="userPwd">* Password :</label>
                    <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="checkPwd">* Password Check :</label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Please Enter Name" required><br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Please Enter Email"><br>
                    
                    <label for="age"> &nbsp; Age :</label>
                    <input type="number" class="form-control" id="age" name="age" placeholder="Please Enter Age"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Please Enter Phone (-없이)"><br>
                    
                    <label for="address"> &nbsp; Address :</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Please Enter Address"><br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button id="enrollBtn" type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger"> 초기화</button>
                </div>
            </form>
        </div>
        
      
        <br><br>
    </div>

    <script>
        $(function(){
            // 아이디를 입력하는 input요소 객체를 변수에 담아두기
            const $idInput = $("#enrollForm input[name=userId]");

            $idInput.keyup(function(){
                // console.log($idInput.val());
                // 우선 최소 5글자 이상으로 입력되어있을때만 ajax 요청해서 중복체크 진행
                if($idInput.val().length > 5){
                    $.ajax({
                        url:"idCheck.me",
                        data:{
                            checkId : $idInput.val()
                        },
                        success:function(result){
                            if(result == 'NNNNN'){ // 사용이 불가능한 상태
                                // => 빨간색 매세지 (사용 불가능) 출력
                                $("#checkResult").show();
                                $("#checkResult").css("color","red").text("중복된 아이디가 존재합니다.")
                                // => 버튼 비활성화
                                $("#enrollForm : submit").attr("disabled", true);
                            }else { // 사용 가능한 상태
                                $("#checkResult").show();
                                $("#checkResult").css("color","green").text("사용 가능한 아이디 입니다.")
                                // 버튼 활성화
                                $("#enrollForm :submit").removeAttr("disabled");
                            }
                        },
                        error:function(){
                            alert("ajax 통신 실패")
                        }

                    })
                }else { // 5글자 미만 => 버튼 비활성화 , 메시지 숨기기
                    $("#checkResult").hide();
                    $("#enrollForm : submit").attr("disabled", true);
                }

            })


        })

    </script>
	<!-- 이쪽에 푸터바 포함할꺼임 -->
	<jsp:include page="../common/footer.jsp" />
</body>
</html>