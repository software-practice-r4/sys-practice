<jsp:useBean id="user" scope="session" class="sys_practice.Profile" />
<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/* 変数の宣言　*/
String userId = "";
String userIdAfter = "";
String email = "";
String displayName = "";
String explain = "";
String icon = "画像予定";
String passWord = "";

/* パラメータの取得 */
try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("displayName") != null) {
		displayName = request.getParameter("displayName");
	}
	if (request.getParameter("explain") != null) {
		explain = request.getParameter("explain");
	}
	if (request.getParameter("userId") != null) {
		userId = request.getParameter("userId");
	}

	session.setAttribute("userIdAfter", userIdAfter);
	session.setAttribute("email", email);
	session.setAttribute("displayName", displayName);
	session.setAttribute("explain", explain);
	session.setAttribute("userId", userId);

	/* Updateメソッドの実行 */
	int err = user.editProfile(userIdAfter, email, displayName, explain, icon, userId);//ID+関数名()
%>
<%
if (err != 0) {
%>
<jsp:forward page="./../jsp/Mypage.jsp" />
<%
}
%>
<%
} catch (Exception e) {
%>

<%
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="プロフィール編集" />
	<jsp:param name="style" value="edit-profile" />
</jsp:include>

<jsp:include page="./../components/header-after.jsp" />
<div id="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">プロフィール作成</h2>
				</div>
				<div class="information">
					<ul>
						<form action="" method="post">
							<p>
								ユーザーID：<br>
								<input type="text" name="userId" size="40"
									placeholder="ユーザーID" class="text-box">
							</p>
							<p>
								メールアドレス：<br>
								<input type="email" name="mail" size="40" placeholder="メールアドレス"
									class="text-box">
							</p>
							<p>
								表示名：<br>
								<input type="text" name="disp_name" size="40" placeholder="表示名"
									class="text-box">
							</p>
							<p>
								自己紹介：<br>
								<input type="text" name="intro" size="40" placeholder="自己紹介"
									class="text-box">
							</p>
							<p>
								画像：<br>
								<input type="file" name="name" size="40" placeholder="画像"
									class="image-box">
							</p>
						</form>
					</ul>
					<div class="completion">
						<input type="submit" class="btn-square-so-pop" value="完了">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
