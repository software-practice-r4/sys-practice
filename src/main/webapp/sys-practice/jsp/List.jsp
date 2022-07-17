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
	System.err.println(e);
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
	System.err.println(e);
}
%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="一覧ページ" />
	<jsp:param name="style" value="list" />
</jsp:include>

<div id="main">
	<jsp:include page="./../components/SearchSideBar.jsp" />
	
	<div class="cnt">
		<div class="post">
			<div class="centering-ttl-box">
				<h2 class="centering-ttl">素材の検索結果</h2>
			</div>
			<div class="material-card-wrapper">
				<%
				for (int i = 0; i < material.getNum(); i++) {
					boolean isAdult = false;
					if(material.getIsAdult(i) == 1){
						isAdult = true;
					}
				%>
				<jsp:include page="./../components/Material-Card.jsp">
					<jsp:param name="materialId" value="<%=material.getMaterialId(i)%>" />
					<jsp:param name="price" value="<%=material.getPrice(i)%>" />
					<jsp:param name="thumbnail"
						value="<%=material.getThumbnail(i)%>" />
					<jsp:param name="category" value="<%=material.getCategoryName(i)%>" />
					<jsp:param name="title" value="<%=material.getMaterialName(i)%>" />
					<jsp:param name="isAdult" value="<%=isAdult %>" />
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
