package main;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.ArtCenterView;
import view.EmployeeView;
import view.EventView;
import view.ReceiptView;
import view.SeatView;
import view.TicketExhibitionView;
import view.TicketPerformanceView;

public class ArtCenter  {

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
	JFrame main;
	public ArtCenter() {

		
		main = new JFrame("ArtCenter");
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
		main.add(background);
		JFrame.setDefaultLookAndFeelDecorated(true);
		main.setSize(900, 950);
		main.setVisible(true);

		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void movecard(String text) {
		System.out.println("받아따");
		card.show(background, text);
	}

	public void changeFrame() {
		main.setSize(800, 800);
	}

	public void originalFrame() {
		main.setSize(900, 950);
	}

	public static void main(String[] args) {
		
//		[출처] [Swing] Look and Feel : basic|작성자 신찬


		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        new ArtCenter();
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    	
	    	System.out.println(e.getMessage());
	    	e.getStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    	System.out.println(e.getMessage());
	    	e.getStackTrace();
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    	System.out.println(e.getMessage());
	    	e.getStackTrace();
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    	System.out.println(e.getMessage());
	    	e.getStackTrace();
	    }

	
		   
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
		tempList.clear();
		tempList = temp;
		// System.out.println(tempList.isEmpty());
		// receipt.settempList(tempList);
		// receipt.setTemp(tempList);
	}

	public ArrayList getTemp() {
		
		return temp;
	}

	public void setTemp(ArrayList itemp) {
		temp.clear();
		temp = itemp;
		receipt.settempList(temp);

	}

}
