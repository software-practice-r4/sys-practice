package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * @author keita
 * @version 1.0
 * */
public class SignIn {

	protected int[] userId = new int[100];
	protected String[] email = new String[50];
	protected String[] password = new String[20];
	protected String[] displayName = new String[50];
	protected int[] questionId = new int[100];
	protected String[] questionTitle = new String[50];
	protected String[] questionAnswer = new String[50];
	protected String[] explanation = new String[100];
	protected String[] icon = new String[50];
	protected int[] wallet = new int[100];
	protected int num;//データ取得件数
	protected int[] cnt = new int[100];//実行回数

	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;

	/*
	 * @author shuya
	 * @author keita
	 * eメール・パスワードと合致するユーザー情報を取得する
	 * @param String email
	 * @param String password
	 * @return データの取得件数を返却 エラー時には-1返す
	 * */
	public int signIn(String email, String password) throws Exception {
		num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM user WHERE email Like ? and password Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.email[num] = resultSet.getString("email");
				this.password[num] = resultSet.getString("password");
				this.displayName[num] = resultSet.getString("displayName");
				this.questionId[num] = resultSet.getInt("questionId");
				this.questionAnswer[num] = resultSet.getString("questionAnswer");
				this.explanation[num] = resultSet.getString("explanation");
				this.icon[num] = resultSet.getString("icon");
				this.wallet[num] = resultSet.getInt("wallet");
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

	/*(未実装)
	 * @author keita
	 * eメールから自身の秘密の質問の情報(Id,Title,Answer)を取得する
	 * @param String email
	 * @return データの取得件数を返却 エラー時には-1返す
	 * */
	public int requestSecretQuestion(String email) throws Exception {
		num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT user.questionId, user.questionAnswer, question.questionTitle FROM user FULL OUTER JOIN question ON user.questionId = question.questionId WHERE email Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.questionId[num] = resultSet.getInt("questionId");
				this.questionAnswer[num] = resultSet.getString("questionAnswer");
				this.questionTitle[num] = resultSet.getString("questionTitle");
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

	public int requestSecretQuestion(String email) throws Exception {
		num = 0;//取得件数の初期化
		try {
			conn = getRemoteConnection();
			String sql = "SELECT user.questionId, user.questionAnswer, question.questionTitle FROM user FULL OUTER JOIN question ON user.questionId = question.questionId WHERE email Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.questionId[num] = resultSet.getInt("questionId");
				this.questionAnswer[num] = resultSet.getString("questionAnswer");
				this.questionTitle[num] = resultSet.getString("questionTitle");
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
      
			return -1;
      
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}

	/*(未実装)
	 * @author keita
	 * eメール・秘密の質問の解答から自身のパスワードを更新する
	 * @param String questionAnswer
	 * @param String email
	 * @param String password
	 * @return 成功時1を返却 エラー時には-1返す
	 * */
	public int resetPassWord(String questionAnswer, String email, String password) throws Exception {
		int num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			Connection conn = aws.getRemoteConnection();

			String sql = "UPDATE user SET password Like ? WHERE questionAnswer Like ? and email Like ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, questionAnswer);
			ps.setString(3, email);
			ps.addBatch(sql);
			cnt = ps.executeBatch();
			if (cnt != null) {
				num = 1;
			}

			ps.close();
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

	public String getDisplayName(int i) {
		if (i >= 0 && num > i) {
			return displayName[i];
		} else {
			return "";
		}
	}

	public int getQuestionId(int i) {
		if (i >= 0 && num > i) {
			return questionId[i];
		} else {
			return 0;
		}
	}

	public String getQuestionTitle(int i) {
		if (i >= 0 && num > i) {
			return questionTitle[i];
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