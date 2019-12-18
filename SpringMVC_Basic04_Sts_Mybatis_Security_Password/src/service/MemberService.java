package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class MemberService {

	@Autowired
	private SqlSession sqlsession;
	
	// 회원정보수정
	public Member getMember(String userid) {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		Member member = null;
		
		try {
			member = memberdao.getMember(userid);
		} catch (Exception e) {
			System.out.println("getMember 예외");
			System.out.println(e.getMessage());
		}
		
		return member;
	}
	
	public void updateMember(Member member) {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		
		try {
			result = memberdao.updateMember(member);
			
			if (result > 0) {
				System.out.println("회원정보수정 성공");
			} else {
				System.out.println("회원정보수정 실패");
			}
		} catch (Exception e) {
			System.out.println("updateMember 예외");
			System.out.println(e.getMessage());
		}
	}
}
