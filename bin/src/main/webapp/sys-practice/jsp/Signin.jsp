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
