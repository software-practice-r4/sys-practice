<jsp:useBean id="sign" scope="session" class="sys_practice.SignIn" />
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

	int err = sign.resetPassWord(questionAnswer, email, password);
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
<jsp:forward page="Secret-question.jsp" />
<%
}
%>