<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="aws" scope="session" class="sys_practice.AWS" />

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
		あなたの好きをここに<br>クラフトボス最強
	</h1>
</div>

<form action="<%=request.getContextPath() %>/awss3" method="POST">
	<input type="submit" value="送信">
</form>
<div class="recommend">
	<div class="inner">
		<div class="content">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">あなたへのおすすめ</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					for (int i = 0; i < 10; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="3039202" />
						<jsp:param name="price" value="500" />
						<jsp:param name="thumbnail" value="./../img/106.jpg" />
						<jsp:param name="category" value="BGM" />
						<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
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
					for (int i = 0; i < 10; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="3039202" />
						<jsp:param name="price" value="500" />
						<jsp:param name="thumbnail" value="./../img/106.jpg" />
						<jsp:param name="category" value="BGM" />
						<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
					</jsp:include>
					<%
					}
					%>
				</div>
				<div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
			</div>

			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">カテゴリー</h2>
				</div>
				<div class="category-list">
					<a href=<%="List.jsp?category_id="+512%> class="btn-square">カテゴリー1</a>
					<a href=<%="List.jsp?category_id="+212%> class="btn-square">カテゴリー2</a>
				</div>
				<div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />

</body>
</html>
