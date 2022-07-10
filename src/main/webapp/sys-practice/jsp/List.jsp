<jsp:useBean id="category" scope="session" class="sys_practice.Category" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="検索結果を表示" />
	<jsp:param name="style" value="list" />
</jsp:include>

<div id="main">
	<div class="sidebar">
		<h3>絞り込み検索</h3>
		<ul>
			<h1>カテゴリー</h1>
			<div class="select">
				<select name="category" class="text-box">
				<% for (int i = 0; i < category.getNum(); i++) {%>
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
			<div class="next">
				<a href="#">1..100</a>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
