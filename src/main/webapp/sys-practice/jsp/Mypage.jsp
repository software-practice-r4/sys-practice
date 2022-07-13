<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="trend" scope="session" class="sys_practice.Trend" />
<jsp:useBean id="material" scope="session" class="sys_practice.Material" />

<%
int userId = -1; // ログイン時のユーザーIDを格納
int dataLength = 0; // 取得してきたデータの総数

Cookie cookie[] = request.getCookies();

for (int i = 0; i < cookie.length; i++) {
	if (cookie[i].getName().equals("userId")) {
		// userIdが空出ないとき
		if (!cookie[i].getValue().equals(""))
			userId = Integer.parseInt(cookie[i].getValue());
	}
}
/* 傾向の取得 */
trend.getTrend(userId);
%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="マイページトップ" />
	<jsp:param name="style" value="mypage" />
</jsp:include>
<div class="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">あなたのこんな作品が人気です</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					for (int i = 0; i < trend.getNumResults(); i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="<%=trend.getMaterialId(i)%>" />
						<jsp:param name="price" value="<%=trend.getPrice(i)%>" />
						<jsp:param name="thumbnail" value="<%=trend.getThumbnail(i)%>" />
						<jsp:param name="category" value="<%=trend.getCategoryName(i)%>" />
						<jsp:param name="title" value="<%=trend.getMaterialName(i)%>" />
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
					<h2 class="centering-ttl">素材を投稿する</h2>
				</div>
				<div class="samune">
					<a href="post-material.jsp" class="btn-square">投稿画面へ</a>
				</div>
			</div>
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">投稿一覧</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					dataLength = material.getProviderMaterial(userId);
					for (int i = 0; i < dataLength; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId"
							value="<%=material.getMaterialId(i)%>" />
						<jsp:param name="price" value="<%=material.getPrice(i)%>" />
						<jsp:param name="thumbnail" value="<%=material.getThumbnail(i)%>" />
						<jsp:param name="category"
							value="<%=material.getCategoryName(i)%>" />
						<jsp:param name="title" value="<%=material.getMaterialName(i)%>" />
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
					<h2 class="centering-ttl">購入履歴</h2>
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
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>