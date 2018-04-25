package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ArtCenter;

public class ArtCenterView extends JPanel implements ActionListener {

	private Font font2 = new Font("포천 오성과 한음 Regular", Font.PLAIN, 20);
	JButton bSalesMgr, bEmpMgr, bEvtMgr, bLogOut;
	ButtonGroup bg = new ButtonGroup();
	JLabel laArt = new JLabel(new ImageIcon("src\\image\\newtitle.png"));
	ArtCenter ac;
	Font bfont = new Font("포천 오성과 한음 Regular", Font.PLAIN, 20);

	public ArtCenterView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
	}

	/**
	 * 이벤트 등록 메서드
	 */
	void eventProc() {

		bSalesMgr.addActionListener(this);
		bEmpMgr.addActionListener(this);
		bEvtMgr.addActionListener(this);
		bLogOut.addActionListener(this);
	}

	/**
	 * 이벤트 효과
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ev = e.getSource();
		if (ev == bSalesMgr) {
			ac.movecard("ticketcard");
		} else if (ev == bEmpMgr) {
			ac.movecard("employeecard");
			ac.changeFrame(800, 800);
		} else if (ev == bEvtMgr) {
			ac.movecard("eventcard");
		} else if (ev == bLogOut) {
			ac.movecard("logincard");
			ac.changeFrame(500, 150);
		}

	}

	/**
	 * 역할 :레이아웃 등록 메서드
	 * 
	 */
	void addLayout() {

		bSalesMgr = new JButton("판매관리");
		bSalesMgr.setFont(bfont);
		bEmpMgr = new JButton("사원관리");
		bEmpMgr.setFont(bfont);
		bEvtMgr = new JButton("행사관리");
		bEvtMgr.setFont(bfont);
		laArt.setFont(font2);
		laArt.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		// 회원가입 부분 붙이기
		// ( 그 복잡하다던 GridBagLayout을 사용해서 복잡해 보임..다른 쉬운것으로...대치 가능 )
		JPanel center = new JPanel();
		center.setLayout(new GridBagLayout());
		GridBagConstraints cbc = new GridBagConstraints();
		cbc.weightx = 1.0;
		cbc.weighty = 1.0;
		cbc.fill = GridBagConstraints.BOTH;
		cbc.gridx = 0;
		cbc.gridy = 0;
		cbc.gridwidth = 1;
		cbc.gridheight = 6;
		center.add(new JPanel(), cbc);
		cbc.gridx = 2;
		cbc.gridy = 0;
		cbc.gridwidth = 1;
		cbc.gridheight = 6;
		center.add(new JPanel(), cbc);
		cbc.gridx = 1;
		cbc.gridy = 0;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(new JPanel(), cbc);
		cbc.gridx = 1;
		cbc.gridy = 1;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(laArt, cbc);
		cbc.gridx = 1;
		cbc.gridy = 2;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(new JPanel(), cbc);
		cbc.gridx = 1;
		cbc.gridy = 3;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(bSalesMgr, cbc);
		cbc.gridx = 1;
		cbc.gridy = 4;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(new JPanel(), cbc);
		cbc.gridx = 1;
		cbc.gridy = 5;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(bEmpMgr, cbc);
		cbc.gridx = 1;
		cbc.gridy = 6;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(new JPanel(), cbc);
		cbc.gridx = 1;
		cbc.gridy = 7;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(bEvtMgr, cbc);
		cbc.gridx = 1;
		cbc.gridy = 8;
		cbc.gridwidth = 1;
		cbc.gridheight = 1;
		center.add(new JPanel(), cbc);
		// 전체 패널에 붙이기
		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.NORTH);
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		south.setLayout(new BorderLayout());
		south.add((bLogOut = new JButton("로그아웃")), BorderLayout.EAST);

	}

	void connectDB() {

	}

	public void setbtn(String dept) { // 들어오는 아이디의 부서에 따라 보여주는 창이 다름
		if (dept.equals("개발부")) {
			bSalesMgr.setEnabled(true);
			bSalesMgr.setVisible(true);
			bEvtMgr.setEnabled(true);
			bEvtMgr.setVisible(true);
			bEmpMgr.setEnabled(true);
			bEmpMgr.setVisible(true);
		} else if (dept.equals("판매부")) {
			bSalesMgr.setEnabled(true);
			bSalesMgr.setVisible(true);
			bEvtMgr.setEnabled(false);
			bEvtMgr.setVisible(false);
			bEmpMgr.setEnabled(false);
			bEmpMgr.setVisible(false);
		} else if (dept.equals("인사부")) {
			bSalesMgr.setEnabled(false);
			bSalesMgr.setVisible(false);
			bEvtMgr.setEnabled(false);
			bEvtMgr.setVisible(false);
			bEmpMgr.setEnabled(true);
			bEmpMgr.setVisible(true);
		} else if (dept.equals("기획부")) {
			bSalesMgr.setEnabled(false);
			bSalesMgr.setVisible(false);
			bEvtMgr.setEnabled(true);
			bEvtMgr.setVisible(true);
			bEmpMgr.setEnabled(false);
			bEmpMgr.setVisible(false);

		}

	}

}
