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
	protected int num; //データ取得件数

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

	public void getTrend(int providerId) throws Exception {

		num=0; //取得件数の初期化
		Connection con = getRemoteConnection(); //上記URL設定でユーザ名とパスワードを使って接続

		String sql = "SELECT * FROM Material INNERJOIN Category ON Material.categoryId = Category.categoryId WHERE ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,providerId);
		stmt.setMaxRows(100); //最大の数を制限
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
		this.materialId[num] = rs,getInt("materialId");
		this.materialName[num] = rs.getString("materialName");
		this.price[num] = rs.getInt("price");
		this.fileName[num] = rs.getString("fileName");
		this.explanation[num] = rs.getString("explanation");
		this.category[num] = rs.getString("category");
		num++;
		}

		rs.close();
		stmt.close();
		con.close();
		}
	
		public String getmaterialId(int i) {
		if (i >= 0 && num > i) {
		return materialName[i];
		} else {
		return "";
		}
		}

		public String getmaterialName(int i) {
		if (i >= 0 && num > i) {
		return materialName[i];
		} else {
		return "";
		}
		}

		public int getprice(int i) {
		if (i >= 0 && num > i) {
		return price[i];
		} else {
		return 0;
		}
		}

		public String getfileName(int i) {
		if (i >= 0 && num > i) {
		return "./img/" + fileName[i];
		} else {
		return "";
		}
		}

		public String getexplanation(int i) {
		if (i >= 0 && num > i) {
		return explanation[i];
		} else {
		return "";
		}
		}

		public String getcategory(int i) {
		if (i >= 0 && num > i) {
		return category[i];
		} else {
		return "";
		}
		}

		public int getNum() {
		return num; // データ件数
		}

}