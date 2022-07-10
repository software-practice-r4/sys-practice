package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author keita
 * @version 1.0
 * */

public class SignUp {

	protected int[] userId = new int[100];
	protected String[] email = new String[50];
	protected String[] password = new String[20];
	protected String[] displayName = new String[50];
	protected int[] questionId = new int[100];
	protected String[] questionTitle = new String[50];
	protected String[] questionAnswer = new String[50];
	protected int num;//データ取得件数
	protected int[] cnt = new int[100];//実行回数

	Connection conn = null;
	Statement readStatement = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;

	/*
	 * ユーザーIDと合致する表示名を取得する
	 * @param String email
	 * @param String password
	 * @param int questionId
	 * @param String questionAnswer
	 * @return 挿入された行数を返却 エラー時には-1返す
	 * */
	public int signUp(String email, String password, int questionId, String questionAnswer) {
		int num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "INSERT INTO user (email,password,displayName,questionId,questionAnswer,wallet)"
					+ "VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setInt(4, questionId);
			ps.setString(5, questionAnswer);
			ps.setInt(6, 0); // wallet

			num = ps.executeUpdate();

			System.out.println("num" + num);

			ps.close();
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

	public int detaloadQuestion() throws Exception {
		num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM question";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.questionId[num] = resultSet.getInt("questionId");
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
		if (i >= 0) {
			return questionId[i];
		} else {
			return 0;
		}
	}

	public String getQuestionAnswer(int i) {
		if (i >= 0) {
			return questionAnswer[i];
		} else {
			return "";
		}
	}

	public String getQuestionTitle(int i) {
		if (i >= 0 && num > i) {
			return questionTitle[i];
		} else {
			return "";
		}
	}

	public int getNum() {
		return num;
	}
}