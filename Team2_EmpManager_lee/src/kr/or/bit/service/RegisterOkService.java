package kr.or.bit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

public class RegisterOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			
			String imagefilename = "";
			
			ServletContext application = request.getServletContext();
			
			String uploadpath = application.getRealPath("upload");
			int size = 1024*1024*10;
			
			MultipartRequest multi = new MultipartRequest(request, uploadpath,size,"utf-8",new DefaultFileRenamePolicy());
			
			int empno = Integer.parseInt(multi.getParameter("empno"));
			String ename = multi.getParameter("ename");
			String job = multi.getParameter("job");
			int mgr = Integer.parseInt(multi.getParameter("mgr"));
			int sal = Integer.parseInt(multi.getParameter("sal"));
			int comm = Integer.parseInt(multi.getParameter("comm"));
			int deptno = Integer.parseInt(multi.getParameter("deptno"));
			Date hiredate= new SimpleDateFormat("yyyy-MM-dd").parse(multi.getParameter("hiredate"));
			
			Enumeration filenames = multi.getFileNames();
			
			String file2 = (String)filenames.nextElement();
			imagefilename = multi.getFilesystemName(file2);
			
			
					
			EmpDao dao = new EmpDao();
			Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
			emp.setImagefilename(imagefilename);
			int result = dao.insertEmp(emp);

			String msg = "";
			String url = "";
			if (result>0) {
				msg = "등록 성공! 로그인 페이지로 이동합니다.";
				url = "Login.do";
			} else {
				msg = "등록 실패! 회원 가입 페이지로 재 이동합니다.";
				url = "Register.do";
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			forward.setPath("/common/redirect.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
