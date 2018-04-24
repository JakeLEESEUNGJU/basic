package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Event;
import vo.Exhibition;
import vo.Performance;

public class EventModel {

	Connection con;
	String oldTitle;
	

	public EventModel() throws Exception {
		con = DBCon.getConnection();
	}

// 전시조회 메소드 -- 완성!!!>_<
	public ArrayList selectExi(String title) throws Exception {
		ArrayList list = new ArrayList();
		Exhibition vo = new Exhibition();
		
		String sql = "SELECT e.evt_no, e.evt_title, e.evt_start, e.evt_end, e.evt_rating, "
				+ "e.evt_price, e.evt_detail, ex.exi_dir, l.loc_no "
				+ "FROM event e, exhibition ex, location l "
				+ "WHERE e.evt_no = ex.evt_no AND e.loc_no = l.loc_no "
				+ "AND e.evt_title = '"+title+"' "
				+ "AND e.evt_flag='t'";
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()){
			vo.setEvtNo(rs.getInt("EVT_NO"));
			vo.setEvtTitle(rs.getString("EVT_TITLE"));
			vo.setEvtStart(rs.getString("EVT_START"));
			vo.setEvtEnd(rs.getString("EVT_END"));
			vo.setEvtPrice(rs.getInt("EVT_PRICE"));
			vo.setEvtRating(rs.getString("EVT_RATING"));
			vo.setEvtDetail(rs.getString("EVT_DETAIL"));
			vo.setLocNo(rs.getInt("LOC_NO"));
			vo.setExiDir(rs.getString("EXI_DIR"));
		}
		oldTitle = vo.getEvtTitle();
	
		list.add(0, vo);
		
		// 장소얻어오기
		String sql2 = "SELECT loc_map FROM location WHERE loc_no=?";
		PreparedStatement ps = con.prepareStatement(sql2);
		ps.setInt(1, vo.getLocNo());
		
		ResultSet rs2 = ps.executeQuery();
		
		if(rs2.next()){
			
			list.add(1,rs2.getString("LOC_MAP"));
		}
		rs2.close();
		rs.close();
		st.close();
		ps.close();
		return list;
	}
	
// 공연조회 메소드 -- 완성!!!>_<
	public ArrayList selectPer(String title) throws Exception {
		ArrayList list = new ArrayList();
		Performance vo = new Performance();
		
		String sql = "SELECT e.evt_no, e.evt_title, e.evt_start, e.evt_end, "
				+ "e.evt_rating, e.evt_price, e.evt_detail, "
				+ "p.per_actor, p.per_dir, p.per_start, p.per_end, l.loc_no "
				+ "FROM event e, performance p, location l "
				+ "WHERE e.evt_no = p.evt_no AND e.loc_no = l.loc_no "
				+ "AND e.evt_title = '"+title+"' "
				+ "AND e.evt_flag='t'";
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			vo.setEvtNo(rs.getInt("EVT_NO"));
			vo.setEvtTitle(rs.getString("EVT_TITLE"));
			vo.setEvtStart(rs.getString("EVT_START"));
//			vo.setEvtEnd(rs.getString("EVT_END"));
			vo.setEvtPrice(rs.getInt("EVT_PRICE"));
			vo.setEvtRating(rs.getString("EVT_RATING"));
			vo.setEvtDetail(rs.getString("EVT_DETAIL"));
			vo.setLocNo(rs.getInt("LOC_NO"));
			vo.setPerActor(rs.getString("PER_ACTOR"));
			vo.setPerDir(rs.getString("PER_DIR"));
		}
		oldTitle = vo.getEvtTitle();
		
		list.add(0, vo);
		
		//장소얻어오기
		String sqlLoc = "SELECT loc_map FROM location WHERE loc_no=?";
		PreparedStatement psLoc = con.prepareStatement(sqlLoc);
		psLoc.setInt(1, vo.getLocNo());
		ResultSet rsLoc = psLoc.executeQuery();
		if(rsLoc.next()){
			list.add(1, rsLoc.getString("LOC_MAP"));
		}
		
		//시작시간얻어오기
		String sqlStime = "SELECT to_char(per_start, 'hh24mi') per_start "
				+ " FROM performance WHERE evt_no=?";
		PreparedStatement psStime = con.prepareStatement(sqlStime);
		psStime.setInt(1, vo.getEvtNo());
		ResultSet rsStime = psStime.executeQuery();
		if(rsStime.next()){
			list.add(2, rsStime.getString("PER_START"));
		}
		
		//종료시간얻어오기
		String sqlEtime = "SELECT to_char(per_end, 'hh24mi') per_end "
				+ " FROM performance WHERE evt_no=?";
		PreparedStatement psEtime = con.prepareStatement(sqlEtime);
		psEtime.setInt(1, vo.getEvtNo());
		ResultSet rsEtime = psEtime.executeQuery();
		if(rsEtime.next()){
			list.add(3, rsEtime.getString("PER_END"));
		}
		rsEtime.close();
		psEtime.close();
		rsStime.close();
		psStime.close();
		rsLoc.close();
		psLoc.close();
		rs.close();
		st.close();
		
		return list;
	
	}
	
