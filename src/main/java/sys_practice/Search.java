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

	/*
	 * 入力された情報(キーワード＝素材名の一部または全部、カテゴリーId, 価格,　年齢制限)から素材を検索する
	 * @author shusaku
	 * @param String keywoed
	 * @param String searchCategoryId
	 * @param String searchPrice
	 * @param String searchIsAdult
	 * */
	public void getmaterial(String keyword, int searchCategoryId, int searchPrice, int searchIsAdult) throws Exception {
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			/*入力フォームで検索*/
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId"+
			" WHERE materialName like %"+keyword+"%";
			
			/*-1==入力されていない -1!=入力済み*/

			if(searchCategoryId==-1 && searchPrice==-1 && searchIsAdult!=-1) {
				/*年齢制限だけ入力されている*/
				sql+=" AND searchIsAdult="+searchIsAdult;
			}
			else if(searchCategoryId==-1 && searchPrice!=-1 && searchIsAdult==-1) {
				/*価格だけ入力されている*/
				sql+=" AND searchPrice="+searchPrice;
			}
			else if(searchCategoryId==-1 && searchPrice!=-1 && searchIsAdult!=-1) {
				/*価格と年齢制限が入力されている*/
				sql+=" AND searchPrice="+searchPrice+" AND searchIsAdult="+searchIsAdult;
			}
			else if(searchCategoryId!=-1 && searchPrice==-1 && searchIsAdult==-1) {
				/*カテゴリーIDだけ入力されている*/
				sql+=" AND searchCategoryId="+searchCategoryId;
			}
			else if(searchCategoryId!=-1 && searchPrice==-1 && searchIsAdult!=-1) {
				/*カテゴリーIDと年齢制限が入力されている*/
				sql+=" AND searchCategoryId="+searchCategoryId+" AND searchIdAdult="+searchIsAdult;
			}
			else if(searchCategoryId!=-1 && searchPrice!=-1 && searchIsAdult==-1) {
				/*カテゴリーIDと価格が入力されている*/
				sql+=" AND searchCategoryId="+searchCategoryId+" AND searchPrice="+searchPrice;
			}
			else if(searchCategoryId==-1 && searchPrice!=-1 && searchIsAdult==-1) {
				/*全ての情報が入力されている*/
				sql+=" AND searchCategoryId="+searchCategoryId+" AND searchPrice="+searchPrice+" AND searchIsAdult="+searchIsAdult;
			}
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

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
	
/*ヘッダーでの検索*/
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
