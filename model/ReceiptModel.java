package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.Receipt;

public class ReceiptModel {

	Connection con;
	SeatModel seatModel = new SeatModel();

	public ReceiptModel() throws Exception {
		con = DBCon.getConnection();
	}

	// 행사번호로 행사이름 찾는 메서드
	public String selectEvtName(int evtNo) {
		String sql = "select evt_title from event where evt_no =" + evtNo;
		String evtTitle = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
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

	// 결제 완료후 영수증 insert메서드
	public int insertRec(Receipt vo, String flag) {

		try {

			if (flag.equals("p")) { // 공연 결제이면
				con.setAutoCommit(false);

				// INSERT SQL
				String sql1 = "INSERT INTO receipt(REC_NO, PER_NO, EMP_NO, REC_DATE, REC_EVTDATE, "
						+ "REC_PAY, REC_TOTAL, REC_SEAT, REC_ADULT, REC_CHILD, REC_ADV, REC_STARTTIME, REC_FINISHTIME,EXI_NO) "
						+ " VALUES (seq_rec_no.nextval,?,?,sysdate,?,?,?,?,?,?,?,?,?,0)";

				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, vo.getPerNo());
				ps1.setInt(2, vo.getEmpNo());
				ps1.setString(3, vo.getSeeDate());
				ps1.setString(4, vo.getRecMethod());
				ps1.setInt(5, vo.getRecPrice());
				ps1.setString(6, vo.getRecSeat());
				ps1.setInt(7, vo.getAdultCnt());
				ps1.setInt(8, vo.getChildCnt());
				ps1.setInt(9, vo.getAdvCnt());
				ps1.setString(10, vo.getStartTime());
				ps1.setString(11, vo.getFinishTime());

				int result1 = ps1.executeUpdate();

				// UPDATE SQL
				String areadySeat = seatModel.selectSoldSeat(vo.getPerNo());
				String sql2 = "UPDATE performance SET PER_READY = ? WHERE PER_NO = ?";

				PreparedStatement ps2 = con.prepareStatement(sql2);

				ps2.setString(1, areadySeat + " " + vo.getRecSeat());
				ps2.setInt(2, vo.getPerNo());

				int result2 = ps2.executeUpdate();

				// 트랜잭션
				if (result1 != 1 || result2 != 1) {
					con.rollback();
				}

				con.commit();
				con.setAutoCommit(true);

				return result1 + result2;

			} else if (flag.equals("e")) { // 전시 결제이면

				// INSERT SQL
				String sql = "INSERT INTO receipt(REC_NO, EXI_NO , EMP_NO, REC_DATE, REC_EVTDATE, "
						+ "REC_PAY, REC_TOTAL,  REC_ADULT, REC_CHILD, REC_ADV, PER_NO) "
						+ " VALUES (seq_rec_no.nextval,?,?,sysdate,?,?,?,?,?,?,0)";

				PreparedStatement ps = con.prepareStatement(sql);

				ps.setInt(1, vo.getExiNo());
				ps.setInt(2, vo.getEmpNo());
				ps.setString(3, vo.getSeeDate());
				ps.setString(4, vo.getRecMethod());
				ps.setInt(5, vo.getRecPrice());
				ps.setInt(6, vo.getAdultCnt());
				ps.setInt(7, vo.getChildCnt());
				ps.setInt(8, vo.getAdvCnt());

				int result = ps.executeUpdate();
				System.out.println("전송완료");
				return result;
			}

		} catch (SQLException e) {
			System.out.println("결제 오류");
			e.printStackTrace();
		}
		return 0;

	}
}
