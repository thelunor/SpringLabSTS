package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInter extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("탔다");
		Object obj = session.getAttribute("uid");
		
		boolean islogin = false;
		
		if(obj == null) {
			//로그인이 되어있지 않으면 로그인 폼으로 이동
			response.sendRedirect("../joinus/login.htm");
			islogin = false;
		}else {
			islogin = true;
		}
		//로그인이 되어있다면 다음 컨트롤러 실행
		return islogin;
	}
}
