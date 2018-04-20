package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.ArtCenter;
import model.EventModel;
import vo.Event;
import vo.Exhibition;
import vo.Performance;


public class EventView extends JPanel{
	ButtonGroup bg;
	JRadioButton rbExi, rbPer;
	JLabel laExiDir, laPerActor,laPerDir, laPerStart, laPerEnd, laLocation,
			laEvtNo, laEvtTitle, laEvtPeriod, laEvtRating, laEvtPrice, laEvtDetail, labt;
	JTextField tfExiDir, tfPerActor, tfPerDir, 
			   tfEvtNo, tfEvtTitle, tfEvtPrice;
	JTextArea taEvtDetail;
	JButton bSelectEvt, bInsertEvt, bModifyEvt, bDeleteEvt;
	
	JButton bHome;
	
	// 장소 combobox
	JComboBox cbLocation = new JComboBox<>();
	ArrayList cbExiLocation = new ArrayList<>();
	ArrayList cbPerLocation = new ArrayList<>();
	
	// 등급 combobox
	String[] listRating = {"전체관람가", "19세미만관람불가"};
	JComboBox cbRating = new JComboBox(listRating);
	
	// 시간 combobox
	JComboBox cbStartTimeH = new JComboBox<>();
	JComboBox cbStartTimeM = new JComboBox<>();
	JPanel pStartTime = new JPanel();
	JComboBox cbEndTimeH = new JComboBox<>();
	JComboBox cbEndTimeM = new JComboBox<>();
	JPanel pEndTime = new JPanel();
	
	// 기간 combobox
	JComboBox cbStartDateY = new JComboBox<>();
	JComboBox cbStartDateM = new JComboBox<>();
	JComboBox cbStartDateD = new JComboBox<>();
	JPanel pStartDate = new JPanel();
	
	JComboBox cbEndDateY = new JComboBox<>();
	JComboBox cbEndDateM = new JComboBox<>();
	JComboBox cbEndDateD = new JComboBox<>();
	JPanel pEndDate = new JPanel();
	
	int[] lastDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	// 기간 combobox 끝 
	
	EventModel model;
	
	
	ArtCenter ac;
	
	Font bfont, titlefont;
	
	
	public EventView(ArtCenter ac) {
		this.ac = ac;
		setList();
		addLayout();
		initStyle();
		connectDB();
		eventProc();
	}
	
	private void initStyle() {
		
		tfEvtNo.setEnabled(false);
		
		
	}

	void setList() {
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.DATE);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		
		// hours
		for (int i = 0; i < 24; i++) {
			cbStartTimeH.addItem(String.valueOf(i));
			cbEndTimeH.addItem(String.valueOf(i));
		}
		// minutes
		for (int i = 0; i < 60; i++) {
			cbStartTimeM.addItem(String.valueOf(i));
			cbEndTimeM.addItem(String.valueOf(i));
		}
		
		// years
		for (int i = c.get(Calendar.YEAR); i <= c.get(Calendar.YEAR) + 10; i++) {
			cbStartDateY.addItem(i);
			cbEndDateY.addItem(i);
		}
		
