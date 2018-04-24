package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.ArtCenter;
import model.EventModel;
import vo.Event;
import vo.Exhibition;
import vo.Performance;


public class EventView extends JPanel{
	ButtonGroup bg; // 라디오버튼 묶는 버튼그룹
	JRadioButton rbExi, rbPer; // 전시, 공연 라디오버튼 
	//텍스트필드 옆 라벨들 
	JLabel laExiDir, laPerActor,laPerDir, laPerStart, laPerEnd, laLocation,
			laEvtNo, laEvtTitle, laEvtPeriod, laEvtRating, laEvtPrice, laEvtDetail, labt;
	//이벤트정보 입력 텍스트필드
	JTextField tfExiDir, tfPerActor, tfPerDir, tfEvtNo, tfEvtTitle, tfEvtPrice;
	//설명 텍스트area
	JTextArea taEvtDetail;
	//행사조회, 입력 수정, 삭제 메소드 
	JButton bSelectEvt, bInsertEvt, bModifyEvt, bDeleteEvt;
	//홈버튼, 초기화버튼 
	JButton bHome, bClear;
	
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
	
	// 월 선택시 일 콤보박스 생성위해 각 월별 마지막날 배열  
	int[] lastDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	// 이벤트모델 객체
	EventModel model;
	// 아트센터 객체
	ArtCenter ac;
	// 폰트객체들 
	Font bfont, titlefont, lafont;
	
	
	public EventView(ArtCenter ac) {
		this.ac = ac;
		setList();
		addLayout();
		initStyle();
		connectDB();
		eventProc();
	}
	
	// 행사번호 비활성화, date초기값설정 
	private void initStyle() {
		Calendar c = Calendar.getInstance();
		tfEvtNo.setEditable(false);
		cbStartDateY.setSelectedItem(c.get(Calendar.YEAR));// date 초기값 설정
		cbStartDateM.setSelectedItem(c.get(Calendar.MONTH)+1);// date 초기값 설정
		cbStartDateD.setSelectedItem(c.get(Calendar.DATE));// date 초기값 설정
		cbEndDateY.setSelectedItem(c.get(Calendar.YEAR));// date 초기값 설정
		cbEndDateM.setSelectedItem(c.get(Calendar.MONTH)+1);// date 초기값 설정
		cbEndDateD.setSelectedItem(c.get(Calendar.DATE));// date 초기값 설정
		
	}
	
