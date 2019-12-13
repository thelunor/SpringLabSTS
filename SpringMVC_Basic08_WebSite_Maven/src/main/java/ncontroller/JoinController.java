package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController { // Controller도 bean 객체로 생성되어 있어야 한다

	private MemberDao memberdao;

	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	// 회원가입 화면
	@RequestMapping(value = "join.htm", method = RequestMethod.GET)
	public String join() {
		return "joinus.join"; // "join.jsp";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "join.htm", method = RequestMethod.POST)
	public String join(Member member) { // MemberDTO member field명과 <input name이 같아야 한다
		System.out.println(member.toString());
		
		try {
			memberdao.insert(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/index.htm";
		// 주의사항
		// 요청 주소 아래처럼
		// http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_jdbcTemplate/index.jsp
		// return "redirect:index.htm"; // 문제 발생(url 경로를 보기 때문에 > 같은 폴더가 아니기 때문에)
	}
}
