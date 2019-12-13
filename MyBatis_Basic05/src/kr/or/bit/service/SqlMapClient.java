package kr.or.bit.service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
  
나쁜 냄새가 나는 코드  
public class SqlMapClient {
	private static SqlSession session;
	1. static
	2. factory > 애플리케이션이 실행되는 동안
	3. factory가 생성하는 session은 안전하지 않다
	
	static {
		String resource = "SqlMapConfig.xml";
		try {
			  Reader reader =  Resources.getResourceAsReader(resource);
			  SqlSessionFactory factory=  new  SqlSessionFactoryBuilder().build(reader);
			  session = factory.openSession();
		}catch (Exception e) {
			
		}
	}
	public static SqlSession getSqlSession() {
		return session;
	}
	
}
*/

public class SqlMapClient {
	private static SqlSessionFactory sqlsessionfactory;
	static {
		String resource = "SqlMapConfig.xml";
		try {
			 Reader reader = Resources.getResourceAsReader(resource);
			 sqlsessionfactory = new SqlSessionFactoryBuilder().build(reader);
			 
		}catch(Exception e) {
			
		}
 
	}
	 public static SqlSessionFactory getSqlSession(){
		  return sqlsessionfactory;
	  }	
}
