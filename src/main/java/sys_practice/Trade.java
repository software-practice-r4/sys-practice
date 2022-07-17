package sys_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/*
 * @author shuya
 * */
public class Trade {
	
	protected int[] tradeId = new int[100];
	protected int[] purchaseId = new int[100];
	protected int[] providerId = new int[100];
	protected int[] providerWallet= new int[100];
	protected int[] price = new int[100];
	protected Date[] date = new Date[100];

	protected int num;//データ件数
	
	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;
	
	/*
	 * ユーザーの残高から、カートの合計金額を引き、
	 * 提供者の残高に、商品の値段分を追加する
	 * その後、購入履歴に購入商品を追加する
	 * @author shuya
	 * */
	public int trade(int userId) {
		int result = 0;
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();

			// 購入者のwalletを取得
			String sql = "";
			PreparedStatement stmt;


			// 素材提供者のIDとwalletを取得
			sql = "SELECT material.providerId, material.price, user.wallet FROM cart INNER JOIN material ON"
				+ " cart.materialId = material.materialId INNER JOIN user ON material.providerId = user.userId";
			stmt = conn.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				providerId[num] = resultSet.getInt("providerId");
				providerWallet[num] = resultSet.getInt("wallet");
				price[num] = resultSet.getInt("price");
				num++;
			}

			// TODO: 途中で購入者のお金がなくなっときは？
			//       処理を戻さないといけない
			for(int i=0;i<num;i++) {
				// 素材提供者のwalletに、素材の価格分の値を足す
				sql = "UPDATE user SET wallet = wallet + ? where userId = ?";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, price[i]);
				stmt.setInt(2, providerId[i]);
				stmt.executeUpdate();

				// 足したのちに、購入者のwalletを価格分引く
				sql = "UPDATE user SET wallet = wallet - ? where userId = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, price[i]);
				stmt.setInt(2, userId);
				result = stmt.executeUpdate();
			}
			
			// 購入履歴にカート内容を挿入
			sql = "INSERT INTO purchaseHistory(materialId, userId, purchase_at)"
					+ " SELECT materialId, userId, now() FROM cart where userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			result = stmt.executeUpdate();
						
			// カートの中身を削除
			// TODO: これも処理が中断されたのであれば、中断しないといけない
			sql = "DELETE from cart where userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			result = stmt.executeUpdate();
			
			
			stmt.close();
			resultSet.close();
			conn.close();
			
			return result;
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
