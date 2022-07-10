<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="category" scope="session" class="sys_practice.Category" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/* データ一覧の取得メソッド */
try {
	category.dispCategory();
%>
<jsp:forward page="List.jsp" />
<%
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
	<article>e</article>
</body>
</html>
<%
}
%>