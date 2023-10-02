<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
		
			<!-- aside -->
			<jsp:include page="/WEB-INF/views/include/asideGallery.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">
						<c:if test="${!empty authUser }">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<div class="clear"></div>
						<ul id="viewArea">
							<!-- 이미지반복영역 -->
							<c:forEach items="${galleryList }" var="galleryVo">
								<li id="t${galleryVo.no }">
									<div class="view" data-no="${galleryVo.no }">
										<img class="imgItem" src="${pageContext.request.contextPath }/upload/${galleryVo.saveName }">
										<div class="imgWriter">작성자: <strong>${galleryVo.userNo.name }</strong></div>
									</div>
								</li>
							</c:forEach>
							<!-- 이미지반복영역 -->
						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //푸터 -->
		
	</div>
	<!-- //wrap -->

	<!-- ///////////////////////////////////////////////////////////////// -->
	<!-- ///////////////////////////////////////////////////////////////// -->
	<!-- 이미지 등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form id="uploadForm" action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 이미지 보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				
				<div class="modal-body">
					<div class="formgroup" >
						<img id="viewModelImg" src ="">	<!-- ajax로 처리 : 이미지출력 위치 -->
					</div>
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<c:if test="${!empty authUser.no}">
						<input type="hidden" name="authUserNo" value="${authUser.no}" >
					</c:if>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	<!-- ///////////////////////////////////////////////////////////////// -->
	<!-- ///////////////////////////////////////////////////////////////// -->
</body>

<script type="text/javascript">
	
	// 이미지 업로드 모달창 버튼 클릭
	$("#btnImgUpload").on("click", function() {
		console.log("btnImgUpload 버튼 클릭");
		
		$("#addModal").modal("show");
	})
	
	// 이미지 등록 버튼 클릭
	$("#uploadForm").on("submit", function(e) {
		console.log("uploadForm의 submit 버튼 클릭");
		
		// e.preventDefault();
		
		let file = $("#file").val();
		let content = $("#addModalContent").val();
		
		if(file == "" || content == "") {
			alert("다시 확인해주세요.");
			return false;
		} else {
			$("#addModal").modal("hide");
			return true;
		}
	})
	
	// 이미지 보기 버튼 클릭
	$(".view").on("click", function() {
		console.log("view 버튼 클릭");
		
		let $this = $(this);
		let no = $this.data("no");
		
		$.ajax({
			url : "${pageContext.request.contextPath}/gallery/detail/",
			type : "post",
			/* contentType : "application/json", */
			data: {no: no},
			
			dataType : "json",
			success : function(jsonResultVo) {
				console.log(jsonResultVo);
				
				$("#viewModelImg").attr("src", "${pageContext.request.contextPath }/upload/"+jsonResultVo.data.saveName);
				$("#viewModelContent").text(jsonResultVo.data.content);
				
				let authUserNo = $('[name="authUserNo"]').val();
				let btnDel = $(".btn-danger").length;
				
				if(authUserNo == jsonResultVo.data.userNo.no && btnDel == 0) {
					let str = '<button type="button" class="btn btn-danger" id="btnDel" data-delno="' + jsonResultVo.data.no + '">삭제</button>';
					$(".modal-footer").append(str);
				}
				$("#viewModal").modal("show");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// 이미지 삭제 버튼 클릭
	$(".modal-footer").on("click", "#btnDel",function() {
		console.log("modal-footer의 btnDel 버튼 클릭");
		
		let $this = $(this);
		let no = $this.data("delno");
		
		$.ajax({
			url : "${pageContext.request.contextPath}/gallery/delete/",
			type : "get",
			/* contentType : "application/json", */
			data: {no: no},
			
			dataType : "json",
			success : function(galleryVo) {
				if(galleryVo.no = no) {
					console.log("삭제성공");
					
					$("#t" + no).remove();
					$("#viewModal").modal("hide");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
</script>

</html>