	// 시간, 년, 월 콤보박스 리스트 생성
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
	// 시작일 콤보박스 리스트 생성
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
	// 종료일 콤보박스 리스트 생성
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
	//레이아아웃 붙이기 
	void addLayout() {

		bfont = new Font("포천 오성과 한음 Regular", Font.PLAIN, 20);
		titlefont = new Font("210 타임라인 R", Font.BOLD, 30);
		lafont = new Font("포천 오성과 한음 Regular", Font.PLAIN, 15);
		
		rbExi = new JRadioButton("전시");
		rbExi.setFont(lafont);
		rbPer = new JRadioButton("공연");
		rbPer.setFont(lafont);
		bg = new ButtonGroup();
		bg.add(rbExi);
		bg.add(rbPer);
		
		laExiDir = new JLabel("작가", JLabel.CENTER);
		laExiDir.setFont(lafont);
		laLocation = new JLabel("장소", JLabel.CENTER);
		laLocation.setFont(lafont);
		laPerActor = new JLabel("출연진", JLabel.CENTER);
		laPerActor.setFont(lafont);
		laPerDir = new JLabel("감독", JLabel.CENTER);
		laPerDir.setFont(lafont);
		laPerStart = new JLabel("시작시간", JLabel.CENTER);
		laPerStart.setFont(lafont);
		laPerEnd = new JLabel("종료시간", JLabel.CENTER);
		laPerEnd.setFont(lafont);
		laEvtNo = new JLabel("행사코드", JLabel.CENTER);
		laEvtNo.setFont(lafont);
		laEvtTitle = new JLabel("제목", JLabel.CENTER);
		laEvtTitle.setFont(lafont);
		laEvtPeriod = new JLabel("기간", JLabel.CENTER);
		laEvtPeriod.setFont(lafont);
		laEvtRating = new JLabel("등급", JLabel.CENTER);
		laEvtRating.setFont(lafont);
		laEvtPrice = new JLabel("가격", JLabel.CENTER);
		laEvtPrice.setFont(lafont);
		labt = new JLabel("~", JLabel.CENTER);
		labt.setFont(lafont);
		
		
		
		tfExiDir = new JTextField();
		tfPerActor = new JTextField();
		tfPerDir = new JTextField();
		tfEvtNo = new JTextField();
		tfEvtTitle = new JTextField();
		tfEvtPrice = new JTextField();
		taEvtDetail = new JTextArea(20,70);
		TitledBorder tb = new TitledBorder("설명");
		tb.setTitleFont(lafont);
		taEvtDetail.setBorder(tb);
		
		
		bSelectEvt = new JButton("조회");
		bSelectEvt.setFont(bfont);
		bInsertEvt = new JButton("입력");
		bInsertEvt.setFont(bfont);
		bModifyEvt = new JButton("수정");
		bModifyEvt.setFont(bfont);
		bDeleteEvt = new JButton("삭제");
		bDeleteEvt.setFont(bfont);
		bHome = new JButton("Home");
		bHome.setFont(lafont);
		bClear = new JButton("초기화");
		bClear.setFont(lafont);
		
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
		pStartDate.add(cbStartDateM);
		pStartDate.add(cbStartDateD);
		
		//종료기간패널
		pEndDate.setLayout(new FlowLayout());
		pEndDate.add(cbEndDateY);
		pEndDate.add(cbEndDateM);
		pEndDate.add(cbEndDateD);
		
		// title 패널
		JPanel ptitle = new JPanel();
		ptitle.setLayout(new BorderLayout());
		ptitle.setBorder(BorderFactory.createEmptyBorder(10,10,20,20));
		JPanel ptitle_b = new JPanel();
		ptitle_b.setLayout(new FlowLayout());
		ptitle_b.add(bClear);
		ptitle_b.add(bHome);
		ptitle.add(ptitle_b, "East");
		
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
		TitledBorder tb1 = new TitledBorder("행사관리");
		tb1.setTitleFont(titlefont);
		setBorder(tb1);
		add("North", ptitle);
		add("Center", ptf);
		add("South", pb);
	}
	// 데이터베이스 연결 
	void connectDB() {
		try {
			model = new EventModel();
			System.out.println("행사관리 DB 연결 성공");
		} catch (Exception e) {
			System.out.println("행사관리 DB 연결 ");
			e.printStackTrace();
		}
	}
	
	//이벤트 등록
	void eventProc() {
		ButtonEventHandler btnHandler = new ButtonEventHandler();
		
		bSelectEvt.addActionListener(btnHandler);
		bInsertEvt.addActionListener(btnHandler);
		bModifyEvt.addActionListener(btnHandler);
		bDeleteEvt.addActionListener(btnHandler);
		bHome.addActionListener(btnHandler);
		bClear.addActionListener(btnHandler);
		rbExi.addActionListener(btnHandler);
		rbPer.addActionListener(btnHandler);
		cbStartDateY.addActionListener(btnHandler);
		cbStartDateM.addActionListener(btnHandler);
		cbStartDateD.addActionListener(btnHandler);
		cbEndDateY.addActionListener(btnHandler);
		cbEndDateM.addActionListener(btnHandler);
		cbEndDateD.addActionListener(btnHandler);
		cbEndTimeH.addActionListener(btnHandler);
		cbEndTimeM.addActionListener(btnHandler);
		
	}

