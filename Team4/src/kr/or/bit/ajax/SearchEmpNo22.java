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

@WebServlet("/SearchEmpNo222.do")
public class SearchEmpNo22 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

    public SearchEmpNo22() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int empNo = Integer.parseInt(request.getParameter("empNo"));
          BitDao dao = null;
          BitEmp empList = null;
      try {
         dao = new BitDao();
            empList = dao.searchEmpNo(empNo);
            
            request.setAttribute("empList", empList);
            System.out.println("empList: " + empList);
//            JSONArray jsonlist = JSONArray.fromObject(empList);
//            out.print(jsonlist);
            
      } catch (Exception e1) {
         e1.printStackTrace();
      }
      
      String no = Integer.toString(empList.getEmpNo());
      String empN = "<tr><td>" + no + "</td>";
      String eName = "<td>" + empList.getdName() + "</td>";
      String job = "<td>" + empList.getJob() + "</td>";
      String sal = "<td>" + empList.getSal() + "</td>";
      String dName = "<td>" + empList.getdName() + "</td></tr>";
      
      out.print(empN + eName + job + sal + dName);

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

}


