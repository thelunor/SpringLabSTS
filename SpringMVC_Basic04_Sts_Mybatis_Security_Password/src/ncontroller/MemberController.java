package ncontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 회원정보수정(비밀번호 인증)
	@RequestMapping(value = "memberConfirm.htm", method = RequestMethod.GET)
	public String memberConfirm() {
		return "joinus.memberConfirm";
	}
	
	@RequestMapping(value = "memberConfirm.htm", method = RequestMethod.POST)
	public String memberConfirm(@RequestParam("password") String rawPassword,
								Principal principal) {
		
		String view = "";
		
		// 회원정보
		Member member = memberservice.getMember(principal.getName());
		
		// DB에서 가져온 암호화된 문자열
		String encodedPassword = member.getPwd();
		
		System.out.println("raw: " + rawPassword);
		System.out.println("encode: " + encodedPassword);
		
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
		
		if (result) {
			view = "redirect:memberUpdate.htm";
		} else {
			view = "redirect:memberConfirm.htm";
		}
				
		return view;
	}
	
	// 회원정보수정
	@RequestMapping(value = "memberUpdate.htm", method = RequestMethod.GET)
	public String memberUpdate(Model model, Principal principal) {
		Member member = memberservice.getMember(principal.getName());
		model.addAttribute("member", member);
		
		return "joinus.memberUpdate";
	}
	
	@RequestMapping(value = "memberUpdate.htm", method = RequestMethod.POST)
	public String memberUpdate(Model model, Member member, Principal principal) {
		Member updatemember = memberservice.getMember(principal.getName());
		
		updatemember.setUserid(member.getUserid());
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		updatemember.setGender(member.getGender());
		updatemember.setName(member.getName());
		updatemember.setCphone(member.getCphone());
		updatemember.setEmail(member.getEmail());
		
		memberservice.updateMember(updatemember);
	
		return "redirect:/index.htm";
	}
}