	//버튼 이벤트 핸들러 클래스
		class ButtonEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				
				//조회
				if(o==bSelectEvt){
					if(rbExi.isSelected()){ 
						selectExi();
						}else if(rbPer.isSelected()){
							selectPer();
						}else if((!rbExi.isSelected()) && (!rbPer.isSelected())){
							JOptionPane.showMessageDialog(null, "전시 또는 공연을 선택해주세요");
						}
				} 
				//입력
				else if (o==bInsertEvt){
					//전시선택시
					if(rbExi.isSelected()){ 
					  if(compareDate()==-1){ return; } 
					  else if(prohibitExi()==-1){ return; }
					  else if(insertExi()==-1){return;}
					//공연선택시
					} else if(rbPer.isSelected()){
						if(compareTime()==-1){ return; }
						else if(prohibitPer()==-1){ return; }
						else if(insertPer()==-1){return;}
					} else if((!rbExi.isSelected()) && (!rbPer.isSelected())){
						JOptionPane.showMessageDialog(null, "전시 또는 공연을 선택해주세요");
					}
				} 
				//수정
			else if (o == bModifyEvt) {
				if (rbExi.isSelected()) {
					if(compareDate()==-1){ return; } 
					else if(prohibitExi()==-1){ return; }
//					else if(modifyExi()==-1){return;}
				} else if (rbPer.isSelected()) {
					if(compareTime()==-1){ return; }
					else if(prohibitPer()==-1){ return; }
//					else if(modifyPer()==-1){return;}
				} else if ((!rbExi.isSelected()) && (!rbPer.isSelected())) {
					JOptionPane.showMessageDialog(null, "전시 또는 공연을 선택해주세요");
				}
			}
				//삭제
				else if (o==bDeleteEvt){
					if(rbExi.isSelected()){ 
						deleteExi();
					}else if(rbPer.isSelected()){
							deletePer();
						}else if((!rbExi.isSelected()) && (!rbPer.isSelected())){
							JOptionPane.showMessageDialog(null, "전시 또는 공연을 선택해주세요");
						}
				} 
				// 홈버튼 
				else if (o==bHome){
					ac.movecard("main");
					clear();
				}
				// 초기화버튼 
				else if(o==bClear){
					clear();
				} 
				// 전시라디오버튼 선택시 화면셋팅
				else if (o==rbExi){
					choiceExi();
				}
				// 공연라디오버튼 선택시 화면셋팅
				else if (o==rbPer){
					choicePer();
				} 
				// 시작년도, 시작월 선택하면 시작일 셋팅됨 
				else if (o==cbStartDateY||o==cbStartDateM){
					setDayStart();
				} 
				// 종료년도, 종료월 선택하면 종료일 셋팅됨 
				else if (o==cbEndDateY||o==cbEndDateM){
					setDayEnd();
				} 
			
			}

			

			
		}

	
// 버튼 메소드들
		
	//전시조회 -- 완성!!!>_<
	void selectExi() {
		String title = tfEvtTitle.getText();
		Exhibition vo ;
		ArrayList list = new ArrayList<>();
		try {
			list = model.selectExi(title);
			vo = (Exhibition) list.get(0);
			tfEvtNo.setText(String.valueOf(vo.getEvtNo()));
			tfEvtTitle.setText(vo.getEvtTitle());
			tfEvtPrice.setText(String.valueOf(vo.getEvtPrice()));
			tfExiDir.setText(vo.getExiDir());
			taEvtDetail.setText(vo.getEvtDetail() );
			cbRating.setSelectedItem(vo.getEvtRating());
			cbLocation.setSelectedItem(list.get(1));
			
			// 년, 월, 일 셋팅
			cbStartDateY.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(0, 4))); 
			cbStartDateM.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(5,7)));
			cbStartDateD.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(8,10)));
			cbEndDateY.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(0, 4))); 
			cbEndDateM.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(5,7)));
			cbEndDateD.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(8,10)));
		
			JOptionPane.showMessageDialog(null, "전시조회완료");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "전시조회실패");			
			e.printStackTrace();
		}
	}
	
	//공연조회 -- 완성!!!>_<
	void selectPer(){
		String title = tfEvtTitle.getText();
		Performance vo;
		ArrayList list = new ArrayList<>();
		
		try {
			list = model.selectPer(title);
			vo = (Performance) list.get(0);
			tfEvtNo.setText(String.valueOf(vo.getEvtNo()));
			tfEvtTitle.setText(vo.getEvtTitle());
			tfEvtPrice.setText(String.valueOf(vo.getEvtPrice()));
			tfPerActor.setText(vo.getPerActor());
			tfPerDir.setText(vo.getPerDir());
			taEvtDetail.setText(vo.getEvtDetail());
			cbRating.setSelectedItem(vo.getEvtRating());
			cbLocation.setSelectedItem(list.get(1));
			
// 년, 월, 일 셋팅
			cbStartDateY.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(0, 4))); 
			cbStartDateM.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(5,7)));
			cbStartDateD.setSelectedItem(Integer.parseInt(vo.getEvtStart().substring(8,10)));
//			cbEndDateY.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(0, 4))); 
//			cbEndDateM.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(5,7)));
//			cbEndDateD.setSelectedItem(Integer.parseInt(vo.getEvtEnd().substring(8,10)));
			
