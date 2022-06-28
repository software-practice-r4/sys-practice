package sys_practice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.FileItem;

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

	private final String dataDir = "C:\\Users\\kuritarou\\git\\sys-practice\\src\\main\\webapp\\sys-practice\\img"; //保存先のパス
	private final FileDB file = new FileDB();

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

	//----------------------------------------
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//エラーを表示するHTMLの出力
		try (PrintWriter out = response.getWriter()) {
			out.println("<html>");
			out.println("<head><title>エラー表示</title></head>");
			out.println("<body><article>画像アップロード時にエラーが発生しました</article></body>");
			out.println("</html>");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード等　基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// アップロードの可否
		boolean uploaded = false;

		// 時刻の取得　ファイルの元ファイル名に時刻を追加するため
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss_");
		String time = df.format(date); //現在の時刻を上記フォーマットに変換する

		// ファイル取得設定
		File dataDirFile = new File(dataDir); //保存先ディレクトリの設定
		DiskFileItemFactory dfi = new DiskFileItemFactory(); // fileuploadのファイル作成オブジェクト
		dfi.setRepository(dataDirFile); // 一時ファイルの保存先フォルダ
		dfi.setSizeThreshold(1024); // バッファサイズ
		ServletFileUpload sfu = new ServletFileUpload(dfi); // fileuploadのサーブレット関連オブジェクト
		sfu.setHeaderEncoding("UTF-8");
		sfu.setSizeMax(-1); // アップロードファイルの最大サイズ（-1は無限）

		// *** ファイルやPostデータの取得 *****
		try {
			// アップロードされたファイル情報をFileItemオブジェクトのリストとして取得
			List objLst = sfu.parseRequest(request);
			Iterator objItr = objLst.iterator();
			// リストから順にファイルデータを取り出し保存
			while (objItr.hasNext()) {
				FileItem objFi = (FileItem) objItr.next(); //fileuploadのファイル関連オブジェクト
				//フォームフィールドの場合
				if (objFi.isFormField()) {
					String name = objFi.getFieldName();
					String value = objFi.getString(request.getCharacterEncoding());
					// if (name.equals("postされてきたパラメータ名")) {
					// フィールド変数 = value;
					//}
					//ファイルデータの場合
				} else {
					icon = objFi.getName();
					// ディレクトリ名などを取り除く
					icon = icon.replaceAll("^.*[^A-Za-z0-9_\\-\\.]", "");
					if (icon != null && !icon.equals("")) {
						uploaded = true; //ファイル名が取得できるかどうか
						icon = time + icon;
						objFi.write(new File(dataDir + "/" + icon));
					}
				}
			} // ファイル名をデータベースに登録する
			icon.insert(icon);

			// ファイル名をデータベースに登録する
			if (!uploaded) {
				processRequest(request, response); //エラー表示
			} else {
				// 次の一覧表示ページへ転送する
				response.sendRedirect("./../jsp/Profile.jsp");
			}
		} catch (Exception e) {
			processRequest(request, response); //エラー表示
		}

	}
	//---------------------------------------------------------
}
