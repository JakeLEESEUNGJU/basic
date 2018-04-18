package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import main.ArtCenter;

public class TicketExhibitionView extends JPanel {
	private Font font1 = new Font("Serif", Font.BOLD, 20);
	private Font font2 = new Font("Serif", Font.BOLD, 30);
	JButton bDateOk, bGoNext;
	ButtonGroup bg = new ButtonGroup();
	TitledBorder taboTitle, taboSelDate,taboSelEvt,taboInfoList;
	
	String [] jTableTitle = {"성인","어린이","우대(노인,장애인","총계"};
	
	//////
	JComboBox<Integer> cbY, cbM, cbD; // 연월일 체크
	String[] strY = new String[11]; // year의 갯수만큼 콤보박스에 넣기 위해 만든 배열이지만 본인은 사용 안함
	String[] strM = new String[12];// Month 의 값을 저장해서 콤보 박스에 넣기 위한 배열
	int[] lastDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 각
																			// 월
																			// 마다
																			// 일수가
																			// 다르기
																			// 때문에
																			// 배열로
																			// 관리,(맨
																			// 앞
																			// 0
																			// 은
																			// for문을
																			// 위해
																			// 생성)
	//////
	JComboBox cbPeople;// 어린이 성인 우대 콤보박스 인원 체크
	JRadioButton rbPerf, rbExhibi;// 공연 및 전시 라디오 버튼
	JTable tbEvtList, tbReciept;
	EvtListTableModel evTbModel;
	TopriTableModel topTbModel;

	ArtCenter ac;

	
	
	public TicketExhibitionView(ArtCenter ac) {
		this.ac = ac;
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.DATE);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
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

	}

	void addLayout() {
		evTbModel = new EvtListTableModel();
		tbEvtList= new JTable(evTbModel);
		topTbModel = new TopriTableModel();
		tbReciept = new JTable(topTbModel);
		rbExhibi = new JRadioButton("전시");
		rbPerf = new JRadioButton("공연");
		bg.add(rbExhibi);
		bg.add(rbPerf);
		
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JPanel());
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		//센터 노스 생성후에 센터에 붙히기 , 센터 노스의 레이아웃 (보더)
		JPanel center_north = new JPanel();
		center.add(center_north,BorderLayout.NORTH);
		center_north.setLayout(new GridLayout(1,6));
		taboTitle= new TitledBorder("판매관리-전시");
		taboTitle.setTitleFont(font2);
		center_north.setBorder(new TitledBorder(taboTitle));
		
		center_north.add(new JPanel());
		center_north.add(bGoNext=new JButton("결제"));
		
		//센터 센터 생성 
		JPanel center_center = new JPanel();
		center.add(center_center,BorderLayout.CENTER);
		center_center.setLayout(new GridLayout(2,1));
		// 날짜 선택, 전시선택  패널				
		
		JPanel center_center_one = new JPanel();
		
		
		
		//인원 및 가격 테이블
		JPanel center_center_two = new JPanel();
		center_center_two.setLayout(new BorderLayout());
		taboInfoList = new TitledBorder("인원 및 가격");
		taboInfoList.setTitleFont(font1);
		center_center_two.setBorder(taboInfoList);
		center_center_two.add(new JScrollPane(tbReciept), BorderLayout.CENTER);
		change();
		
		
		center_center.add(center_center_one);
		center_center.add(center_center_two);
		center_center_one.setLayout(new BorderLayout());
		JPanel center_center_one_west = new JPanel();
		center_center_one.add(center_center_one_west,BorderLayout.WEST);
		//전시 선택 화면
		JPanel center_center_one_center = new JPanel();
		center_center_one.add(center_center_one_center,BorderLayout.CENTER);
		taboSelEvt = new TitledBorder("전시 선택");
		taboSelEvt.setTitleFont(font1);
		center_center_one_center.setBorder(taboSelEvt);		
		center_center_one_center.setLayout(new BorderLayout());
		center_center_one_center.add(new JScrollPane(tbEvtList), BorderLayout.CENTER);
		
		//날짜 선택 화면 
		center_center_one_west.setLayout(new GridLayout(2, 1));
		taboSelDate= new TitledBorder("날짜 선택");
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
	void change() {
		Vector data= new Vector();
		data.removeAllElements();
		
		for (int i = 0; i < 4; i++) {
			Vector<String> temp = new Vector<String>();
			for (int j = 0; j < 3; j++) {
				if( j== 0) {
				temp.add(jTableTitle[i]);
				}else {
				temp.add("0");
				}
			}
			data.add(temp);
		}
		
		
		TableColumn taCol = tbReciept.getColumnModel().getColumn(1);
		 cbPeople  = new JComboBox();

		 	cbPeople.addItem("0");
			 cbPeople.addItem("1");
			 cbPeople.addItem("2");
			 cbPeople.addItem("3");
			 cbPeople.addItem("4");

		taCol.setCellEditor(new DefaultCellEditor(cbPeople));
			
		
		topTbModel.data = data;
		tbReciept.setModel(topTbModel);
		
		topTbModel.fireTableDataChanged();
		
		tbReciept.setRowHeight(50);
	}
	void connectDB() {

	}

	void selectDate() {

	}
}

class TopriTableModel extends AbstractTableModel {

	Vector data = new Vector();
	String[] columnNames = { "구분", "인원", "가격" };

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
	public Class getColumnClass(int c) { 
		return getValueAt(0, c).getClass(); 
	} 
	public boolean isCellEditable(int row,  int col){
		if((row >=0 &&row<=2 )&&col == 1 ) return true;
		else return false;
	}
	
	public void setValueAt(Object value, int row, int col){
		Vector temp = (Vector)data.get(row);
		temp.set(col, value);
		fireTableCellUpdated(row, col); 
		
		
		
	}
	
	
}
class EvtListTableModel extends AbstractTableModel {
	
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