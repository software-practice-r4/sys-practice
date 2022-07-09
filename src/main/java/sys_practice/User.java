package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @author shusaku
 * @version 1.0
 * */

public class User {

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
	

	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;
	
	
	/*
	 * ユーザーIDと合致する全データをロードする
	 * @author shuya
	 * @param int userId
	 * */
	public void dataloadById(int userId) {
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM user WHERE userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				this.userId[0] = resultSet.getInt("userId");
				this.email[0] = resultSet.getString("email");
				this.displayName[0] = resultSet.getString("displayName");
				this.explanation[0] = resultSet.getString("explanation");
				this.icon[0] = resultSet.getString("icon");

			}
			System.err.println(this.icon[0]);
			
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

	/* ユーザーIDと合致するお金を取得する*/
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
	
	/*
	 * メールアドレスと合致するユーザーIDを取得する
	 * @author shuya
	 * @param String email @マークついたままのもの
	 * @return メールアドレスと合致したユーザーIDを返却
	 * */
	public int getUserIdByEmail(String email) {
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT userId FROM user WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();
			
			int[] userId = new int[100];
			
			while (resultSet.next()) {
				userId[0] = resultSet.getInt("userId");
			}

			stmt.close();
			resultSet.close();
			conn.close();

			return userId[0];

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
	 * ユーザーIDと合致する表示名を取得する
	 * @author shuya
	 * @param int userId
	 * @return 合致したユーザーIDを返却
	 * */
	public String getDisplayNameById(int userId) {
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT displayName FROM user WHERE userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();
			
			String[] displayName = new String[100];
			
			while (resultSet.next()) {
				displayName[0] = resultSet.getString("displayName");
			}

			stmt.close();
			resultSet.close();
			conn.close();

			return displayName[0];

		// TODO: catch時のリターン値を決める
		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "";
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}
	
	public int getUserId(int i) {
		if (i >= 0) {
			return userId[i];
		} else {
			return 00;
		}
	}

	public String getEmail(int i) {
		if (i >= 0) {
			return email[i];
		} else {
			return null;
		}
	}

	public String getPassword(int i) {
		if (i >= 0) {
			return password[i];
		} else {
			return null;
		}
	}

	public int getQuestionId(int i) {
		if (i >= 0) {
			return questionId[i];
		} else {
			return 00;
		}
	}

	public String getQuestionAnswer(int i) {
		if (i >= 0 && num > i) {
			return questionAnswer[i];
		} else {
			return null;
		}
	}

	public String getDisplayName(int i) {
		if (i >= 0) {
			return displayName[i];
		} else {
			return null;
		}
	}

	public String getExplanation(int i) {
		if (i >= 0) {
			return explanation[i];
		} else {
			return null;
		}
	}

	public String getIcon(int i) {
		if (i >= 0) {
			return icon[i];
		} else {
			return null;
		}
		}

	public int getWallet(int i) {
		if (i >= 0) {
			return wallet[i];
		} else {
			return 000;
		}
	}

	public int getNum() {
		return num;
	}
}
