package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shusaku
 * @version 1.0
 */
public class Search extends Material {

	/*フィールド定義*/
	String keyword;
	int searchCategoryId;
	int searchPrice;
	int searchIsAdult;
	int param;

	/*
	 * 入力された情報(キーワード＝素材名の一部または全部、カテゴリーId, 価格,　年齢制限)から素材を検索する
	 * @author shusaku
	 * @param String keywoed
	 * @param String searchCategoryId
	 * @param String searchPrice
	 * @param String searchIsAdult
	 * */
	public void getMaterial(String keyword, int searchCategoryId, int searchPrice, int searchIsAdult) throws Exception {
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			/*入力フォームで検索*/
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId" +
					" WHERE materialName like '%"+keyword+"%'";

			/*-1==入力されていない -1!=入力済み*/
			/*年齢制限だけ入力されている*/
			if (!hasData(searchCategoryId) && !hasData(searchPrice) && hasData(searchIsAdult)) {
				sql += " AND isAdult=" + searchIsAdult;
			}
			/*価格だけ入力されている*/
			else if (!hasData(searchCategoryId) && hasData(searchPrice) && !hasData(searchIsAdult)) {
				sql += " AND price=" + searchPrice;
			}
			/*価格と年齢制限が入力されている*/
			else if (!hasData(searchCategoryId) && hasData(searchPrice) && hasData(searchIsAdult)) {
				sql += " AND price=" + searchPrice + " AND isAdult=" + searchIsAdult;
			}
			/*カテゴリーIDだけ入力されている*/
			else if (hasData(searchCategoryId) && !hasData(searchPrice) && !hasData(searchIsAdult)) {
				sql += " AND material.categoryId=" + searchCategoryId;
			}
			/*カテゴリーIDと年齢制限が入力されている*/
			else if (hasData(searchCategoryId) && !hasData(searchPrice) && hasData(searchIsAdult)) {
				sql += " AND material.categoryId=" + searchCategoryId + " AND isAdult=" + searchIsAdult;
			}
			/*カテゴリーIDと価格が入力されている*/
			else if (hasData(searchCategoryId) && hasData(searchPrice) && !hasData(searchIsAdult)) {
				sql += " AND material.categoryId=" + searchCategoryId + " AND price=" + searchPrice;
			}
			/*全ての情報が入力されている*/
			else if (hasData(searchCategoryId) && hasData(searchPrice) && hasData(searchIsAdult)) {
				sql += " AND material.categoryId=" + searchCategoryId + " AND price=" + searchPrice
						+ " AND isAdult=" + searchIsAdult;
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

			num = 0;
			while (rs.next()) {
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

			rs.close();
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

	/**
	 * ヘッダー上で検索ボタンを押した際に、キーワードで検索をかけ、合致した素材をフィールドに格納する
	 * @author shusaku
	 * @param keyword
	 * @throws Exception
	 */
	public void searchHeader(String keyword) throws Exception {
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
	
	public String createWhere(String value) {
		/*<option value>をsql文のWHERE句に置換*/
		String where="";
		if (value.equals("lt500")) {
			/*price < 500 500未満*/
			where= "<500";
		} else if (value.equals("mt500lt2000")) {
			/*price BETWEEN 500 AND 1999 500以上2000未満*/
			where=" BETWEEN 500 AND 1999";
		} else if (value.equals("mt2000lt5000")) {
			/*price BETWEEN 2000 AND 4999 2000以上5000未満*/
			where=" BETWEEN 2000 AND 4999";
		} else if (value.equals("mt5000")) {
			/*price >= 5000 5000以上*/
			where=">=5000";
		}

		return where;
	}
	
	public void narrowDown(int ndCategoryId, String ndPrice, int ndIsAdult) throws Exception {
		try {
			AWS aws = new AWS();
			/*AWSに接続*/
			Connection conn = aws.getRemoteConnection();

			/*materialテーブルとcategoryテーブルを内部結合したテーブルから抽出(materialテーブルにカラム：categoryNameがないため)*/
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId WHERE"
					+ " material.categoryId=" + ndCategoryId + " AND price" + createWhere(ndPrice) + " AND isAdult=" + ndIsAdult;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();
			System.out.println("sql=" + sql);

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
	
	/*home.jspの下部で使用。カテゴリー名のボタン押下でそのカテゴリー別検索を行う*/
	public void searchByCategory(int ndCategoryId) throws Exception {
		try {
			AWS aws = new AWS();
			/*AWSに接続*/
			Connection conn = aws.getRemoteConnection();

			/*materialテーブルとcategoryテーブルを内部結合したテーブルから抽出(materialテーブルにカラム：categoryNameがないため)*/
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId WHERE"
					+ " material.categoryId=" + ndCategoryId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();
			System.out.println("sql=" + sql);

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
	
	
	
	/**
	 * @author shusaku
	 * @param param カテゴリーID、価格、R-18などを入れる
	 * @return {true / false}
	 */
	public boolean hasData(int param) {
		if (param == -1) {
			return false;
		} else {
			return true;
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
