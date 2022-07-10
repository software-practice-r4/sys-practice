<jsp:useBean id="sign" scope="session" class="sys_practice.SignUp" />
<%
request.setCharacterEncoding("UTF-8");
boolean isNull = false;
boolean isErr = false;

if (request.getParameter("isNull") != null) {
	isNull = Boolean.valueOf(request.getParameter("isNull"));
}
if (request.getParameter("isErr") != null) {
	isErr = Boolean.valueOf(request.getParameter("isErr"));
}
%>

<%
request.setCharacterEncoding("UTF-8");
try {
	sign.detaloadQuestion();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
				<form action="<%=request.getContextPath()%>/signup" method="post">
					<%
					if (isNull) {
					%>
					<p class="err-txt">どちらかの値が空です。</p>
					<%
					}
					%>
					<p>
						メールアドレス：<br> <input type="email" name="email" size="40"
							placeholder="メールアドレス" class="text-box">
					</p>
					<p>
						パスワード：<br> <input type="password" name="password" size="40"
							placeholder="パスワード" class="text-box"><br>
					</p>
					<div class="question">
						<div class="title">
							<div class="message">秘密の質問の解答：</div>
							<select name="questionId" id="question">

								<%
								for (int i = 0; i < 5; i++) {
								%>
								<option value="<%=sign.getQuestionId(i)%>"><%=sign.getQuestionTitle(i)%></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="questionform">
							<%-- <br> --%>
							<input type="text" name="questionAnswer" size="40"
								placeholder="秘密の質問への解答" class="text-box"><br>
						</div>
					</div>
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
System.err.println(e);
}
%>
