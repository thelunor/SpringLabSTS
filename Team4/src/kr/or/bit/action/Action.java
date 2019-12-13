package kr.or.bit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로직 제어, 화면 전달, 경로 ... 설정하기
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	
}
