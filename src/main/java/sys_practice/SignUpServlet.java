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

		//譁�ｭ励さ繝ｼ繝臥ｭ� 蝓ｺ譛ｬ險ｭ螳�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String questionAnswer = request.getParameter("questionAnswer");

		/* 繝��繧ｿ縺檎ｩｺ縺�縺｣縺溘→縺阪�蜃ｦ逅� */
		String questionIdString = String.valueOf(questionId);
		if (questionIdString.equals("")) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp?isNull=true");
			return;
		}

		// 繝｡繝ｼ繝ｫ繧｢繝峨Ξ繧ｹ縺ｾ縺溘�繝代せ繝ｯ繝ｼ繝峨∪縺溘�遘伜ｯ��雉ｪ蝠上′蜈･縺｣縺ｦ縺�↑縺九▲縺溘→縺阪↓繧ｵ繧､繝ｳ繧｢繝��繝壹�繧ｸ縺ｫ繝ｪ繝繧､繝ｬ繧ｯ繝�
		if (email == null || password == null || questionId == 0 || questionAnswer == null) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isNull=true");
			return;
		}

		SignUp signup = new SignUp();
		int err = 0;// 繝��繧ｿ縺ｮ譬ｼ邏阪ｒ蛻､螳�
		int hasAccount = -1;

		/* 繝��繧ｿ縺檎ｩｺ縺�縺｣縺溘→縺阪�蜃ｦ逅� */
		if (email.equals("") || password.equals("") || questionAnswer.equals("")) {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isNull=true");
			return;
		}

		//蜈･蜉帙＆繧後◆email縺ｫ隧ｲ蠖薙☆繧九Θ繝ｼ繧ｶ繝ｼ繝��繝悶Ν�医い繧ｫ繧ｦ繝ｳ繝茨ｼ峨′縺ゅｋ繧貞愛螳�
		try {
			//TODO:hasAccount = signup.hasAccountQuantity(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//縺ｾ縺�隧ｲ蠖薙☆繧菊mail縺ｮ繧｢繧ｫ繧ｦ繝ｳ繝医′縺ｪ縺��ｴ蜷医√し繧､繝ｳ繧｢繝��
		if (hasAccount == 0) {
			try {
				err = signup.signUp(email, password, questionId, questionAnswer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		User user = new User();
		// 繝ｦ繝ｼ繧ｶ繝ｼID縺ｨ陦ｨ遉ｺ蜷阪ｒ繧ｻ繝�す繝ｧ繝ｳ縺ｧ菫晄戟
		int userId = user.getUserIdByEmail(email);
		System.out.println("err" + err);
		// 豁｣蟶ｸ譎ょ�逅�
		if (err != 0 && hasAccount == 0) {
			Cookie cookie = new Cookie("userId", String.valueOf(userId));
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			System.out.println("userId=" + userId);
			response.sendRedirect("/sys-practice/sys-practice/jsp/Mypage.jsp");
			return;
		}
		// 繝��繧ｿ縺後↑縺九▲縺殪r譌｢縺ｫ繧｢繧ｫ繧ｦ繝ｳ繝医′蟄伜惠縺吶ｋ蝣ｴ蜷医↓縲√Μ繝繧､繝ｬ繧ｯ繝�
		else {
			response.sendRedirect("/sys-practice/sys-practice/jsp/Signup.jsp?isErr=true");
			return;
		}
	}
}