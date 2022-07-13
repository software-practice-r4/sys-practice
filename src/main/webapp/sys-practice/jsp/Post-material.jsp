<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
boolean isFileNull = false;
boolean isMaterialNameNull = false;
boolean isExplanationNull = false;
boolean isPriceNull = false;
boolean isSuccessed = false;
boolean isFailed = false;
if (request.getParameter("isFileNull") != null) {
	isFileNull = Boolean.valueOf(request.getParameter("isFileNull"));
}
if (request.getParameter("isMaterialNameNull") != null) {
	isMaterialNameNull = Boolean.valueOf(request.getParameter("isMaterialNameNull"));
}
if (request.getParameter("isExplanationNull") != null) {
	isExplanationNull = Boolean.valueOf(request.getParameter("isExplanationNull"));
}
if (request.getParameter("isPriceNull") != null) {
	isPriceNull = Boolean.valueOf(request.getParameter("isPriceNull"));
}
if (request.getParameter("isSuccessed") != null) {
	isSuccessed = Boolean.valueOf(request.getParameter("isSuccessed"));
}
if (request.getParameter("isFailed") != null) {
	isFailed = Boolean.valueOf(request.getParameter("isFailed"));
}

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
%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材の投稿" />
	<jsp:param name="style" value="post-material" />
</jsp:include>

<div id="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">素材の投稿</h2>
				</div>
				<form action="<%=request.getContextPath() %>/fileupload" enctype="multipart/form-data" method="post">
					<div class="information">
						<%if (userId == -1) {%>
							<p class="err-txt">ユーザ情報の取得に失敗しました。再度ログインしてください。</p>
						<%}	else {%>
						<%if (isFileNull) {%>
							<p class="err-txt">アップロードする素材を選択してください。</p>
						<%}	%>
						<%if (isMaterialNameNull) {%>
							<p class="err-txt">素材の名前を入力してください。</p>
						<%}	%>
						<%if (isExplanationNull) {%>
							<p class="err-txt">素材の説明を入力してください。</p>
						<%}	%>
						<%if (isPriceNull) {%>
							<p class="err-txt">素材の価格を入力してください。</p>
						<%}	%>
						<%if (isSuccessed) {%>
							<p class="err-txt">素材のアップロードに成功しました。</p>
						<%}	%>
						<ul>
							<p>
								タイトル：<br>
								<input type="text" name="materialName" size="40" placeholder="タイトル" class="text-box">
							</p>
							<p>
								説明：<br>
								<input type="text" name="explanation" size="40" placeholder="説明" class="text-box">
							</p>
							<p>
								価格：<br>
								<input type="text" name="price" size="40" placeholder="価格"  class="text-box">
							</p>
							<p>
							カテゴリー：
							<div class="select">
								<select name="categoryId" class="text-box">
                                	<option value="1">BGM</option>
                            	 	<option value="2">イラスト</option>
                                	<option value="3">動画</option>
                            		<option value="0">その他</option>
                            	</select>
                            </div>
  	                  		</p>
                    		<p>
                     			カテゴリーの中にないとき：<br><input type="text" name="category" size="40" placeholder="カテゴリーの中にないとき" class="text-box">
                    		</p>
                    		<p>
                        		年齢制限：
                    		<div class="select">
                        		<select name="isAdult" class="text-box">
                        			<option value="0">なし</option>
                            		<option value="1">あり</option>
                        		</select>
                    		</div>
                    		</p>
                    		<p>
                        		アップロードファイル：<br><input type="file" name="name" size="40" placeholder="画像" class="text-box">
                        		<br><input type="hidden" name="providerId" value="<%=userId%>">
							</p>
						</ul>
						<div class="completion">
                        	<input type="submit" class="btn-square-so-pop" value="アップロード"><br>
                    	</div>
                    	<%} %>
                	</div>
                </form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
