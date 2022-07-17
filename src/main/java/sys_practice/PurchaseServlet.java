package sys_practice;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * @author shuya
 * */
public class PurchaseServlet  extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//文字コード等 基本設定
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			User user = new User();
			
			int userId = -1;
			
			if(request.getParameter("userId") != null) {
				userId = Integer.parseInt(request.getParameter("userId"));
			}

			// ユーザーIDが取得できなかったとき 
			// エラーパラメータを付与し、リダイレクトする
			if(userId == -1) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Purchase.jsp?isErr=true");
				return;
			}
			
			int userWallet = 0;
			int totalPrice = -1;
			
			try {
				userWallet = user.getMoneyById(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Cart cart = new Cart();
			cart.getCartByUserId(userId);
			
			for(int i=0;i<cart.getNum(); i++){
        		totalPrice += cart.getPrice(i);
        	}
			
			// ユーザーの残高が、カート内合計金額よりも少ない時
			// エラーパラメータを付与し、リダイレクトする
        	if(userWallet-totalPrice < 0){
        		response.sendRedirect("/sys-practice/sys-practice/jsp/Purchase.jsp?isErr=true");
				return;
        	}
			
        	Trade trade = new Trade();
			int result = trade.trade(userId);
			
			// 取引失敗時の処理
			// エラーパラメータを付与し、リダイレクトする
			if(result == -1) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Purchase.jsp?isErr=true");
				return;
			}
			// 取引成功時の処理
			else {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Purchase-history.jsp");
				return;
			}
	}
}
