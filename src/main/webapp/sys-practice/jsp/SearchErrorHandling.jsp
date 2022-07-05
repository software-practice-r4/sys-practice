<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="search" scope="session" class="sys_practice.Search"/>

<% /* エンコード */
    request.setCharacterEncoding("UTF-8");

    /*変数の宣言*/
    String keyword="";
	  String searchCategoryId="";
	  String searchPrice="";
	  String searchIsAdult="";

    /* パラメータの取得 */
    if (request.getParameter("keyword") != null) {
        keyword = request.getParameter("keyword");
    }
    if (request.getParameter("searchCategoryId") != null) {
    	searchCategoryId = request.getParameter("searchCategoryId");
    }
    if (request.getParameter("searchPrice") != null) {
    	searchPrice = request.getParameter("searchPrice");
    }
    if (request.getParameter("searchIsAdult") != null) {
    	searchIsAdult = request.getParameter("searchIsAdult");
    }

    /*素材検索メソッド */
    try {
        search.getMaterial(keyword, Integer.parseInt(searchCategoryId), Integer.parseInt(searchPrice), Integer.parseInt(searchIsAdult));
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
        <article>
            <%=e %>
        </article>
    </body>
</html>

<%
    }
%>