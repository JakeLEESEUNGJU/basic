package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ArtCenterView;
import view.TicketExhibitionView;
import view.TicketPerformanceView;


public class ArtCenter extends JFrame{
	
	
	ArtCenterView artCenter;
	TicketExhibitionView ticketExhibition;
	TicketPerformanceView ticketPerformance;
	CardLayout card;
	JButton btn = new JButton("확인");
	JPanel background = new JPanel();
	
	public ArtCenter(){
		
		artCenter = new ArtCenterView(this);
		ticketExhibition = new TicketExhibitionView(this);
		ticketPerformance = new TicketPerformanceView();
		
		card = new CardLayout();
		background.setLayout( card );
		background.add("main", artCenter );
		background.add("exhibitioncard", ticketExhibition );
		background.add("performancecard", ticketPerformance );
		add(background);
		
		
		setSize( 800, 900 );
		setVisible( true );
		
	
		
		
		
	}

	public void movecard(String text){
		System.out.println("받아따");
		card.show(background,text);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new ArtCenter();
	}

}
