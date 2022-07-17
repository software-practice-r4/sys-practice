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

<jsp:useBean id="nd" scope="session" class="sys_practice.Search" />
<%
/* エンコード */
request.setCharacterEncoding("UTF-8");
int categoryId=Integer.parseInt(request.getParameter("categoryId"));
/* データ一覧の取得メソッド */
try {
	nd.searchByCategory(categoryId);
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
			<form action="Narrowdown.jsp" method="post">
				<!--nd -> nallow down （絞り込む）-->
				<h1>カテゴリー</h1>
				<div class="select">
					<select name="ndCategoryId" class="text-box">
						<%
						for (int i = 0; i < category.getNum(); i++) {
						%>
						<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
						<%}%>
					</select>
				</div>
				<h1>価格</h1>
				<div class="select">
					<select name="ndPrice" class="text-box">
						<!--ls->less than(未満)  mt->more than(以上)-->
						<option value="lt500">～&yen;500</option>
						<option value="mt500lt2000">&yen;500～&yen;2000</option>
						<option value="mt2000lt5000">&yen;2000～&yen;5000</option>
						<option value="mt5000">&yen;5000～</option>
					</select>
				</div>
				<h1>年齢制限</h1>
				<div class="select">
					<select name="ndIsAdult" class="text-box">
						<option value="0">全年齢</option>
						<option value="1">R-18</option>
					</select>
				</div>
				<br> <input type="submit" value="絞り込む">
			</form>
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
				for (int i = 0; i < nd.getNum(); i++) {
				%>
				<jsp:include page="./../components/Material-Card.jsp">
					<jsp:param name="materialId" value="<%=nd.getMaterialId(i)%>" />
					<jsp:param name="price" value="<%=nd.getPrice(i)%>" />
					<jsp:param name="thumbnail" value="<%=nd.getThumbnail(i)%>" />
					<jsp:param name="category" value="<%=nd.getCategoryName(i)%>" />
					<jsp:param name="title" value="<%=nd.getMaterialName(i)%>" />
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