// 전시입력메소드 -- 완성!!!>_<
	public void insertExi(Exhibition vo, String loc) throws Exception {
		con.setAutoCommit(false);
		String sqlEvt = "INSERT INTO event"
				+ "(evt_no, evt_title, evt_start, evt_end, evt_rating, evt_price, evt_detail, loc_no, evt_kind) "
				+ "VALUES (seq_evt_no.nextval, ?, ?,?, ?,?,?, "
				+ "(select loc_no from location where loc_map = '" +loc+ "'), 'e')";
		
		
		String sqlExi = "INSERT INTO exhibition(exi_no, evt_no, exi_dir) "
				+ "VALUES (seq_exi_no.nextval, seq_evt_no.currval, ?)";
		
		
		PreparedStatement psEvt = con.prepareStatement(sqlEvt);
		psEvt.setString(1, vo.getEvtTitle());
		psEvt.setString(2, vo.getEvtStart());
		psEvt.setString(3, vo.getEvtEnd());
		psEvt.setString(4, vo.getEvtRating());
		psEvt.setInt(5, vo.getEvtPrice());
		psEvt.setString(6, vo.getEvtDetail());
		
		PreparedStatement psExi = con.prepareStatement(sqlExi);
		psExi.setString(1, vo.getExiDir());
		
		int r1 = psEvt.executeUpdate();
		int r2 = psExi.executeUpdate();
		
		if(r1 != 1 || r2!= 1){
			con.rollback();	
		}
		con.commit();
		
		psEvt.close();
		psExi.close();
		
		con.setAutoCommit(true);
		
	}
	
// 공연입력 메소드
	public void insertPer(Performance vo, String loc) throws Exception {
		con.setAutoCommit(false);
		String sqlEvt = "INSERT INTO event"
				+ "(evt_no, evt_title, evt_start, evt_end, evt_rating, evt_price, evt_detail, loc_no, evt_kind) "
				+ "VALUES (seq_evt_no.nextval, ?, "
				+ "TO_DATE(?,'YYYY/mm/dd'), "
				+ "TO_DATE(?,'YYYY/mm/dd'), "
				+ "?,?,?, "
				+ "(select loc_no from location where loc_map = '" +loc+ "'), 'p')";
		
		String sqlPer = "INSERT INTO performance(per_no, evt_no, per_actor, per_dir, per_start, per_end) "
				+ "VALUES (seq_per_no.nextval, seq_evt_no.currval, ?,?, "
				+ "TO_DATE(?,'YYYY/mm/dd hh24:mi'), "
				+ "TO_DATE(?,'YYYY/mm/dd hh24:mi'))";
		
		PreparedStatement psEvt = con.prepareStatement(sqlEvt);
		psEvt.setString(1, vo.getEvtTitle());
		psEvt.setString(2, vo.getEvtStart());
		psEvt.setString(3, vo.getEvtStart());
		psEvt.setString(4, vo.getEvtRating());
		psEvt.setInt(5, vo.getEvtPrice());
		psEvt.setString(6, vo.getEvtDetail());
		
		PreparedStatement psPer = con.prepareStatement(sqlPer);
		psPer.setString(1, vo.getPerActor());
		psPer.setString(2, vo.getPerDir());
		psPer.setString(3, vo.getEvtStart() +" "+ vo.getPerStart()); 
		psPer.setString(4, vo.getEvtStart() +" "+ vo.getPerEnd());  
		
		int r1 = psEvt.executeUpdate();
		int r2 = psPer.executeUpdate();
		
		if(r1 != 1 || r2!= 1){
			con.rollback();	
		}
		con.commit();
		
		psEvt.close();
		psPer.close();
		
		con.setAutoCommit(true);
		
	}
	
