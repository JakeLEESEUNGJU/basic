package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketModel {

	Connection con;

	public TicketModel() throws Exception {
		con = DBCon.getConnection();
	}

	public void selectByDate(String date,String kind) throws Exception{
		// TODO Auto-generated method stub
		String sql = "SELECT e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price "
				+ "FROM event e inner join location l "
				+ "on e.loc_no = l.loc_no "
				+ "where to_char(evt_Start,'YYYY/MM/DD')=? AND  e.EVT_KIND=?";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet result = st.executeQuery();
		ArrayList rentaltemp = new ArrayList();
		while (result.next()) {
			ArrayList rentalstate = new ArrayList();
			rentalstate.add(result.getString("vcode"));
			rentalstate.add(result.getString("title"));
			rentalstate.add(result.getString("name"));
			rentalstate.add(result.getString("tel"));
			rentalstate.add(result.getString("mustreturn"));
			rentalstate.add(result.getString("state"));
			
			rentaltemp.add(rentalstate);
		}
		result.close();
		st.close();

	}

}
