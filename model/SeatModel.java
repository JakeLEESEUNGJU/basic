package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SeatModel {

	Connection con;

	public SeatModel() throws Exception {
		con = DBCon.getConnection();
	}

	// 이미 예약된 좌석 검색
	public String selectSoldSeat(int perNo) {
		String sql = "SELECT per_ready FROM performance WHERE per_no=" + perNo;
		String seat = null;
		//System.out.println(sql);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				seat = rs.getString("per_ready");
			}

		} catch (SQLException e) {
			System.out.println("이미 예약된 좌석 검색 실패");
			e.printStackTrace();
		}
		return seat;

	}

}
