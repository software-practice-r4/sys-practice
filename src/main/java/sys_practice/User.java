package sys_practice;

import java.sql.Connection;
import java.sql.SQLException;

public class User {

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
	
	public int deleteUser(int userId) throws SQLException {
		
		AWS aws = new AWS();
		Connection con = aws.getRemoteConnection();
		

		return 1;
	}
}