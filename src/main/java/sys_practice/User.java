package sys_practice;

import java.sql.Connection;
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
	/* ユーザーIDと合致するお金を取得する*/
	public int getMoneyById(int userId) throws Exception {
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "select wallet from user where userId = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(100); //最大の数を制限
			resultSet = stmt.executeQuery();
			
			int wallet = 0;
			
			while (resultSet.next()) {
				wallet = resultSet.getInt("wallet");
			}

			stmt.close();
			resultSet.close();
			conn.close();
			
			if(wallet == 0) {
				return -1;
			}
			return wallet;

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

			int userId = -1;

			while (resultSet.next()) {
				userId = resultSet.getInt("userId");
			}

			stmt.close();
			resultSet.close();
			conn.close();

			return userId;

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

			String displayName = "";

			while (resultSet.next()) {
				displayName = resultSet.getString("displayName");
			}

			stmt.close();
			resultSet.close();
			conn.close();
			
			return displayName;

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

	/*
	 * 受け取ったユーザIDがuser内に存在するかどうかを判定する
	 * @author kotaro
	 * @param int userId
	 * @return 存在すればtrue,存在しなければfalseを返却
	 * */
	public boolean hasUserIdOnDatabase(int userId) throws Exception {

		Connection conn = null;
		ResultSet resultSet = null;
		try {
			num = 0;
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM user WHERE userId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(10); //最大の数を制限

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				this.userId[num] = resultSet.getInt("userId");
				num++;
			}

			stmt.close();
			resultSet.close();
			conn.close();

			if(num >= 1)
				return true;
			
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
		return false;
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