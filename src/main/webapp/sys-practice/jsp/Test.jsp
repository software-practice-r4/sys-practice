<jsp:useBean id="signup" scope="session" class="sys_practice.SignUp" />
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />
<jsp:useBean id="test" scope="session" class="sys_practice.Test" />
<%
request.setCharacterEncoding("UTF-8");
try {
	int i = 0;
	int j = 0;

	//
	test.doDispCategory();
	/*
	Loading driver...
	Driver loaded!
	categoryId  : 1
	categoryName: BGM
	categoryId  : 2
	categoryName: イラスト
	categoryId  : 3
	categoryName: 動画
	categoryId  : 4
	categoryName: エフェクト
	categoryId  : 6
	categoryName: GIF
	Loading driver...
	Driver loaded!
	Closing the connection.
	*/

	//
	//test.doGetCategoryNameById();
	//1のとき
	/*
	Loading driver...
	Driver loaded!
	categoryId  : 1
	categoryName: BGM
	Return value: BGM
	Loading driver...
	Driver loaded!
	Closing the connection.
	*/

	//100のとき
	/*
	Loading driver...
	Driver loaded!
	Return value: null
	Loading driver...
	Driver loaded!
	Closing the connection.
	*/

	//

	//

	//
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="テストページ" />
	<jsp:param name="style" value="signup" />
</jsp:include>
<div id="C">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">TEST</h2>
		</div>
		<div class="information">
			<%-- データ件数：<%=category.getNum()%><br>
			<ul>
				<%
				for(i = 0; i < category.getNum(); i ++){
				%>
				<div class="test"><%=category.getCategoryId(i)%></div>
				<div class="test"><%=category.getCategoryName(i)%></div>
				<%
				}
				%>
			</ul> --%>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
<%
} catch (Exception e) {
System.err.println(e);
%>
<jsp:forward page="Home.jsp" />
<%
}
%>
