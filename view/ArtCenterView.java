package view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ArtCenter;


public class ArtCenterView extends JPanel implements ActionListener{
	
	private Font font2 = new Font("Serif", Font.BOLD, 40) ;
	JButton		bSalesMgr, bEmpMgr ,bEvtMgr;
	ButtonGroup bg= new ButtonGroup();
	JLabel laArt = new JLabel("예술의 전당");
	ArtCenter ac;
	
	
	
	public ArtCenterView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}

	/**
	 *  이벤트 등록 메서드
	 */
	void eventProc() {

		bSalesMgr.addActionListener(this);
		bEmpMgr.addActionListener(this);
		bEvtMgr.addActionListener(this);
		
	}
	/**
	 * 이벤트 효과 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ev = e.getSource();
		if (ev == bSalesMgr) {
			ac.movecard("exhibitioncard");
		} else if (ev == bEmpMgr) {
			ac.movecard("employeecard");
			ac.changeFrame();
		} else if (ev == bEvtMgr) {
			ac.movecard("eventcard");
		}

		
	}
	/**
	 *  역할 :레이아웃 등록 메서드
	 *  
	 */
	void addLayout() {
		



		bSalesMgr			= new JButton("판매관리");
		bEmpMgr			= new JButton("사원관리");
		bEvtMgr		= new JButton("행사관리");
		laArt.setFont(font2);
		laArt.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		// 회원가입 부분 붙이기 
		//		( 그 복잡하다던 GridBagLayout을 사용해서 복잡해 보임..다른 쉬운것으로...대치 가능 )
		JPanel			center		= new JPanel();
		center.setLayout( new GridBagLayout() );
					GridBagConstraints	cbc = new GridBagConstraints();
			cbc.weightx	= 1.0;
			cbc.weighty	 = 1.0;
			cbc.fill				= GridBagConstraints.BOTH;
		cbc.gridx	=	0;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 6;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	2;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 6;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  1;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add( laArt ,	cbc );
		cbc.gridx	=	1;	 			cbc.gridy	=  2;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  3;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(bSalesMgr, cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  4;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  5;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(bEmpMgr, cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  6;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(new JPanel(),cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  7;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(bEvtMgr, cbc);
		cbc.gridx	=	1;	 			cbc.gridy	=  8;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
		center.add(new JPanel(),cbc);
		// 전체 패널에 붙이기
		setLayout( new BorderLayout() );
		add(center,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.NORTH);
		add(new JPanel(),BorderLayout.SOUTH);
		
		
		
	}

	void connectDB() {

	}

	
}
