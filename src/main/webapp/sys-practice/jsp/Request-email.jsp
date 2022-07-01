<jsp:useBean id="user" scope="session" class="sys_practice.SignIn" />
<%
request.setCharacterEncoding("UTF-8");

String email = "";
String passWord = "";

try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}

	session.setAttribute("email", email);

	int err = user.requestQuestionId(email);
%>
<%
if (err != 0) {
%>
<jsp:forward page="Secret-question.jsp" />
<%
}
%>
<%
} catch (Exception e) {
boolean err_flag = false;
if (request.getParameter("email") == null) {
	err_flag = true;
}
%>
<%--<jsp:forward page="Signin.jsp" />--%>
<%
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="ひみつの質問リクエストページ" />
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
						<p>
							メールアドレス：<br> <input type="email" name="email" size="40"
								placeholder="メールアドレス" class="text-box">
						</p>
					</ul>
					<div class="completion">
						<input type="submit" class="btn-square-so-pop" value="完了"><br>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>