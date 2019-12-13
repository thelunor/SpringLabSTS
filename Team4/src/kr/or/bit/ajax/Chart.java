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

@WebServlet("/Chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Chart() {
        super();
       
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
    	PrintWriter out = response.getWriter();
	  	BitDao dao;
		try {
			dao = new BitDao();
		  ArrayList<BitEmp> empList = dao.getBitList();
		  System.out.println(empList);
		  JSONArray jsonlist = JSONArray.fromObject(empList);
	  	  
	  	  out.print(jsonlist);
	  	  
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
