package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Profile {

	protected int[] userId = new int[100]; //ユーザーID
	protected String[] email = new String[50]; //eメール
	protected String[] passWord = new String[20]; //パスワード
	protected String[] displayName = new String[50];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[] questionAnswer = new String[50];//秘密の質問の応え
	protected String[] explanation = new String[100];//自己紹介文
	protected String[] icon = new String[50]; //アイコン
	protected int[] wallet = new int[100]; //財布
	protected int num;//データ取得件数
	protected int count;//更新されたデータの数

	public int editProfile(String email, String displayName, String explain, String icon, int userId) throws Exception {
		num = 0; //取得件数の初期化
		count = 0;//更新されたテーブルの個数
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql:/              characterEncoding=UTF-8";
			Connection conn = DriverManager.getConnection(url, "admin", "AraikenR4!");

			String sql = "UPDATE user SET";

			if (email != null) {
				if (count > 0) {
					String sqlComma = ",";
					sql = sql.concat(sqlComma);
				}
				String sqlEmail = " email=?";
				sql = sql.concat(sqlEmail);
				count += 1;
			}
			if (displayName != null) {
				if (count > 0) {
					String sqlComma = ",";
					sql = sql.concat(sqlComma);
				}
				String sqlDisplayName = " displayName=?";
				sql = sql.concat(sqlDisplayName);
				count += 1;
			}
			if (explain != null) {
				if (count > 0) {
					String sqlComma = ",";
					sql = sql.concat(sqlComma);
				}
				String sqlExplanation = " explain=?";
				sql = sql.concat(sqlExplanation);
				count += 1;
			}
			String sqlTerms = " WHERE userId Like ?";
			sql = sql.concat(sqlTerms);

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, displayName);
			stmt.setString(3, explain);
			stmt.setString(4, icon);
			stmt.setInt(5, userId);

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

	public String getPassWord(int i) {
		if (i >= 0 && num > i) {
			return passWord[i];
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
