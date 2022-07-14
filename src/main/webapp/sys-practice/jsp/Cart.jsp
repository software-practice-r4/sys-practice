<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="カート" />
	<jsp:param name="style" value="cart" />
</jsp:include>
        <div id="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                カート内
                            </h2>
                        </div>
                        <div class="material-card-wrapper">
                            <%
                            for (int i = 0; i < 10; i++) {
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="id" value="3039202" />
                                <jsp:param name="price" value="500" />
                                <jsp:param name="thumbnail" value="./../img/106.jpg" />
                                <jsp:param name="category" value="BGM" />
                                <jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
                            </jsp:include>
                            <%
                            }
                            %>
                        </div>
                    </div>
                    <div class="amount">
                        <h4>合計金額 : </h4>
                        <h5>20000000円</h>
                    </div>
                    <div class="add">
                        <a href="#" class="btn-gradient-radius">ダウンロードする</a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
