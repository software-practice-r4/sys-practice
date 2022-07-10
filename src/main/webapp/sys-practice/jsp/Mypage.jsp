<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="trend" scope="session" class="sys_practice.Trend" />

<%
int userId = -1;
Cookie cookie[] = request.getCookies();
for(int i=0;i<cookie.length;i++){
	if(cookie[i].getName().equals("userId")){
		userId = Integer.parseInt(cookie[i].getValue());
	}
}

/* 傾向の取得 */
  trend.getTrend(userId);
%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="マイページトップ" />
	<jsp:param name="style" value="mypage" />
</jsp:include>

        <jsp:include page="./../components/header-after.jsp" />
        <div class="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                あなたのこんな作品が人気です
                            </h2>
                        </div>
                        <div class="material-card-wrapper">
                            <%
                            for (int i = 0; i < trend.getNumResults() ; i++) {
                            	int id = trend.getMaterialId(i);
                            	int price = trend.getPrice(i);
                            	String thumbnail = trend.getThumbnail(i);
                            	String category = trend.getCategory(i);
                            	String title = trend.getMaterialName(i);
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="<%=id%>" />
                                <jsp:param name="price" value="<%=price%>" />
                                <jsp:param name="thumbnail" value="<%=thumbnail%>" />
                                <jsp:param name="category" value="<%=category%>" />
                                <jsp:param name="title" value="<%=title%>" />
                            </jsp:include>
                            <%
                            }
                            %>
                        </div>
                        <div class="add">
                            <a href="#" class="btn-gradient-radius">もっとみる</a>
                        </div>
                    </div>
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                素材を投稿する
                            </h2>
                        </div>
                        <div class="samune">
                            <a href="post-material.jsp" class="btn-square">投稿画面へ</a>
                        </div>
                    </div>
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                投稿一覧
                            </h2>
                        </div>
                        <div class="material-card-wrapper">
                            <%
                            for (int i = 0; i < 10; i++) {
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="3039202" />
                                <jsp:param name="price" value="500" />
                                <jsp:param name="thumbnail" value="./../img/106.jpg" />
                                <jsp:param name="category" value="BGM" />
                                <jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
                            </jsp:include>
                            <%
                            }
                            %>
                        </div>
                        <div class="add">
                            <a href="#" class="btn-gradient-radius">もっとみる</a>
                        </div>
                    </div>
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                購入履歴
                            </h2>
                        </div>
                        <div class="material-card-wrapper">
                            <%
                            for (int i = 0; i < 10; i++) {
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="3039202" />
                                <jsp:param name="price" value="500" />
                                <jsp:param name="thumbnail" value="./../img/106.jpg" />
                                <jsp:param name="category" value="BGM" />
                                <jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
                            </jsp:include>
                            <%
                            }
                            %>
                        </div>
                        <div class="add">
                            <a href="#" class="btn-gradient-radius">もっとみる</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>