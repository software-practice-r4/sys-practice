package sys_practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * @author shuya
 * */
public class Cart extends HttpServlet {

	protected int[] materialId = new int[100];
	protected int[] userId = new int[100];
	protected int[] price = new int[100];
	protected String[] thumbnail = new String[100];
	protected String[] categoryName = new String[100];
	protected String[] materialName = new String[100];
	protected int num;
	
	Connection conn = null;
	ResultSet resultSet = null;
	String results = "";
	String statement = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//文字コード等 基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int materialId = -1;
		int userId = -1;
		
		if(request.getParameter("materialId") != null) {
			materialId = Integer.parseInt(request.getParameter("materialId"));
		}else {
			// 素材IDがない時点で、不正なので、トップページにリダイレクト
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp");
			return;
		}
		
		// TODO: リダイレクト先を修正
		try {
			Cookie cookie[] = request.getCookies();
			
			if (cookie != null){
			    for (int i = 0 ; i < cookie.length ; i++){
			      if (cookie[i].getName().equals("userId"))
			        userId = Integer.parseInt(cookie[i].getValue());
			    }
			}
			
			/* 何かでcookieが残ったままだと、
			   ログインしないで、カートに追加すると、userIdが-1の初期値のままくる
		    */
			System.out.println("userID" + userId);
			if(userId == -1) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Material-detail.jsp?materialId=" 
						+ materialId + "&isNotLogin=true");
				return;
			}
		} catch (Exception e) {
			System.err.println(e);
			materialId = -1;
			userId = -1;
			// ユーザーIDがない場合もしくは不正の場合は、トップページにリダイレクトする
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp");
			return;
		}
		
		
		Cart cart = new Cart();
		int result = cart.addCart(materialId, userId);
		
		System.out.println("result"+result);
		
		// カートに追加出来なかったとき
		if(result == -1) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Material-detail.jsp?materialId=" 
			+ materialId + "&isAddCartFailed=true");
			return;
		}
		// カートにすでに追加されていた場合
		else if(result == -2) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Material-detail.jsp?materialId=" 
			+ materialId + "&isExistItems=true");
			return;
		}
		// 正常にカートに追加された場合
		else if(result != -1) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Cart.jsp");
			return;
		}
		
	}
	
	/*
	 * 素材詳細ページ内で、カートに追加するボタンを押した際に発火する
	 * 素材IDとユーザーIDを引数で受け取り、対象のユーザーIDのカートに素材を追加する
	 * @param int materialId 素材ID
	 * @param int userId ユーザーID
	 * @return カートに追加できれば、更新された行数を返却
	 *         カートにすでに追加されていた場合、-2
	 *         それ以外のエラーは、-1を返却する
	 * */
	private int addCart(int materialId, int userId) {
		int result=-1;
		Connection conn = null;
		num = 0;
		
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			
			String sql = "SELECT * FROM cart where userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,userId);
			resultSet = stmt.executeQuery();
			
			
			while (resultSet.next()) {
				this.materialId[num] = resultSet.getInt("materialId");
				num++;
			}
			
			// カートにすでに追加されていた場合
			for(int i=0;i<num;i++) {
				if(this.materialId[i] == materialId)
					return -2;
			}
			

			sql = "INSERT INTO cart (materialId, userId) SELECT * FROM (SELECT ?,?) AS tmp "
					+ "WHERE exists(SELECT materialId FROM material WHERE materialId = ? AND providerId = ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,materialId);
			stmt.setInt(2,userId);
			stmt.setInt(3,materialId);
			stmt.setInt(4,userId);
			result = stmt.executeUpdate();

			stmt.close();
			conn.close();
			
			if(result <= 0) {
				return -1;
			}
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
	
	/*
	 * Cart.jsp内で×ボタンを押したときに発火するメソッド
	 * 引数として素材IDと、ユーザーIDを受け取り
	 * 合致するカートの商品を削除する
	 * @param int materialId 素材ID
	 * @param int userId ユーザーID
	 * @return int 更新できた行の量 エラー時は、-1を返却する
	 * */
	public int removeCart(int materialId, int userId) {
		int result=-1;
		Connection conn = null;
		num = 0;
		
		try {
			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			
			String sql = "DELETE FROM cart where materialId = ? AND userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,materialId);
			stmt.setInt(2,userId);
			result = stmt.executeUpdate();

			stmt.close();
			conn.close();
			
			if(result <= 0) {
				return -1;
			}
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
	
	/*
	 * 引数で渡されたユーザーIDに合致するカートをフィールドに格納する
	 * @param int userId ユーザーID
	 * @return int 取得できたデータ数 エラー時は、-1を返却する 
	 * */
	public int getCartByUserId(int userId) {
		int cartLength = 0;
		try {

			AWS aws = new AWS();
			conn = aws.getRemoteConnection();
			String sql = "SELECT * FROM cart INNER JOIN material on "
					+ "cart.materialId = material.materialId INNER JOIN user ON "
					+ "cart.userId = user.userId INNER JOIN category ON material.categoryId = category.categoryId "
					+ "where cart.userId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  userId);
			stmt.setMaxRows(100); //最大の数を制限
			ResultSet rs = stmt.executeQuery();

			/* 2.1.3 結果の取り出しと表示 */
			num = 0;
			while (rs.next()) { //リザルトセットを1行進める．ない場合は終了
				this.materialId[cartLength] = rs.getInt("materialId");
				this.userId[cartLength] = rs.getInt("userId");
				this.price[cartLength] = rs.getInt("price");
				this.thumbnail[cartLength] = rs.getString("thumbnail");
				this.categoryName[cartLength] = rs.getString("categoryName");
				this.materialName[cartLength] = rs.getString("materialName");
				cartLength++;
				num++;
			}

			/*データベースからの切断*/
			rs.close(); //開いた順に閉じる
			stmt.close();
			conn.close();
			
			return cartLength;
		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return 0;
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}

	}
	public int getMaterialId(int i) {
		if (i >= 0) {
			return materialId[i];
		} else {
			return -1;
		}
	}
	
	public int getUserId(int i) {
		if (i >= 0) {
			return userId[i];
		} else {
			return -1;
		}
	}
	public int getPrice(int i) {
		if (i >= 0) {
			return price[i];
		} else {
			return -1;
		}
	}
	
	public String getThumbnail(int i) {
		if (i >= 0) {
			return thumbnail[i];
		} else {
			return null;
		}
	}
	public String getCategoryName(int i) {
		if (i >= 0) {
			return categoryName[i];
		} else {
			return null;
		}
	}
	public String getMaterialName(int i) {
		if(i >= 0) {
			return materialName[i];
		}else {
			return null;
		}
	}
	public int getNum() {
		return num;
	}
	
}
