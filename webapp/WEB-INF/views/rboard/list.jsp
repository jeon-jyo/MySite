<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->

		<div id="container" class="clearfix">
		
			<!-- aside -->
			<jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">댓글게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						
						<form action="${pageContext.request.contextPath}/rBoard/search" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="">
								
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${boardList }" var="boardVo">
								<tr>
									<td>${boardVo.no }</td>
									<td class="text-left">
										<c:if test="${boardVo.show =='Y'}">
											<a href="${pageContext.request.contextPath}/rBoard/detail/${boardVo.no }">
												<c:forEach var="i" begin="1" end="${boardVo.depth }">
													+
												</c:forEach>
												${boardVo.title }
											</a>
										</c:if>
										<c:if test="${boardVo.show =='N'}">
											<c:forEach var="i" begin="1" end="${boardVo.depth }">
												+
											</c:forEach>
											${boardVo.title }
										</c:if>
									</td>
									<td>${boardVo.userNo.name }</td>
									<td>${boardVo.hit }</td>
									<td>${boardVo.regDate }</td>
									<c:choose>
										<c:when test="${authUser.name == boardVo.userNo.name}">
											<c:if test="${boardVo.show =='Y'}">
												<td><a href="${pageContext.request.contextPath}/rBoard/delete?no=${boardVo.no }&groupNo=${boardVo.groupNo }">[삭제]</a></td>
											</c:if>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							<div class="clear"></div>
						</div>
						
						<c:if test="${!empty authUser }">
							<a id="btn_write" href="${pageContext.request.contextPath}/rBoard/writeForm">글쓰기</a>
						</c:if>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
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
</html>