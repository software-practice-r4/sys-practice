<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="cart" scope="session" class="sys_practice.Cart" />

<% 
	int userId = -1;
	int cartLength = -1;
	boolean isNotLogin = false;
	boolean isErr = false;
	
	Cookie cookie[] = request.getCookies();
	if(cookie.length > 0){
		/* ログインしていた場合にユーザーIDを格納 */
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("userId")){
				if(!cookie[i].getValue().equals(""))
					userId = Integer.parseInt(cookie[i].getValue());
					cartLength = cart.getCartByUserId(userId);
			}
		}
	}
	
	if(request.getParameter("isNotLogin") != null &&
			   !String.valueOf(request.getParameter("isNotLogin")).equals(""))
	{
		isNotLogin = true;	
	}

	if(request.getParameter("isErr") != null &&
			   !String.valueOf(request.getParameter("isErr")).equals(""))
	{
		isErr = true;	
	}
	
%>
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
                        <%if(isNotLogin){ %>
                        	<p class="err-txt">ログインしてください。</p>
                        <%} %>
                        <%if(isErr){ %>
                        	<p class="err-txt">エラーが発生しました。</p>
                        <%} %>
                        <div class="material-card-wrapper">
                        	
                            <%
                            for (int i = 0; i < cart.getNum(); i++) {
                            	boolean isAdult = false;
    							if(material.getIsAdult(i) == 1){
    								isAdult = true;
    							}
                            %>
                            <div class="material-card-cart">
                           	<form action="<%=request.getContextPath() %>/removeCart" method="POST">
	                           	<input type="hidden" name="materialId" value="<%=cart.getMaterialId(i)%>">
	                           	<input type="submit" class="remove-items" value="×">
                           	</form>
                            <jsp:include page="./../components/Material-Card.jsp">
                                <jsp:param name="materialId" value="<%=cart.getMaterialId(i) %>" />
                                <jsp:param name="price" value="<%=cart.getPrice(i) %>" />
                                <jsp:param name="thumbnail" value="<%=cart.getThumbnail(i) %>" />
                                <jsp:param name="category" value="<%=cart.getCategoryName(i) %>" />
                                <jsp:param name="title" value="<%=cart.getMaterialName(i) %>" />
                                <jsp:param name="isAdult" value="<%=isAdult %>" />
                            </jsp:include>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <%if(cart.getNum()<=0){ %>
                    	<p class="err-txt">まだカートに商品はありません</p>
                    <%} %>
                    <div class="amount">
                    <% 
                    	int totalPrice = 0;
                    	for(int i=0;i<cart.getNum(); i++){
                    		totalPrice += cart.getPrice(i);
                    	}
                    %>
                        <h4>合計金額 : </h4>
                        <h5><%=totalPrice %>円</h>
                    </div>
                    <%if(totalPrice > 0){ %>
                    <!-- 合計金額が、0以上の時 -->
	                    <div class="add">
	                        <a href="./Purchase.jsp" class="btn-gradient-radius">購入する</a>
	                    </div>
                    <%}else if(totalPrice <= 0 && cart.getNum()>0){ %>
	                    <div class="add">
	                        <a href="./Purchase.jsp" class="btn-gradient-radius">ダウンロードする</a>
	                    </div>
                    <%} %>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
