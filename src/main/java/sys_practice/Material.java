package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Material {

	/* 1. フィールドの定義 */
	protected String[] materialName = new String[100];
	protected String[] thumbnail = new String[100];
	protected String[] explanation = new String[100];
	protected int[] materialId = new int[100];
	protected int[] price = new int[100];
	protected int[] categoryId = new int[100];
	protected int[] providerId = new int[100];
	protected int[] isAdult = new int[100];
	protected int num;

	public void listMaterial() throws Exception { //エラー処理が必要にする

		/*データベースに接続*/
		Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
		String url = "jdbc:mysql://localhost/softd2?characterEncoding=UTF-8"; //データベース名は適宜修正：文字エンコードはUTF-8
		Connection conn = DriverManager.getConnection(url, "admin", "AraikenR4!"); //上記URL設定でユーザ名とパスワードを使って接続

		/*SELECT文の実行 */
		String sql = "SELECT * FROM Material";
		PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
		stmt.setMaxRows(100); //最大の数を制限
		ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

		/* 2.1.3 結果の取り出しと表示 */
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

		/*データベースからの切断*/
		rs.close(); //開いた順に閉じる
		stmt.close();
		conn.close();
	}

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

	public void setExplanation(String[] explanation) {
		this.explanation = explanation;
	}

	public int getMaterialId(int i) {
		if(i>=0 && num>i) {
			return materialId[i];
		}else {
			return 0;
		}
	}

	public int getPrice(int i) {
		if(i>=0 && num>i) {
			return price[i];
		}else {
		return 0;
		}
	}

	public int getCategoryId(int i) {
		if(i>=0 && num>i) {
			return categoryId[i];
		}else {
		return 0;
		}
	}

	public int getProviderId(int i) {
		if(i>=0 && num>i) {
			return providerId[i];
		}else {
		return 0;
		}
	}

	public boolean getIsAdult(int i) {
		if(i>=0 && num>i) {
			return isAdult[i];
		}else {
		return false;
		}
	}


}
