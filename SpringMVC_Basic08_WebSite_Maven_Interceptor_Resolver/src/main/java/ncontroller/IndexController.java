package ncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("/index.htm")
	public String index() {
		//return "index"; Tiles 적용 전
		return "home.index";
	}
	
	@RequestMapping("/bit.htm")
	public ModelAndView bit() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bitView");
		mav.addObject("bit", "bit입니다.");
		
		return mav;
	}
	
}
