package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Upload {
	/* 1. フィールドの定義 */
	protected String[] materialName = new String[100]; //タイトル
	protected int price; //価格
	protected String[] thumbnail = new String[70]; //サムネイル
	protected int categoryId; //カテゴリID
	protected int providerId; //プロバイダID
	protected String[] explanation = new String[300]; //説明文
	protected String[] category =  new String[10]; //カテゴリ
	protected String[] fileName = new String[100]; //ファイル名

	/* 2.2 データベースへのインサートメソッド */
	public void uploadMaterial(String materialName, String explanation, int price, String category, String thumbnail, String fileName,  int categoryId, int providerId) throws Exception {
	    if(categoryId == 0) {
	    	/* 2.2.1 データベースに接続 */
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String url = "jdbc:mysql://localhost/softd1?characterEncoding=UTF-8";
		    Connection conn = DriverManager.getConnection(url, "softd", "softd");

		    /* 2.2.2 INSERT文の実行 */
		    String sql = "INSERT INTO Category (categoryName) VALUES (?)";
		    PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
		    stmt.setString(1, category); //1つ目の？に引数をセットする

		    /* 2.2.3 実行（UpdateやDeleteも同じメソッドを使う） */
		    stmt.executeUpdate();

		    /* データベースからの切断 */
		    stmt.close();
		    conn.close();


		    /* データベースに接続 */
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
		    Connection conn2 = DriverManager.getConnection(url, "softd", "softd"); //上記URL設定でユーザ名とパスワードを使って接続

		    /* 2.1.2 SELECT文の実行 */ String sql2 = "SELECT categoryId FROM category WHERE categoryName = ?"; //SQL文の設定 ?などパラメータが必要がない場合は通常のStatementを利用
		    PreparedStatement stmt2 = conn2.prepareStatement(sql2); //JDBCのステートメント（SQL文）の作成
		    stmt2.setString(1, category); //1つ目の？に引数をセットする
		    ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入
		    /* 結果の取り出しと表示 */
		    categoryId = rs.getInt("categoryId");

		    /* データベースからの切断 */
		    rs.close();
		    stmt2.close();
		    conn.close();
	    }

		/* データベースに接続 */
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    String url = "jdbc:mysql://localhost/softd1?characterEncoding=UTF-8";
	    Connection conn = DriverManager.getConnection(url, "softd", "softd");

	    /* INSERT文の実行 */
	    String sql = "INSERT INTO Material (materialName,price,thumbnail,categoryId,providerId,explanation) VALUES (?,?,?,?,?,?,?)";
	    PreparedStatement stmt = conn.prepareStatement(sql); //SQL文の作成
	    stmt.setString(1, materialName); //1つ目の？に引数をセットする
	    stmt.setInt(2, price); //2つ目の？に引数をセットする
	    stmt.setString(3, thumbnail); //3つ目の？に引数をセットする
	    stmt.setInt(4, categoryId); //4つ目の？に引数をセットする
	    stmt.setInt(5, providerId); //5つ目の？に引数をセットする
	    stmt.setString(6, explanation); //6つ目の？に引数をセットする

	    /* 実行 */
	    stmt.executeUpdate();

	    /* データベースからの切断 */
	    stmt.close();
	    conn.close();
	}
}