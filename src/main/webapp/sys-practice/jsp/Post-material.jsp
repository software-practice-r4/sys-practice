<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
boolean fileisNull = false;
boolean materialNameisNull = false;
boolean explanationisNull = false;
boolean priceisNull = false;
boolean isSuccessed = false;
boolean isFailed = false;
if (request.getParameter("fileisNull") != null) {
	fileisNull = Boolean.valueOf(request.getParameter("fileisNull"));
}
if (request.getParameter("materialNameisNull") != null) {
	materialNameisNull = Boolean.valueOf(request.getParameter("materialNameisNull"));
}
if (request.getParameter("explanationisNull") != null) {
	explanationisNull = Boolean.valueOf(request.getParameter("explanationisNull"));
}
if (request.getParameter("priceisNull") != null) {
	priceisNull = Boolean.valueOf(request.getParameter("priceisNull"));
}
if (request.getParameter("isSuccessed") != null) {
	isSuccessed = Boolean.valueOf(request.getParameter("isSuccessed"));
}
if (request.getParameter("isFailed") != null) {
	isFailed = Boolean.valueOf(request.getParameter("isFailed"));
}
int userId = -1;
Cookie cookie[] = request.getCookies();
for(int i=0;i<cookie.length;i++){
	if(cookie[i].getName().equals("userId")){
		userId = Integer.parseInt(cookie[i].getValue());
	}
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
						<%}	%>
						<%if (fileisNull) {%>
							<p class="err-txt">アップロードする素材を選択してください。</p>
						<%}	%>
						<%if (materialNameisNull) {%>
							<p class="err-txt">素材の名前を入力してください。</p>
						<%}	%>
						<%if (explanationisNull) {%>
							<p class="err-txt">素材の説明を入力してください。</p>
						<%}	%>
						<%if (priceisNull) {%>
							<p class="err-txt">素材の価格を入力してください。</p>
						<%}	%>
						<%if (isSuccessed) {%>
							<p class="success-txt">素材のアップロードに成功しました。</p>
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
                    	<img src="../img/106.jpg">
                	</div>
                </form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>