package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn {

	protected int[] userId = new int[100];//ユーザーID
	protected String[] email = new String[50]; //eメール
	protected String[] password = new String[20]; //パスワード
	protected String[] displayName = new String[50];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[] questionTitle = new String[50];//秘密の質問の質問内容
	protected String[] questionAnswer = new String[50];//秘密の質問に対する答え
	protected String[] explanation = new String[100];//自己紹介文
	protected String[] icon = new String[50]; //アイコン
	protected int[] wallet = new int[100]; //財布
	protected int num;//データ取得件数

	private static Connection getRemoteConnection() throws SQLException {
		if (System.getenv("syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com") != null) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbName = System.getenv("eddb");
				String userName = System.getenv("admin");
				String password = System.getenv("AraikenR4!");
				String hostname = System.getenv("syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com");
				String port = System.getenv("3306");
				String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName
						+ "&password=" + password;
				Connection con = DriverManager.getConnection(jdbcUrl);
				return con;
			} catch (ClassNotFoundException e) {
			} catch (SQLException e) {
			}
		}
		return null;
	}

	public int signIn(String email, String password) throws Exception {
		num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "SELECT * FROM  user WHERE email Like ? AND password Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				this.userId[num] = rs.getInt("userId");
				this.email[num] = rs.getString("email");
				this.password[num] = rs.getString("password");
				this.displayName[num] = rs.getString("displayName");
				this.questionId[num] = rs.getInt("questionId");
				this.questionAnswer[num] = rs.getString(" questionAnswer");
				this.explanation[num] = rs.getString("explanation");
				this.icon[num] = rs.getString("passWord");
				this.wallet[num] = rs.getInt("wallet");
				num++;
			}

			rs.close();
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	public int requestQuestionId(String email) throws Exception {
		num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "SELECT questionId,questionAnswer FROM  user WHERE email Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				this.questionId[num] = rs.getInt("questionId");
				this.questionAnswer[num] = rs.getString("questionAnswer");
				num++;
			}

			rs.close();
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	public int requestQuestionTitle(int questionId) throws Exception {
		num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "SELECT * FROM  question WHERE questionId Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, questionId);
			stmt.setMaxRows(100);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				this.questionId[num] = rs.getInt("questionId");
				this.questionTitle[num] = rs.getString("questionTitle");
				num++;
			}

			rs.close();
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	public int resetPassWord(String questionAnswer, String email, String password) throws Exception {
		num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "UPDATE user SET password Like ? WHERE questionAnswer Like ? and email Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, questionAnswer);
			stmt.setString(3, email);
			stmt.setMaxRows(100);
			stmt.executeUpdate();

			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	/* アクセッサ */
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
