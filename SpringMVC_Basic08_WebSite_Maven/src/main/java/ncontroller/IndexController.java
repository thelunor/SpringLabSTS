package ncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = "/index.htm")
	public String index() {
		// return "index.jsp"; > tiles 적용 전
		return "home.index"; // *.* > {1} > home... > {2} > index
	}
}
