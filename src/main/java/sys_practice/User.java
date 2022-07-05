package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

	/* 1. フィールドの定義 */
	protected int[] userId = new int[100];
	protected String[] email = new String[100];
	protected String[] password = new String[100];
	protected int[] questionId = new int[100];
	protected String[] questionAnswer = new String[100];
	protected String[] displayName = new String[100];
	protected String[] explanation = new String[100];
	protected String[] icon = new String[100];
	protected int[] wallet = new int[100];
	protected int num;//データ件数
	int agmntUserId;//引数用のuserId

	/*agmntUserIdと一致したレコードを削除する*/
	public void deleteUser(int agmntUserId) throws Exception {

		/*データベースに接続*/
		Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
		String url = "jdbc:mysql://localhost/softd2?characterEncoding=UTF-8"; //データベース名は適宜修正：文字エンコードはUTF-8
		Connection conn = DriverManager.getConnection(url, "admin", "AraikenR4!"); //上記URL設定でユーザ名とパスワードを使って接続

		/*SELECT文の実行 */
		String sql = "DELETE * FROM user WHERE userid = ?";
		PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
		stmt.setMaxRows(100); //最大の数を制限
		stmt.setString(1, String.valueOf(agmntUserId));
		ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

		/*結果の取り出しと表示*/
		num = 0;
		while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
			this.userId[num] = rs.getInt("userId");
			this.email[num] = rs.getString("email");
			this.password[num] = rs.getString("password");
			this.questionId[num] = rs.getInt("questionId");
			this.questionAnswer[num] = rs.getString("questionAnswer");
			this.displayName[num] = rs.getString("displayName");
			this.explanation[num] = rs.getString("explanation");
			this.icon[num] = rs.getString("icon");
			this.wallet[num] = rs.getInt("wallet");
			num++;
		}

		/*データベースからの切断*/
		rs.close(); //開いた順に閉じる
		stmt.close();
		conn.close();
	}

	/*agmntUserIdと一致したレコードを削除する*/
	public void getMoneybyId(int agmntUserId) throws Exception {

		/*データベース接続*/
		Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
		String url = "jdbc:mysql://localhost/softd2?characterEncoding=UTF-8"; //データベース名は適宜修正：文字エンコードはUTF-8
		Connection conn = DriverManager.getConnection(url, "admin", "AraikenR4!"); //上記URL設定でユーザ名とパスワードを使って接続

		/*SELECT文の実行 */
		String sql = "SELECT * FROM user WHERE userid = ?";
		PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
		stmt.setMaxRows(100); //最大の数を制限
		stmt.setString(1, String.valueOf(agmntUserId));
		ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

		/*結果の取り出しと表示*/
		num = 0;
		while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
			this.userId[num] = rs.getInt("userId");
			this.email[num] = rs.getString("email");
			this.password[num] = rs.getString("password");
			this.questionId[num] = rs.getInt("questionId");
			this.questionAnswer[num] = rs.getString("questionAnswer");
			this.displayName[num] = rs.getString("displayName");
			this.explanation[num] = rs.getString("explanation");
			this.icon[num] = rs.getString("icon");
			this.wallet[num] = rs.getInt("wallet");
			num++;
		}

		/*データベース切断*/
		rs.close();
		stmt.close();
		conn.close();
	}

	public int getUserId(int i) {
		if (i >= 0 && num > i) {
			return userId[i];
		} else {
			return 0;
		}
	}

	public String getEmail(int i) {
		if (i >= 0 && num > i) {
			return email[i];
		} else {
			return "";
		}
	}

	public String getPassword(int i) {
		if (i >= 0 && num > i) {
			return password[i];
		} else {
			return "";
		}
	}

	public int getQuestionId(int i) {
		if (i >= 0 && num > i) {
			return questionId[i];
		} else {
			return "";
		}
	}

	public String getQuestionAnswer(int i) {
		if (i >= 0 && num > i) {
			return questionAnswer[i];
		} else {
			return "";
		}
	}

	public String getDisplayName(int i) {
		if (i >= 0 && num > i) {
			return displayName[i];
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

	public String getIcon(int i) {
		if (i >= 0 && num > i) {
			return icon[i];
		} else {
			return "";
		}
		}

	public int getWallet(int i) {
		if (i >= 0 && num > i) {
			return wallet[i];
		} else {
			return 0;
		}
	}

	public int getNum() {
		return num;
	}
}
