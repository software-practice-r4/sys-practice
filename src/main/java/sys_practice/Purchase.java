package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @author shuya
 * */
public class Purchase extends Material{
	
	protected int[] tradeId = new int[100];
	protected int[] purchaseId = new int[100];
	protected int[] providerId = new int[100];
	protected int[] providerWallet= new int[100];

	protected int num;//データ件数
	
	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;
	
	/*
	 * ユーザーIDに合致した購入履歴をフィールドに格納
	 * @author shuya
	 * @param int userId
	 * @return 取得できた購入履歴の長さ エラー時は-1を返却
	 * */
	public int loadPurchaseHistory(int userId) {
		try {

			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM purchaseHistory INNER JOIN material on "
					   + "purchaseHistory.materialId = material.materialId "
					   + "INNER JOIN category ON material.categoryId = category.categoryId WHERE userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

			/* 2.1.3 結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialName[num] = rs.getString("materialName");
				this.thumbnail[num] = rs.getString("thumbnail");
				this.explanation[num] = rs.getString("explanation");
				this.materialId[num] = rs.getInt("materialId");
				this.price[num] = rs.getInt("price");
				this.categoryId[num] = rs.getInt("categoryId");
				this.categoryName[num]=rs.getString("categoryName");
				this.providerId[num] = rs.getInt("providerId");
				this.isAdult[num] = rs.getInt("isAdult");
				num++;
			}

			/*データベースからの切断*/
			rs.close(); //開いた順に閉じる
			stmt.close();
			conn.close();
			
			if(num == 0) {
				return -1;
			}
			return num;
		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return -1;
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
	}
}
