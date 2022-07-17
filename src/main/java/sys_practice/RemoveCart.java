package sys_practice;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RemoveCart extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//文字コード等 基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int materialId = -1;
		int userId = -1;
		
		if(request.getParameter("materialId") != null) {
			materialId = Integer.parseInt(request.getParameter("materialId"));
		}else {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp");
			return;
		}
		
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
			if(userId == -1) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Cart.jsp?isNotLogin=true");
				return;
			}
		} catch (Exception e) {
			System.out.println(e);
			materialId = -1;
			userId = -1;
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp");
			return;
		}
		
		
		Cart cart = new Cart();
		int result = cart.removeCart(materialId, userId);
		
		// 正常にカートに追加された場合
		if(result != -1) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Cart.jsp");
			return;
		}
		else {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Cart.jsp?isRemoveCart=true");
			return;
		}
	}
}
