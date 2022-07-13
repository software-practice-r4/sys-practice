<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");
/* データ一覧の取得メソッド */
try {
	category.dispCategory();
} catch (Exception e) {
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラーの表示</title>
</head>
<body>
	<header>
		<h1>エラーの表示</h1>
	</header>
	<%=e%>
</body>
</html>
<%
}
%>

<jsp:useBean id="search" scope="session" class="sys_practice.Search" />
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="一覧ページ" />
	<jsp:param name="style" value="list" />
</jsp:include>
<html>
<head>
<link rel="stylesheet" href="search-result.css">
</head>
<body>
	<div id="main">
		<div class="sidebar">
			<h3>絞り込み検索</h3>
			<ul>
				<h1>カテゴリー</h1>
				<div class="select">
					<select name="category" class="text-box">
						<%
						for (int i = 0; i < category.getNum(); i++) {
						%>
						<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
						<%}%>
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
					<select name="age" class="text-box">
						<option value="allages">全年齢</option>
						<option value="adult">R-18</option>
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
					<h2 class="centering-ttl">素材の検索結果</h2>
				</div>
				<div class="material-card-wrapper">
					<p>
						データ件数:<%=search.getNum()%></p>
					<%
					if (search.getNum() == 0) {
					%>
					<p>一致する素材はありません</p>
					<br />
					<%
					} else {
					for (int i = 0; i < search.getNum(); i++) {
					%>
					<p>
						<jsp:include page="./../components/Material-Card.jsp">
							<jsp:param name="materialId" value="<%=search.getMaterialId(i)%>" />
							<jsp:param name="price" value="<%=search.getPrice(i)%>" />
							<jsp:param name="thumbnail"
								value="./../img/<%=search.getThumbnail(i)%>" />
							<jsp:param name="category" value="<%=search.getCategoryName(i)%>" />
							<jsp:param name="title" value="<%=search.getMaterialName(i)%>" />
						</jsp:include>
					</p>
					<%
					}
					%>
					<%
					}
					%>
				</div>
	<a href="Search.jsp"><button type="button">検索情報入力画面に戻る</button>></a>
				<div class="next">
					<a href="#">1..100</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
