
package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* 
 * @author shusaku
 */

public class Category {

	protected int[] categoryId = new int[100];
	protected String[] categoryName = new String[100];
	int num;

	/*
	 * カテゴリー一覧を取得する
	 * return カテゴリー名、カテゴリーID
	*/
	public void dispCategory() {
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

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	/* 
	 * 引数で渡されたIDと合致するカテゴリー名を返却する
	 * @author shuya 
	 * @params int categoryId
	 * @returs 合致したカテゴリー名 または 空文字
	 * */
	public String getCategoryNameById(int categoryId) {
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "SELECT * FROM category WHERE categoryId = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();
			
			String categoryName = "";
			
			/*結果の取り出しと表示*/
			while (rs.next()) {
				categoryName = rs.getString("categoryName");
			}
			
			// IDと合致するカテゴリー名がない場合、nullが返却されるため
			// 空文字をここで返却する
			if(categoryName == null) {
				return "";
			}
			return categoryName;
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