		//month
		for (int i = 0; i <= (Calendar.DECEMBER); i++) { 
			cbStartDateM.addItem(i + 1);
			cbEndDateM.addItem(i + 1);
		}
		
		
	}
	
	void setDayStart() {
		//start
		int year = (Integer) cbStartDateY.getSelectedItem(); 
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			lastDay[2] = 29;
		} else
			lastDay[2] = 28;

		int month = (int) cbStartDateM.getSelectedItem(); 
		cbStartDateD.removeAllItems(); 
		for (int i = 1; i <= lastDay[month]; i++) {
			cbStartDateD.addItem(i); 
		}

	}
	void setDayEnd(){
		// end
		int year1 = (Integer) cbEndDateY.getSelectedItem();
		if (year1 % 4 == 0 && year1 % 100 != 0 || year1 % 400 == 0) {
			lastDay[2] = 29;
		} else
			lastDay[2] = 28;

		int month1 = (int) cbEndDateM.getSelectedItem();
		cbEndDateD.removeAllItems();
		for (int i = 1; i <= lastDay[month1]; i++) {
			cbEndDateD.addItem(i);
		}
	}
	

	void addLayout() {

		bfont = new Font("맑은고딕", Font.BOLD, 20);
		titlefont = new Font("Serif", Font.BOLD, 40);
		
		rbExi = new JRadioButton("전시");
		rbPer = new JRadioButton("공연");
		bg = new ButtonGroup();
		bg.add(rbExi);
		bg.add(rbPer);
		
		laExiDir = new JLabel("작가", JLabel.CENTER);
		laLocation = new JLabel("장소", JLabel.CENTER);
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
		
		
		tfExiDir = new JTextField();
		tfPerActor = new JTextField();
		tfPerDir = new JTextField();
		tfEvtNo = new JTextField();
		tfEvtTitle = new JTextField();
		tfEvtPrice = new JTextField();
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
		//시작시간 패널
		pStartTime.setLayout(new FlowLayout());
		pStartTime.add(cbStartTimeH);
		pStartTime.add(new JLabel("시"));
		pStartTime.add(cbStartTimeM);
		pStartTime.add(new JLabel("분"));
		
		//종료시간 패널
		pEndTime.setLayout(new FlowLayout());
		pEndTime.add(cbEndTimeH);
		pEndTime.add(new JLabel("시"));
		pEndTime.add(cbEndTimeM);
		pEndTime.add(new JLabel("분"));
		
		//시작기간 패널
		pStartDate.setLayout(new FlowLayout());
		pStartDate.add(cbStartDateY);
//		pStartDate.add(new JLabel("년"));
		pStartDate.add(cbStartDateM);
//		pStartDate.add(new JLabel("월"));
		pStartDate.add(cbStartDateD);
//		pStartDate.add(new JLabel("일"));
		
		//종료기간패널
		pEndDate.setLayout(new FlowLayout());
		pEndDate.add(cbEndDateY);
//		pEndDate.add(new JLabel("년"));
		pEndDate.add(cbEndDateM);
//		pEndDate.add(new JLabel("월"));
		pEndDate.add(cbEndDateD);
//		pEndDate.add(new JLabel("일"));
		
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
		ptf_west_center.add(laLocation);
		ptf_west_center.add(cbLocation);
		
		ptf_west_center.add(laPerActor);
		ptf_west_center.add(tfPerActor);
		ptf_west_center.add(laPerDir);
		ptf_west_center.add(tfPerDir);
		ptf_west_center.add(laPerStart);
		ptf_west_center.add(pStartTime);
		ptf_west_center.add(laPerEnd);
		ptf_west_center.add(pEndTime);
	
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
		ptf_east_center.add(cbRating);
		ptf_east_center.add(laEvtPrice);
		ptf_east_center.add(tfEvtPrice);
		
		ptf_east_center.add(laEvtPeriod);
		ptf_east_center.add(pStartDate);
		ptf_east_center.add(labt);
		ptf_east_center.add(pEndDate);

		
		JPanel ptf_east = new JPanel();
		ptf_east.setLayout(new BorderLayout()); 
		ptf_east.add(ptf_east_center, "Center");
		
		//textfield center
		JPanel ptf_center = new JPanel();
		ptf_center.setLayout(new GridLayout(1,2));
		ptf_center.add(ptf_west);
		ptf_center.add(ptf_east);
		
		//textarea
		JScrollPane ptf_south = new JScrollPane(taEvtDetail);
//		ptf_south.setLayout(new BorderLayout());
//		ptf_south.add(taEvtDetail, "Center");
		
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
		rbExi.addActionListener(btnHandler);
		rbPer.addActionListener(btnHandler);
		cbStartDateY.addActionListener(btnHandler);
		cbStartDateM.addActionListener(btnHandler);
		cbStartDateD.addActionListener(btnHandler);
		cbEndDateY.addActionListener(btnHandler);
		cbEndDateM.addActionListener(btnHandler);
		cbEndDateD.addActionListener(btnHandler);
		
	}

	//버튼 이벤트 핸들러 클래스
		class ButtonEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				
				if(o==bSelectEvt){
					selectEvt();
				} else if (o==bInsertEvt){
					if(rbExi.isSelected()){
						insertExi();
					}else if(rbPer.isSelected()){
						insertPer();
					}
				} else if (o==bModifyEvt){
					modifyEvt();
				} else if (o==bDeleteEvt){
					deleteEvt();
				} else if (o==bHome){
					ac.movecard("main");
				} else if (o==rbExi){
					choiceExi();
				} else if (o==rbPer){
					choicePer();
				} else if (o==cbStartDateY||o==cbStartDateM){
					setDayStart();
				} else if (o==cbEndDateY||o==cbEndDateM){
					setDayEnd();
				}
			
			}

			
		}

	
