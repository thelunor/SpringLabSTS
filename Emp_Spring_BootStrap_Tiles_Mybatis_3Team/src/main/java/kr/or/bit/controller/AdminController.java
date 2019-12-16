package kr.or.bit.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class AdminController {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	// 로그인 화면(/views/login/Login.jsp)
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "login/Login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "login.htm", method = RequestMethod.POST)
	public String login(String id, String pwd, HttpSession session) {
		String isLogin = null;
		String view = null;
		
		try {
			EmpDao empdao = sqlsession.getMapper(EmpDao.class);
			isLogin = empdao.checkAdminLogin(id, pwd);
			session.setAttribute("id", id);
			
			if (isLogin != null) {
				view = "index";
			} else {
				view = "redirect:Login.jsp";
			}
		} catch (Exception e) {
			System.out.println("view : " + view);
			System.out.println(e.getMessage());
		}
		return view;
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "logout.htm", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:index.htm";
	}
	
	// 전체 사원목록 조회
	@RequestMapping(value = "MemberList.htm", method = RequestMethod.GET)
	public String getEmps(Model model) {
		List<Emp> emplist = null;
		
		try {
			EmpDao empdao = sqlsession.getMapper(EmpDao.class);
			emplist = empdao.getEmps();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("emplist", emplist);
		
		return "admin/MemberList";
	}
	
	// 사원 상세정보보기
	@RequestMapping(value = "MemberDetail.htm", method = RequestMethod.GET)
	public String getEmpByEmpno(int empno, Model model) {
		System.out.println("사원 상세정보보기 컨트롤러 진입");
		Emp emp = null;
		CommonsMultipartFile file = null;
		String imagefilename = null;
		
		try {
			EmpDao empdao = sqlsession.getMapper(EmpDao.class);
			emp = empdao.getEmpByEmpno(empno);
			imagefilename = emp.getFile().getOriginalFilename();
			System.out.println(emp);
		} catch (Exception e) {
			System.out.println("MemberDetail Controller");
			System.out.println("응? " + e.getMessage());
			System.out.println(e.getStackTrace());
			e.getStackTrace();
		}
		
		model.addAttribute("emp", emp);
		
		return "admin/MemberDetail";
	}
	
	// 사원 등록
	@RequestMapping(value = "insertEmp.htm", method = RequestMethod.GET)
	public String insertEmp() {
		return "register/Register";
	}
	
	// 사원 등록
	@RequestMapping(value = "insertEmp.htm", method = RequestMethod.POST)
	public String insertEmp(Emp emp, Model model, HttpServletRequest request) {
		System.out.println("사원 등록 컨트롤러 진입");
		
		CommonsMultipartFile file = emp.getFile();
		String imagefilename = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("upload");
		String filepath = path + "\\" + imagefilename;
		
		if (!file.equals("")) { // 실 파일 업로드
			FileOutputStream fos = null;

			try {
				fos = new FileOutputStream(filepath);
				fos.write(file.getBytes());
				fos.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			emp.setImagefilename(imagefilename);
		}
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		empdao.insertEmp(emp);
		
		model.addAttribute("emp", emp);
		
		return "redirect:index.htm";
	}
	
	// 사원 정보수정
	@RequestMapping(value = "MemberEdit.htm", method = RequestMethod.GET)
	public String updateEmp(int empno, Model model) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		Emp emp = empdao.getEmpByEmpno(empno);
		model.addAttribute("emp", emp);
		
		return "admin/MemberEdit";
	}
	
	// 사원 정보수정
	@RequestMapping(value = "MemberEdit.htm", method = RequestMethod.POST)
	public String updateEmp(Emp emp, Model model, HttpServletRequest request) {
		System.out.println("사원 정보수정 컨트롤러 진입");
		CommonsMultipartFile file = emp.getFile();
		String imagefilename = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("upload");
		String filepath = path + "\\" + imagefilename;
		
		if (!file.equals("")) { // 실 파일 업로드
			FileOutputStream fos = null;

			try {
				fos = new FileOutputStream(filepath);
				fos.write(file.getBytes());
				fos.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			emp.setImagefilename(imagefilename);
		}
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		empdao.updateEmp(emp);
		
		model.addAttribute("emp", emp);
		
		return "redirect:MemberList.htm";
	}
	
	// 사원 삭제
	@RequestMapping(value = "MemberDelete.htm", method = RequestMethod.GET)
	public String deleteEmpByEmpno(int empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		empdao.deleteEmpByEmpno(empno);
		
		return "redirect:MemberList.htm";
	}
}
