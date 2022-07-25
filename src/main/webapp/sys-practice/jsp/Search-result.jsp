<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />
<jsp:useBean id="search" scope="session" class="sys_practice.Search" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");
%>


<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="一覧ページ" />
	<jsp:param name="style" value="list" />
</jsp:include>
<head>
<link rel="stylesheet" href="search-result.css">
</head>
<body>
	<div id="main">
		<jsp:include page="./../components/SearchSideBar.jsp" />
		<div class="cnt">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">素材の検索結果</h2>
				</div>
				<p style="padding: 10px 0 20px; text-align:right;">
					データ件数:<%=search.getNum()%>
				</p>
				<div class="material-card-wrapper">
					<%
					if (search.getNum() == 0) {
					%>
					<p>一致する素材はありません</p>
					<br />
					<%
					} else {
					for (int i = 0; i < search.getNum(); i++) {
						boolean isAdult = false;
						if(search.getIsAdult(i) == 1){
							isAdult = true;
						}	

					%>
						<jsp:include page="./../components/Material-Card.jsp">
							<jsp:param name="materialId" value="<%=search.getMaterialId(i)%>" />
							<jsp:param name="price" value="<%=search.getPrice(i)%>" />
							<jsp:param name="thumbnail"
								value="<%=search.getThumbnail(i)%>" />
							<jsp:param name="category" value="<%=search.getCategoryName(i)%>" />
							<jsp:param name="title" value="<%=search.getMaterialName(i)%>" />
							<jsp:param name="isAdult" value="<%=isAdult %>" />
						</jsp:include>
					<%
					}
					%>
					<%
					}
					%>
				</div>
				<a href="Search.jsp" class="link-color-init">検索情報入力画面に戻る &gt;</a>
				<div class="next">
					<a href="#">1..100</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
