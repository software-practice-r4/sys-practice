package sys_practice;

//SQLに関連したクラスライブラリをインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EditProfile {

	/* 1. フィールドの定義 */
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
	protected int num;

	/*プロフィール編集*/
	public int editProfile(String userIdAfter, String email, String displayName, String explain, String icon,
			String userId) throws Exception { //エラー処理が必要にする
		/* 2.1.1 データベースに接続 */
		try {
			num = 0; //取得件数の初期化
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
			String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8"; //データベース名：文字エンコードはUTF-8
			Connection conn = DriverManager.getConnection(url, "softd", "softd"); //上記URL設定でユーザ名とパスワードを使って接続

			/* 2.1.2 UPDATE文の実行 *///
			String sql = "UPDATE user SET userId=?,email=?,displayName=?,explain=?,icon=? WHERE userId Like ?"; //SQL文の設定 ?などパラメータが必要がない場合は通常のStatementを利用
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setString(1, userIdAfter); //1つ目の？に引数をセットする
			stmt.setString(2, email);
			stmt.setString(3, displayName);
			stmt.setString(4, explain);
			stmt.setString(5, icon);
			stmt.setString(6, userId);

			/* 2.3.3 実行 */
			stmt.executeUpdate();

			/* 2.5 データベースからの切断 */
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	/* 3. アクセッサ */
	/* 3.1 Getアクセッサ */
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
