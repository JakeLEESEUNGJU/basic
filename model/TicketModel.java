package model;

import java.sql.Connection;

public class TicketModel {

	Connection con;

	public TicketModel() throws Exception {
		con = DBCon.getConnection();
	}

}
