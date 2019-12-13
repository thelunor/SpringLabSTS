package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;
import kr.or.bit.dto.chart.TotalSaleryChart;

/**
 * Servlet implementation class GetDeptNosServlet
 */
@WebServlet("/GetEmpnos")
public class GetEmpnosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEmpnosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		EmpDao dao = null;
		dao = new EmpDao();
		List<Emp> results = dao.getEmps();
		JSONArray json = null;
		StringBuilder datalist = new StringBuilder();
		try {
			datalist.append("[");
			for (Emp emp : results)
				datalist.append(String.format("{ename : %s, empno : %d},", emp.getEname(), emp.getEmpno()));

			datalist.append("]");
			json = new JSONArray(datalist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(json);
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
