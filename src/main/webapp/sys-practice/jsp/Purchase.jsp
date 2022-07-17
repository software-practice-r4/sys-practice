<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="cart" scope="session" class="sys_practice.Cart" />

<% 
	int userId = -1;
	int cartLength = -1;
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
	
	if(request.getParameter("isErr") != null && 
			   !String.valueOf(request.getParameter("isErr")).equals(""))
	{
		isErr = true;	
	}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="購入" />
	<jsp:param name="style" value="cart" />
</jsp:include>
        <div id="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                購入画面
                            </h2>
                        </div>
                        <%if(isErr){ %>
                        	<p class="err-txt">購入時にエラーが発生しました。ページを更新してください。</p>
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
                    <div class="amount">
                    <% 
                    	int userWallet = user.getMoneyById(userId);
                    	boolean isErrGetWallet = false;
                    	if(userWallet < 0){
                    		isErrGetWallet = true;
                    	}
                    %>
                        <h4>あなたの残高: </h4>
                        <%if(!isErrGetWallet){ %>
                        	<h5><%=userWallet %>円</h5>
                       	<%}else{ %>
                       		<h5 class="err-txt">取得できませんでした。ページを更新してください。</h5>
                     	<%} %>
                    </div>
                    <%if(userWallet-totalPrice >= 0){ %>
	                    <div class="add">
	                        <a href="<%=request.getContextPath() %>/purchase?userId=<%=userId %>" 
	                        class="btn-gradient-radius">購入する</a>
	                    </div>
                    <%}else{ %>
	                    <div class="add">
	                        <a href="#" class="btn-gradient-radius err-txt" style="pointer-events:none;">
	                        	残高チャージしてください
	                        </a>
	                    </div>
                    <%} %>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
