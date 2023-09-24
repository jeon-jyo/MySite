<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAjax</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->
	
		<div id="container" class="clearfix">
			
			<!-- aside -->
			<jsp:include page="/WEB-INF/views/include/asideGuest.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestForm" action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					</form>
					
					<!-- <button id="btnGetData">데이터가져오기</button> -->
					<div id="guestListArea"></div>
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->
		
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	// DOM이 완성되었을 때 -> 그리기 직전
	$(document).ready(function() {
		console.log("ready()");
		fetchList();
		console.log("ready() 요청 후");
	})
	
	// 화면을 그리고 난 후
	$(window).load(function() {
		console.log("load()");
		// fetchList();
		console.log("load() 요청 후");
	})

	// 데이터가져오기 버튼
	$("#btnGetData").on("click", function() {
		console.log("btnGetData 버튼 클릭");
		// fetchList();
	})
	
	// 등록 버튼
	// $("#btnSubmit").on("click", function(e) {})
	// 폼의 전송 버튼일 경우에는 이벤트를 form 으로 잡아야 하고 이벤트는 submlt 으로
	$("#guestForm").on("submit", function(e) {
		console.log("guestForm의 submit 버튼 클릭");
		/*
			type="submit" 처럼 원래의 기능이 작동하지 않게 한다
			-> 작동하게 된다면 이벤트보다 submit 기능이 쎄서 다시 새로고침 됨
		*/
		e.preventDefault();
		
		// 데이터 수집
		let name = $("#input-uname").val();
		let password = $("#input-pass").val();
		let content = $('[name="content"]').val();
		
		let guestVo = {
			name: name,
			password: password,
			content: $('[name="content"]').val()
		}
		
		$.ajax({
			// 1.
			/* url : "${pageContext.request.contextPath}/api/guest/insert?name=" + name + "&password=" + password + "&content=" + content, */
			url : "${pageContext.request.contextPath}/api/guest/insert/",
			type : "get",
			/* contentType : "application/json", */
			// 2.
			/* data : {name: name, password: password, content: content}, */
			data: guestVo,
			
			dataType : "json",
			success : function(result) {
				// 그리기
				render(result, "up");
				
				// 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$('[name="content"]').val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// 삭제 버튼
	// 1. 여러개 그려지니까 id 말고 class를 줌
	// 2. 부모한테 이벤트를 줘야 함
	// 삭제영역 -> 삭제에게 위임 (새로 추가된 아이템이 있을 때 사용)
	$("#guestListArea").on("click", ".btnDelForm",function(e) {
		console.log("guestListArea의 btnDelForm 버튼 클릭");
		
		// password, no
		let password = window.prompt("비밀번호를 입력하세요");
		
		// -> 버튼에다가 번호를 숨겨놓음 data-no (or 부모의 형제노드 찾기)
		let $this = $(this);
		let no = $this.data("no");
		
		let guestVo = {
			no: no,
			password: password
		}
		
		// ajax 요청 db값 지움
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guest/delete/",
			type : "get",
			/* contentType : "application/json", */
			data: guestVo,
			
			dataType : "json",
			success : function(guestVo) {
				if(guestVo.no = no) {
					console.log("삭제성공");
					// 화면에서 지운다 or 다시 그린다
					// -> table에 id를 줌
					$("#t" + no).remove();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// ajax 통신을 이용해서 데이터를 요청하고 그린다 (render())
	function fetchList() {
		
		// 서버로 부터 방명록 데이터만 받고 싶다
		$.ajax({
			// 요청할 때 필요
			url : "${pageContext.request.contextPath}/api/guest/list",
			type : "get",
			/* contentType : "application/json", */
			/* data : {name: "홍길동"}, */

			// 응답받을 때
			dataType : "json",
			success : function(guestList) {
				/* 성공시 처리해야 될 코드 작성 */
				console.log(guestList);
				
				// 리스트 + html 그리기
				for(let i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	// 방명록을 1개씩 그린다
	function render(guestVo, dir) {
		let str = '';
		str += '<table id="t' + guestVo.no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestVo.no + '</td>';
		str += '		<td>' + guestVo.name + '</td>';
		str += '		<td>' + guestVo.regDate + '</td>';
		str += '		<td><button class="btnDelForm" data-no="' + guestVo.no + '">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(dir == "up") {
			$("#guestListArea").prepend(str);
		} else if(dir == "down") {
			$("#guestListArea").append(str);
		} else {
			console.log("잘못입력");
		}
	}
	
</script>

</html>