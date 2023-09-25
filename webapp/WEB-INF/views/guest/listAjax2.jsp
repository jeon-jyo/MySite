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
					<h3>ajax����2</h3>
					<div id="location">
						<ul>
							<li>Ȩ</li>
							<li>����</li>
							<li class="last">ajax����2</li>
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
									<th><label class="form-text" for="input-uname">�̸�</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">�н�����</label></th>
									<td><input id="input-pass"type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">���</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					</form>
					
					<button id="btnDataSend" type="button">�����ѵ����� ����</button>
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
	
	// ��� ��ư
	$("#guestForm").on("submit", function(e) {
		console.log("guestForm�� submit ��ư Ŭ��");

		e.preventDefault();
		
		// ������ ����
		let name = $("#input-uname").val();
		let password = $("#input-pass").val();
		let content = $('[name="content"]').val();
		
		let guestVo = {
			name: name,
			password: password,
			content: $('[name="content"]').val()
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guest/insert2/",
			type : "post",
			contentType : "application/json",
			data: JSON.stringify(guestVo),	// js��ü -> json���� {"name":"Ȳ�Ͽ�", "password":1234, "content":"����"}
			
			dataType : "json",
			success : function(jsonResultVo) {
				console.log(jsonResultVo);
				/*
					{result: 'success', data: {��}, failMsg: null}
				*/
				
				// name�� ������
				console.log(jsonResultVo.data.name);
				
				// success
				console.log(jsonResultVo.result);
				
				// �ʱ�ȭ
				$("#input-uname").val("");
				$("#input-pass").val("");
				$('[name="content"]').val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// �����ѵ����� ���� ��ư
	$("#btnDataSend").on("click", function() {
		console.log("btnDataSend ��ư Ŭ��");
		
		let guestList = [];
		
		let guestVo1 = {
			name: "���켺",
			password: "1234",
			content: "���켺�ٳన"
		}
		let guestVo2 = {
			name: "��ȿ��",
			password: "1234",
			content: "��ȿ���ٳన"
		}
		let guestVo3 = {
			name: "���缮",
			password: "1234",
			content: "���缮�ٳన"
		}
		
		guestList.push(guestVo1);
		guestList.push(guestVo2);
		guestList.push(guestVo3);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guest/insert3/",
			type : "post",
			contentType : "application/json",
			data: JSON.stringify(guestList),	// js��ü�� json(���ڿ�)�� �����Ѵ�
			
			dataType : "json",
			success : function(result) {

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	})
	
</script>

</html>