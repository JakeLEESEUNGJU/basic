package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.ArtCenter;

public class ReceiptView extends JPanel implements ActionListener {
	JLabel laTitle, laRating, laPayMethod, laTotal; //타이틀,등급,결제수단,총결제금액 Label
	JTextField tfTotal, tfDiscount; 				//총결제금액 TextArea
	JComboBox<String> cbRating, cbPayMethod;		//등급,결제수단 ComboBox
	JButton bPayment, bCancel, bBack;				//결제하기,예매취소,뒤로가기 Button
	JCheckBox cbGroup;								//단체 선택 CheckBox
	JTable tbPriceInfo;								//인원,가격정보 보여주는 Table
	priceTableModel priceModel;						//JTableModel
	ArtCenter ac;									//아트센터 객체
	String flag = null;								//전시인지 공연인지 구분하는 구분자 p or e(인터페이스)
	JTextArea taTicketInfo;
												//이하 인터페이스 값들을 받는 변수
	int exiNo;									//전시번호
	String exiLoc;								//전시 위치
	String exiDate;								//전시 관람일자
	int perNo ;									//공연번호
	String perLoc ;								//공연 위치
	String perDate ;							//공연 관람일자
	String perStartTime ;						//공연 시작시간
	String perFinishTime ;						//공연 끝날시간
	
	// 할인가격
	int afterDiscountPrice = 0;
	// 총가격
	int totalPrice = 0;
	// 성인가격
	int price = 0; 
	// 총인원
	int peopleCnt = 0;
	// 가격
	int adultPrice;
	int childPrice;
	int oldPrice ;
	// 명수
	int adultCnt = 0;
	int childCnt = 0;
	int oldCnt = 0;
	//총 가격
	int totalAdultPrice = 0;
	int totalChildPrice = 0;
	int totalOldPrice = 0;

	ArrayList priceInfoList;
	
	// 인터페이스  arrayList : 인원별 가격정보 
	ArrayList interfaceList; 

	
	public ReceiptView() {
		super();
	}

