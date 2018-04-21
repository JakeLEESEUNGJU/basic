package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	Hall hall = new Hall(this);		// 좌석 객체
	
	public SeatView(ArtCenter ac) {//ArtCenter ac
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}

	void eventProc() {
		bNext.addActionListener(this);
		bBack.addActionListener(this);
		bCancel.addActionListener(this);
	}
	
	public void getTaString(String seatNum){
		setTextArea(seatNum);
	}
	
	public void setTextArea(String str){
		taSeat.setText(str);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == bNext) {
			if (hall.temp != null) {//***
				for(int i=0; i<hall.temp.length;i++){
					System.out.println(">>");
					System.out.println(hall.temp[i]);
				}
				JOptionPane.showMessageDialog(null, "좌석수 확인완료");
				// 결제 완료 db insert
				ac.movecard("receiptcard");
			} else {
				JOptionPane.showMessageDialog(null, "좌석수 확인요망");
			}
		}else if(evt == bBack){
			ac.movecard("exhibitioncard");
			
		}else if(evt == bCancel){
			ac.movecard("main");
		}
		
	}

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

		JPanel p_center_center = new JPanel();
		p_center_center.setLayout(new GridBagLayout());
		GridBagConstraints cbc = new GridBagConstraints();
		
		// 홀 레이아웃 가져오기 *****홀(장소) 인터페이스**수정필수****
		getHall("A", p_center_center, cbc);

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
		hall.setHall(loc, p_south, cbc);		
	}

	void connectDB() {

	}

//	 public static void main(String[] args) {
//	 SeatView view = new SeatView();
//	
//	 }

	
}
