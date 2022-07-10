package sys_practice;

import java.sql.Connection;
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
	protected String[] category = new String[10]; //カテゴリ
	protected boolean[] isAdult = new boolean[100];
	protected int numresults; //データ取得件数

	public void getTrend(int providerId) throws Exception {

		Connection conn = null;
		ResultSet resultSet = null;
		try {
			numresults = 0;
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String getTrend = "SELECT * FROM downloaded INNER JOIN material ON downloaded.materialId = material.materialId"
					 + "RIGHT JOIN category ON material.categoryId = category.categoryId WHRERE providerId = ? ORDER BY downloaded DESC LIMIT 10";
			PreparedStatement stmt = conn.prepareStatement(getTrend);
			stmt.setInt(1, providerId);
			stmt.setMaxRows(10); //最大の数を制限

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.materialId[numresults] = resultSet.getInt("materialId");
				this.materialName[numresults] = resultSet.getString("materialName");
				this.price[numresults] = resultSet.getInt("price");
				this.thumbnail[numresults] = resultSet.getString("thumbnail");
				this.explanation[numresults] = resultSet.getString("explanation");
				this.category[numresults] = resultSet.getString("categoryName");
				numresults++;
			}

			stmt.close();
			resultSet.close();
			conn.close();

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}

	public int getMaterialId(int i) {
		if (i >= 0 && numresults > i) {
			return materialId[i];
		} else {
			return 0;
		}
	}

	public String getMaterialName(int i) {
		if (i >= 0 && numresults > i) {
			return materialName[i];
		} else {
			return "";
		}
	}

	public int getPrice(int i) {
		if (i >= 0 && numresults > i) {
			return price[i];
		} else {
			return 0;
		}
	}

	public String getThumbnail(int i) {
		if (i >= 0 && numresults > i) {
			return thumbnail[i];
		} else {
			return "";
		}
	}

	public String getExplanation(int i) {
		if (i >= 0 && numresults > i) {
			return explanation[i];
		} else {
			return "";
		}
	}

	public String getCategory(int i) {
		if (i >= 0 && numresults > i) {
			return category[i];
		} else {
			return "";
		}
	}

	public int getNumResults() {
		return numresults; // データ件数
	}

}