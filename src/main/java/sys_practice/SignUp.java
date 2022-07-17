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
	protected String[] explanation = new String[100];
	protected String[] icon = new String[50];
	protected int[] wallet = new int[100];
	protected int num;//データ取得件数
	protected int[] cnt = new int[100];//実行回数

	Connection conn = null;
	Statement readStatement = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;

	/*
	 * ユーザーのアカウントを作成する
	 * @param String email
	 * @param String password
	 * @param int questionId
	 * @param String questionAnswer
	 * @return 挿入された行数を返却 エラー時には-1返す
	 * */
	public int signUp(String email, String password, int questionId, String questionAnswer) throws Exception {
		int num = 0;//取得件数の初期化
		try {

			System.out.println("email: " + email);
			System.out.println("password: " + password);
			System.out.println("questionId: " + questionId);
			System.out.println("questionAnswer: " + questionAnswer);

			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "insert into user (email,password,displayName,questionId,questionAnswer,wallet) "
					+ "select * from (select ?,?,?,?,?,?) AS tmp where exists (select * from question where questionId = ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			String displayName =  email.substring(0, email.lastIndexOf("@"))
					+ String.valueOf(System.currentTimeMillis() / 1000L);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, displayName);
			ps.setInt(4, questionId);
			ps.setString(5, questionAnswer);
			ps.setInt(6, 0); // wallet
			ps.setInt(7, 0);
			
			num = ps.executeUpdate();

			ps.close();
			conn.close();
			
			// 更新されなかったときに、-1を返すようにする
			if(num == 0) {
				return -1;
			}
			return num;

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			
			return -1;

		}catch(Exception e) {
			System.err.println(e);
			
			return -1;
		}
		finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}
	/*
	 * 秘密の質問を取得する
	 * @return 取得された件数を返却 エラー時には-1返す
	 * */
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

	/*
	 * 入力されたemailに該当するアカウントを取得する
	 * @param String email
	 * @return ユーザーデータの個数を返却 正常時1or0 エラー時には-1返す
	 * */
	public int existsAccount(String email) throws Exception {
		num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	/*
	 * 受け取ったquestionIdが、questionテーブル内に存在する個数を取得する
	 * @param int questionId
	 * @return 個数を返却 正常時1or0 エラー時には-1返す
	 * */
	public int existsQuestion(int questionId) throws Exception {
		num = 0;//取得件数の初期化
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM question WHERE questionId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, questionId);
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
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
