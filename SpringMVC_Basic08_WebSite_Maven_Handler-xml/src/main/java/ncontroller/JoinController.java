package ncontroller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	private MemberDao memberdao;

	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	//회원가입 페이지
	@RequestMapping(value = "join.htm", method = RequestMethod.GET)
	public String join() {
		return "joinus.join";
	}
	
	//회원가입 처리 (method overloading)
	@RequestMapping(value = "join.htm", method = RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member);
		String view = null;
		
		try {
			int result = memberdao.insert(member);
			if(result>0) {
				view="redirect:/index.htm"; //같은 폴더가 아닐 경우 / root 로 옮겨가게 적어줘야 한다..
			}else {
				view="redirect:join.htm";
			}
		} catch (Exception e) {
			System.out.println("join : " + e.getMessage());
		}
		return view;
	}
	
	//회원가입 페이지
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "joinus.login";
	}
	
	@RequestMapping(value="login.htm", method = RequestMethod.POST)
	public String login(String uid, String pwd, HttpSession session) {
		System.out.println(uid + " / " + pwd);
		String view = null;
		
		try {
			boolean	isLogin = memberdao.login(uid, pwd);
			
			if(isLogin) {
				session.setAttribute("uid", uid);
				view="joinus.loginOk";
			}else {
				view="joinus.login";
			}
		} catch (Exception e) {
			System.out.println("login : " + e.getMessage());
		}
		return view;
	}
	
	@RequestMapping(value="logout.htm")
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.invalidate();
		return "joinus.login";
	}
	
}
