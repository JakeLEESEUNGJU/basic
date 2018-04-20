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
		String sql = "SELECT ex.EXI_NO as 전시번호, e.EVT_NO as 행사번호, e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price	 as 가격 "
				+ "			FROM event e inner join location l "
				+ "on e.loc_no = l.loc_no inner join EXHIBITION ex "
				+ "on e.evt_no = ex.evt_no "
				+ "where (? between to_char(evt_Start,'YYYY/MM/DD') AND To_char(e.EVT_END,'YYYY/MM/DD') )and e.EVT_KIND='?'";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, date);
		st.setString(2, kind);
		ResultSet result = st.executeQuery();
		ArrayList tableList = new ArrayList();
		while (result.next()) {
			ArrayList itemList = new ArrayList();
			itemList.add(result.getString("전시번호"));
			itemList.add(result.getString("행사번호"));
			itemList.add(result.getString("title"));
			itemList.add(result.getString("map"));
			itemList.add(result.getString("가격"));
			
		}
		result.close();
		st.close();

	}

}
