<jsp:useBean id="sign" scope="session" class="sys_practice.SignIn" />
<%
request.setCharacterEncoding("UTF-8");
String email = "";
String password = "";
boolean err_flag = false;
try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	if(request.getParameter("email").equals("") || request.getParameter("password").equals("")){
		throw new Exception("メールアドレスまたは、パスワードが入っていません。");
	}
	session.setAttribute("email", email);
	session.setAttribute("password", password);
	int err = sign.signIn(email, password);
%>
<%
if (err != 0) {
%>
<jsp:forward page="Mypage.jsp" />
<%
}
%>
<%
} catch (Exception e) {
%>
	System.out.println(e);
<%} %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインインページ" />
	<jsp:param name="style" value="signin" />
</jsp:include>

<div id="C">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">ログイン</h2>
		</div>
		<div class="inner">
			<form action="" method="POST">
				<div class="information">
					<c:if test="${err_flag == true;}">
						<p class="err-txt">入力が足りません</p>
					</c:if>
					<ul>
						<li>
							メールアドレス：<br> <input type="email" name="email" size="40"
								placeholder="メールアドレス" class="text-box">
						</li>
						<li>
							パスワード：<br> <input type="password" name="password" size="40"
								placeholder="パスワード" class="text-box"><br>
								<a href="Request-email.jsp" class="link">
								パスワードをお忘れですか？</a>
						</li>
					</ul>
					<div class="completion">
						<input type="submit" class="btn-square-so-pop" value="完了"><br>
						<a href="Signup.jsp" class="link">
							アカウントをお持ちですか？
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
