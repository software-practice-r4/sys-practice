<<<<<<< HEAD
<jsp:useBean id="user" scope="session" class="sys_practice.SignIn" />
<%
request.setCharacterEncoding("UTF-8");

int questionId = 0;//多分取れてない

try {
	if (request.getParameter("questionId") != null) {
		questionId = Integer.parseInt(request.getParameter("questionId"));
	}

	session.setAttribute("questionId", questionId);

	int err = user.requestQuestionTitle(questionId);

	if (err != 0) {
%>

<%
}
%>
<%
} catch (Exception e) {
%>
<jsp:forward page="Request-email.jsp" />
<%
}
=======
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
>>>>>>> branch 'develop' of https://github.com/software-practice-r4/sys-practice.git
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
<<<<<<< HEAD
						<%=user.getQuestionTitle(0)%>
=======
						<%=sign.getQuestionTitle(0)%>
						<%=sign.getQuestionAnswer(0)%>
>>>>>>> branch 'develop' of https://github.com/software-practice-r4/sys-practice.git
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

<<<<<<< HEAD

<%
request.setCharacterEncoding("UTF-8");

String questionAnswer = "";
String email = "";//取れてるかわからない
String password = "";

try {
	if (request.getParameter("questionAnswer") != null) {
		questionAnswer = request.getParameter("questionAnswer");
	}
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}

	session.setAttribute("questionAnswer", questionAnswer);
	session.setAttribute("email", email);
	session.setAttribute("password", password);

	int err = user.resetPassWord(questionAnswer, email, password);
%>
<%
if (err != 0) {
%>
<jsp:forward page="Signin.jsp" />
<%
}
%>
<%
} catch (Exception e) {
boolean err_flag = false;
if (request.getParameter("questionAnswer") == null) {
	err_flag = true;
}
if (request.getParameter("password") == null) {
	err_flag = true;
}
%>

<%
}
%>
=======
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

>>>>>>> branch 'develop' of https://github.com/software-practice-r4/sys-practice.git