// 전시수정메소드
	public void modifyExi(Exhibition vo, String loc) throws Exception{
		
		String sql1 = "UPDATE exhibition SET exi_dir = ? WHERE evt_no=?";
		String sql2 = "UPDATE event SET evt_title=?, evt_start=?, evt_end=?, evt_rating=?, "
				+ "evt_detail=?, evt_price=?, "
				+ "loc_no= (SELECT loc_no FROM location WHERE loc_map = '"+loc+"') "
				+ "WHERE evt_title=?";
		
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, vo.getExiDir());
		ps1.setInt(2, vo.getEvtNo());
		
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, vo.getEvtTitle());
		ps2.setString(2, vo.getEvtStart());
		ps2.setString(3, vo.getEvtEnd());
		ps2.setString(4, vo.getEvtRating());
		ps2.setString(5, vo.getEvtDetail());
		ps2.setInt(6, vo.getEvtPrice());
		ps2.setString(7, oldTitle);
		
		ps1.executeUpdate();
		ps2.executeUpdate();
		
		ps1.close();
		ps2.close();
		
		
		
	}
	
// 공연수정메소드
	public void modifyPer(Performance vo, String loc) throws Exception{
		String sql1 = "UPDATE performance SET per_actor = ?, per_dir=?, "
				+ "per_start=TO_DATE(?,'YYYY/mm/dd hh24:mi'), per_end=TO_DATE(?,'YYYY/mm/dd hh24:mi') "
				+ "WHERE evt_no=?";
		String sql2 = "UPDATE event SET evt_title=?, evt_start=?, evt_end=?, evt_rating=?, "
				+ "evt_detail=?, evt_price=?, "
				+ "loc_no= (SELECT loc_no FROM location WHERE loc_map = '"+loc+"') "
				+ "WHERE evt_title=?";
		
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, vo.getPerActor());
		ps1.setString(2, vo.getPerDir());
		ps1.setString(3, vo.getEvtStart() + " " + vo.getPerStart());
		ps1.setString(4, vo.getEvtStart() +" "+ vo.getPerEnd());
		ps1.setInt(5, vo.getEvtNo());
		
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, vo.getEvtTitle());
		ps2.setString(2, vo.getEvtStart());
		ps2.setString(3, vo.getEvtStart());
		ps2.setString(4, vo.getEvtRating());
		ps2.setString(5, vo.getEvtDetail());
		ps2.setInt(6, vo.getEvtPrice());
		ps2.setString(7, oldTitle);
		
		ps1.executeUpdate();
		ps2.executeUpdate();
		
		ps1.close();
		ps2.close();
		
	}
	
// 전시삭제메소드 -- 완성!!!>_<
	public void deleteExi(Exhibition vo) throws Exception {
		
		String sql1 = "UPDATE event SET evt_flag='f' WHERE evt_no=?";
		
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, vo.getEvtNo());
		
		ps1.executeUpdate();
		
		
		ps1.close();
		
		con.setAutoCommit(true);
		
	}
	
