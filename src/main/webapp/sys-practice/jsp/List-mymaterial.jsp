<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%
int userId = -1; // ログイン時のユーザーIDを格納
int dataLength = 0; // 取得してきたデータの総数

Cookie cookie[] = request.getCookies();

for (int i = 0; i < cookie.length; i++) {
	if (cookie[i].getName().equals("userId")) {
		/* userIdが空出ないとき */
		if (!cookie[i].getValue().equals(""))
			userId = Integer.parseInt(cookie[i].getValue());
	}
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材の投稿一覧" />
	<jsp:param name="style" value="list-mymaterial" />
</jsp:include>

<div id="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">投稿一覧</h2>
				</div>
				<%if (userId == -1) {%>
					<p class="err-txt">ユーザ情報の取得に失敗しました。再度ログインしてください。</p>
				<%}else{ %>
				<div class="material-card-wrapper">
					<%
					dataLength = material.getProviderMaterial(userId);
					for (int i = 0; i < dataLength; i++) {
						boolean isAdult = false;
						if(material.getIsAdult(i) == 1){
							isAdult = true;
						}	
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId"
							value="<%=material.getMaterialId(i)%>" />
						<jsp:param name="price" value="<%=material.getPrice(i)%>" />
						<jsp:param name="thumbnail" value="<%=material.getThumbnail(i)%>" />
						<jsp:param name="category"
							value="<%=material.getCategoryName(i)%>" />
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
				<%} %>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
