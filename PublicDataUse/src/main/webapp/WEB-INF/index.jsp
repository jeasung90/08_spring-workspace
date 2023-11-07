<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<body>

	<h2>서울시 노선 목록 조회</h2>

	<button id="btn1">노선 조회</button>

	<br>
	<br>

	<table id="result1" border="1">

		<thead>

			<tr>

				<th>노선 ID</th>

				<th>노선명</th>

				<th>노선 길이(Km)</th>

				<th>노선 기점</th>

				<th>노선 종점</th>

				<th>운수사명</th>

			</tr>

		</thead>

		<tbody>

		</tbody>

	</table>

	<script>
		$(function() {

			$("#btn1").click(function() {

				$.ajax({

					url : "busroute.do",

					success : function(data) {

						const itemArr = data.msgBody.itemList;

						let value = "";

						for ( let i in itemArr) {

							// console.log(itemArr[i]); 확인 후 주석

							let item = itemArr[i];

							// 화면에서 table tag안의 내용을 가져오는 코딩을 완성하시오
							value += "<tr>"

										+ "<th>" + item.busRouteId +"</th>"
			
										+ "<th>"+ item.busRouteNm +"</th>"
			
										+ "<th>"+ item.length +"(Km)</th>"
			
										+ "<th>"+ item.stStationNm +"</th>
			
										+ "<th>"+ item.edStationNm +"</th>""
			
										+ "<th>"+ item.corpNm +"</th>""
			
									+"</tr>"
							
						}

						$("#result1 tbody").html(value);

					}

				})

			})

		})
	</script>

</body>

</html>