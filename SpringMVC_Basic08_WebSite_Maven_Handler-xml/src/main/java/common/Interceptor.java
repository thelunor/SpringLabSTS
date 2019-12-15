package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
			StopWatch sw = new StopWatch();
			request.setAttribute("sw", sw);
			sw.start();
			System.out.println("Time Start");
			return true;
		}
		
		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
			StopWatch sw = (StopWatch) request.getAttribute("sw");
			sw.stop();
			System.out.println("Time Stop");
			System.out.println("실행시간 : " + sw.getTotalTimeMillis());
		}
}
