<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	String materialId = "";
	String price = "";
	String thumbnail = "";
	String category = "";
	String title = "";
	try{
		materialId = request.getParameter("materialId");
		price = request.getParameter("price");
		thumbnail = request.getParameter("thumbnail");
		category = request.getParameter("category");
		title = request.getParameter("title");
		if(materialId.equals(""))
			throw new Exception("materialIdが入力されていません。");
		if(price.equals(""))
			throw new Exception("価格が入力されていません。");
		if(thumbnail.equals(""))
			throw new Exception("サムネイルが入力されていません。");
		if(category.equals(""))
			throw new Exception("カテゴリーが入力されていません。");
		if(title.equals(""))
			throw new Exception("タイトルが入力されていません。");

		// 文字が制限を超えた時の丸め処理
		if(title.length() > 20){
			title = title.substring(0, 20);
			title += "...";
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>

<a href=<%="Material-detail.jsp?materialId=" + price %> class="material-card link">
	<div class="card-top">
		<%-- <img src="<%=thumbnail %>">--%>
		<img src="../img/<%=thumbnail%>">
		<span class="card-price">
			&yen;<%=price %>
		</span>
	</div>
	<div class="card-btm">
		<p class="card-category">
			<%=category %>
		</p>
		<p class="card-title">
			<%=title %>
		</p>
	</div>
</a>
