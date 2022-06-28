package sys_practice;

//SQLに関連したクラスライブラリをインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUp {

	/* 1. フィールドの定義 */
	protected int[] userId = new int[100]; //ユーザーID
	protected String[] email = new String[50]; //eメール
	protected String[] passWord = new String[20]; //パスワード
	protected String[] displayName = new String[50];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[]  questionAnswer = new String[50];//秘密の質問の応え
	protected String[] explanation = new String[100];//自己紹介文
	protected String[] icon = new String[50]; //アイコン
	protected int[] wallet = new int[100]; //財布
	protected int num;

	/* サインアップ */
	public int signUp(String email, String passWord, int questionId, String questionAnswer) {
		int num = 0; //登録件数のカウント
		try {
			/* 2.2.1 データベースに接続 */
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // SELECTの時と同じ
			String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8";
			Connection conn = DriverManager.getConnection(url, "softd", "softd");

			/* 2.2.2 INSERT文の実行 */
			String sql = "INSERT INTO user (email,passWord,questionId,questionAnswer) VALUES (?,?,?,?)"; //SQL文の設定 INSERTはパラメータが必要なことが多い
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setString(1, email);
			stmt.setString(2, passWord);
			stmt.setInt(3, questionId);
			stmt.setString(4, questionAnswer);


			/* 2.2.3 実行（UpdateやDeleteも同じメソッドを使う） */
			num = stmt.executeUpdate();

			/* 2.2.4 データベースからの切断 */
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	/* 3. アクセッサ */
	/* 3.1 Getアクセッサ */
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
			return  questionAnswer[i];
		} else {
			return "";
		}
	}

	public String getExplain(int i) {
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
		return num; // データ件数
	}

}