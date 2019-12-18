package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
			StopWatch sw = new StopWatch();
			request.setAttribute("sw", sw);
			sw.start();
			System.out.println("preHandle(Controller 실행 전): Time Start");
			return true;
		}
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			System.out.println("postHandle(Controller 실행 후): View 실행 전");
		} 
		
		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
			StopWatch sw = (StopWatch) request.getAttribute("sw");
			sw.stop();
			System.out.println("afterCompletion(View 실행 후): Time Stop");
			System.out.println("실행시간 : " + sw.getTotalTimeMillis());
		}
}
