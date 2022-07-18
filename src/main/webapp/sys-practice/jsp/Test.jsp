<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="cart" scope="session" class="sys_practice.Cart" />

<%
int userId = -1;
int cartLength = -1;
boolean isNotLogin = false;
boolean isErr = false;

Cookie cookie[] = request.getCookies();
if (cookie.length > 0) {
	/* ログインしていた場合にユーザーIDを格納 */
	for (int i = 0; i < cookie.length; i++) {
		if (cookie[i].getName().equals("userId")) {
	if (!cookie[i].getValue().equals(""))
		userId = Integer.parseInt(cookie[i].getValue());
	cartLength = cart.getCartByUserId(userId);
		}
	}
}

if (request.getParameter("isNotLogin") != null &&
		!String.valueOf(request.getParameter("isNotLogin")).equals("")) {
	isNotLogin = true;
}
if (request.getParameter("isErr") != null &&
		!String.valueOf(request.getParameter("isErr")).equals("")) {
	isErr = true;
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="カート" />
	<jsp:param name="style" value="cart" />
</jsp:include>
<div id="main">
		<a href="<%="../img/" +material.getThumbnail(0) %>" 
		   download class="btn-square-emboss link">ダウンロード</a>
		<a href="<%=request.getContextPath()%>/cart"  
		   class="btn-square-emboss link">カートに追加</a>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>