package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trend {
	protected int[] materialId = new int[100000]; //素材ID
	protected String[] materialName = new String[100]; //タイトル
	protected int[] price = new int[100000]; //価格
	protected String[] thumbnail = new String[70]; //サムネイル
	protected int[] categoryId = new int[100]; //カテゴリID
	protected int[] providerId = new int[1000000]; //プロバイダID
	protected String[] explanation = new String[300]; //説明文
	protected String[] category =  new String[10]; //カテゴリ
	protected String[] fileName = new String[100]; //ファイル名
	protected int numresults; //データ取得件数

	/* AWSの接続 */
	private static Connection getRemoteConnection(String dbName) throws SQLException {
		// JDBCドライバー読み込み
		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}

		String userName = "admin";
		String password = "AraikenR4!";
		String hostname = "syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com";
		String port = "3306";
		String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
				"?user=" + userName + "&password=" + password;
		Connection con = DriverManager.getConnection(jdbcUrl);

		return con;
	}

	public void getTrend(int providerId) throws Exception {

		Connection conn = null;
	    ResultSet resultSet = null;
	    try {
	      numresults = 0;
	      conn = getRemoteConnection("sys-practice");
	      String getTrend = "SELECT * FROM Material INNERJOIN Category ON Material.categoryId = Category.categoryId WHERE ?";
	      PreparedStatement stmt = conn.prepareStatement(getTrend);
	      stmt.setInt(1,providerId);
	      stmt.setMaxRows(100); //最大の数を制限

	      resultSet = stmt.executeQuery();

	      while (resultSet.next()) {
			this.materialId[numresults] = resultSet.getInt("materialId");
			this.materialName[numresults] = resultSet.getString("materialName");
			this.price[numresults] = resultSet.getInt("price");
			this.fileName[numresults] = resultSet.getString("fileName");
			this.explanation[numresults] = resultSet.getString("explanation");
			this.category[numresults] = resultSet.getString("category");
			numresults++;
			}

	      stmt.close();
	      resultSet.close();
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
		}

		public int getmaterialId(int i) {
		if (i >= 0 && numresults > i) {
		return materialId[i];
		} else {
		return 0;
		}
		}

		public String getmaterialName(int i) {
		if (i >= 0 && numresults > i) {
		return materialName[i];
		} else {
		return "";
		}
		}

		public int getprice(int i) {
		if (i >= 0 && numresults > i) {
		return price[i];
		} else {
		return 0;
		}
		}

		public String getfileName(int i) {
		if (i >= 0 && numresults > i) {
		return "./img/" + fileName[i];
		} else {
		return "";
		}
		}

		public String getexplanation(int i) {
		if (i >= 0 && numresults > i) {
		return explanation[i];
		} else {
		return "";
		}
		}

		public String getcategory(int i) {
		if (i >= 0 && numresults > i) {
		return category[i];
		} else {
		return "";
		}
		}

		public int getNum() {
		return numresults; // データ件数
		}

}