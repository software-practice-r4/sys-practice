
package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* 
 * @author shusaku
 */

public class Category extends Material{

	protected int[] categoryId = new int[100];
	protected String[] categoryName = new String[100];
	int num;

	/*
	 * return カテゴリー名、カテゴリーID
	*/
	public int dispCategory() {
		num = 0;
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "SELECT * FROM category";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();

			/*結果の取り出しと表示*/
			num = 0;
			while (rs.next()) {
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num] = rs.getString("categoryName");
				num++;
			}
			return num;
		} catch (SQLException e) {
			return 0;
		}
	}

	/* author shuya */
	public String getCategoryNameById(int categoryId) {
		num = 0;
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "SELECT * FROM category WHERE categoryId="+categoryId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();

			/*結果の取り出しと表示*/
			while (rs.next()) {
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num] = rs.getString("categoryName");
				num++;
			}
			return categoryName[0];
		} catch (SQLException e) {
			return "";
		}
	}

	public int getCategoryId(int i) {
		if (i >= 0 && num > i) {
			return categoryId[i];
		} else {
			return 0;
		}
	}

	public String getCategoryName(int i) {
		if (i >= 0 && num > i) {
			return categoryName[i];
		} else {
			return "";
		}
	}

	public int getNum() {
		return num; // データ件数
	}

}