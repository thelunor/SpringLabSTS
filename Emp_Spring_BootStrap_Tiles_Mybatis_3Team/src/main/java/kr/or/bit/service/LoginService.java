package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDaoImpl;

public class LoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");

			EmpDaoImpl dao = new EmpDaoImpl();
			boolean isLogin = dao.checkAdminLogin(userid, pwd);
			
			if (isLogin) {
				request.getSession().setAttribute("userid", userid);
				forward.setPath("index.jsp");
			}
			else 
				forward.setPath("Login.do");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}
}
