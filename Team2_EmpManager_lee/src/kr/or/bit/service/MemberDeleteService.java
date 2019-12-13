package kr.or.bit.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;

public class MemberDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int empno = Integer.parseInt(request.getParameter("empno"));
		System.out.println(empno);
		EmpDao dao = new EmpDao();
		int row = dao.deleteEmpByEmpno(empno);		
		String url = "";
		String msg ="";
		if(row > 0){
			url="MemberList.do";
			msg= empno + "님이 삭제되었습니다";
		} 
	  	request.setAttribute("board_msg", msg);
	  	request.setAttribute("board_url", url);
		System.out.println("row: "+ row);	
	
		forward.setRedirect(false);
		forward.setPath("/common/redirect.jsp");
		System.out.println("포워드: " + forward);
		return forward;
	}
}
