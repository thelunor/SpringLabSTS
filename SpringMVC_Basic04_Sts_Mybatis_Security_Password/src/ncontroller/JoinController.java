package ncontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import dao.MemberDao;
import service.JoinService;
import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	
	/* private MemberDao memberdao; */
//	@Autowired
//	public void setMemberdao(MemberDao memberdao) {
//		this.memberdao = memberdao;
//	}
	
	@Autowired
	private View jsonview; // 비동기 아이디 중복확인
	
	@Autowired
	private JoinService joinservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//회원가입페이지
	@RequestMapping(value = "join.htm", method = RequestMethod.GET)
	public String join() {
		
		return  "joinus.join"; //"join.jsp"; 
	}
	
	//회원가입처리
	@RequestMapping(value = "join.htm", method = RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member.toString());
		int result = 0;
		String view = "";
		
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		result = joinservice.insertMember(member);
		
		if (result > 0) {
			System.out.println("회원가입처리 진행");
			view = "redirect:/index.htm"; // /가 빠진 index.htm은 /joinus/index.htm 이라서 404 error
		} else {
			System.out.println("회원가입 실패");
			view = "join.htm";
		}
		
		return view;  // /index.htm  
		//주의사항
		//요청 주소 ...아래처럼 ..
		//http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplate/index.htm
		//return "redirect:noticeDetail.htm?seq="+n.getSeq();
	}
	
	// 회원가입 시 아이디 중복확인(비동기 jsonview)
	@RequestMapping(value = "idCheck.htm", method = RequestMethod.POST)
	public View idCheck(@RequestParam("userid") String userid, Model model) {
		int result = joinservice.idCheck(userid);
		
		if (result > 0) {
			System.out.println("증복된 아이디입니다.");
			model.addAttribute("result", "Fail");
		} else {
			System.out.println("사용가능한 아이디입니다.");
			model.addAttribute("result", "success");
		}
		
		return jsonview;
	}
	
	// 로그인 화면
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "joinus.login"; // Tiles 적용
	}
}
