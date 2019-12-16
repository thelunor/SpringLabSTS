package kosta.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.EmpDto;
import service.EmpService;

@Controller
public class AjaxController {

	//org.springframework.web.servlet.view.json.MappingJackson2JsonView
	@Autowired
	private View jsonview;
	
	@Autowired
	private EmpService empservice;
	
	@RequestMapping(value="response.ajax")
	public void ajaxResponse(HttpServletResponse response){
		String empJson="[";
		
		List<EmpDto> list = empservice.getEmpList();
		for(int i=0;i<list.size();i++) {
			  empJson +=
			           "{\"empno\":\""+list.get(i).getEmpno()
			           +"\",\"ename\":\""+list.get(i).getEname()
			           +"\",\"job\":\""+list.get(i).getJob()
			           +"\",\"mgr\":\""+list.get(i).getMgr()
			           +"\",\"hiredate\":\""+list.get(i).getHiredate()
			           +"\",\"sal\":\""+list.get(i).getSal()
			           +"\",\"comm\":\""+list.get(i).getComm()
			           +"\",\"deptno\":\""+list.get(i).getDeptno()+"\"}";
			            if(i!=list.size()-1) {
			                empJson+=",";
			            }
		}
		empJson += "]";
		try {
			response.getWriter().print(empJson); // framework를 타지 않는다(lib 필요없다)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="objMapper.ajax")
	public void ajaxObjMapper(HttpServletResponse response){
		
		ObjectMapper mapper = new ObjectMapper();
		List<EmpDto> list = empservice.getEmpList();
		
		try {
			response.getWriter().print(mapper.writeValueAsString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="responseBody.ajax")
	public @ResponseBody List<EmpDto> ajaxResponseBody(){
		// jackson lib 3개가 있어야 한다. >> pom.xml
		// mvc namespace, schema 추가 >> mvc:annotation-driven >> servlet-context.xml
		List<EmpDto> list = empservice.getEmpList();
		
		return list;  
	}
	
	
	@RequestMapping(value="json.ajax")
	public View jsonkosta(Model model){
		
		List<EmpDto> list = empservice.getEmpList();
		model.addAttribute("emp", list);
		
		return jsonview;  //private View jsonview 타입으로 리턴
	}
	
	
	///////////////////////////////////////////////////////////
	
	@RequestMapping(value="delete.ajax")
	
	public View delete(String empno,Model model) {
		empservice.deleteEmp(empno);
		List<EmpDto> list = empservice.getEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	
	@RequestMapping(value="update.ajax",method=RequestMethod.GET)
	public View update(String empno, Model model){
		model.addAttribute("emp",empservice.selectEmp(empno));
		
		return jsonview;  
	}
	
	@RequestMapping(value="update.ajax",method=RequestMethod.POST)
	public View update(EmpDto empdto, Model model){
		empservice.updateEmp(empdto);
		List<EmpDto> list = empservice.getEmpList();
		model.addAttribute("emp", list);
		
		return jsonview;  
	}
	
	@RequestMapping(value="insert.ajax",method=RequestMethod.POST)
	public View insert(EmpDto empdto, Model model){
		empservice.insertEmp(empdto);
		List<EmpDto> list = empservice.getEmpList();
		model.addAttribute("emp", list);		
		return jsonview;  
	}
	
			
}
