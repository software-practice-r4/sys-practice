<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%
	boolean isValidMaterialId = false;
	int dataLength = 0;
	
	// 素材IDがパラメータにない or 値が入ってないとき
	if(request.getParameter("materialId") == null || request.getParameter("materialId").equals("")){
		isValidMaterialId = true;
	}else{
		dataLength = material.getMaterialByMaterialId(Integer.parseInt(request.getParameter("materialId")));

		// 無効な素材IDを取得したとき
		if(dataLength == 0)
			isValidMaterialId = true;
	}
	
	int providerId = material.getProviderId(0);
	int categoryId = material.getCategoryId(0);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材詳細ページ" />
	<jsp:param name="style" value="detail-material" />
</jsp:include>
<%if(!isValidMaterialId){ %>
<div class="content">
	<div class="inner">		
		<div class="intro">
			<div class="intro-left">
				<img src="./../img/<%=material.getThumbnail(0)%>">
			</div>
			<div class="intro-right">
				<h2 class="lead-ttl">素材：<%=material.getCategoryName(0) %></h2>
				<h2 class="lead-ttl">タイトル：<%=material.getMaterialName(0) %></h2>
				<h2 class="lead-ttl">
					投稿者名：
					<a href="Profile.jsp?userId=<%=material.getProviderId(0) %>" style="text-decoration:underline">
						<%=material.getDisplayName(0) %>
					</a>
				</h2>
				<p style="font-size: 32px; font-weight: bold; margin-top: 50px; margin-bottom: 20px;">
					&yen;<%=material.getPrice(0) %>
				</p>
				<a href="#" class="btn-square-emboss link">カートに入れる</a>
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
							System.out.println(material.getMaterialId(i));
							System.out.println(material.getPrice(i));
							System.out.println(material.getThumbnail(i));
							System.out.println(material.getCategoryName(i));
							System.out.println(material.getMaterialName(i));
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
