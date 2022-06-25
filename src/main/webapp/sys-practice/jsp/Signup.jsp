<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<% /* エンコード */
    request.setCharacterEncoding("UTF-8");

    /* 変数の宣言　*/
    String displayName = "";
    String email = "";
    String passWord = "";

    /* パラメータの取得 */
    try {
        if (request.getParameter("displayName") != null) {
        	displayName = request.getParameter("displayName");
        }
        if (request.getParameter("email") != null) {
            email = request.getParameter("email");
        }
        if (request.getParameter("passWord") != null) {
            passWord = request.getParameter("passWord");
        }

        session.setAttribute("displayName", displayName);
        session.setAttribute("email", email);
        session.setAttribute("passWord", passWord);

        /* Insertメソッドの実行 */
        int err = user.signUp(displayName, email, passWord);//ID+関数名()
%>
<%   if (err != 0) { %>
<jsp:forward page="./../jsp/Mypage.jsp" />
<%}%>
<%} catch (Exception e) {
	boolean err_flag = false;
    if (request.getParameter("displayName") == null) {
    	//signUp.errorUserId();
    	err_flag = true;
    }
    if (request.getParameter("email") == null) {
        //signUp.errorEmail();
    	err_flag = true;
    }
    if (request.getParameter("passWord") == null) {
    	//signUp.errorPassWord();
    	err_flag = true;
    }%>
<%--<jsp:forward page="Signup.jsp" />--%>
<%}%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインアップページ" />
	<jsp:param name="style" value="signup" />
</jsp:include>

<div id="C">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">アカウント作成</h2>
		</div>
		<div class="information">
			<ul>
				<form action="" method="post">
					<%= if(err_flag) %>
					<p class="err-txt">間違ってるよ！あんた</p>
					<%= endif; %>
					<p>
						ユーザー名：<br> <input type="text" name="displayName" size="40"
							placeholder="ユーザー名" class="text-box">
					</p>
					<p>
						メールアドレス：<br> <input type="email" name="email" size="40"
							placeholder="メールアドレス" class="text-box">
					</p>
					<p>
						パスワード：<br> <input type="password" name="passWord" size="40"
							placeholder="パスワード" class="text-box"><br>
					</p>
			</ul>
		</div>
		<div class="completion">
			<input type="submit" class="btn-square-so-pop" value="完了"></input><br>
			<a href="signin.jsp" class="link"><h4>アカウントをお持ちですか？</h4></a>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>