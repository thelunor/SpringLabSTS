package kr.or.bit.dao;

import java.util.List;
import java.util.Map;

import kr.or.bit.dto.Emp;

public interface EmpDao {
	
	public int insertEmp(Emp emp);	
	
	public Emp getEmpByEmpno(int no);
	
	public List<String> getJobRegister();
	
	public int checkAdminLogin(Map<String, String> map);
	
	public List<Emp> getEmps();
	
	public int deleteEmpByEmpno(int empno);
	
	public int updateEmp(Emp emp);
	
	public List<Integer> getDethNos();
	
	
}