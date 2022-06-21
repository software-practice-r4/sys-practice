<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材詳細ページ" />
	<jsp:param name="style" value="detail-material" />
</jsp:include>

<div class="content">
	<div class="inner">
		<div class="intro">
			<div class="intro-left">
				<img src="./../img/288627_m.jpg">
			</div>
			<div class="intro-right">
				<h2 class="lead-ttl">素材： BGM</h2>
				<h2 class="lead-ttl">タイトル： テキスト</h2>
				<h2 class="lead-ttl">
					投稿者名： <a href="Profile.jsp" style="text-decoration:underline">テスト太郎さん</a>
				</h2>
				<p
					style="font-size: 32px; font-weight: bold; margin-top: 50px; margin-bottom: 20px;">&yen;500</p>
				<a href="#" class="btn-square-emboss link">カートに入れる</a>
			</div>
		</div>
	</div>

	<div class="explanation">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">作品説明</h2>
				</div>
				<div class="box22">
					<p>テキストテキストテキストテキストテキストテキスト テキストテキストテキストテキストテキストテキスト
						テキストテキストテキストテキストテキストテキスト テキストテキストテキストテキストテキストテキスト
						テキストテキストテキストテキストテキスト</p>
				</div>
			</div>
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">同じ作者の作品</h2>
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
			</div>
		</div>
		<div class="post">
			<div class="inner">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">同じカテゴリーの作品</h2>
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
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
