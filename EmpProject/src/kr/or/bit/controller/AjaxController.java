package kr.or.bit.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class AjaxController {
	
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@RequestMapping(value="GetDeptNos.do", method=RequestMethod.POST)
	public @ResponseBody List<Integer> GetDeptNos() {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Integer> results = empdao.getDethNos();
		return results;
	}
	
	@RequestMapping(value="GetEmpnos.do", method=RequestMethod.POST)
	public @ResponseBody List<Emp> GetEmpnos() {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Emp> results = empdao.getEmps();
		return results;
	}

	@RequestMapping(value="GetJobRegister.do", method=RequestMethod.POST)
	public @ResponseBody List<String> GetJobRegister() {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<String> results = empdao.getJobRegister();
		return results;
	}
	
	@RequestMapping(value="empnoCheck.do", method=RequestMethod.POST)
	public @ResponseBody String empnoCheck(@RequestParam(value="empno") String empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		Emp emp = empdao.getEmpByEmpno(Integer.parseInt(empno));
		String result = "";
		if(emp != null) {
			result = "true"; //사원 번호 사용 불가
		}else {
			result = "false";
		}
		return result;
	}
	
	
}
