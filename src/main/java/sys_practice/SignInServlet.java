package sys_practice;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SignInServlet extends HttpServlet {
	/*
	 * post-materila.jspから、素材情報を取得
	 * その後そのデータをRDSに格納し、元ページへリダイレクトする
	 * */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード等 基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// メールアドレスまたはパスワードが入っていなかったときにマイページにリダイレクト
		if(email == null || password == null) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signin.jsp?isNull=true");
			return;
		}
		SignIn sign = new SignIn();
		int err = 0;// 合致したデータ行の格納行番号を格納
		
		try {
			err = sign.signIn(email, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		HttpSession session = request.getSession(false);
		
		// 正常時処理
		if(err != 0) {
			session = request.getSession(true);
			response.sendRedirect("/sys-practice/sys-practice/jsp/Mypage.jsp");
			return;
		}
		// データがなかった場合に、リダイレクト
		else {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signin.jsp?isErr=true");
			return;
		}
	}
}
