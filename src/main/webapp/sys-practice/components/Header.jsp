<%@page contentType="text/html" pageEncoding="UTF-8"%> <% String title = "";
String style = ""; try{ title = request.getParameter("title"); style =
request.getParameter("style"); if(title.equals("")) throw new
Exception("タイトルが入力されていません。"); }catch(Exception e){
e.printStackTrace(); } %>

<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link
      rel="stylesheet"
      type="text/css"
      media="screen"
      href="../css/start/destroy.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      media="screen"
      href="../css/start/common.css"
    />
    <%if(!style.equals("")) %><link rel="stylesheet"
    href=<%="../css/"+style+".css" %>>
    <script
      src="https://kit.fontawesome.com/313a5a93b1.js"
      crossorigin="anonymous"
    ></script>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100;300;400;500;700;900&display=swap"
      rel="stylesheet"
    />
    <title><%=title + "　|　素材提供サイト"%></title>
  </head>
  <header>
    <div class="inner">
      <div class="head-all">
        <div class="head-title">
          <a href="../jsp/Home.jsp" class="link"> 素材提供サイト </a>
        </div>
        <div class="head-left">
          <form method="GET" action="List.jsp" class="search">
            <input
              id="sbox"
              id="s"
              name="s"
              type="search"
              placeholder="キーワードを入力"
            />
            <input id="sbtn" type="submit" value="検索" />
          </form>
        </div>
        <div class="head-right">
          <a href="../jsp/Cart.jsp" class="btn-text-3d">
            <img
              src="../img/shopping-cart_icon_1477-300x300.png"
              style="width: 30px; height: 30px"
            />
          </a>
          <a href="../jsp/Signin.jsp" class="btn-flat-logo">
            <i class="fa fa-chevron-right"></i>ログイン
          </a>
        </div>
      </div>
    </div>
  </header>
  <body></body>
</html>
