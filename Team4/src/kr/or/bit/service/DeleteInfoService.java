package kr.or.bit.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;

public class DeleteInfoService implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      List<BitEmp> empList = null;
      try {
         String empNo = request.getParameter("empNo");
         forward = new ActionForward();
         BitDao dao = new BitDao();
         int result = dao.deleteInfo(Integer.parseInt(empNo));
         empList = dao.getBitList();
         request.setAttribute("empList", empList);
         
          if (result > 0) {
             System.out.println("삭제 성공");
               forward.setPath("/List.d4b");
               System.out.println(forward.getPath());
           } else {
             System.out.println("삭제 실패");
              forward.setPath("/AdminMain.jsp");
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         return forward;
      }

   }