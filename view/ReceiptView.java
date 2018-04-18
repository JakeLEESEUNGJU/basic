package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.ArtCenter;

public class ReceiptView extends JPanel implements ActionListener {
	JLabel laTitle, laRating, laPayMethod, laTotal;
	JTextField tfTotal;
	JComboBox cbRating, cbPayMethod;
	JButton bPayment, bCancel, bBack;

	JTable priceTable;
	priceTableModel priceModel;
	ArtCenter ac;

	public ReceiptView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}

	void connectDB() {

	}

	void eventProc() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	void addLayout() {
		laTitle = new JLabel("결제");
		bBack = new JButton("<뒤로");
		bPayment = new JButton("결제하기");
		bCancel = new JButton("예매 취소");
		priceModel = new priceTableModel();
		priceTable = new JTable(priceModel);

		laRating = new JLabel("회원 등급");
		laPayMethod = new JLabel("결제 수단");
		laTotal = new JLabel("총 결제금액");
		tfTotal = new JTextField(15);
		tfTotal.setEditable(false);

		cbRating = new JComboBox();
		cbRating.addItem("==선택==");
		cbRating.addItem("해당없음");
		cbRating.addItem("그린");
		cbRating.addItem("블루");
		cbRating.addItem("골드");
		cbRating.addItem("노블");
		cbRating.addItem("싹틔우미");

		cbPayMethod = new JComboBox();
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
		p_center_north.setBorder(new TitledBorder("티켓 가격정보"));
		p_center_north.add(new JScrollPane(priceTable), BorderLayout.CENTER);
		p_center.add(p_center_north, BorderLayout.NORTH);

		JPanel p_center_south = new JPanel();
		p_center_south.setBorder(new TitledBorder("결제 수단"));
		p_center_south.setLayout(new GridLayout(3, 2));
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
		;
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_south, BorderLayout.SOUTH);
		this.add(p_center, BorderLayout.CENTER);
		this.setSize(800, 900);
		this.setVisible(true);

	}

	class priceTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "구분", "인원", "가격" };

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

	// public static void main(String[] args) {
	// ReceiptView v = new ReceiptView();
	// }

}
