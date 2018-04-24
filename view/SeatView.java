package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.synth.SynthSeparatorUI;

import main.ArtCenter;
import model.EmployeeModel;
import model.SeatModel;

public class SeatView extends JPanel implements ActionListener{
	JLabel laTitle, laSeat;			//화면 타이틀,선택한 좌석 보여주는 라벨
	JButton bBack, bCancel, bNext;	//공연선택 ,취소, 결제 버튼
	JTextArea taSeat;				//선택한좌석 보여주는 textArea
	ArtCenter ac;					//아트센터 객체
	Hall hall ;						// 좌석 객체
	GridBagConstraints cbc;			//GridBagConstraints 변수
	JPanel  p_center_center;		//JPanel 변수
	SeatModel model;
	
	public SeatView() {
	}

	public SeatView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		eventProc();
		connectDB();
	}
	
	//엑션리스너 등록하는 메서드
	void eventProc() {
		bNext.addActionListener(this);
		bBack.addActionListener(this);
		bCancel.addActionListener(this);
	}
	
	//textarea에 쓸 문자열 받아오는 메서드
	public void getTaString(String seatNum){
		setTextArea(seatNum);
	}
	
	//textarea에 텍스틑 쓰는 메서드
	public void setTextArea(String str){
		taSeat.setText(str);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == bNext) {//다음 버튼 클릭시
			int cnt = 0;
			for (int i = 0; i < hall.temp.length; i++) {
				if (hall.temp[i] != null) {
					cnt++;
				}
			}
			if (hall.temp.length == cnt) {//선택한 좌석수와 인원수가 같을때 이동
				ac.movecard("receiptcard");
			} else {
				JOptionPane.showMessageDialog(null, "선택한 좌석수 확인하세요");
			}

		} else if (evt == bBack) {//뒤로 버튼 클릭시
			ac.movecard("exhibitioncard");

		}else if(evt == bCancel){//예매취소 버튼 클릭시
			ac.movecard("main");
		}
		
	}

	//레이아웃 그리는 메서드
	void addLayout() {
		laTitle = new JLabel("판매관리-공연-세부-좌석선택");
		bBack = new JButton("<공연선택");
		bCancel = new JButton("예매취소");
		bNext = new JButton("결제>");
		
		taSeat = new JTextArea(10, 10);
		taSeat.setEditable(false);
		taSeat.setBackground(Color.LIGHT_GRAY);

		//맨 위 판넬
		JPanel p_north = new JPanel();
		p_north.setLayout(new FlowLayout());
		p_north.add(laTitle);
		p_north.add(bBack);
		p_north.add(bCancel);
		p_north.add(bNext);

		//가운데 판넬
		JPanel p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		JPanel p_center_north = new JPanel();
		//가운데 위
		p_center_north.setLayout(new BorderLayout());
		p_center_north.add(taSeat);

		//가운데의 가운데
		p_center_center = new JPanel();
		p_center_center.setLayout(new GridBagLayout());
		cbc = new GridBagConstraints();
		
		p_center.add(p_center_north, BorderLayout.NORTH);
		p_center.add(p_center_center, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
		
	}

	// 홀별 좌석도 가져오는 메서드
	void getHall(String loc, JPanel p_south, GridBagConstraints cbc) {
		p_south.removeAll();
		hall.setHall(loc, p_south, cbc);		
	}

	//디비 연결 메서드
	void connectDB() {
		try {
			model = new SeatModel();
			System.out.println("seat DB 연결 성공");
		} catch (Exception e) {
			System.out.println("seat DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	
	public void setTempList(ArrayList temp){ //temp (공연번호,이벤트 번호,이벤트제목,위치,기준가격,시작시간,종료시간)
		String locVar = temp.get(3).toString();//홀 위치 변수
		int perNo = Integer.parseInt(temp.get(0).toString()); //공연 번호 변수
		selectSoldSeats(perNo);
		getHall(locVar, p_center_center, cbc);

	}
	
	// 인원수 가져오기 아트센터에서 호출하는 메서드
	public void setPersonCnt(ArrayList temp) { // temp(성인수 , 아동수, 우대수, 성인기준가격)정보 담긴 리스트
		int cnt = 0; //성인, 아동, 우대 명수 더할 변수
		for (int i = 0; i < temp.size() - 1; i++) {
			cnt += Integer.parseInt(temp.get(i).toString());
		}

		hall = new Hall(this, cnt);
		
	}
	
	//이미 예약된 좌석 가져오는 메서드
	void selectSoldSeats(int perNo){
		//System.out.println("이미 팔린 좌석 조회 메서드 구현해!!");
		String soldSeatStr = model.selectSoldSeat(perNo);
		if(soldSeatStr.equals(null)){
			soldSeatStr=" ";
		}
		
		hall.soldSeat = soldSeatStr;
	}


	
}
