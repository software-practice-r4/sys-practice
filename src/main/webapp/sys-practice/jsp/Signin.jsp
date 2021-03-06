<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="サインインページ" />
	<jsp:param name="style" value="signin" />
</jsp:include>

<div id="C" class="inner">
	<div class="post">
		<div class="centering-ttl-box">
			<h2 class="centering-ttl">ログイン</h2>
		</div>
		<form action="<%=request.getContextPath() %>/signin" method="POST">
			<div class="information">
				<%if(isNull){ %>
					<p class="err-txt">どちらかの値が空です。</p>
				<%} %>
				<%if(isErr){ %>
					<p class="err-txt">メールアドレスまたは、パスワードが間違っています。</p>
				<%} %>
				<ul>
					<li style="margin-top: 40px;">メールアドレス： <br> <input
						type="email" name="email" size="40" placeholder="メールアドレス"
						class="text-box">
					</li>
					<br>
					<li>パスワード：<br> <input type="password" name="password"
						size="40" placeholder="パスワード" class="text-box"> <br>
						<%-- <a href="Request-email.jsp" class="link link-color-init" style="display: inline-block; margin-top: 20px;"> パスワードをお忘れですか？ </a>--%>
					</li>
					<br>
				</ul>
				<div class="completion">
					<input type="submit" class="btn-square-so-pop" value="送信"><br>
					<a href="Signup.jsp" class="link link-color-init"> アカウントをお持ちですか？ </a>
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
