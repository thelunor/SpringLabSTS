package kr.or.bit.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class FrontController {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	// 로그인 화면 보여주기
	@RequestMapping(value = "/Login.do", method = RequestMethod.GET)
	public String login_form() {
		return "/WEB-INF/views/login/Login.jsp";
	}

	// 로그인 하기
	@RequestMapping(value = "/Login.do")
	public String login_submit(@RequestParam(value = "userid") String userid, @RequestParam(value = "pwd") String pwd,
			HttpSession session) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("pwd", pwd);
		System.out.println("아이디" + map.get("userid") + "비번" + map.get("pwd"));
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		int isLogin = empdao.checkAdminLogin(map);
		String view = "";
		if (isLogin > 0) {
			session.setAttribute("userid", userid); // session 저장
			view = "index.jsp";
		} else {
			view = "redirect:Login.do";
		}
		return view;
	}

	// 로그아웃	
	@RequestMapping(value = "/Logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index.jsp";
	}

	// 사원 등록 화면 보여주기
	@RequestMapping(value = "/Register.do", method = RequestMethod.GET)
	public String register_form() {
		return "/WEB-INF/views/register/Register.jsp";
	}

	// 사원 등록 화면 처리
	@RequestMapping(value = "/Register.do", method = RequestMethod.POST)
	public String register_submit(Emp emp, HttpServletRequest request) throws IOException {

		System.out.println("이엠피" + emp.toString());

		// DATE로 변환

		// 파일 업로드
		CommonsMultipartFile imagefile = emp.getFile();
		String filename = imagefile.getOriginalFilename();

		String path = request.getServletContext().getRealPath("/upload");

		String fpath = path + "\\" + filename; // 파일명 경로??

		// 파일쓰기 작업
		FileOutputStream fs = new FileOutputStream(fpath); // 없으면 거기다가 파일 생성함
		fs.write(imagefile.getBytes());
		fs.close();

		// DB에 파일 이름 저장
		emp.setImagefilename(filename);

		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		int result = empdao.insertEmp(emp);
		String view = "";
		System.out.println("result" + result);
		System.out.println("empemp" + emp.toString());
		if (result > 0) {
			view = "index.jsp";
		} else {
			view = "redirect:Register.do";
		}
		return view;
	}

	// 사원 리스트 가져오기
	@RequestMapping(value = "/MemberList.do", method = RequestMethod.GET)
	public String memberlist(Model model) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Emp> emplist = empdao.getEmps();
		model.addAttribute("emplist", emplist);
		return "/WEB-INF/views/admin/MemberList.jsp";
	}

	// 사원 상세보기 페이지
	@RequestMapping(value = "/MemberDetail.do", method = RequestMethod.GET)
	public String memberDetail(@RequestParam(value = "empno") String empno, Model model) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		Emp emp = empdao.getEmpByEmpno(Integer.parseInt(empno));
		model.addAttribute("empdetail", emp);
		return "/WEB-INF/views/admin/MemberDetail.jsp";
	}

	// 사원 삭제
	@RequestMapping(value = "/MemberDelete.do")
	public String memberDelete(@RequestParam(value = "empno") String empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		int result = empdao.deleteEmpByEmpno(Integer.parseInt(empno));
		String view = "";
		if (result > 0) {
			view = "MemberList.do";
		}
		return view;
	}

	// 사원 정보 수정 (페이지 보여주기)
	@RequestMapping(value = "/MemberEdit.do")
	public String memberEdit_form(@RequestParam(value = "empno") String empno, Model model) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		Emp emp = empdao.getEmpByEmpno(Integer.parseInt(empno));
		model.addAttribute("emp", emp);
		return "/WEB-INF/views/admin/MemberEdit.jsp";
	}

	// 사원 정보 수정 (처리)
	@RequestMapping(value = "/MemberEditOk.do")
	public String memberEdit_submit(Emp emp, HttpServletRequest request) throws IOException {

		// 파일 업로드
		CommonsMultipartFile imagefile = emp.getFile();
		String filename = imagefile.getOriginalFilename();

		String path = request.getServletContext().getRealPath("/upload");

		String fpath = path + "\\" + filename; // 파일명 경로??

		// 파일쓰기 작업
		FileOutputStream fs = new FileOutputStream(fpath); // 없으면 거기다가 파일 생성함
		fs.write(imagefile.getBytes());
		fs.close();

		// DB에 파일 이름 저장
		emp.setImagefilename(filename);
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		int result = empdao.updateEmp(emp);
		String view = "";
		if (result > 0) {
			System.out.println("사원 수정 성공");
			view = "index.jsp";
		}
		return view;

	}

}