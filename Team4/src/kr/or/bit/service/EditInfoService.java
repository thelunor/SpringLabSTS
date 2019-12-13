package kr.or.bit.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BitDao;
import kr.or.bit.dto.BitEmp;

public class EditInfoService implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
       List<BitEmp> empList = null;
       ServletContext application = request.getServletContext();
       String uploadpath = application.getRealPath("bitimg"); // 이미지 실경로
      int size = 1024*1024*10; //10M 네이버 계산기
      
      MultipartRequest multi = null;

      try {
         multi = new MultipartRequest(
               request, //기존에 갖고 있는  request 객체의 주소값 
               uploadpath, //실 저장 경로 (배포경로)
               size, //10M
               "UTF-8",
               new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
         ); //파일 업로드 완료
      
         //form에서 값 받아오기
         String eName = multi.getParameter("eName");
         String empNo = multi.getParameter("empNo");
         String job = multi.getParameter("job");
         String sal = multi.getParameter("sal");
         String dName = multi.getParameter("dName");
      
         Enumeration filenames = multi.getFileNames(); //파일이름들

         String file = (String)filenames.nextElement(); //파일명 : 뒤에서부터 읽어옴..
         String saveFileName = multi.getFilesystemName(file); //저장된 파일명
         String originFileName = multi.getOriginalFileName(file); //원 파일명
         
         /* 
         //이미지 없으면 기본이미지 설정
         if( originFileName == null ) {
            originFileName = "avatar.png";
         }
         */
         
         BitEmp bitemp = new BitEmp();
         
         bitemp.setEmpNo(Integer.parseInt(empNo));
         bitemp.seteName(eName);
         bitemp.setJob(job);
         bitemp.setSal(Integer.parseInt(sal));
         bitemp.setdName(dName);
         bitemp.setOriginFileName(originFileName);
         bitemp.setSaveFileName(saveFileName);
         
         //DB연결
         BitDao dao = new BitDao();
         int result = dao.editInfo(bitemp);
         
         empList = new ArrayList<BitEmp>();
         //empList.add(bitemp);
         empList = dao.getBitList();
         request.setAttribute("bitempData", bitemp);
         request.setAttribute("empList", empList);

         
         forward = new ActionForward();

         if (result > 0) {
            System.out.println("수정 완료");
            //System.out.println("수정 후 파일 이름 : "+bitemp.getSaveFileName());
            System.out.println(bitemp.getSaveFileName());
            forward.setRedirect(false); // forward 방식
            forward.setPath("/ListOk.d4b");
         } else {
            System.out.println("수정 실패");
            forward.setRedirect(false); // forward 방식
            forward.setPath("/editForm.d4b");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return forward;

   }
}