<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="search" scope="session" class="sys_practice.Search" />

<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/*変数の宣言*/
String keyword = "";
int searchCategoryId = 0;
int searchPrice = 0;
int searchIsAdult = 0;

/* パラメータの取得 */
/*値がnullじゃない場合はパラメータを代入、nullの場合-1を代入*/
if (request.getParameter("keyword") != null) {
	keyword = request.getParameter("keyword");
}
if (request.getParameter("searchCategoryId") != null && !request.getParameter("searchCategoryId").equals("")) {
	searchCategoryId = Integer.parseInt(request.getParameter("searchCategoryId"));
} else {
	searchCategoryId = -1;
}
if (request.getParameter("searchPrice") != null && !request.getParameter("searchPrice").equals("")) {
	searchPrice = Integer.parseInt(request.getParameter("searchPrice"));
} else {
	searchPrice = -1;
}
if (request.getParameter("searchIsAdult") != null && !request.getParameter("searchIsAdult").equals("")) {
	searchIsAdult = Integer.parseInt(request.getParameter("searchIsAdult"));
} else {
	searchIsAdult = -1;
}

/*素材検索メソッド */
try {
	search.getMaterial(keyword, searchCategoryId, searchPrice, searchIsAdult);
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