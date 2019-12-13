package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;

public class GetBitListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		List<BitEmp> empList = null;
		try {
			BitDao dao = new BitDao();
			empList = dao.getBitList();
			request.setAttribute("empList", empList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward = new ActionForward();
		forward.setPath("/ListOk.d4b");
		return forward;
	}
}
