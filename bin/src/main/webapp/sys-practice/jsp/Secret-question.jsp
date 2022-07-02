<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="ひみつの質問ページ" />
	<jsp:param name="style" value="secret-question" />
</jsp:include>

        <div id="C">
            <div class="post">
                <div class="centering-ttl-box">
                    <h2 class="centering-ttl">
                        ひみつの質問
                    </h2>
                </div>
                <div class="inner">
                    <div class="information">
                        <h3>君の名前は</h3>
                        <div class="completion">
                            <a href="signup.jsp" class="link"><h4>サインインページに戻る</h4></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
