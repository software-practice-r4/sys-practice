<jsp:useBean id="sign" scope="session" class="sys_practice.SignUp" />
<%
request.setCharacterEncoding("UTF-8");

String email = "";
String password = "";
String questionAnswer = "";
int questionId = 0;//取れてるか心配
String dbName = "sys-practice";

try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	if (request.getParameter("questionId") != null) {
		questionId = Integer.parseInt(request.getParameter("questionId"));
	}

	if (request.getParameter("questionAnswer") != null) {
		questionAnswer = request.getParameter("questionAnswer");
	}

	session.setAttribute("email", email);
	session.setAttribute("password", password);
	session.setAttribute("questionId", questionId);
	session.setAttribute("questionAnswer", questionAnswer);

	int err = 1;
	sign.signUp(email, password, questionId, questionAnswer);
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
boolean err_flag = false;
if (request.getParameter("questionAnswer") == null) {
	err_flag = true;
}
if (request.getParameter("email") == null) {
	err_flag = true;
}
if (request.getParameter("password") == null) {
	err_flag = true;
}
%>

<%
}
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインアップページ" />
	<jsp:param name="style" value="signup" />
</jsp:include>

<div id="C">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">アカウント作成</h2>
		</div>
		<div class="information">
			<ul>
				<form action="" method="post">
					<c:if test="${err_flag == true;}">
						<p class="err-txt">入力ミス</p>
					</c:if>
					<p>
						メールアドレス：<br> <input type="email" name="email" size="40"
							placeholder="メールアドレス" class="text-box">
					</p>
					<p>
						パスワード：<br> <input type="password" name="password" size="40"
							placeholder="パスワード" class="text-box"><br>
					</p>
					<select name="questionId" id="question">
						<option value="1">卒業した中学校は？</option>
						<option value="2">卒業した小学校は？</option>
						<option value="3">好きな食べ物は</option>
						<option value="4">ほったいもいじんな</option>
					</select>
					<p>
						秘密の質問：<br> <input type="text" name="questionAnswer" size="40"
							placeholder="秘密の質問" class="text-box"><br>
					</p>
				</form>
			</ul>
		</div>
		<div class="completion">
			<input type="submit" class="btn-square-so-pop" value="完了"></input><br>
			<a href="Signin.jsp" class="link"><h4>アカウントをお持ちですか？</h4></a>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>