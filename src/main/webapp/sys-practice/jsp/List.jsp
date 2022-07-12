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
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");
/* データ一覧の取得メソッド */
try {
	category.dispCategory();
} catch (Exception e) {
%>
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

<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%
/* エンコード */
request.setCharacterEncoding("UTF-8");
/* データ一覧の取得メソッド */
try {
	material.listMaterial();
} catch (Exception e) {
%>
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


<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="一覧を表示" />
	<jsp:param name="style" value="list" />
</jsp:include>
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
				<%
				for (int i = 0; i < material.getNum(); i++) {
				%>
				<jsp:include page="./../components/Material-Card.jsp">
					<jsp:param name="materialId" value="<%=material.getMaterialId(i)%>" />
					<jsp:param name="price" value="<%=material.getPrice(i)%>" />
					<jsp:param name="thumbnail" value="./../img/106.jpg" />
					<jsp:param name="category" value="<%=material.getCategoryName(i)%>" />
					<jsp:param name="title" value="<%=material.getMaterialName(i)%>" />
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
