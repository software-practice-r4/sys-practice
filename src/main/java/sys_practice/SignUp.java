package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public Connection getRemoteConnection() throws SQLException {
		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}

		String userName = "admin";
		String password = "AraikenR4!";
		String hostname = "syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com";
		String port = "3306";
		String dbName = "sys_practice";
		String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
				"?user=" + userName + "&password=" + password;
		Connection con = DriverManager.getConnection(jdbcUrl);

		return con;
	}

	public int signUp(String email, String password, int questionId, String questionAnswer) {
		int num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "INSERT INTO user (email,password,questionId,questionAnswer) VALUES (?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, questionId);
			ps.setString(4, questionAnswer);
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

	public void detaloadQuestion() throws Exception {
		num = 0;//取得件数の初期化
		try {
			conn = getRemoteConnection();
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

	public String getQuestionAnswer(int i) {
		if (i >= 0 && num > i) {
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