// 시간셋팅
			// 시작시간
			String startHour = ((String) list.get(2)).substring(0, 2);
			String startMin = ((String) list.get(2)).substring(2);
			if(startHour.charAt(0)=='0'){
				startHour = ((String) list.get(2)).substring(1, 2);
			} else if (startMin.charAt(0)=='0'){
				startHour = ((String) list.get(2)).substring(3);
			}
			
			cbStartTimeH.setSelectedItem(startHour);
			cbStartTimeM.setSelectedItem(startMin);
			
			//종료시간
			String endHour = ((String) list.get(3)).substring(0, 2);
			String endMin = ((String) list.get(3)).substring(2);
			if(endHour.charAt(0)=='0'){
				endHour = ((String) list.get(3)).substring(1, 2);
			} else if (endMin.charAt(0)=='0'){
				endHour = ((String) list.get(3)).substring(3);
			}
			
			cbEndTimeH.setSelectedItem(endHour);
			cbEndTimeM.setSelectedItem(endMin);
			
			
			JOptionPane.showMessageDialog(null, "공연조회완료");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "공연조회실패" );
			e.printStackTrace();
		}
	}
	
	//전시입력 -- 완성!!!>_<
	int insertExi() {
		
		Exhibition vo = new Exhibition();
		String startDate=(cbStartDateY.getSelectedItem())+" "+
						 (cbStartDateM.getSelectedItem())+" "+
						 (cbStartDateD.getSelectedItem());
		String endDate=(cbEndDateY.getSelectedItem())+" "+
				 	   (cbEndDateM.getSelectedItem())+" "+
				 	   (cbEndDateD.getSelectedItem());
		
		String loc = (String) cbLocation.getSelectedItem();
		vo.setExiDir(tfExiDir.getText());
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		// 전시가격입력 -- null이거나 숫자만 있지 않으면 오류
		if (tfEvtPrice.getText().equals("") || !(Pattern.matches("^[0-9]*$", tfEvtPrice.getText()))) {
			JOptionPane.showMessageDialog(null, "전시가격 잘못입력");
			return -1;
		}
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		
		vo.setEvtStart(startDate);
		vo.setEvtEnd(endDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.insertExi(vo, loc);
			JOptionPane.showMessageDialog(null, "전시입력완료");
			clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "전시입력실패:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}
	
	//공연입력
	int insertPer(){
		Performance vo = new Performance();
		String startTime = (cbStartTimeH.getSelectedItem() +" "+ cbStartTimeM.getSelectedItem());
		String endTime = (cbEndTimeH.getSelectedItem() +" "+ cbEndTimeM.getSelectedItem());
		String startDate=(cbStartDateY.getSelectedItem())+" "+
				 		 (cbStartDateM.getSelectedItem())+" "+
				 		 (cbStartDateD.getSelectedItem());
		
//		String endDate=(cbEndDateY.getSelectedItem())+" "+
//		 	   		   (cbEndDateM.getSelectedItem())+" "+
//		 	   		   (cbEndDateD.getSelectedItem());
	

		String loc = (String) cbLocation.getSelectedItem();
		vo.setPerActor(tfPerActor.getText());
		vo.setPerDir(tfPerDir.getText());
		vo.setPerStart(startTime);
		vo.setPerEnd(endTime);
		
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		// 공연가격입력 -- null이거나 숫자만 있지 않으면 오류
		if (tfEvtPrice.getText().equals("") || !(Pattern.matches("^[0-9]*$", tfEvtPrice.getText()))) {
			JOptionPane.showMessageDialog(null, "전시가격 잘못입력");
			return -1;
		}
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(startDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.insertPer(vo, loc);
			JOptionPane.showMessageDialog(null, "공연입력완료");
			clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "공연입력실패:" + e.getMessage());
			return -1;
		}
		
		return 0;
	}
	//전시수정 
	int modifyExi() {
		
		Exhibition vo = new Exhibition();
		
		String startDate = (cbStartDateY.getSelectedItem()) + " " +
						   (cbStartDateM.getSelectedItem()) + " " + 
						   (cbStartDateD.getSelectedItem());
		String endDate = (cbEndDateY.getSelectedItem()) + " " + 
						 (cbEndDateM.getSelectedItem()) + " " + 
						 (cbEndDateD.getSelectedItem());
		
		String loc = (String) cbLocation.getSelectedItem();
		vo.setEvtNo(Integer.parseInt(tfEvtNo.getText()));
		vo.setExiDir(tfExiDir.getText());
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		// 전시가격입력 -- null이거나 숫자만 있지 않으면 오류
		if (tfEvtPrice.getText().equals("") || !(Pattern.matches("^[0-9]*$", tfEvtPrice.getText()))) {
			JOptionPane.showMessageDialog(null, "전시가격 잘못입력");
			return -1;
		}
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(endDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.modifyExi(vo, loc);
			JOptionPane.showMessageDialog(null, "전시수정완료");
			clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "전시수정실패: "+e.getMessage());
			return -1;
		}
		
		return 0;
	}
	
	//공연수정
	int modifyPer(){
		Performance vo = new Performance();
		String startTime = (cbStartTimeH.getSelectedItem() +" "+ cbStartTimeM.getSelectedItem());
		String endTime = (cbEndTimeH.getSelectedItem() +" "+ cbEndTimeM.getSelectedItem());
		String startDate=(cbStartDateY.getSelectedItem())+" "+
				 		 (cbStartDateM.getSelectedItem())+" "+
				 		 (cbStartDateD.getSelectedItem());
		
//		String endDate=(cbEndDateY.getSelectedItem())+" "+
//		 	   		   (cbEndDateM.getSelectedItem())+" "+
//		 	   		   (cbEndDateD.getSelectedItem());
		

		String loc = (String) cbLocation.getSelectedItem();
		vo.setPerActor(tfPerActor.getText());
		vo.setPerDir(tfPerDir.getText());
		vo.setPerStart(startTime);
		vo.setPerEnd(endTime);
		vo.setEvtNo(Integer.parseInt(tfEvtNo.getText()));
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		// 전시가격입력 -- null이거나 숫자만 있지 않으면 오류
		if (tfEvtPrice.getText().equals("") || !(Pattern.matches("^[0-9]*$", tfEvtPrice.getText()))) {
			JOptionPane.showMessageDialog(null, "전시가격 잘못입력");
			return -1;
		}
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(startDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.modifyPer(vo, loc);
			JOptionPane.showMessageDialog(null, "공연수정완료");
			clear();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "공연수정실패:"+e.getMessage());
			return -1;
		}
		return 0;
	}
	//전시삭제 -- 완성!!!>_<
	void deleteExi() {
		Exhibition vo = new Exhibition();

		String startDate = (cbStartDateY.getSelectedItem()) + " " +
						   (cbStartDateM.getSelectedItem()) + " " + 
						   (cbStartDateD.getSelectedItem());
//		String endDate = (cbEndDateY.getSelectedItem()) + " " + 
//						 (cbEndDateM.getSelectedItem()) + " " + 
//						 (cbEndDateD.getSelectedItem());
		vo.setEvtNo(Integer.parseInt(tfEvtNo.getText()));
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtTitle(tfEvtTitle.getText());
		vo.setEvtRating((String) cbRating.getSelectedItem());
		vo.setEvtPrice(Integer.parseInt(tfEvtPrice.getText()));
		vo.setEvtStart(startDate);
		vo.setEvtEnd(startDate);
		vo.setEvtDetail(taEvtDetail.getText());
		
		try {
			model.deleteExi(vo);
			JOptionPane.showMessageDialog(null, "전시삭제완료");
			clear();
			
		} catch (Exception e) {
			System.out.println("전시삭제실패");
			e.printStackTrace();
		}
	}
	//공연삭제 -- 완성!!!>_<
	void deletePer(){
		Performance vo = new Performance();
		
		String startTime = (cbStartTimeH.getSelectedItem() +" "+ cbStartTimeM.getSelectedItem());
		String endTime = (cbEndTimeH.getSelectedItem() +" "+ cbEndTimeM.getSelectedItem());
		String startDate=(cbStartDateY.getSelectedItem())+" "+
				 		 (cbStartDateM.getSelectedItem())+" "+
				 		 (cbStartDateD.getSelectedItem());
		
		String endDate=(cbEndDateY.getSelectedItem())+" "+
		 	   		   (cbEndDateM.getSelectedItem())+" "+
		 	   		   (cbEndDateD.getSelectedItem());
		
		vo.setEvtNo(Integer.parseInt(tfEvtNo.getText()));
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
			model.deletePer(vo);
			JOptionPane.showMessageDialog(null, "공연삭제완료");
			clear();
		} catch (Exception e) {
			System.out.println("공연삭제실패");
			e.printStackTrace();
		}
		
		
	}

