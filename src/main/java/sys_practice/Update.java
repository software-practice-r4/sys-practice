package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {
	protected int materialId;
	protected String[] materialName = new String[100]; //タイトル
	protected int price; //価格
	protected String[] thumbnail = new String[70]; //サムネイル
	protected int categoryId; //カテゴリID
	protected int providerId; //プロバイダID
	protected String[] explanation = new String[300]; //説明文
	protected String[] category =  new String[10]; //カテゴリ
	protected String[] fileName = new String[100]; //ファイル名

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


	public void updateMaterial(int materialId, String materialName, String explanation, int price, String category, String thumbnail, String fileName,  int categoryId, int providerId) throws Exception {

		Connection conn = getRemoteConnection("sys-practice");

		/* categoryIdでその他が選択されているとき */
		if(categoryId == 0) {

		    /* categoryに新規カテゴリを追加 */
		    String sql = "INSERT INTO category (categoryName) VALUES (?)";
		    PreparedStatement stmt1 = conn.prepareStatement(sql);
		    stmt1.setString(1, category);
		    stmt1.executeUpdate();

		    stmt1.close();


		    /* 新規カテゴリのIDをcategoryから取り出し */
		    String sql2 = "SELECT categoryId FROM category WHERE categoryName = ?";
		    PreparedStatement stmt2 = conn.prepareStatement(sql2);
		    stmt2.setString(1, category);
		    ResultSet rs = stmt2.executeQuery();
		    categoryId = rs.getInt("categoryId");

		    rs.close();
		    stmt2.close();
	    }

		/* Materialの素材を更新 */
	    String sql = "UPDATE Material SET materialName=?, price =?, thumbnail=?, categoryId=?, providerId=?, explanation=? WHERE materialId=?";
	    PreparedStatement stmt3 = conn.prepareStatement(sql);
	    stmt3.setString(1, materialName);
	    stmt3.setInt(2, price);
	    stmt3.setString(3, thumbnail);
	    stmt3.setInt(4, categoryId);
	    stmt3.setInt(5, providerId);
	    stmt3.setString(6, explanation);
	    stmt3.setInt(7, materialId);

	    stmt3.executeUpdate();

	    stmt3.close();
	    conn.close();
	}
}