package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Material {

	/* 1. フィールドの定義 */
	protected String[] materialName = new String[100];
	protected String[] thumbnail = new String[100];
	protected String[] explanation = new String[100];
	protected int[] materialId = new int[100];
	protected int[] price = new int[100];
	protected int[] categoryId = new int[100];
	protected String[] categoryName = new String[100];
	protected int[] providerId = new int[100];
	protected String[] displayName = new String[100]; // 提供者の名前を格納
	protected int[] isAdult = new int[100];
	protected int num;

	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;
	
	/*
	 * 最新の素材を読み込み、フィールドに格納する
	 * */
	public void listMaterial() throws Exception { //エラー処理が必要にする
		try {

			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

			/* 2.1.3 結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialName[num] = rs.getString("materialName");
				this.thumbnail[num] = rs.getString("thumbnail");
				this.explanation[num] = rs.getString("explanation");
				this.materialId[num] = rs.getInt("materialId");
				this.price[num] = rs.getInt("price");
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num]=rs.getString("categoryName");
				this.providerId[num] = rs.getInt("providerId");
				this.isAdult[num] = rs.getInt("isAdult");
				num++;
			}

			/*データベースからの切断*/
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
	
	/*
	 * ランダムに素材を取得する
	 * ランキング機能の代わり
	 * */
	public void getRandomMaterial() throws Exception { //エラー処理が必要にする
		try {

			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId"
					   + " ORDER BY RAND() LIMIT 10";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

			/* 2.1.3 結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialName[num] = rs.getString("materialName");
				this.thumbnail[num] = rs.getString("thumbnail");
				this.explanation[num] = rs.getString("explanation");
				this.materialId[num] = rs.getInt("materialId");
				this.price[num] = rs.getInt("price");
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num]=rs.getString("categoryName");
				this.providerId[num] = rs.getInt("providerId");
				this.isAdult[num] = rs.getInt("isAdult");
				num++;
			}

			/*データベースからの切断*/
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
	
	/*
	 * ユーザーIDと合致する素材を取得する
	 * @author shuya
	 * @param int userId
	 * */
	public void getMaterialByUserId(int userId) {
		num = 0; // 取得したデータの量を格納
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "select * from material inner join category on material.categoryId = category.categoryId where providerId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.materialName[num] = resultSet.getString("materialName");
				this.thumbnail[num] = resultSet.getString("thumbnail");
				this.explanation[num] = resultSet.getString("explanation");
				this.materialId[num] = resultSet.getInt("materialId");
				this.price[num] = resultSet.getInt("price");
				this.categoryId[num] = resultSet.getInt("categoryId");
				this.categoryName[num] = resultSet.getString("categoryName");
				this.providerId[num] = resultSet.getInt("providerId");
				this.isAdult[num] = resultSet.getInt("isAdult");
				num++;
			}

			stmt.close();
			resultSet.close();
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

	/*
	 * 素材IDと合致する素材を取得する
	 * @author shuya
	 * @param int userId
	 * @return 取得したデータ数 0 <= num <= 100
	 * */
	public int getMaterialByMaterialId(int materialId) {
		num = 0; // 取得したデータの量を格納
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM material INNER JOIN user ON material.providerId = user.userId "
					+ "INNER JOIN category on material.categoryId = category.categoryId WHERE materialId = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, materialId);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.materialId[num] = resultSet.getInt("materialId");
				this.materialName[num] = resultSet.getString("materialName");
				this.thumbnail[num] = resultSet.getString("thumbnail");
				this.explanation[num] = resultSet.getString("explanation");
				this.price[num] = resultSet.getInt("price");
				this.providerId[num] = resultSet.getInt("providerId");
				this.categoryId[num] = resultSet.getInt("categoryId");
				this.categoryName[num] = resultSet.getString("categoryName");
				this.displayName[num] = resultSet.getString("displayName");
				this.isAdult[num] = resultSet.getInt("isAdult");
				num++;
			}
			
			stmt.close();
			resultSet.close();
			conn.close();

			return num;

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return 0;
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}

	/*
	 * 投稿者が同じ素材を取得
	 * @author shuya
	 * @param int userId
	 * @return 取得したデータ数 0 <= num <= 10
	 * */
	public int getProviderMaterial(int userId) {
		num = 0; // 取得したデータの量を格納
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM material INNER JOIN user ON material.providerId = user.userId "
					+ "INNER JOIN category on material.categoryId = category.categoryId WHERE providerId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(10); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.materialId[num] = resultSet.getInt("materialId");
				this.materialName[num] = resultSet.getString("materialName");
				this.thumbnail[num] = resultSet.getString("thumbnail");
				this.explanation[num] = resultSet.getString("explanation");
				this.price[num] = resultSet.getInt("price");
				this.providerId[num] = resultSet.getInt("providerId");
				this.categoryId[num] = resultSet.getInt("categoryId");
				this.categoryName[num] = resultSet.getString("categoryName");
				this.displayName[num] = resultSet.getString("displayName");
				this.isAdult[num] = resultSet.getInt("isAdult");
				num++;
			}

			stmt.close();
			resultSet.close();
			conn.close();

			return num;

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return 0;
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}

	/*
	 * カテゴリーIDが同じ素材をランダムに取得
	 * @author shuya
	 * @param int userId
	 * @return 取得したデータ数 0 <= num <= 10
	 * */
	public int getSameCategoryMaterial(int categoryId) {
		num = 0; // 取得したデータの量を格納
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM material INNER JOIN category ON material.categoryId = category.categoryId "
					+ "inner join user on material.providerid = user.userId WHERE material.categoryId = ? ORDER BY RAND() limit 10;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setMaxRows(10); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.materialId[num] = resultSet.getInt("materialId");
				this.materialName[num] = resultSet.getString("materialName");
				this.thumbnail[num] = resultSet.getString("thumbnail");
				this.explanation[num] = resultSet.getString("explanation");
				this.price[num] = resultSet.getInt("price");
				this.providerId[num] = resultSet.getInt("providerId");
				this.categoryId[num] = resultSet.getInt("categoryId");
				this.categoryName[num] = resultSet.getString("categoryName");
				this.displayName[num] = resultSet.getString("displayName");
				this.isAdult[num] = resultSet.getInt("isAdult");
				num++;
			}

			stmt.close();
			resultSet.close();
			conn.close();

			return num;

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return 0;
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}

	public String getMaterialName(int i) {
		if (i >= 0) {
			return materialName[i];
		} else {
			return "";
		}
	}

	public String getThumbnail(int i) {
		if (i >= 0) {
			return thumbnail[i];
		} else {
			return "";
		}
	}

	public String getExplanation(int i) {
		if (i >= 0) {
			return explanation[i];
		} else {
			return "";
		}
	}

	public void setExplanation(String[] explanation) {
		this.explanation = explanation;
	}

	public int getMaterialId(int i) {
		if (i >= 0) {
			return materialId[i];
		} else {
			return 0;
		}
	}

	public int getPrice(int i) {
		if (i >= 0) {
			return price[i];
		} else {
			return 0;
		}
	}

	public int getCategoryId(int i) {
		if (i >= 0) {
			return categoryId[i];
		} else {
			return 0;
		}
	}

	public String getCategoryName(int i) {
		if (i >= 0) {
			return categoryName[i];
		} else {
			return "";
		}
	}

	public int getProviderId(int i) {
		if (i >= 0) {
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
		if (i >= 0) {
			return isAdult[i];
		} else {
			return -1;
		}
	}

	public int getNum() {
		return num;
	}

}
