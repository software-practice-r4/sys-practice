<jsp:useBean id="sign" scope="session" class="sys_practice.SignIn" />
<%
request.setCharacterEncoding("UTF-8");

String email = "";

try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}

	session.setAttribute("email", email);

	int err = sign.requestSecretQuestion(email);

if (err != 0) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="ひみつの質問ページ" />
	<jsp:param name="style" value="secret-question" />
</jsp:include>

<div id="C">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">ひみつの質問</h2>
		</div>
		<div class="inner">
			<div class="information">
				<c:if test="${err_flag == true;}">
					<p class="err-txt">入力が足りません</p>
				</c:if>
				<ul>
					<p>
						<%=sign.getQuestionTitle(0)%>
						<%=sign.getQuestionAnswer(0)%>
					</p>
					<p>
						解答：<br> <input type="text" name="questionAnswer" size="40"
							placeholder="解答" class="text-box">
					</p>
					<p>
						新しいパスワード：<br> <input type="password" name="password"
							size="40" placeholder="パスワード" class="text-box"><br>
					</p>
				</ul>
				<div class="completion">
					<input type="submit" class="btn-square-so-pop" value="完了"><br>
				</div>
				<div class="completion">
					<a href="signup.jsp" class="link"><h4>サインインページに戻る</h4></a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>

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
<jsp:forward page="Request-email.jsp" />
<%
}
%>


