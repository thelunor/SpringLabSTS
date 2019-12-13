package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;
import net.sf.json.JSONArray;


@WebServlet("/SearchDname")
public class SearchDname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchDname() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8"); //클라언트에게 전달한 페이지의 정보 구성
		  PrintWriter out = response.getWriter();
	  	  String dName = request.getParameter("dName");
	  	  BitDao dao = null;
		try {
			System.out.println("dName : " + dName);
			dao = new BitDao();
		  ArrayList<BitEmp> empList = dao.searchDname(dName);
	  	  JSONArray jsonlist = JSONArray.fromObject(empList);
	  	  out.print(jsonlist);
	  	  System.out.println(jsonlist);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
