package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.AddInfoService;
import kr.or.bit.service.DeleteInfoService;
import kr.or.bit.service.EditInfoService;
import kr.or.bit.service.GetBitListService;
import kr.or.bit.service.LoginService;
import kr.or.bit.service.LogoutService;
import kr.or.bit.service.ReadInfoService;
import kr.or.bit.service.SearchEmpNoService;

@WebServlet("*.d4b")
public class BitFrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public BitFrontController() {
      super();
   }

   protected void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String requestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String url_Command = requestURI.substring(contextPath.length());

      Action action = null;
      ActionForward forward = null;

      if (url_Command.equals("/addInfo.d4b")) { // 사원정보 추가
         action = new AddInfoService();
         forward = action.execute(request, response);
      } else if (url_Command.equals("/addInfoOk.d4b")) {
         try {
            action = new GetBitListService(); //
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }else if (url_Command.equals("/AdminMain.d4b")) {
         try {
            action = new GetBitListService();
            forward = action.execute(request, response);
            forward.setPath("AdminMain.jsp");
         } catch (Exception e) {
            e.printStackTrace();
         }

      } else if (url_Command.equals("/deleteInfo.d4b")) { // 사원정보 삭제
         try {
            action = new DeleteInfoService();
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }  else if (url_Command.equals("/List.d4b")) { // 전체 목록 보기
         try {
            action = new GetBitListService();
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (url_Command.equals("/ListOk.d4b")) { // 전체 목록 보기
         try {
            forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/AdminMain.jsp");
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (url_Command.equals("/logout.d4b")) {
         action = new LogoutService();
         forward = action.execute(request, response);

      } else if (url_Command.equals("/loginok.d4b")) {
         action = new LoginService();
         forward = action.execute(request, response);
      } else if(url_Command.equals("/editForm.d4b")) {
         action = new ReadInfoService();
         forward = action.execute(request, response);
      } else if(url_Command.equals("/editOk.d4b")) {
         action = new EditInfoService();
         forward = action.execute(request, response);
      } else if(url_Command.equals("/searchEmpno.d4b")) {
         action = new SearchEmpNoService();
         forward = action.execute(request, response);
         
      }

      if (forward != null) {
         if (forward.isRedirect()) { // true
            response.sendRedirect(forward.getPath());
         } else {
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
         }
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