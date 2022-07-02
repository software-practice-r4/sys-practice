package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Upload {
	protected String[] materialName = new String[100]; //タイトル
	protected int price; //価格
	protected String[] thumbnail = new String[70]; //サムネイル
	protected int categoryId; //カテゴリID
	protected int providerId; //プロバイダID
	protected String[] explanation = new String[300]; //説明文
	protected String[] category =  new String[10]; //カテゴリ
	protected String[] fileName = new String[100]; //ファイル名

	/* AWSの接続 */
	private static Connection getRemoteConnection() throws SQLException {
	    if (System.getenv("syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com") != null) {
	      try {
	      Class.forName("org.postgresql.Driver");
	      String dbName = System.getenv("eddb");
	      String userName = System.getenv("admin");
	      String password = System.getenv("AraikenR4!");
	      String hostname = System.getenv("syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com");
	      String port = System.getenv("3306");
	      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	      Connection con = DriverManager.getConnection(jdbcUrl);
	      return con;
	    }
	    catch (ClassNotFoundException e) {}
	    catch (SQLException e) {}
	    }
	    return null;
	  }


	public void uploadMaterial(String materialName, String explanation, int price, String category, String thumbnail, String fileName,  int categoryId, int providerId) throws Exception {

		/* categoryIdでその他が選択されているとき */
		if(categoryId == 0) {

			Connection con = getRemoteConnection();

		    /* categoryに新規カテゴリを追加 */
		    String sql = "INSERT INTO category (categoryName) VALUES (?)";
		    PreparedStatement stmt1 = con.prepareStatement(sql);
		    stmt1.setString(1, category);
		    stmt1.executeUpdate();

		    stmt1.close();


		    /* 新規カテゴリのIDをcategoryから取り出し */
		    String sql2 = "SELECT categoryId FROM category WHERE categoryName = ?";
		    PreparedStatement stmt2 = con.prepareStatement(sql2);
		    stmt2.setString(1, category);
		    ResultSet rs = stmt2.executeQuery();
		    categoryId = rs.getInt("categoryId");

		    rs.close();
		    stmt2.close();
		    con.close();
	    }

		/* Materialへ素材を追加 */
		Connection con = getRemoteConnection();

	    String sql = "INSERT INTO Material (materialName,price,thumbnail,categoryId,providerId,explanation) VALUES (?,?,?,?,?,?,?)";
	    PreparedStatement stmt3 = con.prepareStatement(sql);
	    stmt3.setString(1, materialName);
	    stmt3.setInt(2, price);
	    stmt3.setString(3, thumbnail);
	    stmt3.setInt(4, categoryId);
	    stmt3.setInt(5, providerId);
	    stmt3.setString(6, explanation);

	    stmt3.executeUpdate();

	    stmt3.close();
	    con.close();
	}
}