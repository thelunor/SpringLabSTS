package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import kr.or.bit.dto.Emp;
import kr.or.bit.dto.chart.AvgMaxMinSalaryByDept;
import kr.or.bit.dto.chart.DataByYear;
import kr.or.bit.dto.chart.LocDept;
import kr.or.bit.dto.chart.StatisticsByMgr;
import kr.or.bit.dto.chart.TotalSaleryChart;
import kr.or.bit.utils.DBHelper;

public class EmpDao {
	public int insertEmp(Emp emp) {
		int resultRow = 0;
		Connection connection = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO, IMAGEFILENAME)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setDate(5, new Date(emp.getHiredate().getTime()));
			pstmt.setInt(6, emp.getSal());
			pstmt.setInt(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			pstmt.setString(9, emp.getImagefilename());
			resultRow = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return resultRow;
	}

	public Emp getEmpByEmpno(int no) {
		Emp emp = null;

		Connection connection = DBHelper.getConnection("oracle");
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO , IMAGEFILENAME" + " FROM EMP WHERE EMPNO=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, no);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				int empno = resultSet.getInt(1);
				String ename = resultSet.getString(2);
				String job = resultSet.getString(3);
				int mgr = resultSet.getInt(4);
				Date hiredate = resultSet.getDate(5);
				int sal = resultSet.getInt(6);
				int comm = resultSet.getInt(7);
				int deptno = resultSet.getInt(8);
				String imagefilename = resultSet.getString(9);
				emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				emp.setImagefilename(imagefilename);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(resultSet);
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return emp;
	}
	
	public List<String> getJobRegister() {
		Connection conn = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> emps = new ArrayList<String>();
		String sql = "select distinct job from emp";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				emps.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(pstmt);
			DBHelper.close(rs);
			DBHelper.close(conn);
		}
		return emps;		
	}
	
	public boolean checkAdminLogin(String userid, String pwd) {
		boolean result = false;

		Connection connection = DBHelper.getConnection("oracle");
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT USERID, PWD FROM ADMINLIST WHERE USERID=?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userid);
			resultSet = pstmt.executeQuery();

			if (resultSet.next() && resultSet.getString("PWD").equals(pwd))
				result = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(resultSet);
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return result;
	}

	public List<Emp> getEmps() {
		List<Emp> emps = new ArrayList<Emp>();

		Connection connection = DBHelper.getConnection("oracle");
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO" + " FROM EMP";

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int empno = resultSet.getInt(1);
				String ename = resultSet.getString(2);
				String job = resultSet.getString(3);
				int mgr = resultSet.getInt(4);
				Date hiredate = resultSet.getDate(5);
				int sal = resultSet.getInt(6);
				int comm = resultSet.getInt(7);
				int deptno = resultSet.getInt(8);

				emps.add(new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(resultSet);
			DBHelper.close(connection);
		}

		return emps;
	}

	public int deleteEmpByEmpno(int empno) {
		int resultRow = 0;
		Connection connection = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM EMP WHERE EMPNO = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, empno);

			resultRow = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return resultRow;
	}

	public int updateEmp(Emp emp) {
		int resultRow = 0;
		Connection connection = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;

		String sql = "UPDATE EMP SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=?, IMAGEFILENAME=? " + " WHERE EMPNO=?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getMgr());
			pstmt.setDate(4, new Date(emp.getHiredate().getTime()));
			pstmt.setInt(5, emp.getSal());
			pstmt.setInt(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(9, emp.getEmpno());
			pstmt.setString(8, emp.getImagefilename());
			resultRow = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return resultRow;
	}

	public List<TotalSaleryChart> ChartDataByTotalSalery(int count) {
		List<TotalSaleryChart> results = new ArrayList<TotalSaleryChart>();
		Connection connection = DBHelper.getConnection("oracle");
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT ENAME, totalSal" + "  FROM (SELECT ENAME, SAL+NVL(COMM,0) AS totalSal"
				+ "           FROM EMP ORDER BY totalSal DESC)" + "WHERE ROWNUM <= ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, count);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				results.add(new TotalSaleryChart(resultSet.getString(1), resultSet.getInt(2)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}

		return results;
	}

	public List<DataByYear> dataByYear() {
		Connection conn = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "select to_char(hiredate,'YYYY')as hiredate, round(avg(sal),0) as avgsal ,max(sal) as maxsal ,min(sal)as minsal"
				+ " from emp group by to_char(hiredate,'YYYY') order by hiredate";
		ResultSet rs = null;
		List<DataByYear> data = new ArrayList<DataByYear>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DataByYear yearchart = new DataByYear();
				yearchart.setHiredate(rs.getString("hiredate"));
				yearchart.setAvgsal(rs.getInt("avgsal"));
				yearchart.setMaxsal(rs.getInt("maxsal"));
				yearchart.setMinsal(rs.getInt("minsal"));
				data.add(yearchart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(rs);
			DBHelper.close(conn);
		}
		return data;
	}

	public List<Integer> getDethNos() {
		List<Integer> results = new ArrayList<Integer>();
		Connection conn = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "SELECT DEPTNO FROM DEPT";
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(pstmt);
			DBHelper.close(rs);
			DBHelper.close(conn);
		}

		return results;
	}

	public List<AvgMaxMinSalaryByDept> ChartSalByDept() {
		Connection connection = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		String sql = "select e.deptno as deptno, round(avg(e.sal),0) as avgsal , max(e.sal) as maxsal, min(e.sal) as minsal from emp e join dept d on e.deptno = d.deptno group by e.deptno";

		List<AvgMaxMinSalaryByDept> empdata = new ArrayList<AvgMaxMinSalaryByDept>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				AvgMaxMinSalaryByDept data = new AvgMaxMinSalaryByDept();
				data.setDeptno(resultSet.getInt("deptno"));
				data.setAvg(resultSet.getInt("avgsal"));
				data.setMax(resultSet.getInt("maxsal"));
				data.setMin(resultSet.getInt("minsal"));

				empdata.add(data);
			}
		} catch (Exception e) {
			System.out.println("wonbo dao exception");
		} finally {
			DBHelper.close(resultSet);
			DBHelper.close(pstmt);
			DBHelper.close(connection);
		}
		return empdata;
	}

	public List<LocDept> LocChart() {
		Connection conn = DBHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LocDept> locdatas = new ArrayList<LocDept>();
		String sql = "select d.loc , count(d.loc) from emp e join dept d on e.deptno = d.deptno group by loc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LocDept ld = new LocDept();
				ld.setCity(rs.getString(1));
				ld.setCount(rs.getInt(2));

				locdatas.add(ld);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.close(rs);
			DBHelper.close(pstmt);
			DBHelper.close(conn);
		}
		return locdatas;

	}
	
	public List<StatisticsByMgr> statisticsByMgr(){
		List<StatisticsByMgr> list = null ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection("oracle");
			String sql = "select e.ename as ename, statistics.\"mgrnum\" as empno, trunc(statistics.\"avg\",0) as avg, statistics.\"max\", statistics.\"min\" " + 
					"from statistics join emp e " + 
					"on statistics.\"mgrnum\" = e.empno";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<StatisticsByMgr>();
			while(rs.next()) {
				StatisticsByMgr data = new StatisticsByMgr();
				data.setEname(rs.getString(1));
				data.setEmpno(rs.getInt(2));
				data.setAvg(rs.getInt(3));
				data.setMax(rs.getInt(4));
				data.setMin(rs.getInt(5));
				list.add(data);
			}
			
		}catch(Exception e) {
			System.out.println("Error at statisticsByMgr : " + e.getMessage());
		}finally {
			DBHelper.close(rs);
			DBHelper.close(pstmt);
			DBHelper.close(conn);
		}
		
		
		return list;
	}
}
