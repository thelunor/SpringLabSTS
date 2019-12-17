package kr.or.bit.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.bit.dao.EmpDao;

@Controller
public class AjaxController {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	// 사원등록(dept 비동기)
	@RequestMapping(value = "getDeptNos.do", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getDeptNos() {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Integer> list = empdao.getDeptNos();
		System.out.println("list: " + list);
		
		return list;
	}
}
