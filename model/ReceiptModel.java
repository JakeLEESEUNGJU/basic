package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReceiptModel {

	Connection con;

	public ReceiptModel() throws Exception {
		con = DBCon.getConnection();
	}
	
	public String selectEvtName(int evtNo){
		String sql = "select evt_title from event where evt_no ="+evtNo;
		String evtTitle = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()){
				evtTitle = rs.getString("evt_title");
			}
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			System.out.println("행사 이름 찾기 오류");
			e.printStackTrace();
		}
		return evtTitle;
	}
}
