package sys_practice;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signout  extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード等　基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			Cookie cookie = new Cookie("userId", "");
			  cookie.setMaxAge(0);
			  response.addCookie(cookie);
			  response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp?isLogout=true");
			  return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
