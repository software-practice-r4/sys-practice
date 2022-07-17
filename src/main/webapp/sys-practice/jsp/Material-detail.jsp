<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%
	boolean isInvalidMaterialId = false;
	boolean isAddCartFailed = false;
	boolean isNotLogin = false;
	boolean isExistItems = false;
	
	int dataLength = 0;
	int userId = -1;
	
	Cookie cookie[] = request.getCookies();
	if(cookie.length > 0){
		/* ログインしていた場合にユーザーIDを格納 */
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("userId")){
				if(!cookie[i].getValue().equals(""))
					userId = Integer.parseInt(cookie[i].getValue());
			}
		}
	}

	// 素材IDがパラメータにない or 値が入ってないとき
	if(request.getParameter("materialId") == null || request.getParameter("materialId").equals("")){
		isInvalidMaterialId = true;
	}else{
		dataLength = material.getMaterialByMaterialId(Integer.parseInt(request.getParameter("materialId")));

		// 無効な素材IDを取得したとき
		if(dataLength == 0)
			isInvalidMaterialId = true;
	}
	
	if(request.getParameter("isAddCartFailed") != null && 
	   !String.valueOf(request.getParameter("isAddCartFailed")).equals(""))
	{
		isAddCartFailed = true;	
	}
	
	if(request.getParameter("isExistItems") != null && 
			   !String.valueOf(request.getParameter("isExistItems")).equals(""))
	{
		isExistItems = true;	
	}
	
	if(request.getParameter("isNotLogin") != null &&
	   !String.valueOf(request.getParameter("isAddCartFailed")).equals("") && userId == -1)
	{
		isNotLogin = true;	
	}
	
	int providerId = material.getProviderId(0);
	int categoryId = material.getCategoryId(0);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材詳細ページ" />
	<jsp:param name="style" value="detail-material" />
</jsp:include>
<%if(!isInvalidMaterialId){ %>
<div class="content">
	<div class="inner">
		<%if(isAddCartFailed){%>
			<p class="err-txt" style="margin-bottom: 20px;">カートの追加に失敗しました。</p>
		<%} %>
		<%if(isNotLogin){ %>
			<p class="err-txt" style="margin-bottom: 20px;">ログインしてください。</p>
		<%} %>
		<%if(isExistItems){ %>
			<p class="err-txt" style="margin-bottom: 20px;">すでにカートに存在しています</p>
		<%} %>
		<div class="intro">
		
			<div class="intro-left">
				<img src="./../img/<%=material.getThumbnail(0)%>">
			</div>
			<div class="intro-right">
				<h2 class="lead-ttl">素材：<%=material.getCategoryName(0) %></h2>
				<h2 class="lead-ttl">タイトル：<%=material.getMaterialName(0) %></h2>
				<h2 class="lead-ttl">
					投稿者名：
					<a href="Profile.jsp?userId=<%=providerId %>" style="text-decoration:underline">
						<%=material.getDisplayName(0) %>
					</a>
				</h2>
				<p style="font-size: 32px; font-weight: bold; margin-top: 50px; margin-bottom: 20px;">
					&yen;<%=material.getPrice(0) %>
				</p>
				<%if(material.getPrice(0) <= 0){ %>
					<a href="<%="../img/" +material.getThumbnail(0) %>" 
					   download class="btn-square-emboss link">ダウンロード</a>
				<%}else{ %>
					<a href="<%=request.getContextPath()%>/cart?materialId=<%=material.getMaterialId(0) %>"  
					   class="btn-square-emboss link">カートに追加</a>
				<%} %>
				
			</div>
		</div>
	</div>

	<div class="explanation">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">作品説明</h2>
				</div>
				<div class="box22">
					<p><%=material.getExplanation(0) %></p>
				</div>
			</div>
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">同じ作者の作品</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					// TODO: ないときにないって表示する

					dataLength = material.getProviderMaterial(providerId);
					
					// 投稿者の別の素材がない時
					if(dataLength == 0){
						
					}
					else{
						for (int i = 0; i < dataLength; i++) {
							boolean isAdult = false;
							if(material.getIsAdult(i) == 1){
								isAdult = true;
							}
						%>
						<jsp:include page="./../components/Material-Card.jsp">
							<jsp:param name="materialId" value="<%=material.getMaterialId(i) %>" />
							<jsp:param name="price" value="<%=material.getPrice(i) %>" />
							<jsp:param name="thumbnail" value="<%=material.getThumbnail(i) %>" />
							<jsp:param name="category" value="<%=material.getCategoryName(i) %>" />
							<jsp:param name="title" value="<%=material.getMaterialName(i) %>" />
							<jsp:param name="isAdult" value="<%=isAdult %>" />
						</jsp:include>
						<%
						}
					}
					%>
				</div>
			</div>
		</div>
		<div class="post">
			<div class="inner">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">同じカテゴリーの作品</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					dataLength = material.getSameCategoryMaterial(categoryId);
					
					for (int i = 0; i < dataLength; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="materialId" value="<%=material.getMaterialId(i) %>" />
						<jsp:param name="price" value="<%=material.getPrice(i) %>" />
						<jsp:param name="thumbnail" value="<%=material.getThumbnail(i) %>" />
						<jsp:param name="category" value="<%=material.getCategoryName(i) %>" />
						<jsp:param name="title" value="<%=material.getMaterialName(i) %>" />
					</jsp:include>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
<%}else{
%>
	<p class="err-txt">無効な素材IDもしくは不正なパラメータです。</p>
<%
}%>
</body>
</html>
