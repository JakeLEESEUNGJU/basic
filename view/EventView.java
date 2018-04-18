package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import main.ArtCenter;
import model.EmployeeModel;
import model.EventModel;


public class EventView extends JPanel{

	JRadioButton rbExi, rbPer;
	JLabel laExiDir, laPerActor,laPerDir, laPerStart, laPerEnd, 
			laEvtNo, laEvtTitle, laEvtPeriod, laEvtRating, laEvtPrice, laEvtDetail,labt;
	JTextField tfExiDir, tfPerActor, tfPerDir, tfPerStart, tfPerEnd, 
			   tfEvtNo, tfEvtTitle, tfEvtStart, tfEvtEnd, tfEvtRating, tfEvtPrice;
	JTextArea taEvtDetail;
	JButton bSelectEvt, bInsertEvt, bModifyEvt, bDeleteEvt;
	
	JButton bHome;
	
	EventModel model;
	ArtCenter ac;
	
	Font bfont, titlefont;
	
	public EventView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}
	
	void addLayout() {

		bfont = new Font("맑은고딕", Font.BOLD, 20);
		titlefont = new Font("Serif", Font.BOLD, 40);
		
		rbExi = new JRadioButton("전시");
		rbPer = new JRadioButton("공연");
		
		laExiDir = new JLabel("작가", JLabel.CENTER);
		laPerActor = new JLabel("출연진", JLabel.CENTER);
		laPerDir = new JLabel("감독", JLabel.CENTER);
		laPerStart = new JLabel("시작시간", JLabel.CENTER);
		laPerEnd = new JLabel("종료시간", JLabel.CENTER);
		laEvtNo = new JLabel("행사코드", JLabel.CENTER);
		laEvtTitle = new JLabel("제목", JLabel.CENTER);
		laEvtPeriod = new JLabel("기간", JLabel.CENTER);
		laEvtRating = new JLabel("등급", JLabel.CENTER);
		laEvtPrice = new JLabel("가격", JLabel.CENTER);
		labt = new JLabel("~", JLabel.CENTER);
		labt.setFont(bfont);
//		laEvtDetail = new JLabel("설명");
		
		tfExiDir = new JTextField(10);
		tfPerActor = new JTextField(10);
		tfPerDir = new JTextField(10);
		tfPerStart = new JTextField(10);
		tfPerEnd = new JTextField(10);
		tfEvtNo = new JTextField(10);
		tfEvtTitle = new JTextField(10);
		tfEvtStart = new JTextField(12);
		tfEvtEnd = new JTextField(12);
		tfEvtRating = new JTextField(10);
		tfEvtPrice = new JTextField(10);
		taEvtDetail = new JTextArea(20,70);
		taEvtDetail.setBorder(new TitledBorder("설명"));
		
		bSelectEvt = new JButton("조회");
		bSelectEvt.setFont(bfont);
		bInsertEvt = new JButton("입력");
		bInsertEvt.setFont(bfont);
		bModifyEvt = new JButton("수정");
		bModifyEvt.setFont(bfont);
		bDeleteEvt = new JButton("삭제");
		bDeleteEvt.setFont(bfont);
		bHome = new JButton("Home");
		
		
		
//붙이기
		// title 패널
		JPanel ptitle = new JPanel();
		ptitle.setLayout(new BorderLayout());
		ptitle.setBorder(BorderFactory.createEmptyBorder(10,10,20,20));
		ptitle.add(bHome,"East");
		
		// textfield들 붙이는 패널 
		
		// textfield_west
		JPanel ptf_west_north = new JPanel();
		ptf_west_north.setBorder(BorderFactory.createEmptyBorder(0,10,40,20));
		ptf_west_north.add(rbExi);
		ptf_west_north.add(rbPer);
		
		JPanel ptf_west_center = new JPanel();
		GridLayout glo = new GridLayout(6, 2);
		glo.setHgap(15);
		glo.setVgap(15);
		ptf_west_center.setLayout(glo);
		ptf_west_center.add(laExiDir);
		ptf_west_center.add(tfExiDir);
		ptf_west_center.add(new JLabel(""));
		ptf_west_center.add(new JLabel(""));
		
		ptf_west_center.add(laPerActor);
		ptf_west_center.add(tfPerActor);
		ptf_west_center.add(laPerDir);
		ptf_west_center.add(tfPerDir);
		ptf_west_center.add(laPerStart);
		ptf_west_center.add(tfPerStart);
		ptf_west_center.add(laPerEnd);
		ptf_west_center.add(tfPerEnd);
	
		JPanel ptf_west = new JPanel();
		ptf_west.setLayout(new BorderLayout());
		ptf_west.add(ptf_west_north, "North");
		ptf_west.add(ptf_west_center, "Center");
		
		// textfield_east
		
		JPanel ptf_east_center = new JPanel();
		GridLayout glo1 = new GridLayout(6, 2);
		glo1.setHgap(15);
		glo1.setVgap(30);
		ptf_east_center.setLayout(glo1);
		ptf_east_center.add(laEvtNo);
		ptf_east_center.add(tfEvtNo);
		ptf_east_center.add(laEvtTitle);
		ptf_east_center.add(tfEvtTitle);
		ptf_east_center.add(laEvtRating);
		ptf_east_center.add(tfEvtRating);
		ptf_east_center.add(laEvtPrice);
		ptf_east_center.add(tfEvtPrice);
		ptf_east_center.add(laEvtPeriod);
		ptf_east_center.add(tfEvtStart);
		ptf_east_center.add(labt);
		ptf_east_center.add(tfEvtEnd);
		
//		JPanel ptf_east_center_south = new JPanel();
//		ptf_east_center_south.setBorder(BorderFactory.createEmptyBorder(0,10,20,0));
//		ptf_east_center_south.add(laEvtPeriod);
//		ptf_east_center_south.add(tfEvtStart);
//		ptf_east_center_south.add(new JLabel("~"));
//		ptf_east_center_south.add(tfEvtEnd);
		
		
		
		JPanel ptf_east = new JPanel();
		ptf_east.setLayout(new BorderLayout()); 
		ptf_east.add(ptf_east_center, "Center");
//		ptf_east.add(ptf_east_center_south, "South");
		
		//textfield center
		JPanel ptf_center = new JPanel();
		ptf_center.setLayout(new GridLayout(1,2));
		ptf_center.add(ptf_west);
		ptf_center.add(ptf_east);
		
		//textarea
		JPanel ptf_south = new JPanel();
		ptf_south.add(taEvtDetail);
		
		// textfield 전체
		JPanel ptf = new JPanel();
		ptf.setLayout(new BorderLayout());
		ptf_center.setBorder(BorderFactory.createEmptyBorder(0,10,20,80));
		ptf.add(ptf_center, "Center");
		ptf.add(ptf_south, "South");
		
		// button 붙이는 패널
		JPanel pb = new JPanel();
		pb.setLayout(new GridLayout(1, 4));
		pb.setBorder(BorderFactory.createEmptyBorder(10 , 0 , 10 , 0));
		pb.add(bSelectEvt);
		pb.add(bInsertEvt);
		pb.add(bModifyEvt);
		pb.add(bDeleteEvt);
		
		// 전체 패널에 붙이기
		setLayout(new BorderLayout());
		TitledBorder tb = new TitledBorder("행사관리");
		tb.setTitleFont(titlefont);
		setBorder(tb);
		add("North", ptitle);
		add("Center", ptf);
		add("South", pb);
	}

	void connectDB() {
		try {
			model = new EventModel();
			System.out.println("행사관리 DB 연결 성공");
		} catch (Exception e) {
			System.out.println("행사관리 DB 연결 ");
			e.printStackTrace();
		}
	}
	void eventProc() {
		ButtonEventHandler btnHandler = new ButtonEventHandler();
		
		//이벤트 등록
		bSelectEvt.addActionListener(btnHandler);
		bInsertEvt.addActionListener(btnHandler);
		bModifyEvt.addActionListener(btnHandler);
		bDeleteEvt.addActionListener(btnHandler);
		bHome.addActionListener(btnHandler);
	}

	//버튼 이벤트 핸들러 클래스
		class ButtonEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				
				if(o==bSelectEvt){
					selectEvt();
				} else if (o==bInsertEvt){
					insertEvt();
				} else if (o==bModifyEvt){
					modifyEvt();
				} else if (o==bDeleteEvt){
					deleteEvt();
				} else if (o==bHome){
					ac.movecard("main");
				}
			
			}
			
		}

	
//버튼 메소드들 
	//조회
	void selectEvt() {

	}
	//입력
	void insertEvt() {

	}
	//수정
	void modifyEvt() {

	}
	//삭제
	void deleteEvt() {

	}

	//초기화
	void clear() {

	}
}
