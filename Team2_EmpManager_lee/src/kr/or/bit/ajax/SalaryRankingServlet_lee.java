package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.chart.DataByYear;
import kr.or.bit.dto.chart.StatisticsByMgr;
import kr.or.bit.dto.chart.TotalSaleryChart;

@WebServlet("/SalaryRanking_lee.do")
public class SalaryRankingServlet_lee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalaryRankingServlet_lee() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String command = request.getParameter("cmd");

		if (command.equals("show")) { // 화면 보기
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/chart/SalaryRankingChart_lee.jsp");
			dis.forward(request, response);
		} else if (command.equals("chart")) {
			PrintWriter out = response.getWriter();
			EmpDao dao = null;
			JSONArray json = null;
			try {
				dao = new EmpDao();
				List<StatisticsByMgr> chart = dao.statisticsByMgr();
				StringBuilder datalist = new StringBuilder();
				datalist.append("[");
				for (StatisticsByMgr data : chart)
					datalist.append(
					String.format("{ename : %s, empno : %d, avg : %d, max : %d, min : %d},", data.getEname(), data.getEmpno(), data.getAvg(),data.getMax(),data.getMin()));

				datalist.append("]");
				json = new JSONArray(datalist.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print(json);
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
