package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.Employee;

public class EmployeeModel {

	Connection con;

	public EmployeeModel() throws Exception {
		con = DBCon.getConnection();
	}

	//사원입력 -- 완성
	public void insertEmp(Employee vo) throws Exception {
		String sql = "INSERT INTO employee(emp_no, emp_name, emp_tel, emp_email, emp_dept) "
				+ "VALUES (SEQ_EMP_NO.nextval,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,vo.getEmpName());
		ps.setString(2, vo.getEmpTel());
		ps.setString(3, vo.getEmpEmail());
		ps.setString(4, vo.getEmpDept());
		ps.executeUpdate();
		ps.close();
	}
	
	//사원조회-전화번호로 -- 완성
	public Employee selectEmp(String tel) throws Exception{
		Employee vo = new Employee();
		
		String sql = "SELECT * FROM employee WHERE emp_tel=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			vo.setEmpNo(rs.getInt("EMP_NO"));
			vo.setEmpName(rs.getString("EMP_NAME"));
			vo.setEmpTel(rs.getString("EMP_TEL"));
			vo.setEmpEmail(rs.getString("EMP_EMAIL"));
			vo.setEmpDept(rs.getString("EMP_DEPT"));
		}
		
		rs.close();
		ps.close();
		
		return vo;
		
	}

	//사원수정 -- 완성
	public void modifyEmp(Employee vo) throws Exception{
		String sql = "UPDATE employee SET emp_name=?, emp_tel=?, emp_email=?, emp_dept=? "
				+ "WHERE emp_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getEmpName());
		ps.setString(2, vo.getEmpTel());
		ps.setString(3, vo.getEmpEmail());
		ps.setString(4, vo.getEmpDept());
		ps.setInt(5, vo.getEmpNo());
		
		ps.executeUpdate();
		ps.close();
	}
	
	//사원삭제 -- 완성
	public void deleteEmp(Employee vo) throws Exception {
		String sql = "DELETE FROM employee WHERE emp_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vo.getEmpNo());
		ps.executeUpdate();
		ps.close();
	}

	public ArrayList<String> loginDB(String ID, String PW) throws Exception{
		ArrayList<String> temp = new ArrayList<String>();
		String sql= "SELECT EMP_NO ,EMP_NAME,EMP_DEPT"
				+ " FROM EMPLOYEE "
				+ " WHERE EMP_EMAIL =? AND EMP_PW=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ID);
		ps.setString(2, PW);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			temp.add(rs.getString("EMP_NO"));
			temp.add(rs.getString("EMP_NAME"));
			temp.add(rs.getString("EMP_DEPT"));
		}
		return temp;
		
	}
	
}
