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
	JButton[][] seats; // 좌석버튼
	int w, h; // 좌석 갯수 설정 변수
	SeatView parent; // 좌석선택화면 변수
	int seatCnt = 5;// 하드코딩 인터페이스(수정필요)*** //인원수
	int cnt = seatCnt;
	String defaultText ="총인원 " + seatCnt + ": ";
	StringBuffer seatChoice = new StringBuffer(defaultText); // 선택한좌석

	JButton[] temp; // 선택한 버튼 저장하는 배열
	String[] saledSeatArray;
	
	String saledSeat ="A1 A2 A3";//이미 팔린 좌석 ***인터페이스 *** 수정해야함

	public Hall(SeatView parent) {
		this.parent = parent;
		// 하드코딩 인터페이스(수정필요)*** //인원수
		temp = new JButton[seatCnt];
		//seatChoice = new StringBuffer("총인원 " + seatCnt + ": ");
		//parent.test("선택할 총 인원 " + seatCnt+"");

		 
	}
	void setSaledSeat(){
		saledSeatArray = saledSeat.split(" ");
		
		//디버깅
		for(int k=0; k< saledSeatArray.length;k++){
			for(int i=0; i<w;i++){
				for(int j=0; j<h;j++){
					//System.out.println(seats[i][j].getText()+">>>"+i+" "+j);
					//System.out.println("w:"+w+"h:"+h);
					if(seats[i][j].getText().equals(saledSeatArray[k] )){
						//System.out.println(">"+saledSeatArray[k]);
						seats[i][j].setEnabled(false);
					}
				}
			}
		}
		
	}
	

	//홀(장소) 선택하는 메서드
	public void setHall(String HallType, JPanel p_south, GridBagConstraints cbc) {
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

	//홀A 좌석도 그리는 메서드
	public void getHallA(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		w = 10;
		h = 10;
		// 버튼배열 설정
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
		// stage 영역
		cbc.gridx = 1;
		cbc.gridy = 0;
		cbc.gridwidth = 11;
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
		setSaledSeat();
	}

	//홀B 좌석도 그리는 메서드
	public void getHallB(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		w = 12;
		h = 10;
		// 버튼 배열 설정
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
		setSaledSeat();//***
	}

	//홀C 좌석도 그리는 메서드
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
		setSaledSeat();//***
	}

	void eventProc() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				seats[i][j].addActionListener(this);

			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		END: for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				// 버튼 누를 때
				if (evt == seats[i][j]) {
					for (int k = 0; k < seatCnt; k++) {
						for (int l = 0; l < seatCnt; l++) {
							if (seats[i][j] == temp[l]) {// 같은버튼 누르면
								temp[l] = null;
								seats[i][j].setBackground(null);
								seatChoice = new StringBuffer(defaultText);
								appendText(temp);
								break END;
							}
						}
						if (temp[k] == null) {// 비어있으면
							temp[k] = seats[i][j];
							seats[i][j].setBackground(Color.yellow);
							seatChoice = new StringBuffer(defaultText);
							appendText(temp);
							break;
						}
					}
				}
			}
			
		}
	}
	
	
	void printTemp(JButton[] array){
		System.out.println("??");
		for (int p = 0; p < array.length; p++) {
			if(temp[p] != null)
				System.out.println(array[p].getText());
		}
		
	}
	
	//StringBuffer에 텍스트 붙이는 메서드
	void appendText(JButton[] temp) {
		for (int i = 0; i < temp.length; i++) {
			if (i == 0) {
				if (temp[i] != null) {
					seatChoice.append(temp[i].getText());
				}
			} else if (i < temp.length) {
				if (temp[i] != null) {
					seatChoice.append(" ").append(temp[i].getText());
				}
			}
		}
		parent.getTaString(seatChoice.toString());
	}

	
}
