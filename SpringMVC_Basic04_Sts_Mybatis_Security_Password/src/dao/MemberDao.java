package dao;

import java.sql.SQLException;
import vo.Member;

public interface MemberDao {
	
	//회원정보 얻기
	public Member getMember(String userid); // MemberDao.xml id값과 함수명 일치
	
	//회원가입
	public int insertMember(Member member);

	// 회원정보수정
	public int updateMember(Member member);

	// 로그인 체크
	public int loginCheck(String username, String password);
	
	// 회원 ID 검증
	public int idCheck(String userid);
}
