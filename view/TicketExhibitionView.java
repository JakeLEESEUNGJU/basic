package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.ArtCenter;
import model.TicketModel;
import vo.Event;

public class TicketExhibitionView extends JPanel implements ActionListener {
	private Font font1 = new Font("Serif", Font.BOLD, 20);
	private Font font2 = new Font("Serif", Font.BOLD, 30);
	JButton bDateOk, bGoNext, bHome;
	ButtonGroup bg = new ButtonGroup();
	TitledBorder taboTitle, taboSelDate, taboSelEvt, taboInfoList;
	JPanel center_center_one_center,center_north;
	String[] jTableTitle = { "성인", "어린이", "우대(노인,장애인", "총계" };

	// 인원 및 가격 라벨
	JLabel laKind, laPep, laCash, laAdult, laChild, laAdv, laTotal, laToPep, laToCash, laToAduC, laToChC, laToAdvC;
	// 구성원 텍스트필드
	JTextField tfAdult, tfChild, tfAdv;
	
	//////
	JComboBox<Integer> cbY, cbM, cbD; // 연월일 체크
	String[] strY = new String[11]; // year의 갯수만큼 콤보박스에 넣기 위해 만든 배열이지만 본인은 사용 안함
	String[] strM = new String[12];// Month 의 값을 저장해서 콤보 박스에 넣기 위한 배열
	int[] lastDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 각
//	JComboBox<Integer> cbPeople;// 어린이 성인 우대 콤보박스 인원 체크
	JRadioButton rbPerf, rbExhibi;// 공연 및 전시 라디오 버튼
	JTable tbExhiList,tbPerfList;
	// , tbReciept
	ExhibListTableModel exhiTbModel;
	PerfListTableModel perfTbModel;
	
	// TopriTableModel topTbModel;
	TicketModel model;
	ArtCenter ac;
	ArrayList<String> table;

	public TicketExhibitionView(ArtCenter ac) {
		EtchedBorder eborder = new EtchedBorder();
		laKind = new JLabel("구분", JLabel.CENTER);
		laKind.setBorder(eborder);
		laPep = new JLabel("인원", JLabel.CENTER);
		laPep.setBorder(eborder);
		laCash = new JLabel("가격", JLabel.CENTER);
		laCash.setBorder(eborder);
		laAdult = new JLabel("성인", JLabel.CENTER);
		laAdult.setBorder(eborder);
		laChild = new JLabel("어린이", JLabel.CENTER);
		laChild.setBorder(eborder);
		laAdv = new JLabel("우대(노인,장애인 등)", JLabel.CENTER);
		laAdv.setBorder(eborder);
		laTotal = new JLabel("총계", JLabel.CENTER);
		laTotal.setBorder(eborder);
		laToPep = new JLabel("0", JLabel.CENTER);
		laToPep.setBorder(eborder);
		laToCash = new JLabel("0", JLabel.CENTER);
		laToCash.setBorder(eborder);
		laToAduC = new JLabel("0", JLabel.CENTER);
		laToAduC.setBorder(eborder);
		laToChC = new JLabel("0", JLabel.CENTER);
		laToChC.setBorder(eborder);
		laToAdvC = new JLabel("0", JLabel.CENTER);
		laToAdvC.setBorder(eborder);
		laKind.setBackground(Color.gray);
		laKind.setOpaque(true);
		laPep.setBackground(Color.gray);
		laPep.setOpaque(true);
		laCash.setBackground(Color.gray);
		laCash.setOpaque(true);
		tfAdult = new JTextField("0");
		tfChild = new JTextField("0");
		tfAdv = new JTextField("0");

		this.ac = ac;
		Calendar c = Calendar.getInstance();
		// int date = c.get(Calendar.DATE);
		// int year = c.get(Calendar.YEAR);
		// int month = c.get(Calendar.MONTH);
		cbY = new JComboBox<Integer>();
		cbM = new JComboBox<Integer>();
		cbD = new JComboBox<Integer>();
		for (int i = c.get(Calendar.YEAR) + 5; i > 1800; i--) {// 올해 +5 부터
																// 1800년도 까지
																// 콤보박스에 입력하기 위한
																// for 문
			cbY.addItem(i);
		}
		for (int i = 0; i <= (Calendar.DECEMBER); i++) { // DECEMBER 를 사용한 이유는 저
															// 값이 12니깐?
			cbM.addItem(i + 1);
		}
		cbY.setSelectedItem(c.get(Calendar.YEAR)); // year 초기값
		cbM.setSelectedItem(c.get(Calendar.MONTH) + 1);// month 초기값
		cbD.setSelectedItem(c.get(Calendar.DATE));// date 초기값 설정

		setDay(); // 선택 되어있는 월의 일 설정 메소드

		addLayout();
		connectDB();
		eventProc();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object evt = e.getSource();
		if (evt == bHome) {
			ac.movecard("main");
		} else if (evt == bGoNext) {
			
			ac.movecard("");
		} else if (evt == tfAdult) {
			settotal();
		} else if (evt == bDateOk) {
			serchByDate();
		} else if (evt == tfAdv) {
			settotal();
		} else if (evt == tfChild) {
			settotal();
		} else if (evt == rbExhibi){
			showFrame();
		} else if (evt == rbPerf){
			showFrame();
		} else if(evt == cbY||evt == cbM){
			setDay(); //setDat 메소드 출력
		} 

	}

