package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * @author kotaro
 * @version 1.0
 * */

public class Upload{
	protected String[] materialName = new String[100];
	protected int price;
	protected String[] thumbnail = new String[70];
	protected int categoryId;
	protected int providerId;
	protected String[] explanation = new String[300];
	protected String[] categoryName =  new String[10];
	protected int[] isAdult;
	
	Connection conn = null;
	
	public int uploadMaterial(String materialName, String explanation, String price, String categoryId, String categoryName, String isAdult, String providerId, String thumbnail) throws Exception{
		int result = -1;
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			/* categoryIdでその他が選択されているとき */
			if(Integer.parseInt(categoryId) == 0) {
	
			    /* categoryに新規カテゴリを追加 */
			    String sql = "INSERT INTO category (categoryName) VALUES (?)";
			    PreparedStatement stmt1 = conn.prepareStatement(sql);
			    stmt1.setString(1, categoryName);
			    stmt1.executeUpdate();
	
			    stmt1.close();
	
	
			    /* 新規カテゴリのIDをcategoryから取り出し */
			    String sql2 = "SELECT categoryId FROM category WHERE categoryName = ?";
			    PreparedStatement stmt2 = conn.prepareStatement(sql2);
			    stmt2.setString(1, categoryName);
			    ResultSet rs = stmt2.executeQuery();
			    categoryId = rs.getString("categoryId");
	
			    rs.close();
			    stmt2.close();
		    }
	
			/* Materialへ素材を追加 */
		    String sql = "INSERT INTO material (materialName,price,thumbnail,categoryId,providerId,explanation,isAdult) VALUES (?,?,?,?,?,?,?)";
		    PreparedStatement stmt3 = conn.prepareStatement(sql);
		    stmt3.setString(1, materialName);
		    stmt3.setInt(2, Integer.parseInt(price));
		    stmt3.setString(3, thumbnail);
		    stmt3.setInt(4, Integer.parseInt(categoryId));
		    stmt3.setInt(5, Integer.parseInt(providerId));
		    stmt3.setString(6, explanation);
		    stmt3.setInt(7, Integer.parseInt(isAdult));
	
		    result = stmt3.executeUpdate();
	
		    stmt3.close();
		    conn.close();
		    
		    return result;
		}catch(Exception e) {
		    conn.close();
		    e.printStackTrace();
		    return -1;
		}
	}
}
