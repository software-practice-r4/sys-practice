package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Upload{
	protected String[] materialName = new String[100]; //タイトル
	protected int price; //価格
	protected String[] thumbnail = new String[70]; //サムネイル
	protected int categoryId; //カテゴリID
	protected int providerId; //プロバイダID
	protected String[] explanation = new String[300]; //説明文
	protected String[] category =  new String[10]; //カテゴリ
	protected String[] fileName = new String[100]; //ファイル名

	public void uploadMaterial(String materialName, String explanation, String price, String categoryId, String category, String providerId, String thumbnail, String fileName) throws Exception {
		
		AWS aws = new AWS();
		Connection conn = aws.getRemoteConnection();

		/* categoryIdでその他が選択されているとき */
		if(Integer.parseInt(categoryId) == 0) {

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
		    categoryId = rs.getString("categoryId");

		    rs.close();
		    stmt2.close();
	    }

		/* Materialへ素材を追加 */
	    String sql = "INSERT INTO Material (materialName,price,thumbnail,categoryId,providerId,explanation) VALUES (?,?,?,?,?,?,?)";
	    PreparedStatement stmt3 = conn.prepareStatement(sql);
	    stmt3.setString(1, materialName);
	    stmt3.setInt(2, Integer.parseInt(price));
	    stmt3.setString(3, thumbnail);
	    stmt3.setInt(4, Integer.parseInt(categoryId));
	    stmt3.setInt(5, Integer.parseInt(providerId));
	    stmt3.setString(6, explanation);

	    stmt3.executeUpdate();

	    stmt3.close();
	    conn.close();
	}
}