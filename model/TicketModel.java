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

	// 날짜,이벤트 타입 받아와서 데이터 검색
	public ArrayList<Object> selectByDate(String date, String kind) throws Exception {
		ArrayList<Object> tableList = new ArrayList<Object>();

		if (kind.equals("e")) {
			String sql = "SELECT  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price "
					+ " FROM event e inner join location l " + " on e.loc_no = l.loc_no "
					+ " where (to_date(?,'YYYY/MM/DD') between to_char(evt_Start,'YYYY/MM/DD') AND To_char(e.EVT_END,'YYYY/MM/DD') )and e.EVT_KIND=? and e.evt_flag='t'";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, date); // 1번째 물음표 날짜
			st.setString(2, kind); // 2번째 물음표 타입
			ResultSet result = st.executeQuery();
			while (result.next()) {
				ArrayList<String> itemList = new ArrayList<String>();
				itemList.add(result.getString("title")); // 제목
				itemList.add(result.getString("map")); // 위치
				itemList.add(result.getString("price")); // 가격
				tableList.add(itemList);
			}
			result.close();
			st.close();
		} else if (kind.equals("p")) {
			String sql = "SELECT  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price , "
					+ " to_char(p.PER_START,'hh24:mi') as stime,to_char(p.PER_end,'hh24:mi') as etime "
					+ " FROM event e inner join location l  on e.loc_no = l.loc_no inner join PERFORMANCE p "
					+ " on e.evt_no = p.evt_no "
					+ " where (to_date(?,'YYYY/MM/DD') between to_char(e.evt_Start,'YYYY/MM/DD') AND To_char(e.EVT_END,'YYYY/MM/DD') )and e.EVT_KIND=? and e.evt_flag='t'";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, date); // 날짜
			st.setString(2, kind); // 타입
			ResultSet result = st.executeQuery();
			while (result.next()) {
				ArrayList<String> itemList = new ArrayList<String>();
				itemList.add(result.getString("title")); // 제목
				itemList.add(result.getString("map")); // 위치
				itemList.add(result.getString("price")); // 가격
				itemList.add(result.getString("stime")); // 시작시간
				itemList.add(result.getString("etime")); // 종료시간
				tableList.add(itemList);

			}
			result.close();
			st.close();
		}
		return tableList;
	}

	// 제목 , 이벤트 종류(무엇을 리턴하는지 구분하기 위해사용) , 날짜 받아옴 ] -> 카디널리티 한개만 반환
	public ArrayList<String> searchItems(String title, String kind, String date) throws Exception {
		ArrayList<String> itemList = new ArrayList<String>();
		if (kind.equals("p")) {
			String sql = "SELECT p.PER_NO as pno, e.EVT_NO as eno,  e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price , "
					+ " to_char(p.PER_START,'hh24:mi') as stime,to_char(p.PER_end,'hh24:mi') as endtime "
					+ " FROM event e inner join location l "
					+ " on e.loc_no = l.loc_no inner join PERFORMANCE p on e.evt_no = p.evt_no "
					+ " where e.evt_title=? and e.evt_flag='t'";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				itemList.add(result.getString("pno")); // 공연번호
				itemList.add(result.getString("eno")); // 이벤트 번호
				itemList.add(result.getString("title"));// 이벤트 제목
				itemList.add(result.getString("map"));// 위치
				itemList.add(result.getString("price")); // 가격
				itemList.add(result.getString("stime"));// 시작시간
				itemList.add(result.getString("endtime"));// 종료시간
				itemList.add(date); // 관람할 날짜 YYYY/MM/DD
				itemList.add(kind); // p
			}
			result.close();
			st.close();
		} else if (kind.equals("e")) {
			String sql = "SELECT ex.EXI_NO as exno, e.EVT_NO as eno, e.EVT_TITLE title,l.LOC_MAP as map ,e.evt_price as price "
					+ " FROM event e inner join location l " + " on e.loc_no = l.loc_no inner join EXHIBITION ex "
					+ " on e.evt_no = ex.evt_no " + " where e.evt_title=? and e.evt_flag='t'";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				itemList.add(result.getString("exno")); // 전시번호
				itemList.add(result.getString("eno")); // 이벤트 번호
				itemList.add(result.getString("title"));// 제목
				itemList.add(result.getString("map")); // 위 치
				itemList.add(result.getString("price")); // 가격
				itemList.add(date); // 관람할 날짜 YYYY/MM/DD
				itemList.add(kind); // 관람할 이벤트 종류 e
			}

			result.close();
			st.close();
		}

		return itemList;

	}

}
