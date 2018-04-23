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
	JButton[][] seats; 										// 좌석버튼
	int height, width; 										// 좌석 갯수 설정 변수 eventProc전용변수
	SeatView parent; 										// 좌석선택화면 변수
	int peopleCnt = 0;// 하드코딩 인터페이스(수정필요)*** 				//인원수
	int cnt = peopleCnt;										
	String defaultText="";		//textarea에 뜨는 기본 문자열
	StringBuffer choicedSeats = new StringBuffer(defaultText);// 선택한 좌석
	JButton[] temp; 										// 선택한 버튼 저장하는 배열
	String[] soldSeatsArray;
	String soldSeat ="A1 A2 A3";//이미 팔린 좌석 ***인터페이스 *** 수정해야함

	
	public Hall() {
	}

	public Hall(SeatView parent, int peopleCnt) {
		this.parent = parent;
		this.peopleCnt = peopleCnt;
		defaultText = "총인원 " + peopleCnt + ": ";

		// 하드코딩 인터페이스(수정필요)*** //인원수
		temp = new JButton[peopleCnt];
	}

	// 이미 예매된 좌석 enable 시키는 메서드
	void setSaledSeat(int w , int h) {
		soldSeatsArray = soldSeat.split(" ");
		for (int k = 0; k < soldSeatsArray.length; k++) {
			//System.out.println(soldSeatsArray[k]);
			//System.out.println(soldSeatsArray.length+"!");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					//System.out.println(i+" "+j+" "+k);
					if (seats[i][j].getText().equals(soldSeatsArray[k])) {
						seats[i][j].setEnabled(false);
					}
				}
			}
		}
	}
	
	// 홀(장소) 선택하는 메서드
	public void setHall(String HallType, JPanel p_south, GridBagConstraints cbc) {
		switch (HallType) {
		case "CharilPuth홀":
			// 
			getHallA(p_south, cbc);
			break;
		case "Ariana홀":
			getHallB(p_south, cbc);
			break;
		case "Piggy홀":
			getHallC(p_south, cbc);
			break;
		default:
		}
		eventProc(HallType);
	}

	//홀A 좌석도 그리는 메서드
	public void getHallA(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		//10*10
		int w = 10;
		int h = 10;
		height = 10;
		width= 10;
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
		setSaledSeat(w ,h);//
	}

	//홀B 좌석도 그리는 메서드
	public void getHallB(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		//10*10
		int w = 12;
		int h = 10;
		height = 10;
		width= 12;
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
		setSaledSeat(w ,h);
	}

	//홀C 좌석도 그리는 메서드
	public void getHallC(JPanel p_south, GridBagConstraints cbc) {
		// 홀크기 설정
		//10*12
		int w = 12;
		int h = 12;
		height = 12;
		width= 12;
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
		setSaledSeat(w ,h);
	}

	void eventProc(String hallType) {
/*		switch (hallType) {
		case "CharilPuth홀":
			// 
			break;
		case "Ariana홀":
			break;
		case "Piggy홀":
			break;
		default:
			

		}*/
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				seats[i][j].addActionListener(this);

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		END: for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// 버튼 누를 때
				if (evt == seats[i][j]) {
					for (int k = 0; k < peopleCnt; k++) {
						for (int l = 0; l < peopleCnt; l++) {
							if (seats[i][j] == temp[l]) {// 같은버튼 누르면
								temp[l] = null;
								seats[i][j].setBackground(null);
								choicedSeats = new StringBuffer(defaultText);
								appendText(temp);
								break END;
							}
						}
						if (temp[k] == null) {// 비어있으면
							temp[k] = seats[i][j];
							seats[i][j].setBackground(Color.yellow);
							choicedSeats = new StringBuffer(defaultText);
							appendText(temp);
							break;
						}
					}
				}
			}
			
		}
	}
	
	//디버깅용 temp배열 출력 메서드
	void printTemp(JButton[] array){
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
					choicedSeats.append(temp[i].getText());
				}
			} else if (i < temp.length) {
				if (temp[i] != null) {
					choicedSeats.append(" ").append(temp[i].getText());
				}
			}
		}
		parent.getTaString(choicedSeats.toString());
	}

	
}
