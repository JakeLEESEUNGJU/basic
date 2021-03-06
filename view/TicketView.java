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

public class TicketView extends JPanel implements ActionListener {
	private Font font1 = new Font("포천 오성과 한음 Regular", Font.BOLD, 20); // 중간 크기
																		// 폰트설정
	private Font font2 = new Font("210 타임라인 R", Font.BOLD, 30); // 대문 폰트
	JButton bDateOk, bGoNext, bHome; // 날짜 확인 버튼 //결제 혹은 좌석선택 버튼 //초기화면 버튼
	ButtonGroup bg = new ButtonGroup();// 라디오 버튼의 중복 선택 방지를 위한 버튼 그룹
	TitledBorder taboTitle, taboSelDate, taboSelEvt, taboInfoList, taboPerValue;
	// 제목이 달린 보더 클래스 각각 타이틀,날짜선택,이벤트 선택, 인원 및 가격 //구성인원별 가격
	JPanel center_center_one_center, center_north; // 그냥 JPanel ... ㅋㅋ
	String[] jTableTitle = { "성인", "어린이", "우대(노인,장애인", "총계" }; // JTable 컬럼 제목

	// 인원 및 가격 라벨
	JLabel laKind, laPep, laCash, laAdult, laChild, laAdv, laTotal, laToPep, laToCash, laToAduC, laToChC, laToAdvC;
	// 구성원 텍스트필드
	public JTextField tfAdult, tfChild, tfAdv;
	// ( 성인 , 어린이 , 우대 ) 1인당 가격 표시할 라벨들
	JLabel laPerAdu, laPerChild, laPerAdv, laPerAduCash, laPerChildCash, laPerAdvCash;

	//////
	JComboBox<Integer> cbY, cbM, cbD; // 연월일 체크
	String[] strY = new String[11]; // year의 갯수만큼 콤보박스에 넣기 위해 만든 배열이지만 본인은 사용 안함
	String[] strM = new String[12];// Month 의 값을 저장해서 콤보 박스에 넣기 위한 배열
	int[] lastDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 각
	// JComboBox<Integer> cbPeople;// 어린이 성인 우대 콤보박스 인원 체크
	JRadioButton rbPerf, rbExhibi;// 공연 및 전시 라디오 버튼
	JTable tbExhiList, tbPerfList;
	// , tbReciept
	ExhibListTableModel exhiTbModel;
	PerfListTableModel perfTbModel;

	// TopriTableModel topTbModel;
	TicketModel model;
	ArtCenter ac;
	ArrayList<String> table;

