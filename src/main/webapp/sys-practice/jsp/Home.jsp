<jsp:useBean id="user" scope="session" class="sys_practice.User" />
<jsp:useBean id="aws" scope="session" class="sys_practice.AWS" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="トップページ" />
	<jsp:param name="style" value="home" />
</jsp:include>

<%@ page import="java.sql.*" %>
<%
  // Read RDS connection information from the environment
  String dbName = "sys_practice";
  String userName = "admin";
  String password = "AraikenR4!";
  String hostname = "syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com";
  String port = "3306";
  String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
		  "?user=" + userName + "&password=" + password + 
		  "characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
  // Load the JDBC driver
  try {
    System.out.println("Loading driver...");
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded!");
  } catch (ClassNotFoundException e) {
    throw new RuntimeException("Cannot find the driver in the classpath!", e);
  }

  Connection conn = null;
  Statement setupStatement = null;
  Statement readStatement = null;
  ResultSet resultSet = null;
  String results = "";
  int numresults = 0;
  String statement = null;

  try {
    // Create connection to RDS DB instance
    conn = DriverManager.getConnection(jdbcUrl);
    
    // Create a table and write two rows
    setupStatement = conn.createStatement();
    String createTable = "CREATE TABLE Beanstalk (Resource char(50));";
    String insertRow1 = "INSERT INTO Beanstalk (Resource) VALUES ('EC2 Instance');";
    String insertRow2 = "INSERT INTO Beanstalk (Resource) VALUES ('RDS Instance');";
    
    setupStatement.addBatch(createTable);
    setupStatement.addBatch(insertRow1);
    setupStatement.addBatch(insertRow2);
    setupStatement.executeBatch();
    setupStatement.close();
    
  } catch (SQLException ex) {
    // Handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
  } finally {
    System.out.println("Closing the connection.");
    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
  }

  try {
    conn = DriverManager.getConnection(jdbcUrl);
    
    readStatement = conn.createStatement();
    resultSet = readStatement.executeQuery("SELECT Resource FROM Beanstalk;");

    resultSet.first();
    results = resultSet.getString("Resource");
    resultSet.next();
    results += ", " + resultSet.getString("Resource");
    
    resultSet.close();
    readStatement.close();
    conn.close();

  } catch (SQLException ex) {
    // Handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
  } finally {
       System.out.println("Closing the connection.");
      if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
  }
%>

<div class="intro">
	<span class="cover"></span>
	<p><%=aws.getRemoteConnection() %></p>
	<%
		System.err.println(aws.getRemoteConnection());
	%>
	<p>Established connection to RDS. Read first two rows: <%= results %></p>
	<h1>
		あなたの好きをここに<br>クラフトボス最強
	</h1>
</div>

<div class="recommend">
	<div class="inner">
		<div class="content">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">あなたへのおすすめ</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					for (int i = 0; i < 10; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="id" value="3039202" />
						<jsp:param name="price" value="500" />
						<jsp:param name="thumbnail" value="./../img/106.jpg" />
						<jsp:param name="category" value="BGM" />
						<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
					</jsp:include>
					<%
					}
					%>
				</div>
				<!--  <div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
				-->
			</div>
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">今週の人気ランキング</h2>
				</div>
				<div class="material-card-wrapper">
					<%
					for (int i = 0; i < 10; i++) {
					%>
					<jsp:include page="./../components/Material-Card.jsp">
						<jsp:param name="id" value="3039202" />
						<jsp:param name="price" value="500" />
						<jsp:param name="thumbnail" value="./../img/106.jpg" />
						<jsp:param name="category" value="BGM" />
						<jsp:param name="title" value="タイトルタイトルタイトルタイトルタイトルタイトルタイトル" />
					</jsp:include>
					<%
					}
					%>
				</div>
				<div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
			</div>

			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">カテゴリー</h2>
				</div>
				<div class="category-list">
					<a href=<%="List.jsp?category_id="+512%> class="btn-square">カテゴリー1</a>
					<a href=<%="List.jsp?category_id="+212%> class="btn-square">カテゴリー2</a>
				</div>
				<div class="add">
					<a href="#" class="btn-gradient-radius">もっとみる</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />

</body>
</html>