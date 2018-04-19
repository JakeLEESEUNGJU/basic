package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ArtCenterView;
import view.EmployeeView;
import view.EventView;
import view.TicketExhibitionView;
import view.TicketPerformanceView;


public class ArtCenter extends JFrame{
	
	
	ArtCenterView artCenter;
	TicketExhibitionView ticketExhibition;
	TicketPerformanceView ticketPerformance;
	EmployeeView employee;
    EventView event;
	CardLayout card;
	JButton btn = new JButton("확인");
	JPanel background = new JPanel();
	
	
	public ArtCenter(){
		
		artCenter = new ArtCenterView(this);
		ticketExhibition = new TicketExhibitionView(this);
		ticketPerformance = new TicketPerformanceView();
		employee = new EmployeeView(this);
        event = new EventView(this);
        
		card = new CardLayout();
		background.setLayout( card );
		background.add("main", artCenter );
		background.add("exhibitioncard", ticketExhibition );
		background.add("performancecard", ticketPerformance );
		background.add("employeecard", employee);
        background.add("eventcard", event);
		add(background);
		
		
		setSize( 800, 900 );
		setVisible( true );
	
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
		
		
	}

	public void movecard(String text){
		System.out.println("받아따");
		card.show(background,text);
	}
	
	public void changeFrame(){
    	this.setSize(700, 800);
    }
    
    public void originalFrame(){
    	this.setSize(800, 900);
    }
	
	
	
	
	
	public static void main(String[] args) {
		new ArtCenter();
	}

}
