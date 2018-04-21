package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ArtCenterView;
import view.EmployeeView;
import view.EventView;
import view.ReceiptView;
import view.SeatView;
import view.TicketExhibitionView;
import view.TicketPerformanceView;

public class ArtCenter extends JFrame {

	ArtCenterView artCenter;
	TicketExhibitionView ticketExhibition;
	TicketPerformanceView ticketPerformance;
	EmployeeView employee;
	EventView event;
	SeatView seat;
	ReceiptView receipt;
	CardLayout card;
	JButton btn = new JButton("확인");
	JPanel background = new JPanel();
	public ArrayList tempList, temp = null;

	public ArtCenter() {

		artCenter = new ArtCenterView(this);
		ticketExhibition = new TicketExhibitionView(this);
		employee = new EmployeeView(this);
		event = new EventView(this);
		seat = new SeatView(this);
		receipt = new ReceiptView(this);
		tempList = new ArrayList<>();
		temp = new ArrayList<>();
		card = new CardLayout();
		background.setLayout(card);
		background.add("main", artCenter);
		background.add("exhibitioncard", ticketExhibition);
		background.add("employeecard", employee);
		background.add("eventcard", event);
		background.add("seatcard", seat);
		background.add("receiptcard", receipt);
		add(background);
		setSize(800, 900);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void movecard(String text) {
		System.out.println("받아따");
		card.show(background, text);
	}

	public void changeFrame() {
		this.setSize(700, 800);
	}

	public void originalFrame() {
		this.setSize(800, 900);
	}

	public static void main(String[] args) {
		new ArtCenter();
	}

	public ArrayList getTempList() {
		return tempList;
	}

	/**
	 * settemp메서드로 ArrayList 가져오기
	 * 
	 * @param temp
	 */
	public void setTempList(ArrayList temp) {
		tempList = temp;
		// System.out.println(tempList.isEmpty());
		// receipt.settempList(tempList);
		// receipt.setTemp(tempList);
	}

	public ArrayList getTemp() {
		return temp;
	}

	public void setTemp(ArrayList itemp) {
		temp = itemp;
		receipt.settempList(temp);

	}

}
