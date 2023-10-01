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
							<li class="last">일반게시판페이징</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						
						<form action="${pageContext.request.contextPath}/board/listPagingSearch" method="get">
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
							<c:forEach items="${requestScope.pageMap.boardList }" var="boardVo">
								<tr>
									<td>${boardVo.no }</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/detail/${boardVo.no }">${boardVo.title }</a></td>
									<td>${boardVo.userNo.name }</td>
									<td>${boardVo.hit }</td>
									<td>${boardVo.regDate }</td>
									<c:choose>
										<c:when test="${authUser.name == boardVo.userNo.name}">
											<td><a href="${pageContext.request.contextPath}/board/delete/${boardVo.no }">[삭제]</a></td>
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
								<c:if test="${pageMap.prev }">
									<li><a href="${pageContext.request.contextPath}/board/listPagingSearch?crtPage=${pageMap.startPageBtnNo - 1}&keyword=${pageMap.keyword}">◀</a></li>
								</c:if>
								<c:forEach begin="${pageMap.startPageBtnNo }" end="${pageMap.endPageBtnNo }" step="1" var="page">
									<c:choose>
										<c:when test="${param.crtPage == page }">
											<li class="active"><a href="${pageContext.request.contextPath}/board/listPagingSearch?crtPage=${page}&keyword=${pageMap.keyword}">${page }</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/board/listPagingSearch?crtPage=${page}&keyword=${pageMap.keyword}">${page }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageMap.next }">
									<li><a href="${pageContext.request.contextPath}/board/listPagingSearch?crtPage=${pageMap.endPageBtnNo + 1}&keyword=${pageMap.keyword}">▶</a></li>
								</c:if>
							</ul>
							<div class="clear"></div>
						</div>
						
						<c:if test="${!empty authUser }">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
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