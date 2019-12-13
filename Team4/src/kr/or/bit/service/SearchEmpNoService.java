package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;
import net.sf.json.JSONObject;

public class SearchEmpNoService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String empNo = request.getParameter("empNo");
		try {
		
		BitDao dao = new BitDao();
		BitEmp bitemp = dao.searchEmpNo(Integer.parseInt(empNo));
		
		JSONObject empjson = JSONObject.fromObject(bitemp);
		
		request.setAttribute("empNoJson", empjson);
		
		forward = new ActionForward();
		forward.setPath("/WEB-INF/ajax/SearchEmpno.jsp");
		
		}catch(Exception e) {
			
		}

		return forward;
	}

}
