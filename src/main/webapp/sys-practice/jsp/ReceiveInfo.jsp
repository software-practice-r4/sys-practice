<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="search" scope="session" class="sys_practice.Search" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/*変数の宣言*/
String keyword = "";
int searchCategoryId = 000;
int searchPrice = 000;
int searchIsAdult = 000;

/* パラメータの取得 */
if (request.getParameter("keyword") != null) {
	keyword = request.getParameter("keyword");
	System.out.println(keyword);
}
if (request.getParameter("searchCategoryId") != null) {
	searchCategoryId = Integer.parseInt(request.getParameter("searchCategoryId"));
	System.out.println(searchCategoryId);
}
if (request.getParameter("searchPrice") != null) {
	searchPrice = Integer.parseInt(request.getParameter("searchPrice"));
	System.out.println(searchPrice);
}
if (request.getParameter("searchIsAdult") != null) {
	searchIsAdult = Integer.parseInt(request.getParameter("searchIsAdult"));
	System.out.println(searchIsAdult);
}

/*素材検索メソッド */
try {
	search.getmaterial(keyword, searchCategoryId, searchPrice, searchIsAdult);
	System.out.println("mesod call");
%>
<jsp:forward page="Search-result.jsp" />
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
	<%=e%>
</body>
</html>

<%
}
%>