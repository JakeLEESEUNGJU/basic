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

	public ArrayList searchItems(String item ,String kind, String date) throws Exception{
		ArrayList itemList = new ArrayList();
		System.out.println("전송해볼까요~");
		if(kind.equals("p")){
		String sql =  "SELECT p.PER_NO as pno, e.EVT_NO as eno,  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price , "
				+ " to_char(p.PER_START,'hh24:mi') as stime,to_char(p.PER_end,'hh24:mi') as endtime "
				+ " FROM event e inner join location l "
				+ " on e.loc_no = l.loc_no inner join PERFORMANCE p on e.evt_no = p.evt_no "
				+ " where e.evt_title=?";
		//System.out.println(date + "/" + kind);	
		System.out.println(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, item);
		ResultSet result = st.executeQuery();
		System.out.println(result);
		if (result.next()) {
				itemList.add(result.getString("pno")); //공연번호
				itemList.add(result.getString("eno")); //이벤트 번호
				itemList.add(result.getString("title"));//이벤트 제목
				itemList.add(result.getString("map"));//위치
				itemList.add(result.getString("price")); // 가격
				itemList.add(result.getString("stime"));//시작시간
				itemList.add(result.getString("endtime"));//종료시간
				itemList.add(date); //관람할 날짜 YYYY/MM/DD
				itemList.add(kind); // p
		}		
		result.close();
		st.close();
		}else if(kind.equals("e")){
			String sql = "SELECT ex.EXI_NO as exno, e.EVT_NO as eno, e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price "
					+ " FROM event e inner join location l "
					+ " on e.loc_no = l.loc_no inner join EXHIBITION ex "
					+ " on e.evt_no = ex.evt_no "
					+ " where e.evt_title=?";
			//System.out.println(date + "/" + kind);	
			System.out.println(sql);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, item);
			ResultSet result = st.executeQuery();
			System.out.println(result);
			if (result.next()) {
				itemList.add(result.getString("exno")); //전시번호
				itemList.add(result.getString("eno")); //이벤트 번호
				itemList.add(result.getString("title"));//제목
				itemList.add(result.getString("map")); // 위 치
				itemList.add(result.getString("price")); //가격
				itemList.add(date); //관람할 날짜 YYYY/MM/DD
				itemList.add(kind); //관람할 이벤트 종류  e
			}
			
			result.close();
			st.close();
		}
		
		return itemList;
		
	}

}
