package model;

import java.sql.Connection;

public class SeatModel {

	Connection con;

	public SeatModel() throws Exception {
		con = DBCon.getConnection();
	}
}
