package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.ArtCenterView;
import view.EmployeeView;
import view.EventView;
import view.LoginView;
import view.ReceiptView;
import view.SeatView;
import view.TicketView;

public class ArtCenter {

	LoginView login;
	ArtCenterView artCenter;
	TicketView ticket;
	EmployeeView employee;
	EventView event;
	SeatView seat;
	ReceiptView receipt;
	CardLayout card;

	JButton btn = new JButton("확인");
	JPanel background = new JPanel();
	public ArrayList tempList, temp = null;

	JFrame main;

	public ArtCenter() {

		main = new JFrame("ArtCenter");
		artCenter = new ArtCenterView(this);
		ticket = new TicketView(this);
		employee = new EmployeeView(this);
		event = new EventView(this);
		seat = new SeatView(this);
		receipt = new ReceiptView(this);
		login = new LoginView(this);
		tempList = new ArrayList<>();
		temp = new ArrayList<>();
		card = new CardLayout(15, 10);
		background.setLayout(card);
		background.add("logincard", login);
		background.add("main", artCenter);
		background.add("ticketcard", ticket);
		background.add("employeecard", employee);
		background.add("eventcard", event);
		background.add("seatcard", seat);
		background.add("receiptcard", receipt);
		main.add(background);
		JFrame.setDefaultLookAndFeelDecorated(true);
		main.setVisible(true);

		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void movecard(String text) {
		card.show(background, text);
	}

	public void changeFrame(int width, int height) {
		main.setSize(width, height);
	}

	public void originalFrame() {
		main.setSize(900, 950);
	}

	public static void main(String[] args) {

		try {
			// Set System L&F
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			new ArtCenter();
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception

			System.out.println(e.getMessage());
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			// handle exception
			System.out.println(e.getMessage());
			e.getStackTrace();
		} catch (InstantiationException e) {
			// handle exception
			System.out.println(e.getMessage());
			e.getStackTrace();
		} catch (IllegalAccessException e) {
			// handle exception
			System.out.println(e.getMessage());
			e.getStackTrace();
		}

	}

	/**
	 * setTempSql메서드로 ArrayList 가져오기
	 * 
	 */
	public void setTempSql(ArrayList temp) {
		tempList.clear();
		tempList = temp;
		if (tempList.get(6).equals("e")) {
			receipt.setTempList(tempList);
		} else if (tempList.get(8).equals("p")) {
			seat.setTempList(tempList);
			receipt.setTempList(tempList);
		}
	}

	//
	public void setPeopleSeat(ArrayList itemp) {
		temp.clear();
		temp = itemp;
		// System.out.println(temp.get(4));
		if (temp.get(4).equals("p")) { // 공연이면
			temp.remove(4);
			seat.setPersonCnt(temp);
			receipt.settempList(temp);
		} else if (temp.get(4).equals("e")) { // 전시면
			temp.remove(4);
			receipt.settempList(temp);
		}
	}

	// 직원DB에서 부서 이름을 가져오는 메서드
	public void empInfoSending(ArrayList<String> info) { // 0:empno , 1:
															// empname,
															// 2:deptname
		String dept = info.get(2);
		artCenter.setbtn(dept);
		if (dept.equals("개발부") || dept.equals("판매부")) {
			int empno = Integer.parseInt(info.get(0));
			receipt.empNo = empno;
		}
	}

	public void goingHome() {
		ticket.tfAdult.setText("0");
		ticket.tfAdv.setText("0");
		ticket.tfChild.setText("0");
		ticket.settotal();
	}

	public ReceiptView getReceiptView() {
		return receipt;
	}
}
