package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Hall extends JPanel implements ActionListener {
	JButton[][] seats ;			//좌석버튼
	int w,h;					//좌석 갯수 설정 변수 
	SeatView parent;			// 좌석선택화면 변수
	int seatCnt = 3;//하드코딩 인터페이스(수정필요)*** //인원수
	String seatChoice="" ; //  선택한 좌석
	public Hall(SeatView parent) {
		this.parent = parent;
		
	}
	
	public void setHall(String HallType, JPanel p_south, GridBagConstraints cbc){
		switch (HallType) {
		case "A":
			getHallA(p_south, cbc);
			break;
		case "B":
			getHallB(p_south, cbc);
			break;
		case "C":
			getHallC(p_south, cbc);
			break;
		default:		
		}
		
		eventProc();
	}

	public void getHallA(JPanel p_south, GridBagConstraints cbc) {
		 w = 10;
		 h = 10;
		seats = new JButton[h][w];

		char row = 'A';
		int col = 1;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				String loc = row + "" + col;
				seats[i][j] = new JButton(loc);
				col = col + 1;
			}
			col = 1;
			row = (char) (row + 1);
		}
		cbc.weightx = 1;
		cbc.weighty = 1;
		cbc.fill = GridBagConstraints.BOTH;

		cbc.gridx = 1;
		cbc.gridy = 0;
		cbc.gridwidth = 11;
		cbc.gridheight = 1;
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel laStage = new JLabel("stage", JLabel.CENTER);
		laStage.setBorder(border);
		p_south.add(laStage, cbc);

		cbc.gridx = 0;
		cbc.gridy = 1;
		cbc.gridwidth = 11;
		cbc.gridheight = 1;
		p_south.add(new JLabel(""), cbc);

		for (int i = 0; i < h; i++) {
			cbc.gridx = 0;
			cbc.gridy = 0;
			cbc.gridwidth = 1;
			cbc.gridheight = 1;
			p_south.add(new JLabel(""), cbc);

			for (int j = 0; j < w; j++) {
				cbc.gridx = j + 1;// 컴포넌트가 위치할 행을 나타냄
				cbc.gridy = i + 2;
				cbc.gridwidth = 1;// 컴포넌트가 가로로 차지할 셀의 크기
				cbc.gridheight = 1;
				cbc.insets = new Insets(1, 1, 1, 1);
				p_south.add(seats[i][j], cbc);

				cbc.gridx = 12;
				cbc.gridy = 0;
				cbc.gridwidth = 1;
				cbc.gridheight = 1;
				p_south.add(new JLabel(""), cbc);
			}
		}
	}

	public void getHallB(JPanel p_south, GridBagConstraints cbc) {
		 w = 12;
		 h = 10;
		seats = new JButton[h][w];
		char row = 'A';
		for (int i = 0; i < h; i++, row = (char) (row + 1)) {
			int col = 1;
			for (int j = 0; j < w; j++) {
				if (j == 2 || j == 9) {// 제외 버튼 생성
					seats[i][j] = new JButton();
					continue;
				}
				// 각 좌석 버튼에 좌표이름표 부여
				String loc = row + "" + col;
				seats[i][j] = new JButton(loc);
				col = col + 1;
			}
		}
		cbc.weightx = 1;
		cbc.weighty = 1;
		cbc.fill = GridBagConstraints.BOTH;
		cbc.gridx = 1;
		cbc.gridy = 0;
		cbc.gridwidth = 12;
		cbc.gridheight = 1;
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel laStage = new JLabel("stage", JLabel.CENTER);
		laStage.setBorder(border);
		p_south.add(laStage, cbc);

		cbc.gridx = 0;
		cbc.gridy = 1;
		cbc.gridwidth = 11;
		cbc.gridheight = 1;
		p_south.add(new JLabel(""), cbc);

		for (int i = 0; i < h; i++) {
			cbc.gridx = 0;
			cbc.gridy = 0;
			cbc.gridwidth = 1;
			cbc.gridheight = 1;
			p_south.add(new JLabel(""), cbc);
			for (int j = 0; j < w; j++) {
				if (j == 2 || j == 9) {
					cbc.gridx = j + 1;
					cbc.gridy = i + 2;
					cbc.gridwidth = 1;
					cbc.gridheight = 1;

					p_south.add(new JLabel(""), cbc);
					continue;
				}

				cbc.gridx = j + 1;// 컴포넌트가 위치할 행을 나타냄
				cbc.gridy = i + 2;
				cbc.gridwidth = 1;// 컴포넌트가 가로로 차지할 셀의 크기
				cbc.gridheight = 1;
				cbc.insets = new Insets(1, 1, 1, 1);
				p_south.add(seats[i][j], cbc);

				cbc.gridx = 14;
				cbc.gridy = 0;
				cbc.gridwidth = 1;
				cbc.gridheight = 1;
				p_south.add(new JLabel(""), cbc);
			}
		}
	}

	public void getHallC(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		w = 12;
		h = 12;
		// 버튼 설정
		seats = new JButton[h][w];
		char row = 'A';
		for (int i = 0; i < h; i++, row = (char) (row + 1)) {
			int col = 1;
			for (int j = 0; j < w; j++) {
				if (j == 2 || j == 9) {
					seats[i][j] = new JButton();
					continue;
				}
				// 각 좌석 버튼에 좌표이름표 부여
				String loc = row + "" + col;
				seats[i][j] = new JButton(loc);
				col = col + 1;
			}
		}
		cbc.weightx = 1;
		cbc.weighty = 1;
		cbc.fill = GridBagConstraints.BOTH;
		// stage 영역
		cbc.gridx = 1;
		cbc.gridy = 0;
		cbc.gridwidth = 12;
		cbc.gridheight = 1;
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel laStage = new JLabel("stage", JLabel.CENTER);
		laStage.setBorder(border);
		p_south.add(laStage, cbc);
		// stage와 좌석사이 복도 영역
		cbc.gridx = 0;
		cbc.gridy = 1;
		cbc.gridwidth = 11;
		cbc.gridheight = 1;
		p_south.add(new JLabel(""), cbc);

		for (int i = 0; i < h; i++) {
			// 사이드 영역(왼쪽)
			cbc.gridx = 0;
			cbc.gridy = 0;
			cbc.gridwidth = 1;
			cbc.gridheight = 1;
			p_south.add(new JLabel(""), cbc);
			for (int j = 0; j < w; j++) {
				// 복도 영역
				if (j == 2 || j == 9) {
					cbc.gridx = j + 1;
					cbc.gridy = i + 2;
					cbc.gridwidth = 1;
					cbc.gridheight = 1;

					p_south.add(new JLabel(""), cbc);
					continue;
				}
				// 좌석 영역
				cbc.gridx = j + 1;// 컴포넌트가 위치할 행을 나타냄
				cbc.gridy = i + 2;
				cbc.gridwidth = 1;// 컴포넌트가 가로로 차지할 셀의 크기
				cbc.gridheight = 1;
				cbc.insets = new Insets(1, 1, 1, 1);
				p_south.add(seats[i][j], cbc);
				// 사이드 영역(오른쪽)
				cbc.gridx = 14;
				cbc.gridy = 0;
				cbc.gridwidth = 1;
				cbc.gridheight = 1;
				p_south.add(new JLabel(""), cbc);

			}
		}
	}

	void eventProc() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				seats[i][j].addActionListener(this);

			}
		}
	}
	// System.out.println(">>" + seatCnt);
	// parent.test(seatChoice.append(seats[i][j].getText()));
	// System.out.println(">>");

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		//버튼 누를 때 
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (evt == seats[i][j]) {
					if (seatCnt > 0) {
						if(seatCnt == 0){
							seatChoice += seats[i][j].getText();
							break;
						}
						seatChoice += seats[i][j].getText()+"/";
						System.out.println(seatChoice);
						seats[i][j].setBackground(Color.BLUE);
						seatCnt--;
					}
				}
			}

			}

		}
	
	
	public void choiceSeat(int seatCnt){
		
	}
}
	
	