// 버튼 메소드들
		
	//조회
	void selectEvt() {

	}
	//전시입력
	void insertExi() {
		
		Exhibition vo = new Exhibition();
		String startDate=((String)cbStartDateY.getSelectedItem())+" "+
						 ((String)cbStartDateM.getSelectedItem())+" "+
						 ((String)cbStartDateD.getSelectedItem());
		String endDate=((String)cbEndDateY.getSelectedItem())+" "+
				 	   ((String)cbEndDateM.getSelectedItem())+" "+
				 	   ((String)cbEndDateD.getSelectedItem());
		
		String loc = (String) cbLocation.getSelectedItem();
		vo.setExiDir(tfExiDir.getText());
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(endDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.insertExi(vo, loc);
			JOptionPane.showMessageDialog(null, "전시입력완료");
			tfExiDir.setText(null);
			tfEvtTitle.setText(null);
			tfEvtPrice.setText(null);
			cbRating.setSelectedIndex(0);
			cbLocation.setSelectedIndex(0);
			cbStartDateY.setSelectedIndex(0);
			cbStartDateM.setSelectedIndex(0);
			cbStartDateD.setSelectedIndex(0);
			cbEndDateY.setSelectedIndex(0);
			cbEndDateM.setSelectedIndex(0);
			cbEndDateD.setSelectedIndex(0);

			taEvtDetail.setText(null);
		} catch (Exception e) {
			System.out.println("전시입력실패");
			e.printStackTrace();
		}
	}
	
	//공연입력
	void insertPer(){
		Performance vo = new Performance();
		String startTime = (cbStartTimeH.getSelectedItem() +" "+ cbStartTimeM.getSelectedItem());
		String endTime = (cbEndTimeH.getSelectedItem() +" "+ cbEndTimeM.getSelectedItem());
		String startDate=(cbStartDateY.getSelectedItem())+" "+
				 		 (cbStartDateM.getSelectedItem())+" "+
				 		 (cbStartDateD.getSelectedItem());
		System.out.println(cbStartDateY.getSelectedItem() +" "+cbStartDateM.getSelectedItem() +" "+ cbStartDateD.getSelectedItem());
		String endDate=(cbEndDateY.getSelectedItem())+" "+
		 	   		   (cbEndDateM.getSelectedItem())+" "+
		 	   		   (cbEndDateD.getSelectedItem());
		System.out.println(cbEndDateY.getSelectedItem() +" "+cbEndDateM.getSelectedItem() +" "+ cbEndDateD.getSelectedItem());

		String loc = (String) cbLocation.getSelectedItem();
		vo.setPerActor(tfPerActor.getText());
		vo.setPerDir(tfPerDir.getText());
		vo.setPerStart(startTime);
		vo.setPerEnd(endTime);
		
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(endDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.insertPer(vo, loc);
			JOptionPane.showMessageDialog(null, "공연입력완료");
			tfPerActor.setText(null);
			tfPerDir.setText(null);
			cbStartTimeH.setSelectedIndex(0);
			cbStartTimeM.setSelectedIndex(0);
			cbEndTimeH.setSelectedIndex(0);
			cbEndTimeM.setSelectedIndex(0);
			tfEvtTitle.setText(null);
			tfEvtPrice.setText(null);
			cbRating.setSelectedIndex(0);
			cbLocation.setSelectedIndex(0);
			cbStartDateY.setSelectedIndex(0);
			cbStartDateM.setSelectedIndex(0);
			cbStartDateD.setSelectedIndex(0);
			cbEndDateY.setSelectedIndex(0);
			cbEndDateM.setSelectedIndex(0);
			cbEndDateD.setSelectedIndex(0);
		} catch (Exception e) {
			System.out.println("공연입력실패");
			e.printStackTrace();
		}
		
		
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
	
// radiobutton 선택시
	
	//전시선택
	void choiceExi(){
		// 공연정보 false
		tfPerActor.setEnabled(false);
		tfPerDir.setEnabled(false);
		cbStartTimeH.setEnabled(false);
		cbStartTimeM.setEnabled(false);
		cbEndTimeH.setEnabled(false);
		cbEndTimeM.setEnabled(false);
		// 전시정보 true 
		tfExiDir.setEnabled(true);
		
		//전시장소받아오기
		cbLocation.removeAllItems();
		cbExiLocation = setLocation(0);
		for (int i = 0; i < cbExiLocation.size(); i++) {
			cbLocation.addItem(cbExiLocation.get(i));
			
		}
		
	}
	
	//공연선택
	void choicePer(){
		// 전시정보 false
		tfExiDir.setEnabled(false);
		// 공연정보 true
		tfPerActor.setEnabled(true);
		tfPerDir.setEnabled(true);
		cbStartTimeH.setEnabled(true);
		cbStartTimeM.setEnabled(true);
		cbEndTimeH.setEnabled(true);
		cbEndTimeM.setEnabled(true);
	
		//장소받아오기
		cbLocation.removeAllItems();
		cbPerLocation = setLocation(1);
		for (int i = 0; i < cbPerLocation.size(); i++) {
			cbLocation.addItem(cbPerLocation.get(i));
			
		}
	}
	
	// 장소명 배열에 받아오는 메소드
	ArrayList setLocation(int flag){
		ArrayList list = new ArrayList<>();
		try {
			list = model.setLocation(flag);
		} catch (Exception e) {
			System.out.println("장소명 조회 실패");
			e.printStackTrace();
		}
		
		return list;
	}
}
