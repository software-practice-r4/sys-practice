<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />

<%
boolean isLogout = false;
boolean isNull = false;//秘密の質問テーブルのデータが空だったとき

if(request.getParameter("isLogout") != null){
	isLogout = Boolean.valueOf(request.getParameter("isLogout"));
}
if(request.getParameter("isNull") != null){
	isNull = Boolean.valueOf(request.getParameter("isNull"));
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="トップページ" />
	<jsp:param name="style" value="home" />
</jsp:include>

<%if(isLogout){ %>
	<p class="err-txt timeout no-margin">ログアウトしました</p>
<%} %>
<%if(isNull){ %>
	<p class="err-txt timeout no-margin">現在アカウントを作ることが出来ませんしました</p>
<%} %>
<div class="intro">
	<span class="cover"></span>
	<h1>
		あなたの好きをここに<br>素材提供サイト
	</h1>
</div>

<div class="recommend">
	<div class="inner">
		<div class="content">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">あなたへのおすすめ</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					material.getRandomMaterial();
					for (int i = 0; i < 10; i++) {
						boolean isAdult = false;
						if(material.getIsAdult(i) == 1){
							isAdult = true;
						}
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="<%=material.getMaterialId(i) %>" />
						<jsp:param name="price" value="<%=material.getPrice(i) %>" />
						<jsp:param name="thumbnail" value="<%=material.getThumbnail(i) %>" />
						<jsp:param name="category" value="<%=material.getCategoryName(i) %>" />
						<jsp:param name="title" value="<%=material.getMaterialName(i) %>" />
						<jsp:param name="isAdult" value="<%=isAdult %>" />
					</jsp:include>
					<%
					}
					%>
				</div>

			</div>
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">今週の人気ランキング</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					material.getRandomMaterial();
					for (int i = 0; i < 10; i++) {
						boolean isAdult = false;
						if(material.getIsAdult(i) == 1){
							isAdult = true;
						}
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="<%=material.getMaterialId(i) %>" />
						<jsp:param name="price" value="<%=material.getPrice(i) %>" />
						<jsp:param name="thumbnail" value="<%=material.getThumbnail(i) %>" />
						<jsp:param name="category" value="<%=material.getCategoryName(i) %>" />
						<jsp:param name="title" value="<%=material.getMaterialName(i) %>" />
						<jsp:param name="isAdult" value="<%=isAdult %>" />
					</jsp:include>
					<%
					}
					%>
				</div>
			</div>

			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">カテゴリー</h2>
				</div>
				<div class="category-list">
					<%
					category.dispCategory();
					for (int i = 0; i < category.getNum(); i++) {
					%>
					<a href=<%="List.jsp?ndCategoryId="+category.getCategoryId(i)%>
						class="btn-square"><%= category.getCategoryName(i)%></a>
					<%}%>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />

</body>
</html>
