package kr.or.bit.controller;

import java.util.ArrayList;
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
	@RequestMapping(value = "logout.htm")
	public String logout() {
		return "index.htm";
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
	
	// 사원 정보수정
	public String updateEmp(Emp emp, HttpServletRequest request) {
		List<CommonsMultipartFile> files = emp.getFiles();
		List<String> filenames = new ArrayList<String>();
		
		if (files != null && files.size() > 0) {
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/images");
			}
		}
		return null;
	}
	
}
