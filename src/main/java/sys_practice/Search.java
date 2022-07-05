package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Search extends Material {

	/*フィールド定義*/
	String keyword;
	String searchCategoryId;
	String searchPrice;
	String searchIsAdult;

	/*素材検索メソッド*/
	public void getMaterial(String keyword, String searchCategoryId, String searchPrice, String searchIsAdult)
			throws Exception {
		/*ータベースに接続*/
		AWS aws = new AWS();
		Connection conn = aws.getRemoteConnection();

		num = 0;
		String sql = "SELECT * from material WHERE materialName like ? AND categoryId=? AMD price=? AMD isAdult=?";
		PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
		stmt.setMaxRows(100); //最大の数を制限
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, searchCategoryId);
		stmt.setString(3, searchPrice);
		stmt.setString(4, searchIsAdult);
		ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

		/*結果の取り出しと表示 */
		num = 0;
		while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
			this.materialName[num] = rs.getString("materialName");
			this.thumbnail[num] = rs.getString("thumbnail");
			this.explanation[num] = rs.getString("explanation");
			this.materialId[num] = rs.getInt("materialId");
			this.price[num] = rs.getInt("price");
			this.categoryId[num] = rs.getInt("categoryId");
			this.providerId[num] = rs.getInt("providerId");
			this.isAdult[num] = rs.getBoolean("isAdult");
			num++;
		}
		/* 2.1.4 データベースからの切断 */
		rs.close(); //開いた順に閉じる
		stmt.close();
		conn.close();
	}

	/*ゲッター*/
	public String getMaterialName(int i) {
		if (i >= 0 && num > i) {
			return materialName[i];
		} else {
			return "";
		}
	}

	public String getThumbnail(int i) {
		if (i >= 0 && num > i) {
			return thumbnail[i];
		} else {
			return "";
		}
	}

	public String getExplanation(int i) {
		if (i >= 0 && num > i) {
			return explanation[i];
		} else {
			return "";
		}
	}

	public int getMaterialId(int i) {
		if (i >= 0 && num > i) {
			return materialId[i];
		} else {
			return 0;
		}
	}

	public int getPrice(int i) {
		if (i >= 0 && num > i) {
			return price[i];
		} else {
			return 0;
		}
	}

	public int getCategoryId(int i) {
		if (i >= 0 && num > i) {
			return categoryId[i];
		} else {
			return 0;
		}
	}

	public int getProviderId(int i) {
		if (i >= 0 && num > i) {
			return providerId[i];
		} else {
			return 0;
		}
	}

	public boolean getIsAdult(int i) {
		if (i >= 0 && num > i) {
			return isAdult[i];
		} else {
			return false;
		}
	}

	public int getNum() {
		return num;
	}
}
