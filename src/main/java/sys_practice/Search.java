package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends Material {

	/*フィールド定義*/
	String keyword;
	int searchCategoryId;
	int searchPrice;
	int searchIsAdult;

	/*素材検索メソッド*/
	public void getmaterial(String keyword, int searchCategoryId, int searchPrice, int searchIsAdult) throws Exception {
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "SELECT * FROM material inner join category ON material.categoryId = category.categoryId" +
					" AND materialName like ? AND material.categoryId=? AND price=? AND isAdult=?";
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setMaxRows(100); //最大の数を制限
			stmt.setString(1, "%" + keyword + "%");
			stmt.setInt(2, searchCategoryId);
			stmt.setInt(3, searchPrice);
			stmt.setInt(4, searchIsAdult);
			ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

			/*結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialId[num] = rs.getInt("materialId");
				this.materialName[num] = rs.getString("materialName");
				this.price[num] = rs.getInt("price");
				this.thumbnail[num] = rs.getString("thumbnail");
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num] = rs.getString("categoryName");
				this.providerId[num] = rs.getInt("providerId");
				this.explanation[num] = rs.getString("explanation");
				this.isAdult[num] = rs.getInt("isAdult");
				num++;
			}
			/* 2.1.4 データベースからの切断 */
			rs.close(); //開いた順に閉じる
			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}

	public void getmaterial(String keyword) throws Exception {
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "SELECT * FROM material inner join category on material.categoryId = category.categoryId" +
					" WHERE materialName like ?";
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setMaxRows(100); //最大の数を制限
			stmt.setString(1, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

			/*結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialId[num] = rs.getInt("materialId");
				this.materialName[num] = rs.getString("materialName");
				this.price[num] = rs.getInt("price");
				this.thumbnail[num] = rs.getString("thumbnail");
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num] = rs.getString("categoryName");
				this.providerId[num] = rs.getInt("providerId");
				this.explanation[num] = rs.getString("explanation");
				this.isAdult[num] = rs.getInt("isAdult");
				num++;
			}
			/* 2.1.4 データベースからの切断 */
			rs.close(); //開いた順に閉じる
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}

	/*ゲッター*/
	public String getMaterialName(int i) {
		if (0 <= i && i < num) {
			return materialName[i];
		} else {
			return "";
		}
	}

	public String getThumbnail(int i) {
		if (0 <= i && i < num) {
			return thumbnail[i];
		} else {
			return "";
		}
	}

	public String getExplanation(int i) {
		if (0 <= i && i < num) {
			return explanation[i];
		} else {
			return "";
		}
	}

	public void setExplanation(String[] explanation) {
		this.explanation = explanation;
	}

	public int getMaterialId(int i) {
		if (0 <= i && i < num) {
			return materialId[i];
		} else {
			return 0;
		}
	}

	public int getPrice(int i) {
		if (0 <= i && i < num) {
			return price[i];
		} else {
			return 0;
		}
	}

	public int getCategoryId(int i) {
		if (0 <= i && i < num) {
			return categoryId[i];
		} else {
			return 0;
		}
	}

	public String getCategoryName(int i) {
		if (0 <= i && i < num) {
			return categoryName[i];
		} else {
			return "";
		}
	}

	public int getProviderId(int i) {
		if (0 <= i && i < num) {
			return providerId[i];
		} else {
			return 0;
		}
	}

	public String getDisplayName(int i) {
		if (0 <= i && i < num) {
			return displayName[i];
		} else {
			return "";
		}
	}

	public int getIsAdult(int i) {
		if (0 <= i && i < num) {
			return isAdult[i];
		} else {
			return 0;
		}
	}

	public int getNum() {
		return num;
	}
}
