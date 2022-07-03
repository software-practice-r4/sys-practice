package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {

	protected int[] userId = new int[100]; //ユーザーID
	protected String[] email = new String[50]; //eメール
	protected String[] password = new String[20]; //パスワード
	protected String[] displayName = new String[50];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[] questionAnswer = new String[50];//秘密の質問の応え
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

	public int signUp(String email, String password, int questionId, String questionAnswer) {
		int num = 0;//取得件数の初期化
		try {
			Connection conn = getRemoteConnection();

			String sql = "INSERT INTO user (email,password,questionId,questionAnswer) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setInt(3, questionId);
			stmt.setString(4, questionAnswer);

			num = stmt.executeUpdate();

			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	/*アクセッサ */
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