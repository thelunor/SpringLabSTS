import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

/*
CREATE OR REPLACE PROCEDURE usp_EmpList
(
 p_sal IN number,
  p_cursor OUT SYS_REFCURSOR --APP ����ϱ� ���� Ÿ��
)
IS
 BEGIN
     OPEN p_cursor
        FOR
           SELECT empno, ename, sal FROM EMP WHERE sal > p_sal;
  END;
*/
public class Ex08_Oracle_Procedure_Select {

	public static void main(String[] args) {
		 Connection conn = null;
		  //��ɰ�ü
		  CallableStatement cstmt= null; //���� (procedure ó���ϴ� ��ü)
		  ResultSet rs = null;
		  try{
		   Class.forName("oracle.jdbc.OracleDriver");
		   conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bituser", "1004"); 
		      
		   //p_sal IN number,
		   //p_cursor OUT SYS_REFCURSOR --APP ����ϱ� ���� Ÿ��
		   String sql = "{call usp_EmpList(?,?)}";
		      cstmt = conn.prepareCall(sql);
		      
		      //usp_EmpList(?,?) input , output
		      cstmt.setInt(1, 2000);
		      cstmt.registerOutParameter(2, OracleTypes.CURSOR); //OUTPUT
		      cstmt.execute(); //���� ���� �ٸ��� ���ุ ........
		      
		      rs = (ResultSet)cstmt.getObject(2); //�ι�° ? �� ó�� ....
		   
		   while(rs.next()){
		    System.out.println(rs.getInt(1) +"/" + rs.getString(2) +"/" + rs.getInt(3));
		   }
		   
		  }catch(Exception e){
		   
		  }finally{
		   if(rs != null){try{rs.close();}catch(Exception e){}};
		   if(cstmt != null){try{cstmt.close();}catch(Exception e){}};
		   if(conn != null){try{conn.close();}catch(Exception e){}};
		  }

	}

}
