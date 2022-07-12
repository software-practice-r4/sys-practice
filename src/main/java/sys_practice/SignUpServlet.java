package sys_practice;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * @author keita
 * @version 1.0
 * */
public class SignUpServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード等 基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String questionAnswer = request.getParameter("questionAnswer");

		//秘密の質問テーブルにデータが格納されていなかった場合にリダイレクト
		String questionIdString = String.valueOf(questionId);
		if (questionIdString.equals("")) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp?isZero=true");
			return;
		}

		// メールアドレスまたはパスワードまたは秘密の質問が入っていなかったときにサインアップページにリダイレクト
		if (email == null || password == null || questionId == 0 || questionAnswer == null) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isNull=true");
			return;
		}

		SignUp signup = new SignUp();
		int err = 0;// データの格納を判定
		int acount = -1;// アカウントが既に存在するかを判定

		/* データが空だったときの処理 */
		// TODO:
		if (email.equals("") || password.equals("") || questionAnswer.equals("")) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isNull=true");
			return;
		}

		//入力されたemailに該当するユーザーテーブル（アカウント）があるを判定
		try {
			acount = signup.acountQuantity(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//まだ該当するemailのアカウントがない場合、サインアップ
		if (acount == 0) {
			try {
				err = signup.signUp(email, password, questionId, questionAnswer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		User user = new User();
		// ユーザーIDと表示名をセッションで保持
		int userId = user.getUserIdByEmail(email);
		System.out.println("err" + err);
		// 正常時処理
		if (err != 0 && acount == 0) {
			Cookie cookie = new Cookie("userId", String.valueOf(userId));
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			System.out.println("userId=" + userId);
			response.sendRedirect("/sys-practice/sys-practice/jsp/Mypage.jsp");
			return;
		}
		// データがなかったor既にアカウントが存在する場合に、リダイレクト
		else {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isErr=true");
			return;
		}
	}
}