package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Profile {

	protected String[] userId = new String[100]; //ユーザーID
	protected String[] userIdAfter = new String[100]; //ユーザーID
	protected String[] email = new String[100]; //eメール
	protected String[] passWord = new String[100]; //パスワード
	protected String[] displayName = new String[100];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[] answer = new String[100];//秘密の質問の応え
	protected String[] explain = new String[100];//自己紹介文
	protected String[] icon = new String[100]; //アイコン
	protected int[] wallet = new int[100]; //財布
	protected int num;//取得件数の初期化
	protected int count;//更新されたテーブルの個数

	public int editProfile(String userIdAfter, String email, String displayName, String explain, String icon,
			String userId) throws Exception {
		try {
			num = 0;
			count = 0;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8";
			Connection conn = DriverManager.getConnection(url, "softd", "softd");

			String sql = "UPDATE user SET";
			if (userId != null) {
				String sqlUserId = " userId=?";
				sql = sql.concat(sqlUserId);
				count += 1;
			}
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
				String sqlExplain = " explain=?";
				sql = sql.concat(sqlExplain);
				count += 1;
			}
			if (userId != null) {
				if (count > 0) {
					String sqlComma = ",";
					sql = sql.concat(sqlComma);
				}
				String sqlIcon = " icon=?";
				sql = sql.concat(sqlIcon);
			}
			String sqlTerms = " WHERE userId Like ?";
			sql = sql.concat(sqlTerms);

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userIdAfter);
			stmt.setString(2, email);
			stmt.setString(3, displayName);
			stmt.setString(4, explain);
			stmt.setString(5, icon);
			stmt.setString(6, userId);
			stmt.executeUpdate();

			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	public String getUserId(int i) {
		if (i >= 0 && num > i) {
			return userId[i];
		} else {
			return "";
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

	public String Answer(int i) {
		if (i >= 0 && num > i) {
			return answer[i];
		} else {
			return "";
		}
	}

	public String getExplain(int i) {
		if (i >= 0 && num > i) {
			return explain[i];
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
		return num; // データ件数
	}
}