	private void showFrame() {
		if(rbExhibi.isSelected()){
			bGoNext.setText("결제");
			taboTitle.setTitle("판매관리-전시");
			center_north.setBorder(new TitledBorder(taboTitle));
			taboSelEvt.setTitle("전시 선택");
			center_center_one_center.removeAll();
			center_center_one_center.setBorder(new TitledBorder(taboSelEvt));
			center_center_one_center.add(new JScrollPane(tbExhiList), BorderLayout.CENTER);
		}else if(rbPerf.isSelected()){
			bGoNext.setText("좌석선택");
			taboTitle.setTitle("판매관리-공연");
			center_north.setBorder(new TitledBorder(taboTitle));
			taboSelEvt.setTitle("공연선택");
			center_center_one_center.removeAll();
			center_center_one_center.setBorder(new TitledBorder(taboSelEvt));
			center_center_one_center.add(new JScrollPane(tbPerfList), BorderLayout.CENTER);
		}
		
	}

	private void serchByDate() {
		Event ev= new Event();
		if(rbExhibi.isSelected()){
			
			
		}else if(rbPerf.isSelected()){
			
		}
		
	}

	private void settotal() {
		int totalAdu=0;
		int totalAdv=0;
		int totalChild=0;
		int row = tbExhiList.getSelectedRow();
		int col =2;
		try{
		String data= (String)tbExhiList.getValueAt(row, col);
		int price= Integer.parseInt(data);
			totalAdu = (Integer.parseInt(tfAdult.getText())) * price;
			totalAdv =  (int)(Integer.parseInt(tfAdv.getText()) * price *0.5);
			totalChild = (int)(Integer.parseInt(tfChild.getText()) * price*0.75);
		
			laToAduC.setText(String.valueOf(totalAdu));
			laToAdvC.setText(String.valueOf(totalAdv));
			laToChC.setText(String.valueOf(totalChild));
		int total = totalAdu + totalAdv + totalChild;
		laToCash.setText(String.valueOf(total));
		laToPep.setText(String.valueOf((Integer.parseInt(tfAdult.getText())) + (Integer.parseInt(tfAdv.getText()))
				+ (Integer.parseInt(tfChild.getText()))));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "이벤트를 선택하지 않았습니다.");
			System.out.println(e.getMessage());
			
		}
	}

	void setDay() {

		int year = (Integer) cbY.getSelectedItem(); // int year 에 cbY 에 선택 되어있는
													// 값을 집어 넣기
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 만약에
																	// (year/4의
																	// 나머지가 0 이고
																	// year/100
																	// 의 나머지가 0이
																	// 아니면)이거나(year/400의
																	// 나머지가 0
																	// 이면)
			lastDay[2] = 29;// lastDay[2] => 3번째 배열의 값을 29로 변경
		} else
			lastDay[2] = 28;// lastDay[2] => 3번째 배열의 값을 28로 변경

		int month = (int) cbM.getSelectedItem(); // int month에 cbM 에 선택되어있는 값을
													// 집어넣기
		cbD.removeAllItems(); // cdD에 있는 아이템을 모두 지워주세요
		for (int i = 1; i <= lastDay[month]; i++) {// 초기값 i=1;조건 i가
													// lastDay[month]의 값 보다 작거나
													// 같을 때 까지;i++
			cbD.addItem(i); // cdD에 i값을 쫘라라라띾 다 집어 넣겠지
		}
	}

	void eventProc() {
		bGoNext.addActionListener(this);
		bDateOk.addActionListener(this);
		// cbPeople.addActionListener(this);
		bHome.addActionListener(this);
		tfAdult.addActionListener(this);
		tfChild.addActionListener(this);
		tfAdv.addActionListener(this);
		rbExhibi.addActionListener(this);
		rbPerf.addActionListener(this);
		cbD.addActionListener(this);
		cbY.addActionListener(this);
		cbM.addActionListener(this);
		tbExhiList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbExhiList.getSelectedRow();
				int col =2;
				String data= (String)tbExhiList.getValueAt(row, col);
				int price= Integer.parseInt(data);
				
			
			}
		});
		
		
	}

	void addLayout() {
		exhiTbModel = new ExhibListTableModel();
		tbExhiList = new JTable(exhiTbModel);
		perfTbModel = new PerfListTableModel();
		tbPerfList = new JTable(perfTbModel);
		// topTbModel = new TopriTableModel();
		// tbReciept = new JTable(topTbModel);
		rbExhibi = new JRadioButton("전시");
		rbPerf = new JRadioButton("공연");
		bg.add(rbExhibi);
		bg.add(rbPerf);

		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JPanel());
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		// 센터 노스 생성후에 센터에 붙히기 , 센터 노스의 레이아웃 (보더)
		center_north = new JPanel();

		center.add(center_north, BorderLayout.NORTH);
		center_north.setLayout(new GridLayout(1, 6));
		taboTitle = new TitledBorder("판매관리-전시");
		taboTitle.setTitleFont(font2);
		center_north.setBorder(new TitledBorder(taboTitle));

		center_north.add(new JPanel());
		center_north.add(new JPanel());
		center_north.add(new JPanel());
		center_north.add(new JPanel());
		center_north.add(bGoNext = new JButton("결제"));
		center_north.add(bHome = new JButton("Home"));

		// 센터 센터 생성
		JPanel center_center = new JPanel();
		center.add(center_center, BorderLayout.CENTER);
		center_center.setLayout(new GridLayout(2, 1));
		// 날짜 선택, 전시선택 패널

		JPanel center_center_one = new JPanel();

		// 인원 및 가격 테이블
		JPanel center_center_two = new JPanel();
		center_center_two.setLayout(new GridLayout(5, 3));
		taboInfoList = new TitledBorder("인원 및 가격");
		taboInfoList.setTitleFont(font1);
		center_center_two.setBorder(taboInfoList);
		// center_center_two.add(new JScrollPane(tbReciept),
		// BorderLayout.CENTER);
		// change();

		center_center_two.add(laKind);
		center_center_two.add(laPep);
		center_center_two.add(laCash);
		center_center_two.add(laAdult);
		center_center_two.add(tfAdult);//

		center_center_two.add(laToAduC);
		center_center_two.add(laChild);
		center_center_two.add(tfChild);
		center_center_two.add(laToChC);
		center_center_two.add(laAdv);//

		center_center_two.add(tfAdv);
		center_center_two.add(laToAdvC);
		center_center_two.add(laTotal);
		center_center_two.add(laToPep);
		center_center_two.add(laToCash);//

		// center_center_two.add(new JLabel("성인"));

		center_center.add(center_center_one);
		center_center.add(center_center_two);
		center_center_one.setLayout(new BorderLayout());
		JPanel center_center_one_west = new JPanel();
		center_center_one.add(center_center_one_west, BorderLayout.WEST);
		// 전시 선택 화면
		center_center_one_center = new JPanel();
		center_center_one.add(center_center_one_center, BorderLayout.CENTER);
		taboSelEvt = new TitledBorder("전시 선택");
		taboSelEvt.setTitleFont(font1);
		center_center_one_center.setBorder(taboSelEvt);
		center_center_one_center.setLayout(new BorderLayout());
		center_center_one_center.add(new JScrollPane(tbExhiList), BorderLayout.CENTER);

		// 날짜 선택 화면
		center_center_one_west.setLayout(new GridLayout(2, 1));
		taboSelDate = new TitledBorder("날짜 선택");
		taboSelDate.setTitleFont(font1);
		JPanel center_center_one_west_one = new JPanel();
		center_center_one_west.add(center_center_one_west_one);
		center_center_one_west_one.setBorder(new TitledBorder(taboSelDate));
		JPanel center_center_one_west_two = new JPanel();

		center_center_one_west.add(center_center_one_west_two);
		center_center_one_west_one.add(cbY);
		center_center_one_west_one.add(cbM);
		center_center_one_west_one.add(cbD);
		center_center_one_west_one.add(bDateOk = new JButton("확인"));

		center_center_one_west_two.add(rbExhibi);
		center_center_one_west_two.add(rbPerf);

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
	}
	// void change() {
	// Vector data= new Vector();
	// data.removeAllElements();
	//
	// for (int i = 0; i < 4; i++) {
	// Vector<String> temp = new Vector<String>();
	// for (int j = 0; j < 3; j++) {
	// if( j== 0) {
	// temp.add(jTableTitle[i]);
	// }else {
	// temp.add("0");
	// }
	// }
	// data.add(temp);
	// }

	// TableColumn taCol = tbReciept.getColumnModel().getColumn(1);
	// cbPeople = new JComboBox();
	//
	// cbPeople.addItem("0");
	// cbPeople.addItem("1");
	// cbPeople.addItem("2");
	// cbPeople.addItem("3");

	// cbPeople.addItem("4");

	// taCol.setCellEditor(new DefaultCellEditor(cbPeople));

	// topTbModel.data = data;
	// tbReciept.setModel(topTbModel);
	//
	// topTbModel.fireTableDataChanged();
	//
	// tbReciept.setRowHeight(50);
	// }
	void connectDB() {
		try {
			model = new TicketModel();
			System.out.println("티켓 뷰(DB 연결 성공)");
		} catch (Exception e) {
			System.out.println("티켓 DB 연결 ");
			e.printStackTrace();
		}
	}

	void selectDate() {

	}

}

