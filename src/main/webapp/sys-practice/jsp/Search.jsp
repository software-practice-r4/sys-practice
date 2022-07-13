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

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材の投稿" />
	<jsp:param name="style" value="post-material" />
</jsp:include>
<div id="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">素材を検索</h2>
				</div>
				<div class="information">
					<ul>
						<form action="ReceiveInfo.jsp" method="post">
							<p>
								キーワード:<br> <input type="search" name="keyword" size="40"
									placeholder="アザラシ" class="text-box" required>
							</p>
							<p>
								価格：<br> <input type="number" name="searchPrice" size="40"
									placeholder="9999" class="text-box" max="100000">
							</p>
							<p>
								カテゴリー：<br>
							<div class="select">
								<select name="searchCategoryId" class="text-box">
									<option value="" selected>選択してください</option>
									<%
									for (int i = 0; i < category.getNum(); i++) {
									%>
									<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
									<%
									}
									%>
								</select>
							</div>
							<p>
								年齢制限：<br>
							<div class="select">
								<select name="searchIsAdult" class="text-box" size=>
									<option value="" selected>選択してください</option>
									<option value="0">全年齢</option>
									<option value="1">R-18</option>
								</select>
							</div>
							<p>
								<input type="submit" value="検索">
						</form>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
