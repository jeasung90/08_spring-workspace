<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여가 메인이여</title>
</head>
<body>
	<jsp:include page="common/header.jsp"/>
	
	<div class="content">
		<br><br>
		<div class="innerOuter">
			<h4>게시글 Top5</h4>
			<br>

			<a href="" style="float: right;">더보기</a>
			<br><br>

			<table id="boardList" class="table table-hover" align="center">
                <thead>
                  <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                  </tr>
                </thead>
                <tbody>
                	<!-- 현재 조회수가 가장 높은 상위 5개의 게시글 조회해서 뿌리기 (ajax) -->



                </tbody>
            </table>
		</div>

	</div>
	
	
	<script>
		$(function(){
			topBoardList();

			setInterval(topBoardList,1000)
			
			/* 이방법으로는 동적으로 만들어진 요소에 접근 불가 
			$("#boardList>tbody>tr").click(function(){
				location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
			})
			*/
			
			// 동적으로 만들어진 요소에 이벤트 부여 방법
			$(document).on("click", "#boardList>tbody>tr", function(){
				location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
			})
			
			
		})

		function topBoardList(){
			$.ajax({
				url:"topList.bo",
				success:function(data){
					let value ="";
					for(let i in data){
						value += "<tr>"
							+ "<td>" + data[i].boardNo + "</td>"
							+ "<td>" + data[i].boardTitle + "</td>"
							+ "<td>" + data[i].boardWriter + "</td>"
							+ "<td>" + data[i].count + "</td>"
							+ "<td>" + data[i].createDate + "</td>"
							+ "<td>" ;
							if(data[i].originName != null){ //첨부파일 존재함
									value += "  🎗";
							}
							value+=  "</td></tr>";
					}

					$("#boardList tbody").html(value)
				},
				error:function(){
					alert("ajax 통신 실패 랭킹");
				}
			})
		}
	</script>
	
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>