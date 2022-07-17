<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="nd" scope="session" class="sys_practice.Search" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/*変数の宣言*/
int ndCategoryId = 0;
String ndPrice = "";
int ndIsAdult = 0;

/* パラメータの取得 */
if (request.getParameter("ndCategoryId") != null && !request.getParameter("ndCategoryId").equals("")) {
	ndCategoryId = Integer.parseInt(request.getParameter("ndCategoryId"));
} else {
	ndCategoryId = -1;
}
if (request.getParameter("ndPrice") != null && !request.getParameter("ndPrice").equals("")) {
	ndPrice = request.getParameter("ndPrice");
} else {
	ndPrice = "";
}
if (request.getParameter("ndIsAdult") != null && !request.getParameter("ndIsAdult").equals("")) {
	ndIsAdult = Integer.parseInt(request.getParameter("ndIsAdult"));
} else {
	ndIsAdult = -1;
}

/*素材検索メソッド */
try {
	System.out.println("narrowdown");
	nd.narrowdown(ndCategoryId, ndPrice, ndIsAdult);
%>

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
				<form action="Narrowdown.jsp" method="post">
					<!--nd -> nallow down （絞り込む）-->
					<h1>カテゴリー</h1>
					<div class="select">
						<select name="ndCategoryId" class="text-box">
							<%
							for (int i = 0; i < category.getNum(); i++) {
							%>
							<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
							<%
							}
							%>
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
					<h2 class="centering-ttl">素材の絞り込み結果</h2>
				</div>
				<div class="material-card-wrapper">
					<p>
						データ件数:<%=nd.getNum()%></p>
					<%
					if (nd.getNum() == 0) {
					%>
					<p>一致する素材はありません</p>
					<%
					} else {
					for (int i = 0; i < nd.getNum(); i++) {
					%>
					<p>
						<jsp:include page="./../components/Material-Card.jsp">
							<jsp:param name="materialId" value="<%=nd.getMaterialId(i)%>" />
							<jsp:param name="price" value="<%=nd.getPrice(i)%>" />
							<jsp:param name="thumbnail" value="<%=nd.getThumbnail(i)%>" />
							<jsp:param name="category" value="<%=nd.getCategoryName(i)%>" />
							<jsp:param name="title" value="<%=nd.getMaterialName(i)%>" />
						</jsp:include>
					</p>
					<%
					}
					%>
					<%
					}
					%>
				</div>
				<a href="List.jsp"><button type="button">一覧表示に戻る</button>></a>
				<div class="next">
					<a href="#">1..100</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./../components/Footer.jsp" />
</body>
</html>

<%
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