// class TopriTableModel extends AbstractTableModel {
//
// Vector data = new Vector();
// String[] columnNames = { "구분", "인원", "가격" };
//
// @Override
// public int getRowCount() {
// // TODO Auto-generated method stub
// return data.size();
// }
//
// @Override
// public int getColumnCount() {
// // TODO Auto-generated method stub
// return columnNames.length;
// }
//
// @Override
// public Object getValueAt(int rowIndex, int columnIndex) {
// Vector temp = (Vector) data.elementAt(rowIndex);
// return temp.elementAt(columnIndex);
// }
//
// public String getColumnName(int col) {
// return columnNames[col];
//
// }
// public Class getColumnClass(int c) {
// return getValueAt(0, c).getClass();
// }
// public boolean isCellEditable(int row, int col){
// if((row >=0 &&row<=2 )&&col == 1 ) return true;
// else return false;
// }
//
// public void setValueAt(Object value, int row, int col){
// Vector temp = (Vector)data.get(row);
// temp.set(col, value);
// fireTableCellUpdated(row, col);
//
//
//
// }
//
//
// }
class ExhibListTableModel extends AbstractTableModel {

	Vector data = new Vector();
	String[] columnNames = { "제목", "장소", "가격" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector temp = (Vector) data.elementAt(rowIndex);
		return temp.elementAt(columnIndex);
	}

	public String getColumnName(int col) {
		return columnNames[col];

	}

}
class PerfListTableModel extends AbstractTableModel {
	
	Vector data = new Vector();
	String[] columnNames = { "제목", "장소", "가격" ,"시작시간" , "종료시간"};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector temp = (Vector) data.elementAt(rowIndex);
		return temp.elementAt(columnIndex);
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
		
	}
	
}