	public ReceiptView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		eventProc();//이벤트는 생성자에서 호출되어야함 순서 꼬임 ***
		connectDB();

	}

	//테이블에 들어가는 데이터 arraylist 설정하는 메서드
	void setTableInfo() {
		
		// jtable에 담을 리스트
		priceInfoList = new ArrayList();

		// 성인정보 리스트
		ArrayList adultList = new ArrayList();
		adultList.add("성인");
		adultList.add(adultCnt);
		adultList.add(adultPrice);
		adultList.add(totalAdultPrice);
		priceInfoList.add(adultList);
		// 어린이정보 리스트
		ArrayList childList = new ArrayList();
		childList.add("어린이");
		childList.add(childCnt);
		childList.add(childPrice);
		childList.add(totalChildPrice);
		priceInfoList.add(childList);
		// 우대정보 리스트
		ArrayList oldList = new ArrayList();
		oldList.add("우대");
		oldList.add(oldCnt);
		oldList.add(oldPrice);
		oldList.add(totalOldPrice);
		priceInfoList.add(oldList);
		// 총정보 리스트
		ArrayList total = new ArrayList();
		total.add("총계");
		total.add(peopleCnt);
		total.add("");
		total.add(totalPrice);
		priceInfoList.add(total);

		//테이블 크기 설정
		Dimension d = tbPriceInfo.getPreferredSize();
		d.setSize(d.getWidth(), 200);
		tbPriceInfo.setPreferredScrollableViewportSize(d);
	}

	
	// jtable에 들어가는 정보의 변수 설정
	void getPriceInfo(ArrayList abc) {
		//기준 가격(성인) 설정
		price = Integer.parseInt(abc.get(3).toString());
		//가격 설정
		adultPrice = price;
		childPrice = (int) (price * 0.5);
		oldPrice = (int) (price * 0.8);
		// 인터페이스 arrayalist 
		interfaceList = new ArrayList<>();
		//add안 변수 수정
		
		interfaceList.add(abc.get(0));// 성인 명수 
		interfaceList.add(abc.get(1));//어린이 명수 
		interfaceList.add(abc.get(2)); //우대 명수
		// 명수
		adultCnt = Integer.parseInt(interfaceList.get(0).toString());
		childCnt = Integer.parseInt(interfaceList.get(1).toString());
		oldCnt = Integer.parseInt(interfaceList.get(2).toString());
		// 명수별 가격
		totalAdultPrice = (int) (price * adultCnt);
		totalChildPrice = (int) (price * 0.5 * childCnt);
		totalOldPrice = (int) (price * 0.75 * oldCnt);
		// 총명수
		peopleCnt = adultCnt + childCnt + oldCnt;
		// 할인 전 총 가격
		totalPrice = totalAdultPrice + totalChildPrice + totalOldPrice;
		//
		
		//단체여부확인
		if (peopleCnt >= 20) {
			cbGroup.setEnabled(true);
		} else {
			cbGroup.setEnabled(false);
		}	
		//arraylist 설정하는 메서드 호출
		setTableInfo();
	}

	//디비 연결하는 메서드
	void connectDB() {

	}

	//엑션리스너 등록하는 메서드
	void eventProc() {
		//콤보박스 액션리스너 등록
		cbRating.addActionListener(this);
		cbGroup.addActionListener(this);
		//버튼 액션리스너 등록
		bPayment.addActionListener(this);
		bCancel.addActionListener(this);
		bBack.addActionListener(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		//콤보박스 이벤트 등록
		if (evt == cbRating) {				
			setPriceByrating(cbRating.getSelectedItem().toString());
			tfTotal.setText(afterDiscountPrice + "");
			tfDiscount.setText(String.valueOf((totalPrice-afterDiscountPrice)));
		} else if (evt == cbGroup) {		//단체 체크박스 선택시
			cbRating.setEnabled(!cbGroup.isSelected());
			if(cbGroup.isSelected()){
				tfDiscount.setText(((int)(totalPrice*0.1)+""));//단체 10퍼 할인가격으로 셋팅
				tfTotal.setText((  (int)(totalPrice * 0.9)  ) + "");//단체 할인받은 가격으로 셋팅
			}else{
				tfDiscount.setText("0");//할인가격 0으로 셋팅
				tfTotal.setText(((totalPrice)) + "");//총가격 원래 가격으로 셋팅
				
			}

		}else if(evt == bBack){//뒤로 버튼 클릭시
			if(flag == "p"){//공연이면 좌석선택화면으로
				ac.movecard("seatcard");
			}else if(flag == "e"){//전시면 전시선택화면으로
				ac.movecard("ticketcard");
				
			}
		} else if (evt == bCancel) {//예매 취소 버튼 클릭시
			System.out.println(">>결제취소");
			ac.movecard("main");
		} else if (evt == bPayment) {//결제 버튼 클릭시
			System.out.println(">>결제하기");
			JOptionPane.showMessageDialog(null, "결제 버튼 누름!");
			// 결제메서드 호출 추가 ***
		}

	}

	// 등급에 따른 가격 정하는 메서드
	void setPriceByrating(String rating) {
		switch (rating) {
		case "그린":
			if (adultCnt > 1) {
				afterDiscountPrice = (int) (adultPrice * 2 * 0.9 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				afterDiscountPrice = (int) (adultPrice * adultCnt * 0.9 + childPrice * childCnt + oldPrice * oldCnt);
			}
			
			break;
		case "블루":
			if (adultCnt > 1) {
				afterDiscountPrice = (int) (adultPrice * 2 * 0.75 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				afterDiscountPrice = (int) (adultPrice * adultCnt * 0.75 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;
		case "골드":
			if (adultCnt > 1) {
				afterDiscountPrice = (int) (adultPrice * 2 * 0.6 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				afterDiscountPrice = (int) (adultPrice * adultCnt * 0.6 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;

		/*
		 * case "노블": discountPrice = (int) (totalPrice * 0.5); break;
		 */

		case "싹틔우미":
			if (adultCnt > 1) {
				afterDiscountPrice = (int) (adultPrice * 2 * 0.5 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				afterDiscountPrice = (int) (adultPrice * adultCnt * 0.5 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;
		default:
			afterDiscountPrice = totalPrice;
			break;
		}
	}

	// JTable 그리는 메서드
	void drawtable(ArrayList list) {
		priceModel.data = list;
		tbPriceInfo.setModel(priceModel);
		priceModel.fireTableDataChanged();
	}

	//레이아웃 그리는 메서드
	void addLayout() {
		laTitle = new JLabel("결제");
		priceModel = new priceTableModel();
		tbPriceInfo = new JTable(priceModel);
		tbPriceInfo.setRowHeight(50);

		laRating = new JLabel("회원 등급(그린:10%,블루:25%,골드:40%,싹틔우미:50%,최대동반2인)");
		laPayMethod = new JLabel("결제 수단");
		laTotal = new JLabel("총 결제금액");
		tfTotal = new JTextField(15);
		tfTotal.setEditable(false);
		cbGroup = new JCheckBox("단체 여부(10% 할인,20인 이상)");

		tfDiscount =new JTextField();
		tfDiscount.setEditable(false);
		tfDiscount.setText("0");
		
		bBack = new JButton("<뒤로");
		bPayment = new JButton("결제하기>");
		bCancel = new JButton("예매 취소");
		
		taTicketInfo = new JTextArea(15,20);
		taTicketInfo.setEditable(false);

		cbRating = new JComboBox<String>();
		cbRating.addItem("==선택==");
		cbRating.addItem("비회원");
		cbRating.addItem("그린");
		cbRating.addItem("블루");
		cbRating.addItem("골드");
		// cbRating.addItem("노블"); 
		cbRating.addItem("싹틔우미");

		cbPayMethod = new JComboBox<String>();
		cbPayMethod.addItem("==선택==");
		cbPayMethod.addItem("신용카드");
		cbPayMethod.addItem("현금");
		cbPayMethod.addItem("삼성페이");
		cbPayMethod.addItem("상품권");

		JPanel p_north = new JPanel();
		p_north.setLayout(new BorderLayout());
		p_north.add(laTitle);

		JPanel p_center = new JPanel();
		p_center.setLayout(new BorderLayout());

		JPanel p_center_north = new JPanel();
		p_center_north.setBorder(new TitledBorder("티켓 정보"));
		p_center_north.add(taTicketInfo);// 행사 상세 정보 추가***
		p_center_north.add(new JScrollPane(tbPriceInfo), BorderLayout.NORTH);
		p_center.add(p_center_north, BorderLayout.NORTH);

		JPanel p_center_south = new JPanel();
		p_center_south.setBorder(new TitledBorder("결제 정보"));
		p_center_south.setLayout(new GridLayout(5, 2));
		p_center_south.add(new JLabel(""));
		p_center_south.add(cbGroup);
		p_center_south.add(laRating);
		p_center_south.add(cbRating);
		p_center_south.add(laPayMethod);
		p_center_south.add(cbPayMethod);
		p_center_south.add(new JLabel("할인 가격"));
		p_center_south.add(tfDiscount);
		p_center_south.add(laTotal);
		p_center_south.add(tfTotal);
		p_center.add(p_center_south, BorderLayout.CENTER);

		JPanel p_south = new JPanel();
		p_south.setLayout(new BorderLayout());
		JPanel p_south_east = new JPanel();
		p_south.add(p_south_east, BorderLayout.EAST);
		p_south_east.add(bBack);
		p_south_east.add(bCancel);
		p_south_east.add(bPayment);

		this.setLayout(new BorderLayout());
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_south, BorderLayout.SOUTH);
		this.add(p_center, BorderLayout.CENTER);
		//this.setSize(800, 900);
		//this.setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void setTextArea(JTextArea ta){
		ta.setText("");
		ta.append("== Multicampus Arts Center ==\n");
		if(flag == "e"){
			
			ta.append("==   전시정보   ==\n");
			ta.append("전시 제목 : ");
			ta.append("select"+"\n");//**
			ta.append("전시 위치 : ");
			ta.append(exiLoc+"\n");
			ta.append("관람일 : ");
			ta.append(exiDate);
		}else if(flag == "p"){
			ta.append("==   공연정보   ==\n");
			ta.append("공연 제목 : ");
			ta.append("select"+"\n");//**
			ta.append("공연 위치 : ");
			ta.append(perLoc+"\n");
			ta.append("선택한 좌석 : ");
			ta.append("넘어오는 정보"+"\n");//**
			ta.append("관람일 : ");
			ta.append(perDate+"\n");
			ta.append("관람 시간 : ");
			ta.append(perStartTime+" ~ "+perFinishTime);
		}
	}

	//테이블 모델 클래스
	class priceTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "구분", "인원", "가격", "총 가격" };

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
	
	//성인,아동,우대 인원수 정보가 담긴 arraylist가 넘어오는 메서드(인터페이스)
	public void settempList(ArrayList temp){
		getPriceInfo(temp);			 	//price 정보 arraylist 설정하는 메서드 호출
		drawtable(priceInfoList);		//테이블 그리는 메서드 호출
		tfTotal.setText(totalPrice + "");// 처음 총가격 텍스트필드에 띄우기
	}
	
	//공연 전시 정보가 넘어오는 메서드
	public void setTempList(ArrayList temp){ //temp (공연번호,이벤트 번호,이벤트제목,위치,기준가격,시작시간,종료시간,관람일자,p/e구분)
		if(temp.size() == 7){								//전시면
			flag = temp.get(6).toString();
			exiNo = Integer.parseInt(temp.get(1).toString());	 //전시번호
			exiLoc = temp.get(3).toString(); 				//전시 위치
			exiDate = temp.get(5).toString();				//전시 관람일자
		}else if(temp.size() == 9){							//공연이면
			flag = temp.get(8).toString();
			perNo = Integer.parseInt(temp.get(0).toString()); 	//공연번호
			perLoc = temp.get(3).toString(); 				//공연 위치
			perDate = temp.get(7).toString();				//공연 관람일자
			perStartTime = temp.get(5).toString(); 			//공연 시작시간
			perFinishTime = temp.get(6).toString();			//공연 끝날시간
		}
		setTextArea(taTicketInfo);
	}
	
}
