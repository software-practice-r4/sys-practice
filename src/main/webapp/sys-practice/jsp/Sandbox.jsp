<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="sb" scope="session" class="sys_practice.Search" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>講義科目の表示</title>
</head>
<body>
	<header>
		<h1>講義科目の表示</h1>
	</header>
	<article>
		データ件数：<%=sb.getNum()%>
		<table border="1">
			<tr>
				<th>素材名</th>
				<th>サムネイル</th>
				<th>説明文</th>
				<th>素材ID</th>
				<th>値段</th>
				<th>カテゴリーId</th>
				<th>提供者ID</th>
				<th>年齢制限</th>
			</tr>
			<%
			for (int i = 0; i < sb.getNum(); i++) {
			%>
			<tr>
				<td><%=sb.getMaterialName(i)%></td>
				<td><%=sb.getThumbnail(i)%></td>
				<td><%=sb.getExplanation(i)%></td>
				<td><%=sb.getMaterialId(i)%></td>
				<td><%=sb.getPrice(i)%></td>
				<td><%=sb.getCategoryId(i)%></td>
				<td><%=sb.getProviderId(i)%></td>
				<td><%=sb.getIsAdult(i)%></td>
			</tr>
			<%
			}
			%>
		</table>
	</article>
</body>
</html>