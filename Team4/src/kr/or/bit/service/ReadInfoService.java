package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;

public class ReadInfoService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		BitEmp bitemp = new BitEmp();
		BitDao dao = null;
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		try {
			
		forward = new ActionForward();
		dao = new BitDao();
		bitemp = dao.readEmp(empNo);
		if(bitemp !=null) {
			System.out.println("조회 성공");
			request.setAttribute("bitempData", bitemp);
			forward.setPath("/WEB-INF/tables/DatatableModify.jsp");
		}else {			
			System.out.println("조회 실패");
       		forward.setPath("/AdminMain.jsp");
		}
		}catch(Exception e) {
			
		}
		return forward;
	}

}
