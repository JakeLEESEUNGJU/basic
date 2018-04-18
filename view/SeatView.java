package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.ArtCenter;

public class SeatView extends JPanel {
	JLabel laTitle;
	JButton bBack, bCancel, bNext;
	JTextArea taSeat;
	ArtCenter ac;
	

	public SeatView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}

	void eventProc() {

	}

	void addLayout() {
		laTitle = new JLabel("판매관리-공연-세부-좌석선택");
		bBack = new JButton("<공연선택");
		bCancel = new JButton("예매취소");
		bNext = new JButton("결제>");
		taSeat = new JTextArea(10, 10);
		taSeat.setBackground(Color.gray);

		JPanel p_north = new JPanel();
		p_north.setLayout(new FlowLayout());
		p_north.add(laTitle);
		p_north.add(bBack);
		p_north.add(bCancel);
		p_north.add(bNext);

		JPanel p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		JPanel p_center_north = new JPanel();
		p_center_north.setLayout(new BorderLayout());
		p_center_north.add(taSeat);

		JPanel p_center_center = new JPanel();
		p_center_center.setLayout(new GridBagLayout());
		GridBagConstraints cbc = new GridBagConstraints();
		// 홀 레이아웃 가져오기
		getHall("C", p_center_center, cbc);

		p_center.add(p_center_north, BorderLayout.NORTH);
		p_center.add(p_center_center, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
		setSize(800, 900);
		setVisible(true);

	}

	// 홀별 좌석도 가져오는 메서드
	void getHall(String loc, JPanel p_south, GridBagConstraints cbc) {
		Hall hall = new Hall();
		switch (loc) {
		case "A":
			hall.getHallA(p_south, cbc);
			break;
		case "B":
			hall.getHallB(p_south, cbc);
			break;
		case "C":
			hall.getHallC(p_south, cbc);
			break;
		default:
			break;
		}
	}

	void connectDB() {

	}

//	 public static void main(String[] args) {
//	 SeatView view = new SeatView();
//	
//	 }
}
