package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

/**
 * Servlet implementation class EmpnoCheck
 */
@WebServlet("/ec")
public class EmpnoCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //클라언트에게 전달한 페이지의 정보 구성
		PrintWriter out = response.getWriter();
		Emp emp;
		String result="";
		int empno = Integer.parseInt(request.getParameter("empno"));
		EmpDao dao = new EmpDao();
		emp = dao.getEmpByEmpno(empno);
		if(emp == null) {
			result = "true";
		}else {
			result = "false";
		}
		out.print(result);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

}
