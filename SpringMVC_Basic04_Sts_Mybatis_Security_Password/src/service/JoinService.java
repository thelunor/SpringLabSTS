package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;
	
	// 회원가입
	public int insertMember(Member member) {
		int result = 0;
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		
		try {
			result = memberdao.insertMember(member);
			
		} catch (Exception e) {
			System.out.println("insertMember 예외");
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int idCheck(String userid) {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		int result = memberdao.idCheck(userid);
		System.out.println(result);
		
		return result;
	}
}
