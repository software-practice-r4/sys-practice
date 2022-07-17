<jsp:useBean id="history" scope="session" class="sys_practice.Purchase" />
<%
int userId = -1; // ログイン時のユーザーIDを格納
Cookie cookie[] = request.getCookies();
/* クッキーが空でないとき */
if(cookie.length > 0){
	/* ログインしていた場合にユーザーIDを格納 */
	for(int i=0;i<cookie.length;i++){
		if(cookie[i].getName().equals("userId")){
			if(!cookie[i].getValue().equals(""))
				userId = Integer.parseInt(cookie[i].getValue());
		}
	}
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="購入履歴" />
	<jsp:param name="style" value="purchase-history" />
</jsp:include>

        <div id="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                購入履歴
                            </h2>
                        </div>
                       <div class="material-card-wrapper">
                            <%
                            int dataLength = history.loadPurchaseHistory(userId);
                            if(dataLength != -1){
                            for (int i = 0; i < dataLength; i++) {
                            	
                            	boolean isAdult = false;
            					if(history.getIsAdult(i) == 1){
            						isAdult = true;
            					}
                            %>
                            <div class="purchase-wrap">
	                            <a href="../img/<%=history.getThumbnail(i)%>" download class="download-btn"></a>
	                            <jsp:include page="./../components/Material-Card.jsp">
									<jsp:param name="materialId" value="<%=history.getMaterialId(i)%>" />
									<jsp:param name="price" value="<%=history.getPrice(i)%>" />
									<jsp:param name="thumbnail" value="<%=history.getThumbnail(i)%>" />
									<jsp:param name="category" value="<%=history.getCategoryName(i)%>" />
									<jsp:param name="title" value="<%=history.getMaterialName(i)%>" />
									<jsp:param name="isAdult" value="<%=isAdult %>" />
								</jsp:include>
							</div>
                            <%
                            }
                            }else{
                            	%>
                            	<p class="err-txt">購入履歴はまだありません</p>
                            	<%
                            }
                            %>
                        </div>
                        <div class="next">
                            <a href="#">1..100</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
