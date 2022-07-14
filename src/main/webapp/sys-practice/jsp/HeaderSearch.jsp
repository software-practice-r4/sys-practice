<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="search" scope="session" class="sys_practice.Search"/>

<% /* エンコード */
    request.setCharacterEncoding("UTF-8");

    /*変数の宣言*/
    String keyword="";
    /* パラメータの取得 */
    if (request.getParameter("sbox") != null) {
        keyword = request.getParameter("sbox");
    }
    /*素材検索メソッド */
    try {
        search.getMaterial(keyword);
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