	public TicketView(ArtCenter ac) {

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
		laPerAdu = new JLabel("성인");
		laPerAdu.setBorder(eborder);
		laPerAdv = new JLabel("우대");
		laPerAdv.setBorder(eborder);
		laPerChild = new JLabel("어린이");
		laPerChild.setBorder(eborder);
		laPerAduCash = new JLabel("0");
		laPerAduCash.setBorder(eborder);
		laPerAdvCash = new JLabel("0");
		laPerAdvCash.setBorder(eborder);
		laPerChildCash = new JLabel("0");
		laPerChildCash.setBorder(eborder);

		this.ac = ac;
		Calendar c = Calendar.getInstance();
		cbY = new JComboBox<Integer>();
		cbM = new JComboBox<Integer>();
		cbD = new JComboBox<Integer>();
		// 올해 +5 부터 올해 까지 콤보박스에 입력하기 위한 for 문
		for (int i = c.get(Calendar.YEAR) + 5; i >= c.get(Calendar.YEAR); i--) {
			cbY.addItem(i);
		}
		// DECEMBER 를 사용한 이유는 저 값이 12니깐?
		for (int i = 0; i <= (Calendar.DECEMBER); i++) {
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
		Object evt = e.getSource();
		if (evt == bHome) {
			ac.goingHome();
			ac.movecard("main");
		} else if (evt == bGoNext) {
			try {
				searchForSend();
				if (laToPep.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "인원을 설정하지 않았습니다.");
					return;
				}
				if (rbExhibi.isSelected()) {

					ac.movecard("receiptcard");
				} else if (rbPerf.isSelected()) {
					ac.movecard("seatcard");
				}
				// try문 끝
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "이벤트 설정 혹은 총가격 갱신이 되지 않았습니다. " + ex.getMessage());
			}
		} else if (evt == tfAdult || evt == tfAdv || evt == tfChild) {
			settotal();
		} else if (evt == bDateOk) {
			serchByDate();
		} else if (evt == rbExhibi) {
			showFrame();
		} else if (evt == rbPerf) {
			showFrame();
		} else if (evt == cbY || evt == cbM) {
			setDay(); // setDat 메소드 출력
		}
	}

	void searchForSend() { // 결제 뷰에 사용하기위한 아이템을 모아주는 메서드
		ArrayList<String> forTableE;
		ArrayList<String> forTableP;
		String date = String.valueOf(cbY.getSelectedItem()) + "/" + String.valueOf(cbM.getSelectedItem()) + "/"
				+ String.valueOf(cbD.getSelectedItem()); // 날짜 저장 'YYYY/MM/DD'
		if (rbExhibi.isSelected()) {
			int row = tbExhiList.getSelectedRow();// 내가 클릭한 행 위치를 저장하는 변수
			int col = 0; // 뭘 클릭 하던 0 열에 있는 데이터를 뽑기 위한 변수
			forTableE = new ArrayList<String>();
			forTableE.add(tfAdult.getText()); // 어른 인원수
			forTableE.add(tfChild.getText()); // 어린이 인원수
			forTableE.add(tfAdv.getText()); // 우대 인원수
			forTableE.add((String) tbExhiList.getValueAt(row, 2)); // ?행 2열
			forTableE.add("e");
			String title = (String) tbExhiList.getValueAt(row, col); // ?행 0열
			try {
				ArrayList<String> forSql = new ArrayList<String>();
				forSql = model.searchItems(title, "e", date);
				// sql 문을 통해서 얻어온 데이터들을 변수에 저장
				ac.setTempSql(forSql); // forSql을 아트센터 (tempList)에 저장
				ac.setPeopleSeat(forTableE); // forTableE -> 전시 구성인원을 어레이리스트에
												// 저장해서
				// 전송~~~
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "제목 찾기 실패");
				e.printStackTrace();
			}
		} else if (rbPerf.isSelected()) {
			int row = tbPerfList.getSelectedRow();
			int col = 0;
			forTableP = new ArrayList<String>();
			forTableP.add(tfAdult.getText());
			forTableP.add(tfChild.getText());
			forTableP.add(tfAdv.getText());
			forTableP.add((String) tbPerfList.getValueAt(row, 2));
			forTableP.add("p");
			String title = (String) tbPerfList.getValueAt(row, col);

			try {
				ArrayList<String> forSql = new ArrayList<String>();
				forSql = model.searchItems(title, "p", date);
				ac.setPeopleSeat(forTableP);// forTableP -> 공연 구성인원을 어레이리스트에
											// 저장해서
				// 전송~~~
				ac.setTempSql(forSql);// forsql을 아트센터 (tempList)에 저장
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "제목 찾기 실패" + e.getMessage());
			}
		}
	}

	void showFrame() { // 라디오 버튼을 찍을 때 마다 바뀌는 (라벨, 테이블) 설정 메서드
		if (rbExhibi.isSelected()) {
			bGoNext.setText("결제");
			taboTitle.setTitle("판매관리-전시");
			center_north.setBorder(new TitledBorder(taboTitle));
			taboSelEvt.setTitle("전시 선택");
			center_center_one_center.removeAll(); // 안에 있는걸 비우고 생성
			center_center_one_center.setBorder(new TitledBorder(taboSelEvt));
			center_center_one_center.add(new JScrollPane(tbExhiList), BorderLayout.CENTER);
		} else if (rbPerf.isSelected()) {
			bGoNext.setText("좌석선택");
			taboTitle.setTitle("판매관리-공연");
			center_north.setBorder(new TitledBorder(taboTitle));
			taboSelEvt.setTitle("공연선택");
			center_center_one_center.removeAll();
			center_center_one_center.setBorder(new TitledBorder(taboSelEvt));
			center_center_one_center.add(new JScrollPane(tbPerfList), BorderLayout.CENTER);
		}

	}

	void serchByDate() { // 날짜별 이벤트 검색 메서드
		// if 문 을 줘서 라디오 버튼 선택에 따라 검색환경이 바뀜
		if (rbExhibi.isSelected()) {
			String date = String.valueOf(cbY.getSelectedItem()) + "/" + String.valueOf(cbM.getSelectedItem()) + "/"
					+ String.valueOf(cbD.getSelectedItem());
			try {
				ArrayList<Object> table;

				table = model.selectByDate(date, "e");
				exhiTbModel.data = table;
				tbExhiList.setModel(exhiTbModel);
				exhiTbModel.fireTableDataChanged();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "날짜검색 실패" + e.getMessage());
				e.printStackTrace();
			}
		} else if (rbPerf.isSelected()) {
			String date = String.valueOf(cbY.getSelectedItem()) + "/" + String.valueOf(cbM.getSelectedItem()) + "/"
					+ String.valueOf(cbD.getSelectedItem());
			try {
				ArrayList<Object> table;

				table = model.selectByDate(date, "p");
				perfTbModel.data = table;
				tbPerfList.setModel(perfTbModel);
				perfTbModel.fireTableDataChanged();

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "날짜검색 실패" + e.getMessage());
			}
		}
	}

	public void settotal() {

		int totalAdu = 0;
		int totalAdv = 0;
		int totalChild = 0;

		if (rbExhibi.isSelected()) {
			int row = tbExhiList.getSelectedRow();
			int col = 2;
			try {

				String data = (String) tbExhiList.getValueAt(row, col);
				int price = Integer.parseInt(data);
				// 구성 인원별 총가격 저장
				totalAdu = (Integer.parseInt(tfAdult.getText())) * price;
				totalAdv = (int) (Integer.parseInt(tfAdv.getText()) * price * 0.75); //
				totalChild = (int) (Integer.parseInt(tfChild.getText()) * price * 0.5);
				// 각 라벨에 구성인원별 총가격 세팅
				laToAduC.setText(String.valueOf(totalAdu));
				laToAdvC.setText(String.valueOf(totalAdv));
				laToChC.setText(String.valueOf(totalChild));
				int total = totalAdu + totalAdv + totalChild; // 총가격 저장
				laToCash.setText(String.valueOf(total)); // 총가격 세팅
				// 총인원 수를 구해서 바로 세팅
				laToPep.setText(String.valueOf((Integer.parseInt(tfAdult.getText()))
						+ (Integer.parseInt(tfAdv.getText())) + (Integer.parseInt(tfChild.getText()))));

			} catch (Exception e) {

			}
		} else if (rbPerf.isSelected()) {
			int row = tbPerfList.getSelectedRow();
			int col = 2;
			try {
				String data = (String) tbPerfList.getValueAt(row, col);
				int price = Integer.parseInt(data);
				totalAdu = (Integer.parseInt(tfAdult.getText())) * price;
				totalAdv = (int) (Integer.parseInt(tfAdv.getText()) * price * 0.5);
				totalChild = (int) (Integer.parseInt(tfChild.getText()) * price * 0.75);

				laToAduC.setText(String.valueOf(totalAdu));
				laToAdvC.setText(String.valueOf(totalAdv));
				laToChC.setText(String.valueOf(totalChild));
				int total = totalAdu + totalAdv + totalChild;
				laToCash.setText(String.valueOf(total));
				laToPep.setText(String.valueOf((Integer.parseInt(tfAdult.getText()))
						+ (Integer.parseInt(tfAdv.getText())) + (Integer.parseInt(tfChild.getText()))));
			} catch (Exception e) {

			}
		}
	}

	void setDay() {

		int year = (Integer) cbY.getSelectedItem();
		// int year 에 cbY 에 선택 되어있는 값을 집어 넣기
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			// 만약에 (year/4의 나머지가 0 이고 year/100 의 나머지가 0이 아니면)
			// 이거나(year/400의 나머지가 0 이면)
			lastDay[2] = 29;// lastDay[2] => 3번째 배열의 값을 29로 변경
		} else
			lastDay[2] = 28;// lastDay[2] => 3번째 배열의 값을 28로 변경

		int month = (int) cbM.getSelectedItem();
		// int month에 cbM 에 선택되어있는 값을 집어넣기
		cbD.removeAllItems(); // cdD에 있는 아이템을 모두 지워주세요
		for (int i = 1; i <= lastDay[month]; i++) {
			// 초기값 i=1;조건 i가 lastDay[month]의 값 보다 작거나 같을 때 까지;i++
			cbD.addItem(i); // cdD에 i값을 쫘라라라띾 다 집어 넣겠지
		}
	}

	public void eventProc() {
		bGoNext.addActionListener(this);
		bDateOk.addActionListener(this);
		bHome.addActionListener(this);
		tfAdult.addActionListener(this);
		tfAdult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tfAdult.getText().equals("0")) {
					tfAdult.setText("");
				}
			}
		});
		tfChild.addActionListener(this);
		tfChild.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tfChild.getText().equals("0")) {
					tfChild.setText("");
				}
			}
		});
		tfAdv.addActionListener(this);
		tfAdv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tfAdv.getText().equals("0")) {
					tfAdv.setText("");
				}
			}
		});
		rbExhibi.addActionListener(this);
		rbPerf.addActionListener(this);
		cbD.addActionListener(this);
		cbY.addActionListener(this);
		cbM.addActionListener(this);
		tbExhiList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbExhiList.getSelectedRow();
				int col = 2;
				String data = (String) tbExhiList.getValueAt(row, col);
				int price = Integer.parseInt(data);
				laPerAduCash.setText(String.valueOf(price));
				laPerAdvCash.setText(String.valueOf((int) (price * 0.5)));
				laPerChildCash.setText(String.valueOf((int) (price * 0.75)));
				settotal();
			}

		});
		tbPerfList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbPerfList.getSelectedRow();
				int col = 2;
				String data = (String) tbPerfList.getValueAt(row, col);
				int price = Integer.parseInt(data);

				laPerAduCash.setText(String.valueOf(price));
				laPerAdvCash.setText(String.valueOf((int) (price * 0.5)));
				laPerChildCash.setText(String.valueOf((int) (price * 0.75)));
				settotal();
			}
		});

	}

	public void addLayout() {
		exhiTbModel = new ExhibListTableModel();
		tbExhiList = new JTable(exhiTbModel);
		perfTbModel = new PerfListTableModel();
		tbPerfList = new JTable(perfTbModel);
		rbExhibi = new JRadioButton("전시");
		rbPerf = new JRadioButton("공연");
		bg.add(rbExhibi);
		bg.add(rbPerf);
		rbExhibi.setSelected(true);
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
		center_center_one_west_two.setLayout(new BorderLayout());
		JPanel center_center_one_west_two_north = new JPanel();
		center_center_one_west_two.add(center_center_one_west_two_north, BorderLayout.NORTH);
		center_center_one_west_two_north.add(rbExhibi);
		center_center_one_west_two_north.add(rbPerf);
		JPanel center_center_one_west_two_center = new JPanel();
		center_center_one_west_two.add(center_center_one_west_two_center, BorderLayout.CENTER);
		center_center_one_west_two_center.setLayout(new GridLayout(3, 2));
		taboPerValue = new TitledBorder("1인당 가격");
		taboPerValue.setTitleFont(font1);
		center_center_one_west_two_center.setBorder(taboPerValue);
		center_center_one_west_two_center.add(laPerAdu);
		center_center_one_west_two_center.add(laPerAduCash);
		center_center_one_west_two_center.add(laPerChild);
		center_center_one_west_two_center.add(laPerChildCash);
		center_center_one_west_two_center.add(laPerAdv);
		center_center_one_west_two_center.add(laPerAdvCash);

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
	}

	void connectDB() {
		try {
			model = new TicketModel();
			// System.out.println("티켓 뷰(DB 연결 성공)");
		} catch (Exception e) {
			// System.out.println("티켓 DB 연결 ");
			e.printStackTrace();
		}
	}

}

class ExhibListTableModel extends AbstractTableModel { // 전시 테이블 모델

	ArrayList data = new ArrayList();
	String[] columnNames = { "제목", "장소", "가격" };

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
}

class PerfListTableModel extends AbstractTableModel { // 공연 테이블 모델

	ArrayList data = new ArrayList();
	String[] columnNames = { "제목", "장소", "가격", "시작시간", "종료시간" };

	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}