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
<head>
<link rel="stylesheet" href="search-result.css">
</head>
<body>
	<div id="main">
		<div class="sidebar">
			<h3>絞り込み検索</h3>
			<ul>
			]<form action="Narrowdown.jsp" method="post">
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
				<br> <input type="submit" value="絞り込む">
			</form>
			</ul>
		</div>
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
				<a href="Search.jsp" class="link-init">検索情報入力画面に戻る &gt;</a>
				<div class="next">
					<a href="#">1..100</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
