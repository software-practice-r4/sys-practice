<%--
<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/* 変数の宣言　*/
String userId = "";

/* パラメータの取得 */
try {
	if (request.getParameter("userId") != null) {
		userId = request.getParameter("userId");
	}

	session.setAttribute("userId", userId);

	/* Updateメソッドの実行 */
	int err = user.dataload(userId);//ID+関数名()
%>
<%
if (err != 0) {
%>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="○○さんのプロフィール" />
	<jsp:param name="style" value="profile" />
</jsp:include>

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
							<%--<%= user.getDisplayName(0)%>--%>テスト太郎さん
						    <a href="Dm-detail.jsp" class="btn-circle-border-double">
								<i class="fa fa-envelope-o"></i>
							</a>
						</h3>
					</div>
				</div>
				<p class="txt" style="margin-top: 40px;">
					<%-- <%= user.getExplain(0)%>--%>テキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキスト
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
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>

<%--
<%
}
%>
<%
} catch (Exception e) {
%>

<%
}
%>
--%>
