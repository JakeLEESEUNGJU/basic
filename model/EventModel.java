package model;

import java.sql.Connection;

public class EventModel {

	Connection con;

	public EventModel() throws Exception {
		con = DBCon.getConnection();
	}
}
