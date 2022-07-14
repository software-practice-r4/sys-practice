<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%

int userId = -1;
boolean isInvalidParameter = false;
boolean isInvalidUserId = false;
String pageTitle = "";

/* パラメータ不足もしくは、空だったとき */
if(request.getParameter("userId") != null && request.getParameter("userId").equals("")){
	userId = Integer.parseInt(request.getParameter("userId"));
}
else{
	isInvalidParameter = true;
}

/* ユーザーIDはパラメータに含まれているが、無効なIDの場合 */
String displayName = user.getDisplayNameById(userId);
if(!displayName.equals("")){
	isInvalidUserId = true;
	pageTitle = "プロフィールページ";
}

if(!isInvalidParameter && !isInvalidUserId){
	user.dataloadById(userId);
	material.getMaterialByUserId(userId);
	pageTitle = displayName + "さんのプロフィール";
}

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="○○さんのプロフィール" />
	<jsp:param name="style" value="profile" />
</jsp:include>
<%
	if(!isInvalidParameter && !isInvalidUserId){
%>

<div class="content">
	<div class="inner">
		<div class="intro">
			<div class="intro-top">
				<img src="./../img/288627_m.jpg">
			</div>
			<div class="intro-bottom">
				<div class="user-information">
					<div class="lead-ttl">
						<h3>
							テスト太郎さん
						    <a href="Dm-detail.jsp" class="btn-circle-border-double">
								<i class="fa fa-envelope-o"></i>
							</a>
						</h3>
					</div>
				</div>
				<p class="txt" style="margin-top: 40px;">
					テキストテキストテキストテキステキストテキストテキストテキステキストテキストテキストテキステキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキスト
				</p>
			</div>
		</div>
	</div>
	<div class="inner" style="margin-top: 140px;">
		<div class="post">
			<div class="centering-ttl-box">
				<h2 class="centering-ttl">同じ作者の作品</h2>
			</div>
			<div class="material-card-wrapper">
				<%
				for (int i = 0; i < 10; i++) {
				%>
				<jsp:include page="./../components/Material-Card.jsp">
					<jsp:param name="id" value="3039202" />
					<jsp:param name="price" value="500" />
					<jsp:param name="thumbnail" value="./../img/106.jpg" />
					<jsp:param name="category" value="BGM" />
					<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
				</jsp:include>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>
<%}else if(isInvalidParameter){%>
	<p class="err-txt">無効なパラメータです</p>
<%}else if(isInvalidUserId){ %>
	<p class="err-txt">無効なIDです</p>
<%} %>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
