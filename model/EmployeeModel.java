package model;

import java.sql.Connection;

public class EmployeeModel {

	Connection con;

	public EmployeeModel() throws Exception {
		con = DBCon.getConnection();
	}
}
