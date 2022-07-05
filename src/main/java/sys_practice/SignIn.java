package sys_practice;

//こっちはわからん
//SQLに関連したクラスライブラリをインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignIn {

	/* 1. フィールドの定義 */
	protected String[] userId = new String[100]; //ユーザーID
	protected String[] email = new String[100]; //eメール
	protected String[] passWord = new String[100]; //パスワード
	protected String[] displayName = new String[100];//表示名
	protected int[] questionId = new int[100]; //秘密の質問ID
	protected String[] answer = new String[100];//秘密の質問の応え
	protected String[] explain = new String[100];//自己紹介文
	protected String[] icon = new String[100]; //アイコン
	protected int[] wallet = new int[100]; //財布
	protected int num;

	/* 2. メソッド */
	/* サインイン */
	public int signIn(String email, String passWord) throws Exception { //エラー処理が必要にする throws Exception
		/* 2.1.1 データベースに接続 */
		num = 0; //取得件数の初期化
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
			String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8"; //データベース名：文字エンコードはUTF-8
			Connection conn = DriverManager.getConnection(url, "softd", "softd"); //上記URL設定でユーザ名とパスワードを使って接続

			/* 2.1.2 SELECT文の実行 */
			String sql = "SELECT * FROM  user WHERE email Like ? AND passWord Like ?"; //SQL文の設定 ?などパラメータが必要がない場合は通常のStatementを利用
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setString(1, email);
			stmt.setString(2, passWord);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

			/* 2.1.3 結果の取り出しと表示 */
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.userId[num] = rs.getString("userId");
				this.email[num] = rs.getString("email");
				this.passWord[num] = rs.getString("passWord");
				this.displayName[num] = rs.getString("displayName");
				this.questionId[num] = rs.getInt("questionId");
				this.answer[num] = rs.getString("answer");
				this.explain[num] = rs.getString("explain");
				this.icon[num] = rs.getString("passWord");
				this.wallet[num] = rs.getInt("wallet");
				num++;
			}

			/* 2.1.4 データベースからの切断 */
			rs.close(); //開いた順に閉じる
			stmt.close();
			conn.close();
			return num;
		} catch (Exception e) {
			return 0;
		}
	}

	/*秘密の質問*/
	public int resetPassWord(String email) throws Exception { //エラー処理が必要にする throws Exception
		/* 2.1.1 データベースに接続 */
		num = 0; //取得件数の初期化
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
			String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8"; //データベース名：文字エンコードはUTF-8
			Connection conn = DriverManager.getConnection(url, "softd", "softd"); //上記URL設定でユーザ名とパスワードを使って接続

			/* 2.1.2 SELECT文の実行 */
			String sql = "SELECT answer FROM  user WHERE email Like ?"; //SQL文の設定 ?などパラメータが必要がない場合は通常のStatementを利用
			PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
			stmt.setString(1, email);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入

			/* 2.1.3 結果の取り出しと表示 */
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.answer[num] = rs.getString("answer");
				num++;
			}

			/* 2.1.4 データベースからの切断 */
			rs.close(); //開いた順に閉じる
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
