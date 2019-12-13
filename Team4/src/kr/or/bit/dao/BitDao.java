package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.bit.dto.BitEmp;
import kr.or.bit.utils.DB_Close;

public class BitDao {

   DataSource ds = null;

   public BitDao() throws NamingException {
      Context context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
   }

   public ArrayList<BitEmp> getBitList() { // 전체조회

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList<BitEmp> empList = null;
      try {
         conn = ds.getConnection();
         String sql = "select empNo, eName, job, sal, dName, savefilename, originfilename from BitEmp";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         empList = new ArrayList<BitEmp>();
         while (rs.next()) {
            BitEmp bitemp = new BitEmp();
            bitemp.setEmpNo(rs.getInt("empNo")); // 사원 번호
            bitemp.seteName(rs.getString("eName")); // 사원 이름
            bitemp.setJob(rs.getString("job")); // 직무
            bitemp.setSal(rs.getInt("sal")); // 급여
            bitemp.setdName(rs.getString("dName")); // 부서 이름
            bitemp.setSaveFileName(rs.getString("saveFileName"));
            bitemp.setOriginFileName(rs.getString("originFileName"));
            empList.add(bitemp);
         }
      } catch (SQLException e) {
         e.printStackTrace();

      }
      try {
         DB_Close.close(rs);
         DB_Close.close(pstmt);
         conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return empList;
   }

   public BitEmp readEmp(int empNo) { // 한명만 불러 올 때
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      BitEmp bitemp = null;
      String sql = "select empNo, eName, job, sal, dName, saveFileName, originFileName from bitemp where empNo=?";
      
      try {
         conn = ds.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, empNo);
         bitemp = new BitEmp();
         
         rs = pstmt.executeQuery();
         
         if (rs.next()) {
            bitemp.setEmpNo(rs.getInt("empNo"));
            bitemp.seteName(rs.getString("eName"));
            bitemp.setJob(rs.getString("job"));
            bitemp.setSal(rs.getInt("sal"));
            bitemp.setdName(rs.getString("dName"));
            bitemp.setOriginFileName(rs.getString("originFileName"));
            bitemp.setSaveFileName(rs.getString("saveFileName"));
            System.out.println("bit emp 가져옴");
         }
                  
      } catch (Exception e) {
         System.out.println("readEmp 오류");
      } finally {
         DB_Close.close(rs);
         DB_Close.close(pstmt);
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return bitemp;
   }

   public BitEmp searchEmpNo(int empNo) { // 사번조회
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      BitEmp bitemp = null;

      try {
         conn = ds.getConnection();
         String sql = "select empNo, eName, job, sal, dName from bitemp where empNo=?";
         pstmt = conn.prepareStatement(sql);

         pstmt.setInt(1, empNo);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            bitemp = new BitEmp();
            bitemp.setEmpNo(rs.getInt("empNo")); // 사원 번호
            bitemp.seteName(rs.getString("eName")); // 사원 이름
            bitemp.setJob(rs.getString("job")); // 직무
            bitemp.setSal(rs.getInt("sal")); // 급여
            bitemp.setdName(rs.getString("dName")); // 부서 이름
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      try {
         DB_Close.close(rs);
         DB_Close.close(pstmt);
         conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return bitemp;
   }

   public ArrayList<BitEmp> searchDname(String dName) { // 부서이름 조회
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList<BitEmp> empList = null;
      String sql = "select empNo, eName, job, sal, dName from bitemp where dName=?";
      try {
         conn = ds.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dName);
         rs = pstmt.executeQuery();
         empList = new ArrayList<BitEmp>();

         while (rs.next()) {
            BitEmp bitemp = new BitEmp();
            bitemp.setEmpNo(rs.getInt("empNo")); // 사원 번호
            bitemp.seteName(rs.getString("eName")); // 사원 이름
            bitemp.setJob(rs.getString("job")); // 직무
            bitemp.setSal(rs.getInt("sal")); // 급여
            bitemp.setdName(rs.getString("dName")); // 부서 이름
            empList.add(bitemp);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DB_Close.close(rs);
         DB_Close.close(pstmt);
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return empList;

   }

   public ArrayList<BitEmp> searchEname(String eName) { // 이름조회
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList<BitEmp> empList = null;

      String sql = "select empNo, eName, job, sal, dName from BitEmp where eName=?";
      try {
         conn = ds.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, eName);
         rs = pstmt.executeQuery();
         empList = new ArrayList<BitEmp>();
         while (rs.next()) {

            BitEmp bitemp = new BitEmp();
            bitemp.setEmpNo(rs.getInt("empNo")); // 사원 번호
            bitemp.seteName(rs.getString("eName")); // 사원 이름
            bitemp.setJob(rs.getString("job")); // 직무
            bitemp.setSal(rs.getInt("sal")); // 급여
            bitemp.setdName(rs.getString("dName")); // 부서 이름
            empList.add(bitemp);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DB_Close.close(rs);
         DB_Close.close(pstmt);
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return empList;
   }

   public int addInfo(int empNo, String eName, String job, int sal, String dName, String originFileName,
         String saveFileName) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int resultRow = 0;
      try {
         conn = ds.getConnection();
         conn.setAutoCommit(false);
         String sql = "insert into bitemp(empno, ename, job, sal, dName, saveFileName, originFileName) values(?,?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, empNo);
         pstmt.setString(2, eName);
         pstmt.setString(3, job);
         pstmt.setInt(4, sal);
         pstmt.setString(5, dName);
         pstmt.setString(6, saveFileName);
         pstmt.setString(7, originFileName);
         resultRow = pstmt.executeUpdate();

         String sql_img = "INSERT INTO bitimg(empno, originFileName, saveFileName) VALUES (?,?,?)";
         pstmt = conn.prepareStatement(sql_img);
         pstmt.setInt(1, empNo);
         pstmt.setString(2, originFileName);
         pstmt.setString(3, saveFileName);

         resultRow = pstmt.executeUpdate();

         if (resultRow > 0) {
            conn.commit();
         }
      } catch (Exception e) {
         System.out.println("Insert : " + e.getMessage());
      } finally {
         DB_Close.close(pstmt);
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return resultRow;
   }

   /* 바로 삭제 */
   public int deleteInfo(int empNo) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int resultRow = 0;
      try {

         conn = ds.getConnection();
         conn.setAutoCommit(false);

         String deleteInfo = "delete from bitemp where empNo=?";
         String sql_imgDelete = "delete from bitimg where empNo=?";
         pstmt = conn.prepareStatement(sql_imgDelete);
         pstmt.setInt(1, empNo);
         resultRow = pstmt.executeUpdate();

         pstmt = conn.prepareStatement(deleteInfo);
         pstmt.setInt(1, empNo);
         resultRow = pstmt.executeUpdate();

         if (resultRow > 0) {
            conn.commit();
         }

      } catch (Exception e) {

      } finally {
         DB_Close.close(pstmt);
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return resultRow;
   }

   public int editInfo(BitEmp bitemp) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int resultRow = 0;
      try {
         conn = ds.getConnection();
         conn.setAutoCommit(false);

         String editInfo = "update bitemp set job=?, sal=?, dName=?, saveFileName=?, originFileName=? where empNo=?";
         String editImg = "update BitImg set originFileName=?, saveFileName=? where empNo=?";

         pstmt = conn.prepareStatement(editInfo);
         
         pstmt.setString(1, bitemp.getJob());
         pstmt.setInt(2, bitemp.getSal());
         pstmt.setString(3, bitemp.getdName());
         pstmt.setString(4, bitemp.getSaveFileName());
         pstmt.setString(5, bitemp.getOriginFileName());
         pstmt.setInt(6, bitemp.getEmpNo());
         
         resultRow = pstmt.executeUpdate();

         pstmt = conn.prepareStatement(editImg);
         
         pstmt.setString(1, bitemp.getOriginFileName());
         pstmt.setString(2, bitemp.getSaveFileName());
         pstmt.setInt(3, bitemp.getEmpNo());
         resultRow = pstmt.executeUpdate();
         if (resultRow > 0) {
            conn.commit();
         }
      } catch (Exception e) {
      } finally {
         DB_Close.close(rs);
         DB_Close.close(pstmt);

         try {
            conn.close();
         } catch (SQLException e) {
            System.out.println("update 예외");
         }

      }
      return resultRow;
   }

   public boolean adminLogin(String id, String pwd) {
      boolean check = false;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = ds.getConnection();
         String sql = "select pwd from bitadmin where id=? and pwd=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, pwd);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            check = true;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return check;
   }

}