//초기화
	void clear() {
		rbExi.setSelected(false);
		rbPer.setSelected(false);
		
		tfExiDir.setText(null);
		if(cbLocation.getItemCount() > 0)
			cbLocation.setSelectedIndex(0);
		tfPerActor.setText(null);
		tfPerDir.setText(null);
		
		cbStartTimeH.setSelectedIndex(0);
		cbStartTimeM.setSelectedIndex(0);
		cbEndTimeH.setSelectedIndex(0);
		cbEndTimeM.setSelectedIndex(0);
		
		tfEvtNo.setText(null);
		tfEvtTitle.setText(null);
		cbRating.setSelectedIndex(0);
		tfEvtPrice.setText(null);
		cbStartDateY.setSelectedIndex(0);
		cbStartDateM.setSelectedIndex(0);
		cbStartDateD.setSelectedIndex(0);
		cbEndDateY.setSelectedIndex(0);
		cbEndDateM.setSelectedIndex(0);
		cbEndDateD.setSelectedIndex(0);
		taEvtDetail.setText(null);
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
		cbEndDateD.setEnabled(true);
		cbEndDateM.setEnabled(true);
		cbEndDateY.setEnabled(true);
		
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
		cbEndDateD.setEnabled(false);
		cbEndDateM.setEnabled(false);
		cbEndDateY.setEnabled(false);
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
	ArrayList setLocation(int flag) {
		ArrayList list = new ArrayList<>();
		try {
			list = model.setLocation(flag);
		} catch (Exception e) {
			System.out.println("장소명 조회 실패");
			e.printStackTrace();
		}

		return list;
	}
	
	
	
// 날짜 시작-종료 비교 메소드
	private int compareDate() {
	
		String format1 = "%s%s%s";
		String format2 = "%s%s%s";
		
		//시작일
		if (cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s0%s0%s";
		}else if(cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() >= 9 ){
			format1="%s0%s%s";
		}else if(cbStartDateM.getSelectedIndex() >= 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s%s0%s";
		}
		
		//종료일
		if (cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() < 9){
			format2="%s0%s0%s";
		}else if(cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() >= 9){
			format2="%s0%s%s";
		}else if(cbEndDateM.getSelectedIndex() >= 9 && cbEndDateD.getSelectedIndex() < 9 ){
			format2="%s%s0%s";
		}
		
		String startYear = String.format(format1, cbStartDateY.getSelectedItem(), 
				cbStartDateM.getSelectedItem(), cbStartDateD.getSelectedItem());
		
		String endYear = String.format(format2, cbEndDateY.getSelectedItem(), 
				cbEndDateM.getSelectedItem(), cbEndDateD.getSelectedItem());
		
		
		if ((Integer.parseInt(endYear))-(Integer.parseInt(startYear)) < 0) {
			JOptionPane.showMessageDialog(null, "날짜잘못입력");
			return -1;
		}
		return 0;
	}

// 시간 시작-종료 비교 메소드
	private int compareTime() {
		String format1="%s%s";
		String format2="%s%s";

		// 시작시간설정
		if (cbStartTimeH.getSelectedIndex() < 9 && cbStartTimeM.getSelectedIndex() < 9) {//시,분이 10미만
			format1 = "0%s0%s";
		} else if (cbStartTimeH.getSelectedIndex() < 9 && cbStartTimeM.getSelectedIndex() >= 9) {//시만 10미만
			format1 = "0%s%s";
		} else if (cbStartTimeH.getSelectedIndex() >= 9 && cbStartTimeM.getSelectedIndex() < 9) {//분만 10미만
			format1 = "%s0%s";
		}
		// 종료시간설정
		if (cbEndTimeH.getSelectedIndex() < 9 && cbEndTimeM.getSelectedIndex() < 9) {
			format2 = "0%s0%s";
		} else if (cbEndTimeH.getSelectedIndex() < 9 && cbEndTimeM.getSelectedIndex() >= 9) {
			format2 = "0%s%s";
		} else if (cbEndTimeH.getSelectedIndex() >= 9 && cbEndTimeM.getSelectedIndex() < 9) {
			format2 = "%s0%s";
		}
		
		String startTime = String.format(format1, cbStartTimeH.getSelectedItem(), cbStartTimeM.getSelectedItem());
		String endTime = String.format(format2, cbEndTimeH.getSelectedItem(), cbEndTimeM.getSelectedItem());
		
		if ((Integer.parseInt(endTime))-(Integer.parseInt(startTime)) < 0) {
			JOptionPane.showMessageDialog(null, "시간잘못입력");
			return -1;
		} 

		return 0;

	}
	
	
// 전시 중복입력 금지 메소드
	private int prohibitExi(){
		
		String loc = (String) cbLocation.getSelectedItem();
		int evtNo = 0;
		if(tfEvtNo.equals("")){
			evtNo = Integer.parseInt(tfEvtNo.getText());
		}		
		ArrayList list1 = new ArrayList<>();
		ArrayList list2 = new ArrayList<>();
		
		// 기존 입력된 데이터에서 시작일 얻어오기 
		try {
			list1 = model.searchStartDate(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 시작일 얻어오기 실패");
			e.printStackTrace();
		}
		// 기존 입력된 데이터에서 종료일 얻어오기 
		try {
			list2 = model.searchEndDate(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 종료일 얻어오기 실패");
			e.printStackTrace();
		}
		
		String format1 = "%s%s%s";
		String format2 = "%s%s%s";
		
		// 지금 입력한 데이터에서 시작일 얻어오기 
		if (cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s0%s0%s";
		}else if(cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() >= 9 ){
			format1="%s0%s%s";
		}else if(cbStartDateM.getSelectedIndex() >= 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s%s0%s";
		}
		
		// 지금 입력한 데이터에서 종료일 얻어오기
		if (cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() < 9){
			format2="%s0%s0%s";
		}else if(cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() >= 9){
			format2="%s0%s%s";
		}else if(cbEndDateM.getSelectedIndex() >= 9 && cbEndDateD.getSelectedIndex() < 9 ){
			format2="%s%s0%s";
		}
		
		String startYear = String.format(format1, cbStartDateY.getSelectedItem(), 
				cbStartDateM.getSelectedItem(), cbStartDateD.getSelectedItem());
		
		String endYear = String.format(format2, cbEndDateY.getSelectedItem(), 
				cbEndDateM.getSelectedItem(), cbEndDateD.getSelectedItem());
		
		int sy = Integer.parseInt(startYear);
		int ey = Integer.parseInt(endYear);
		
		
		// 중복제거 
	/*	for (int i = 0; i < list1.size(); i++) {
				int s = Integer.parseInt((String) list1.get(i)); //시작일
			for (int j = 0; j < list2.size(); j++) {
				int e = Integer.parseInt((String) list2.get(j)); //종료일
				
				if( (sy> s && sy < e) || (ey> s && ey < e) || (sy<s && ey >e)){
					JOptionPane.showMessageDialog(null, "기간중복");
					return -1;
				}
			}
		}*/
		
		for (int i = 0; i < list1.size(); i++) {
			int s = Integer.parseInt((String) list1.get(i)); //시작일
			int e = Integer.parseInt((String) list2.get(i)); //종료일
			
			if( (sy>= s && sy <= e) || (ey>= s && ey <= e) || (sy<=s && ey >=e)){
				JOptionPane.showMessageDialog(null, "기간중복");
				return -1;
			}
		
	}
		
		
		
		
		return 0;
		
	}
	
	
// 공연 중복입력 금지 메소드
	private int prohibitPer(){
		
		String loc = (String) cbLocation.getSelectedItem();
		int evtNo = 0;
		if(tfEvtNo.equals("")){
			evtNo = Integer.parseInt(tfEvtNo.getText());
		}
				
		ArrayList list1 = new ArrayList<>();
		ArrayList list2 = new ArrayList<>();
		ArrayList list3 = new ArrayList<>();
		ArrayList list4 = new ArrayList<>();
		
// 기간 얻어오기		
		// 기존 입력된 데이터에서 시작일 얻어오기 
		try {
			list1 = model.searchStartDate(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 시작일 얻어오기 실패");
			e.printStackTrace();
		}
		// 기존 입력된 데이터에서 종료일 얻어오기 
		/*
		try {
			list2 = model.searchEndDate(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 종료일 얻어오기 실패");
			e.printStackTrace();
		}
		*/
		
		String format1 = "%s%s%s";
		String format2 = "%s%s%s";
		
		// 지금 입력한 데이터에서 시작일 얻어오기 
		if (cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s0%s0%s";
		}else if(cbStartDateM.getSelectedIndex() < 9 && cbStartDateD.getSelectedIndex() >= 9 ){
			format1="%s0%s%s";
		}else if(cbStartDateM.getSelectedIndex() >= 9 && cbStartDateD.getSelectedIndex() < 9){
			format1="%s%s0%s";
		}
		
		// 지금 입력한 데이터에서 종료일 얻어오기
		/*
		if (cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() < 9){
			format2="%s0%s0%s";
		}else if(cbEndDateM.getSelectedIndex() < 9 && cbEndDateD.getSelectedIndex() >= 9){
			format2="%s0%s%s";
		}else if(cbEndDateM.getSelectedIndex() >= 9 && cbEndDateD.getSelectedIndex() < 9 ){
			format2="%s%s0%s";
		}
		*/
		
		String startYear = String.format(format1, cbStartDateY.getSelectedItem(), 
				cbStartDateM.getSelectedItem(), cbStartDateD.getSelectedItem());
		
//		String endYear = String.format(format2, cbEndDateY.getSelectedItem(), 
//				cbEndDateM.getSelectedItem(), cbEndDateD.getSelectedItem());
		
		int sy = Integer.parseInt(startYear);
//		int ey = Integer.parseInt(endYear);
		
		
		
// 시간 얻어오기
		
		// 입력된 장소와 같은 장소의 시간을 불러옴
		
		try {
			list3 = model.searchStartTime(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 시작시간 얻어오기 실패");
			e.printStackTrace();
		}

		try {
			list4 = model.searchEndTime(loc, evtNo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "기존 입력된 데이터에서 종료시간 얻어오기 실패");
			e.printStackTrace();
		}
		
		String format3="%s%s";
		String format4="%s%s";

		// 시작시간설정
		if (cbStartTimeH.getSelectedIndex() < 9 && cbStartTimeM.getSelectedIndex() < 9) {
			format3 = "0%s0%s";
		} else if (cbStartTimeH.getSelectedIndex() < 9 && cbStartTimeM.getSelectedIndex() >= 9) {
			format3 = "0%s%s";
		} else if (cbStartTimeH.getSelectedIndex() >= 9 && cbStartTimeM.getSelectedIndex() < 9) {
			format3 = "%s0%s";
		}
		// 종료시간설정
		if (cbEndTimeH.getSelectedIndex() < 9 && cbEndTimeM.getSelectedIndex() < 9) {
			format4 = "0%s0%s";
		} else if (cbEndTimeH.getSelectedIndex() < 9 && cbEndTimeM.getSelectedIndex() >= 9) {
			format4 = "0%s%s";
		} else if (cbEndTimeH.getSelectedIndex() >= 9 && cbEndTimeM.getSelectedIndex() < 9) {
			format4 = "%s0%s";
		}
		
		String startTime = String.format(format3, cbStartTimeH.getSelectedItem(), cbStartTimeM.getSelectedItem());
		String endTime = String.format(format4, cbEndTimeH.getSelectedItem(), cbEndTimeM.getSelectedItem());
		
		int st = Integer.parseInt(startTime);
		int et = Integer.parseInt(endTime);

		
// 중복제거 
		
		for (int i = 0; i < list1.size(); i++) {
			int s = Integer.parseInt((String) list1.get(i)); // 기존데이터의 시작일
//			int e = Integer.parseInt((String) list2.get(i)); // 기존데이터의 종료일
			int s1 = Integer.parseInt((String) list3.get(i)); // 기존데이터의 시작시간
			int e1 = Integer.parseInt((String) list4.get(i)); // 기존데이터의 종료시간

			if (/*(sy >= s && sy <= e) || (ey >= s && ey <= e) || (sy <= s && ey >= e)*/
					sy==s ) { // 기간중복
				// JOptionPane.showMessageDialog(null, "기간중복, 아직 시간중복아님");

				if ((st >= s1 && st <= e1) || (et >= s1 && et <= e1) || (st <= s1 && et >= e1)) {// 시간중복
					JOptionPane.showMessageDialog(null, "시간중복");
					return -1;
				}
			}
		}

		
		return 0;
	}
	
	
	
}
