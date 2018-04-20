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

	public ArrayList selectByDate(String date, String kind) throws Exception {
		ArrayList tableList = new ArrayList();
		
		if (kind.equals("e")) {
			String sql = "SELECT  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price "
					+ " FROM event e inner join location l "
					+ " on e.loc_no = l.loc_no "
					+ " where (to_date(?,'YYYY/MM/DD') between to_char(evt_Start,'YYYY/MM/DD') AND To_char(e.EVT_END,'YYYY/MM/DD') )and e.EVT_KIND=?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, date);
			st.setString(2, kind);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				ArrayList itemList = new ArrayList();
				itemList.add(result.getString("title"));
				itemList.add(result.getString("map"));
				itemList.add(result.getString("price"));
				tableList.add(itemList);
			}
			result.close();
			st.close();
		} else if (kind.equals("p")) {
			String sql = "SELECT  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price , "
					+ " to_char(p.PER_START,'hh24:mi') as stime,to_char(p.PER_end,'hh24:mi') as etime "
					+ " FROM event e inner join location l  on e.loc_no = l.loc_no inner join PERFORMANCE p "
					+ " on e.evt_no = p.evt_no "
					+ " where (to_date(?,'YYYY/MM/DD') between to_char(e.evt_Start,'YYYY/MM/DD') AND To_char(e.EVT_END,'YYYY/MM/DD') )and e.EVT_KIND=?"; 
			//System.out.println(date + "/" + kind);		
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, date);
			st.setString(2, kind);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				ArrayList itemList = new ArrayList();
				itemList.add(result.getString("title"));
				itemList.add(result.getString("map"));
				itemList.add(result.getString("price"));
				itemList.add(result.getString("stime"));
				itemList.add(result.getString("etime"));
				tableList.add(itemList);
				
			}
			result.close();
			st.close();
		}
		return tableList;
	}

}
