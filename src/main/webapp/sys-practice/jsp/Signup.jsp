<jsp:useBean id="user" scope="session" class="sys_practice.SignUp" />
<%
/* エンコード */
request.setCharacterEncoding("UTF-8");

/* 変数の宣言　*/
String email = "";
String passWord = "";
String questionAnswer = "";

/* パラメータの取得 */
try {
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("passWord") != null) {
		passWord = request.getParameter("passWord");
	}
	int questionId = Integer.parseInt(request.getParameter("questionId"));
	if (request.getParameter("questionAnswer") != null) {
		questionAnswer = request.getParameter("questionAnswer");
	}

	session.setAttribute("email", email);
	session.setAttribute("passWord", passWord);
	session.setAttribute("questionId", questionId);
	session.setAttribute("questionAnswer", questionAnswer);

	/* Insertメソッドの実行 */
	int err = user.signUp(email, passWord, questionId, questionAnswer);//ID+関数名()
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
boolean err_flag = false;
if (request.getParameter("questionAnswer") == null) {
	err_flag = true;
}
if (request.getParameter("email") == null) {
	err_flag = true;
}
if (request.getParameter("passWord") == null) {
	err_flag = true;
}
if (Integer.parseInt(request.getParameter("questionId")) == 0) {
	err_flag = true;
}
%>
<%--<jsp:forward page="Signup.jsp" />--%>
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
						パスワード：<br> <input type="password" name="passWord" size="40"
							placeholder="パスワード" class="text-box"><br>
					</p>
					<p>
						質問Id：<br> <input type="text" name="questionId" size="40"
							placeholder="質問Id" class="text-box">
					</p>
					<p>
						秘密の質問：<br> <input type="text" name="questionAnswer" size="40"
							placeholder="秘密の質問" class="text-box"><br>
					</p>
				</form>
			</ul>
		</div>
		<div class="completion">
			<input type="submit" class="btn-square-so-pop" value="完了"></input><br>
			<a href="signin.jsp" class="link"><h4>アカウントをお持ちですか？</h4></a>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>