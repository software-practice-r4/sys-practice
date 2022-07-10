<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="material" scope="session" class="sys_practice.Material" />
<%


String displayName = user.getDisplayNameById(userId);;
String pageTitle = displayName + "さんのプロフィール";

user.dataloadById(userId);
material.getMaterialByUserId(userId);

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="<%=pageTitle%>" />
	<jsp:param name="style" value="profile" />
</jsp:include>

<div class="content">
	<div class="inner">
		<div class="intro">
			<div class="intro-top">
			<%
				String iconUrl = "./../img/" + user.getIcon(0);

			%>
				<img src="<%=iconUrl%>">
			</div>
			<div class="intro-bottom">
				<div class="user-information">
					<div class="lead-ttl">
						<h3>
							<%=user.getDisplayName(0) %>
						    <a href="Dm-detail.jsp" class="btn-circle-border-double">
								<i class="fa fa-envelope-o"></i>
							</a>
						</h3>
					</div>
				</div>
				<p class="txt" style="margin-top: 40px;">
					<%=user.getExplanation(0) %>
				</p>
			</div>
		</div>
	</div>
	<div class="inner" style="margin-top: 140px;">
		<div class="post">
			<div class="centering-ttl-box">
				<h2 class="centering-ttl">同じ作者の作品</h2>
			</div>
			<div class="material-card-wrapper">
				<%

				for (int i = 0; i < material.getNum(); i++) {
				%>
				<jsp:include page="./../components/Material-Card.jsp">
					<jsp:param name="materialId" value="<%=material.getMaterialId(i) %>" />
					<jsp:param name="price" value="<%=material.getPrice(i) %>" />
					<jsp:param name="thumbnail" value="<%=material.getThumbnail(i)%>" />
					<jsp:param name="category" value="<%=material.getCategoryName(i) %>" />
					<jsp:param name="title" value="<%=material.getMaterialName(i) %>" />
				</jsp:include>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>

<%--
<%
}
%>
<%
} catch (Exception e) {
%>

<%
}
%>
--%>
