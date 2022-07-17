<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="trend" scope="session" class="sys_practice.Trend" />
<jsp:useBean id="check" scope="session" class="sys_practice.User" />
<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<jsp:useBean id="history" scope="session" class="sys_practice.Purchase" />

<%
int userId = -1;

try{
Cookie cookie[] = request.getCookies();
	for(int i=0;i<cookie.length;i++){
		if(cookie[i].getName().equals("userId")){
			userId = Integer.parseInt(cookie[i].getValue());
		}
	}
} catch(Exception e){
	userId = -1;
}

boolean hasUser = check.hasUserIdOnDatabase(userId);
if(!hasUser){
	userId = -1;
}

else if(hasUser){
  trend.getTrend(userId);
}
%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="マイページトップ" />
	<jsp:param name="style" value="mypage" />
</jsp:include>
        <div class="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                        <%if(userId == -1) {%>
							<p class="err-txt">ユーザIDの取得に失敗しました。再度ログインしてください。</p>
						<%}	else {%>
                            <h2 class="centering-ttl">
                                あなたのこんな作品が人気です
                            </h2>
                        </div>
                        <div class="material-card-wrapper">
                        	<%if(trend.getNumResults() == 0) {%>
								<p class="err-txt m-auto">まだ素材が投稿されていません。</p>
							<%}else{
                            for (int i = 0; i < trend.getNumResults() ; i++) {
                            	boolean isAdult = false;
                            	if(material.getIsAdult(i) == 1){
                            		isAdult = true;
                            	}	

                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="<%=trend.getMaterialId(i)%>" />
                                <jsp:param name="price" value="<%=trend.getPrice(i)%>" />
                                <jsp:param name="thumbnail" value="<%=trend.getThumbnail(i)%>" />
                                <jsp:param name="category" value="<%=trend.getCategory(i)%>" />
                                <jsp:param name="title" value="<%=trend.getMaterialName(i)%>" />
                                <jsp:param name="isAdult" value="<%=isAdult %>" />
                            </jsp:include>
                            <%
                            }
                            %>
                            
                          <%} %>
                        </div>
                        <%if(trend.getNumResults() != 0) {%>
	                        <div class="add">
	                           	<a href="#" class="btn-gradient-radius">もっとみる</a>
	                       	</div>
                       	<%} %>
                    </div>
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                素材を投稿する
                            </h2>
                        </div>
                        <div class="samune">
                            <a href="Post-material.jsp" class="btn-square">投稿画面へ</a>
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
                            material.getMaterialByUserId(userId);
                            int length = material.getNum();
                            if(length > 10){
                            	length = 10;
                            }
                            for (int i = 0; i < length; i++) {
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="<%=material.getMaterialId(i)%>" />
                                <jsp:param name="price" value="<%=material.getPrice(i)%>" />
                                <jsp:param name="thumbnail" value="<%=material.getThumbnail(i)%>" />
                                <jsp:param name="category" value="<%=material.getCategoryName(i)%>" />
                                <jsp:param name="title" value="<%=material.getMaterialName(i)%>" />
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
                            int dataLength = history.loadPurchaseHistory(userId);
                            for (int i = 0; i < dataLength; i++) {
                            	boolean isAdult = false;
            					if(history.getIsAdult(i) == 1){
            						isAdult = true;
            					}
                            %>
                            <jsp:include page="./../components/Material-Card.jsp">
								<jsp:param name="materialId" value="<%=history.getMaterialId(i)%>" />
								<jsp:param name="price" value="<%=history.getPrice(i)%>" />
								<jsp:param name="thumbnail" value="<%=history.getThumbnail(i)%>" />
								<jsp:param name="category" value="<%=history.getCategoryName(i)%>" />
								<jsp:param name="title" value="<%=history.getMaterialName(i)%>" />
								<jsp:param name="isAdult" value="<%=isAdult %>" />
							</jsp:include>
                            <%
                            }
                            %>
                        </div>
                        <div class="add">
                            <a href="#" class="btn-gradient-radius">もっとみる</a>
                        </div>
                    <%} %>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
