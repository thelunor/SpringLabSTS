package kr.or.bit.dao;

import java.util.List;

import kr.or.bit.dto.Emp;

public interface EmpDao {

	// 사원 생성
	public int insertEmp(Emp emp);
	
	// 사원 조회(사번)
	public Emp getEmpByEmpno(int empno);
	
	// 관리자 로그인 체크
	public String checkAdminLogin(String id, String pwd);
	
	// 사원 목록 조회
	public List<Emp> getEmps();
	
	// 사원 삭제(사번)
	public int deleteEmpByEmpno(int empno);
	
	// 사원정보수정
	public int updateEmp(Emp emp);
	
}
