package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;

public class LoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		try {
			BitDao dao = new BitDao();

			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");

			boolean result = dao.adminLogin(id, pwd);

			if (result) { // 로그인 o
				forward.setPath("Main.jsp");
				session.setAttribute("id", id);
			} else {
				forward.setPath("/WEB-INF/login/Login_form.jsp");
			}

		} catch (Exception e) {
			System.out.println("LoginService 예외");
		}
		return forward;
	}
}
