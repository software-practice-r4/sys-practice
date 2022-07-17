<jsp:useBean id="category" scope="session" class="sys_practice.Category" />
<%
try {
	category.dispCategory();
} catch (Exception e) {
%>
	System.err.println(e);
<%
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="sidebar">
			<h3>絞り込み検索</h3>
			<ul>
			<form action="./Narrowdown.jsp" method="post">
				<h1>カテゴリー</h1>
				<div class="select">
					<select name="category" class="text-box">
						<%
						for (int i = 0; i < category.getNum(); i++) {
						%>
						<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
						<%}%>
					</select>
				</div>
				<h1>価格</h1>
				<div class="select">
					<select name="price" class="text-box">
						<option value="A">～&yen;500</option>
						<option value="B">&yen;500～&yen;2000</option>
						<option value="C">&yen;2000～&yen;5000</option>
						<option value="D">&yen;5000～</option>
					</select>
				</div>
				<h1>年齢制限</h1>
				<div class="select">
					<select name="age" class="text-box">
						<option value="allages">全年齢</option>
						<option value="adult">R-18</option>
					</select>
				</div>
				<br> <input type="submit" value="絞り込む">
			</form>
			</ul>
		</div>