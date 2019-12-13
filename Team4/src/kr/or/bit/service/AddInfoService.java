package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;

public class AddInfoService implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
         ActionForward forward = new ActionForward();
         
         //1. 데이터 받기.
      ServletContext application = request.getServletContext();
      String uploadpath = application.getRealPath("bitimg"); 
      int size = 1024*1024*10; //10M 네이버 계산기
     
      MultipartRequest multi;

      try {
         multi = new MultipartRequest(
               request, //기존에 있는  request 객체의 주소값 
               uploadpath, //실 저장 경로 (배포경로)
               size, //10M
               "UTF-8",
               new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경))
               );

          
            String empNo = multi.getParameter("empNo");              
            String eName = multi.getParameter("eName");
            String job = multi.getParameter("job");
            String sal = multi.getParameter("sal");
            String dName = multi.getParameter("dName");
            String originFileName = "";
            String saveFileName = "";
            System.out.println(empNo);
            System.out.println(sal);
            System.out.println(originFileName);
            System.out.println(saveFileName);
            
            
         Enumeration filenames = multi.getFileNames();
         String file = (String)filenames.nextElement();
         saveFileName = multi.getFilesystemName(file);
         originFileName = multi.getOriginalFileName(file);
  

            BitDao dao = new BitDao();
            int result = dao.addInfo(Integer.parseInt(empNo), eName, job, Integer.parseInt(sal), dName, originFileName, saveFileName);

            if (result > 0) {
               forward.setPath("/AdminMain.d4b");
           } else {
              forward.setPath("/Join_form.jsp");
            }         
         
      }catch(Exception e) {            
      }
      return forward;         
   }

}