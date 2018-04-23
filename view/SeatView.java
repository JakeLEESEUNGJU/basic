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

import main.ArtCenter;

public class SeatView extends JPanel implements ActionListener{
	JLabel laTitle, laSeat;			//화면 타이틀,선택한 좌석 보여주는 라벨
	JButton bBack, bCancel, bNext;	//공연선택 ,취소, 결제 버튼
	JTextArea taSeat;				//선택한좌석 보여주는 textArea
	ArtCenter ac;					//아트센터 객체
	Hall hall ;		// 좌석 객체
	GridBagConstraints cbc;
	JPanel  p_center_center;
	
	public SeatView() {
	}

	public SeatView(ArtCenter ac) {//ArtCenter ac
		this.ac = ac;
		addLayout();
		connectDB();
		//eventProc();
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
			if (hall.temp.length == cnt) {//선택한 좌석수와 인원수가 같을때
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
		/*laSeat = new JLabel(">>");
		laSeat.setEnabled(false);*/
		
		taSeat = new JTextArea(10, 10);
		taSeat.setEditable(false);
		//taSeat.setText(t);
		taSeat.setBackground(Color.LIGHT_GRAY);

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
		//p_center_north.add(laSeat);

		p_center_center = new JPanel();
		p_center_center.setLayout(new GridBagLayout());
		cbc = new GridBagConstraints();
		
		// 홀 레이아웃 가져오기 *****홀(장소) 인터페이스**수정필수****
		//getHall("piggy홀", p_center_center, cbc);

		p_center.add(p_center_north, BorderLayout.NORTH);
		p_center.add(p_center_center, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
//		setSize(800, 900);
//		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	// 홀별 좌석도 가져오는 메서드
	void getHall(String loc, JPanel p_south, GridBagConstraints cbc) {
		p_south.removeAll();
		hall.setHall(loc, p_south, cbc);		
	}

	//디비 연결 메서드
	void connectDB() {

	}
	
	
	public void setTempList(ArrayList temp){
		//System.out.println( temp.get(3));
		System.out.println(temp.get(2)+"세번째 확인");
		System.out.println(temp.get(3)+"세번째 확인");
		String locvar = temp.get(3).toString();
		
		System.out.println(">>>두번째");
		System.out.println(locvar+"!");
		getHall(locvar, p_center_center, cbc);

	}
	
	// 인원수 가져오기 아트센터에서 호출하는 메서드
	public void setPersonCnt(ArrayList temp) { // temp(성인수 , 아동수, 우대수, 성인기준가격)정보 담긴 리스트
		int cnt = 0;
		for (int i = 0; i < temp.size() - 1; i++) {
			// temp.get(i);
			// System.out.println(temp.get(i)+">>?");
			cnt += Integer.parseInt(temp.get(i).toString());
		}

		hall = new Hall(this, cnt);
		// System.out.println(">>seatview :"+hall.seatCnt);
//		addLayout();
		System.out.println(">>>첫번째");
		eventProc();
	}

//	 public static void main(String[] args) {
//	 SeatView view = new SeatView();
//	
//	 }

	
}
