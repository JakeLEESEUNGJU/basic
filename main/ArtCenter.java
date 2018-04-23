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
import view.TicketExhibitionView;
import view.TicketPerformanceView;

public class ArtCenter  {

	
	LoginView login;
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
		login = new LoginView(this);
		tempList = new ArrayList<>();
		temp = new ArrayList<>();
		card = new CardLayout(15,10);
		background.setLayout(card);
		background.add("logincard", login);
		background.add("main", artCenter);
		background.add("exhibitioncard", ticketExhibition);
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
		System.out.println("받아따");
		card.show(background, text);
	}

	public void changeFrame(int width , int height) {
		main.setSize(width	, height);
	}

	public void originalFrame() {
		main.setSize(900, 950);
	}

	public static void main(String[] args) {
		


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

		seat.setTempList(tempList);
	}

	public ArrayList getTemp() {
		
		return temp;
	}

	public void setTemp(ArrayList itemp) {
		temp.clear();
		temp = itemp;
		receipt.settempList(temp);
		seat.setPersonCnt(temp);

	}
	//직원DB에서 부서 이름을 가져오는 메서드
	public void empInfoSending(ArrayList<String> info) {
		String dept = info.get(2);
		artCenter.setbtn(dept);
	}

}