// 공연삭제메소드 -- 완성!!!>_<
	public void deletePer(Performance vo) throws Exception{

		String sql1 = "UPDATE event SET evt_flag='f' WHERE evt_no=?";

		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, vo.getEvtNo());
		ps1.executeUpdate();

		ps1.close();

	}

	
	
// 콤보박스 위해 장소목록 받아오는 메소드 
	public ArrayList setLocation(int flag) throws Exception {

		String sql = null;
		if (flag == 0) {
			sql = "SELECT loc_map FROM location WHERE loc_seat = ?";
		} else if (flag != 0) {
			sql = "SELECT loc_map FROM location WHERE loc_seat <> ?";
		}

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 0);

		ArrayList temp = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			temp.add(rs.getString("LOC_MAP"));
		}

		rs.close();
		ps.close();

		return temp;
	}

// 중복입력 방지 위해 입력된 시작일 얻어오는 메소드 
	public ArrayList searchStartDate(String loc, int evtNo) throws Exception {
		
		String sql = "SELECT to_char(evt_start,'YYYYMMDD') evt_start FROM event "
				+ "WHERE loc_no = "
				+ "(select loc_no from location where loc_map = '" +loc+ "') "
				+ "AND evt_no <> ?"
				+ "ORDER BY evt_no";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, evtNo);
		
		ResultSet rs= ps.executeQuery();
		ArrayList data = new ArrayList();
		while(rs.next()){
			String temp = rs.getString("EVT_START");
			
			data.add(temp);
		}
		
		rs.close();
		ps.close();
		
		
		return data;
		
	}

// 중복입력 방지 위해 입력된 종료일 얻어오는 메소드 
	public ArrayList searchEndDate(String loc, int evtNo) throws Exception{
		
		String sql = "SELECT to_char(evt_end,'YYYYMMDD') evt_end FROM event "
				+ "WHERE loc_no = "
				+ "(select loc_no from location where loc_map = '" +loc+ "') "
				+ "AND evt_no <> ?"
				+ "ORDER BY evt_no";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, evtNo);
		
		ResultSet rs= ps.executeQuery();
	
		
		ArrayList data = new ArrayList();
		while(rs.next()){
			String temp = rs.getString("EVT_END");
			data.add(temp);
		}
		
		rs.close();
		ps.close();
		
		
		return data;
		
	}

// 중복입력 방지 위해 입력된 시작시간 얻어오는 메소드 
	public ArrayList searchStartTime(String loc, int evtNo) throws Exception{
		String sql = "SELECT to_char(p.per_start, 'hh24mi') per_start "
				+ "FROM performance p, event e, location l "
				+ "WHERE (l.loc_no = e.loc_no) AND (e.evt_no = p.evt_no) "
				+ "AND l.loc_map = '" +loc+ "' "
				+ "AND e.evt_no <> ? "
				+ "ORDER BY e.evt_no";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, evtNo);
		
		ResultSet rs= ps.executeQuery();
		
		ArrayList data = new ArrayList();
		while(rs.next()){
			String temp = rs.getString("PER_START");
			data.add(temp);
		}
		
		rs.close();
		ps.close();
		
		
		return data;
		
	}

// 중복입력 방지 위해 입력된 종료시간 얻어오는 메소드
	public ArrayList searchEndTime(String loc, int evtNo) throws Exception{
		String sql = "SELECT to_char(p.per_end, 'hh24mi') per_end "
				+ "FROM performance p, event e, location l "
				+ "WHERE (l.loc_no = e.loc_no) AND (e.evt_no = p.evt_no) "
				+ "AND l.loc_map = '" +loc+ "' "
				+ "AND e.evt_no <> ? "
				+ "ORDER BY e.evt_no";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, evtNo);
				
		ResultSet rs= ps.executeQuery();
		
		ArrayList data = new ArrayList();
		while(rs.next()){
			String temp = rs.getString("PER_END");
			data.add(temp);
		}
		
		rs.close();
		ps.close();
		
		
		return data;
		
	}

	

	
	
	


	



	
	

	
}
