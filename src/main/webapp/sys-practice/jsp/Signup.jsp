<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインアップページ" />
	<jsp:param name="style" value="signup" />
</jsp:include>

        <div id="C">
            <div class="post">
                <div class="centering-ttl-box">
                    <h2 class="centering-ttl">
                        アカウント作成
                    </h2>
                </div>
                <div class="information">
                    <ul>
                        <form action="" method="post">
                            <p>
                                ユーザー名：<br><input type="text" name="userId" size="40" placeholder="ユーザー名" class="text-box">
                            </p>
                            <p>
                                メールアドレス：<br><input type="email" name="email" size="40" placeholder="メールアドレス" class="text-box">
                            </p>
                            <p>
                                パスワード：<br><input type="password" name="passWord" size="40" placeholder="パスワード" class="text-box"><br>
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