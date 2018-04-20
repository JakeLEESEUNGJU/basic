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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.ArtCenter;

public class ReceiptView extends JPanel implements ActionListener {
	JLabel laTitle, laRating, laPayMethod, laTotal;
	JTextField tfTotal;
	JComboBox<String> cbRating, cbPayMethod;
	JButton bPayment, bCancel, bBack;
	JCheckBox cbGroup;

	JTable tbPriceInfo;
	priceTableModel priceModel;
	ArtCenter ac;

	// 할인가격
	int discountPrice = 0;
	// 총가격
	int totalPrice = 0;
	// 성인가격
	int price = 10000; // 하드코딩(수정필수)//인터페이스********
	// 총인원
	int peopleCnt = 0;
	// 가격
	int adultPrice = price;
	int childPrice = (int) (price * 0.5);
	int oldPrice = (int) (price * 0.8);
	// 명수
	int adultCnt = 0;
	int childCnt = 0;
	int oldCnt = 0;
	//
	int totalAdultPrice=0;
	int totalChildPrice=0;
	int totalOldPrice=0;
	//

	// 인터페이스 arrayList : 인원별 가격정보
	ArrayList priceInfoList;
	ArrayList<Integer> interList;

	public ReceiptView(ArtCenter ac) {// ArtCenter ac
		 this.ac = ac;
		connectDB();
		getPriceInfo();
		addLayout();
		setTableInfo();// addLayout후에
		eventProc();

		drawtable(priceInfoList);
		tfTotal.setText(totalPrice + "");// 처음 총가격 텍스트필드에 띄우기
	}

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

		Dimension d = tbPriceInfo.getPreferredSize();
		d.setSize(d.getWidth(), 200);
		tbPriceInfo.setPreferredScrollableViewportSize(d);

	}

	// jtable에 들어가는 정보 arraylist 설정
	void getPriceInfo() {
		// 인터페이스 arrayalist (수정필요) 넘어오는 변수**************
		interList = new ArrayList<>();
		interList.add(0);
		interList.add(20);
		interList.add(0);
		// 명수
		adultCnt = Integer.parseInt(interList.get(0).toString());
		childCnt = Integer.parseInt(interList.get(1).toString());
		oldCnt = Integer.parseInt(interList.get(2).toString());
		// 명수별 가격
		totalAdultPrice = (int) (price * adultCnt);
		totalChildPrice = (int) (price * 0.5 * childCnt);
		totalOldPrice = (int) (price * 0.8 * oldCnt);
		// 총명수
		peopleCnt = adultCnt + childCnt + oldCnt;
		// 할인 전 총 가격
		totalPrice = totalAdultPrice + totalChildPrice + totalOldPrice;
		//

	}

	void connectDB() {

	}

	void eventProc() {
		cbRating.addActionListener(this);
		cbGroup.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == cbRating) {
			setPriceByrating(cbRating.getSelectedItem().toString());
			tfTotal.setText(discountPrice + "");
		} else if (evt == cbGroup) {
			//cbRating.setSelectedItem(0);
			cbRating.setEnabled( !cbGroup.isSelected());
			tfTotal.setText(((int)(totalPrice*0.9))+"");
			
		}

	}

	// 등급에 따른 가격 정하는 메서드
	void setPriceByrating(String rating) {
		switch (rating) {
		case "그린":
			if (adultCnt > 1) {
				discountPrice = (int) (adultPrice * 2 * 0.9 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				discountPrice = (int) (adultPrice * adultCnt * 0.9 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;
		case "블루":
			if (adultCnt > 1) {
				discountPrice = (int) (adultPrice * 2 * 0.75 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				discountPrice = (int) (adultPrice * adultCnt * 0.75 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;
		case "골드":
			if (adultCnt > 1) {
				discountPrice = (int) (adultPrice * 2 * 0.6 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				discountPrice = (int) (adultPrice * adultCnt * 0.6 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;

		/*
		 * case "노블": discountPrice = (int) (totalPrice * 0.5); break;
		 */

		case "싹틔우미":
			if (adultCnt > 1) {
				discountPrice = (int) (adultPrice * 2 * 0.5 + adultPrice * (adultCnt - 2) + childPrice * childCnt
						+ oldPrice * oldCnt);
			} else {
				discountPrice = (int) (adultPrice * adultCnt * 0.5 + childPrice * childCnt + oldPrice * oldCnt);
			}
			break;
		default:
			discountPrice = totalPrice;
			break;
		}
	}

	// jtable 그리는 메서드
	void drawtable(ArrayList list) {
		priceModel.data = list;
		tbPriceInfo.setModel(priceModel);
		priceModel.fireTableDataChanged();
	}

	void addLayout() {
		laTitle = new JLabel("결제");
		bBack = new JButton("<뒤로");
		bPayment = new JButton("결제하기>");
		bCancel = new JButton("예매 취소");
		priceModel = new priceTableModel();
		tbPriceInfo = new JTable(priceModel);
		tbPriceInfo.setRowHeight(50);

		laRating = new JLabel("회원 등급(그린:10%,블루:25%,골드:40%,싹틔우미:50%,최대동반2인)");
		laPayMethod = new JLabel("결제 수단");
		laTotal = new JLabel("총 결제금액");
		tfTotal = new JTextField(15);
		tfTotal.setEditable(false);
		cbGroup = new JCheckBox("단체 여부(10% 할인,20인 이상)");
		if (peopleCnt >= 20) {
			cbGroup.setEnabled(true);
		} else {
			cbGroup.setEnabled(false);

		}

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
		p_center_north.add(new JTextArea(10,20));//행사 상세 정보 추가***
		p_center_north.add(new JScrollPane(tbPriceInfo), BorderLayout.NORTH);
		p_center.add(p_center_north, BorderLayout.NORTH);

		JPanel p_center_south = new JPanel();
		p_center_south.setBorder(new TitledBorder("결제 정보"));
		p_center_south.setLayout(new GridLayout(4, 2));
		p_center_south.add(new JLabel(""));
		p_center_south.add(cbGroup);
		p_center_south.add(laRating);
		p_center_south.add(cbRating);
		p_center_south.add(laPayMethod);
		p_center_south.add(cbPayMethod);
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
		this.setSize(800, 900);
		this.setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

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

//	public static void main(String[] args) {
//		ReceiptView view = new ReceiptView();
//	}

}
