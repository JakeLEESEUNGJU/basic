package model;

import java.sql.Connection;

public class ReceiptModel {

	Connection con;

	public ReceiptModel() throws Exception {
		con = DBCon.getConnection();
	}
}
