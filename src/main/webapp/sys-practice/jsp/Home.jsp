<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="トップページ" />
	<jsp:param name="style" value="home" />
</jsp:include>

<div class="intro">
	<span class="cover"></span>
	<h1>
		あなたの好きをここに<br> 鈴木アンチ
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
					for (int i = 0; i < 10; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="id" value="3039202" />
						<jsp:param name="price" value="500" />
						<jsp:param name="thumbnail" value="./../img/106.jpg" />
						<jsp:param name="category" value="BGM" />
						<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
					</jsp:include>
					<%
					}
					%>
				</div>
				<!--  <div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
				-->
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
						<jsp:param name="id" value="3039202" />
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

