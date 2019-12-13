package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;
import net.sf.json.JSONArray;

@WebServlet("/SearchEmpNo")
public class SearchEmpNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchEmpNo() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8"); //클라언트에게 전달한 페이지의 정보 구성
		  PrintWriter out = response.getWriter();
	  	  int empNo = Integer.parseInt(request.getParameter("empNo"));
	  	  BitDao dao = null;
		try {
			dao = new BitDao();
		  BitEmp empList = dao.searchEmpNo(empNo);
	  	  JSONArray jsonlist = JSONArray.fromObject(empList);
	  	  out.print(jsonlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
