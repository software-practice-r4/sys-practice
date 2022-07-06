<jsp:useBean id="sign" scope="session" class="sys_practice.SignUp" />
<%
request.setCharacterEncoding("UTF-8");
String email = "";
String password = "";
String questionAnswer = "";
int questionId = 0;
boolean errFlag = false;

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

	int err = sign.signUp(email, password, questionId, questionAnswer);
	if (request.getParameter("email").equals("password") ||
	request.getParameter("questionId").equals("questionAnswer")) {
		throw new Exception("いずかのパラメーターが不足しています。");
	}
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
System.err.println(e);
}
%>



<%
request.setCharacterEncoding("UTF-8");
try {
	sign.detaloadQuestion();
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
					<c:if test="${errFlag == true;}">
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
						<%
						for (int i = 0; i < 4; i++) {
						%>
						<option value="<%=sign.getQuestionId(i)%>">sign.getQuestionTitle(i)</option>
						<%
						}
						%>
					</select>
					<p>
						秘密の質問の解答：<br> <input type="text" name="questionAnswer"
							size="40" placeholder="秘密の質問" class="text-box"><br>
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

<%
} catch (Exception e) {

}
%>