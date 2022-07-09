<jsp:useBean id="search" scope="session" class="sys_practice.Search" />
<%
String keyword = "";
int category = -1; // -1で全検索をかける
String price = "Z"; // A-Dの値が返ってくるため、エラー処理用にzを格納
int isAdult = 0; // 全年齢をデフォルト値

if (request.getParameter("keyword") != null) {
	keyword = request.getParameter("keyword");
}
if (request.getParameter("category") != null) {
	keyword = request.getParameter("category");
}
if (request.getParameter("price") != null) {
	keyword = request.getParameter("price");
}
if (request.getParameter("isAdult") != null) {
	keyword = request.getParameter("isAdult");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="一覧ページ" />
	<jsp:param name="style" value="list" />
</jsp:include>

<div id="main">
	<div class="sidebar">
		<h3>絞り込み検索</h3>
		<ul>
			<h1>カテゴリー</h1>
			<div class="select">
				<select name="category" class="text-box">
					<option value="1">BGM</option>
					<option value="2">画像</option>
					<option value="3">動画</option>
				</select>
			</div>
			<h1>価格</h1>
			<div class="select">
				<select name="price" class="text-box">
					<option value="A">～&yen;500</option>
					<option value="B">&yen;500～&yen;2000</option>
					<option value="C">&yen;2000～&yen;5000</option>
					<option value="D">&yen;5000～</option>
				</select>
			</div>
			<h1>年齢制限</h1>
			<div class="select">
				<select name="isAdult" class="text-box">
					<option value="0">---</option>
					<option value="1">R-18</option>
				</select>
			</div>
		</ul>
		<!--<div class="search-more">
				<input class="btn-gradient-3d" type="submit" value="条件追加" />
			</div>
		-->
	</div>
	<div class="cnt">
		<div class="post">
			<div class="centering-ttl-box">
				<h2 class="centering-ttl">素材一覧</h2>
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
			<div class="next">
				<a href="#">1..100</a>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
