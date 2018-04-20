package model;

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

	public EventModel() throws Exception {
		con = DBCon.getConnection();
	}

	//장소 받아오는 메소드
	public ArrayList setLocation(int flag) throws Exception {
		
		String sql = null;
		if(flag == 0){
			sql = "SELECT loc_map FROM location WHERE loc_seat = ?";
		} else if(flag !=0){
			sql = "SELECT loc_map FROM location WHERE loc_seat <> ?";
		}
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 0);
		
		ArrayList temp = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			temp.add(rs.getString("LOC_MAP"));
		}
		
		rs.close();
		ps.close();
		
		return temp;
	}
	
	//전시입력메소드
	public void insertExi(Exhibition vo, String loc) throws Exception {
		
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
		
		psEvt.executeUpdate();
		psExi.executeUpdate();
		
		psEvt.close();
		psExi.close();
		
	}
// 공연입력 메소드
	public void insertPer(Performance vo, String loc) throws Exception {
		
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
		psEvt.setString(3, vo.getEvtEnd());
		psEvt.setString(4, vo.getEvtRating());
		psEvt.setInt(5, vo.getEvtPrice());
		psEvt.setString(6, vo.getEvtDetail());
		
		PreparedStatement psPer = con.prepareStatement(sqlPer);
		psPer.setString(1, vo.getPerActor());
		psPer.setString(2, vo.getPerDir());
		psPer.setString(3, vo.getEvtStart() +" "+ vo.getPerStart()); 
		psPer.setString(4, vo.getEvtEnd() +" "+ vo.getPerEnd());  
		
 
		
		psEvt.executeUpdate();
		psPer.executeUpdate();
		
		psEvt.close();
		psPer.close();
		
	}

	
	
	
	
	

	
}
