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
		
		artCenter = new ArtCenterView();
		ticketExhibition = new TicketExhibitionView();
		ticketPerformance = new TicketPerformanceView();
		
		card = new CardLayout();
		background.setLayout( card );
		background.add("main", artCenter );
		background.add("exhibitionscreen", ticketExhibition );
		background.add("performancescreen", ticketPerformance );
		add(background);
		
		
		setSize( 300, 400 );
		setVisible( true );
		
	
		btn.addActionListener( new ActionListener() 
		{public void actionPerformed( ActionEvent ev )
			{card.show(background , "exhibitionscreen");
				
			}
		}
							);
	}


	
	
	
	
	
	
	public static void main(String[] args) {
		new ArtCenter();
	}

}
