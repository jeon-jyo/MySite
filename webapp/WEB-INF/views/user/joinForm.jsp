<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<form id="join-Form" action="${pageContext.request.contextPath}/user/join" method="get">
							
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label>
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="btn-checkId">중복체크</button>
								<input type="hidden" id="input-checked" value="false">
							</div>
							
							<!-- 중복체크 안내 -->
							<div id="check-id">
								<span class="checkId-f">아이디 중복체크를 해주세요.</span>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">비밀번호</label>
								<input type="password" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label>
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span>
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" > 
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span>
								
								<input type="checkbox" id="chk-agree" value="" name="chk-agree">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
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

	// 회원가입 버튼
	$("#btn-submit").on("click", function(e) {
		console.log("btn-submit 버튼 클릭");
		
		e.preventDefault();
		
		// 데이터 수집
		let id = $("#input-uid").val();
		let password = $("#input-pass").val();
		let name = $("#input-name").val();
		let gender = $("input[type=radio][name=gender]:checked").val();
		let chkAgree = $("#chk-agree").is(':checked');
		let chkId = $("#input-checked").val();
		
		if(id == "" || password == "" || name == "" || gender == undefined) {
			alert("회원가입란을 확인해주세요.");
		} else if(chkId == "false") {
			alert("아이디 중복체크를 해주세요.");
		} else if(!chkAgree) {
			alert("서비스 약관에 동의해주세요.");
		} else {
			console.log("등록")
			$("#join-Form").submit();
		}
	})
	
	// 중복체크 버튼
	$("#btn-checkId").on("click", function() {
		console.log("btn-checkId 버튼 클릭");
		
		let id = $("#input-uid").val();
		
		if(id != "") {
			$.ajax({
				url : "${pageContext.request.contextPath}/user/checkId/",
				type : "get",
				/* contentType : "application/json", */
				data: {id: id},
				
				dataType : "json",
				success : function(result) {
					if(result != id) {
						$("#check-id").html("<span class='checkId-t'>사용 가능한 아이디입니다.</span>");
						$("#input-checked").val("true");
					} else {
						$("#check-id").html("<span class='checkId-f'>이미 존재하는 아이디입니다.</span>");
						$("#input-checked").val("false");
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		} else {
			alert("아이디를 입력해주세요.");
		}
	})
	
</script>

</html>