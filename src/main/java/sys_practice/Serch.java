package sys_practice;

import java.sql.*;

public class Serch extends Material{

    /*フィールド定義*/
	String keyWord;
	String category;
	int price;
	boolean isAdult;
	protected int num;/*データ件数*/

    /*素材検索メソッド*/
	public int getMaterial(String keyWord, String category, int price, boolean isAdult) throws Exception {
　　　/*データベースに接続*/
　　　　Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.jdbc.Driverはドライバのクラス名
	String url = "jdbc:mysql://localhost/sys_practice?characterEncoding=UTF-8"; //データベース名は適宜修正：文字エンコードはUTF-8
	Connection conn = DriverManager.getConnection(url, "adomin", "AraikenR4!"); //上記URL設定でユーザ名とパスワードを使って接続

    num = 0;
    String sql =
    "SELECT * from Material WHERE AND price=price AMD isAdult=isAdult";
    PreparedStatement stmt = conn.prepareStatement(sql); //JDBCのステートメント（SQL文）の作成
    stmt.setMaxRows(100); //最大の数を制限
    ResultSet rs = stmt.executeQuery(); //ステートメントを実行しリザルトセットに代入
/*結果の取り出しと表示 */
    while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
    	this.materialId[num] = rs.getInt("materialId");
    	this.materialName[num] = rs.getString("materialName");
    	this.price[num] = rs.getInt("price");
    	this.thumbnail[num] = rs.getString("thumbnail");
    	this.categoryId[num] = rs.getString("categoryId");
    	this.providerId[num] = rs.getString("providerId");
    	num++;
   }
        /* 2.1.4 データベースからの切断 */
        rs.close(); //開いた順に閉じる
        stmt.close();
        conn.close();
    }

/*アクセッサ */
/*ゲッター*/
