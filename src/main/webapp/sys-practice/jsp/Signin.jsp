<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインインページ" />
	<jsp:param name="style" value="signin" />
</jsp:include>

        <div id="C">
            <div class="post">
                <div class="centering-ttl-box">
                    <h2 class="centering-ttl">
                        ログイン
                    </h2>
                </div>
                <div class="inner">
                    <form action="./" method="POST">
                        <div class="information">
                            <ul>
                                <p>
                                    メールアドレス：<br><input type="email" name="mail" size="40" placeholder="メールアドレス" class="text-box">
                                </p>
                                <p>
                                    パスワード：<br><input type="password" name="password" size="40" placeholder="パスワード" class="text-box"><br>
                                    <a href="secret-question.jsp" class="link"><h3>パスワードをお忘れですか？</h3></a>
                                </p>
                            </ul>
                            <div class="completion">
                                <input type="submit" class="btn-square-so-pop" value="完了"><br>
                                <a href="signup.jsp" class="link"><h4>アカウントをお持ちですか？</h4></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>

<jsp:useBean id="signIn" scope="session" class="sys_practice.SignIn" />
<%
    try {
        signIn.signIn();
%>

<%
} catch (Exception e) {

%>


<% /* エンコード */
    request.setCharacterEncoding("UTF-8");

    /* 変数の宣言　*/
    String pr_name = "";

    /* パラメータの取得 */
    try {
        if (request.getParameter("pr_name") != null) {
            pr_name = request.getParameter("pr_name");
        }

    session.setAttribute("pr_name", pr_name);

    /* Insertメソッドの実行 */
        int err = product.insert(pr_name);
%>

<% if (err != 0) { %>
<jsp:forward page="Mypage.jsp" />
<%} catch (Exception e) {%>
<jsp:forward page="Signin.jsp" />
警告
<%} %>
