package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.ArtCenter;
import model.EmployeeModel;



public class EmployeeView extends JPanel{

	JLabel laEmpNo, laEmpName, laEmpTel, laEmpEmail;
	JTextField tfEmpNo,tfEmpName, tfEmpTel, tfEmpEmail;
	JButton bSelectEmp, bInsertEmp, bModifyEmp, bDeleteEmp;
	
	JButton bHome;
	
	EmployeeModel model;
	ArtCenter ac;
	
	Font bfont, titlefont, lafont;
	

	
	public EmployeeView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
		
	}
	
	void addLayout() {
		bfont = new Font("맑은고딕", Font.BOLD, 20);
		titlefont = new Font("Serif", Font.BOLD, 40);
		
		tfEmpNo = new JTextField(30);
		tfEmpName = new JTextField(30);
		tfEmpTel = new JTextField(30);
		tfEmpEmail = new JTextField(30);
		
		laEmpNo = new JLabel("직원번호", JLabel.CENTER);
		laEmpName = new JLabel("이름", JLabel.CENTER);
		laEmpTel = new JLabel("전화번호", JLabel.CENTER);
		laEmpEmail = new JLabel("이메일", JLabel.CENTER);
		
		bSelectEmp = new JButton("조회");
		bSelectEmp.setFont(bfont);
		bInsertEmp = new JButton("입력");
		bInsertEmp.setFont(bfont);
		bModifyEmp = new JButton("수정");
		bModifyEmp.setFont(bfont);
		bDeleteEmp = new JButton("삭제");
		bDeleteEmp.setFont(bfont);
		
		bHome = new JButton("Home");
		bHome.setSize(6,6);
		
/**	
		JPanel pAll = new JPanel();
		pAll.setLayout(new GridBagLayout());
		pAll.setBorder(new TitledBorder("사원관리"));
		GridBagConstraints g = new GridBagConstraints();
		g.weightx = 0.5;
		g.weighty = 0.5;
		g.insets = new Insets(5, 5, 5, 5);
		g.fill = GridBagConstraints.BOTH;
		
		g.gridx = 0;			g.gridy = 0;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(new JLabel(""), g);
		g.gridx = 1;			g.gridy = 0;		g.gridwidth = 1;		g.gridheight = 1;		
//		pAll.add(laTitle, g);
		g.gridx = 2;			g.gridy = 0;		g.gridwidth = 2;		g.gridheight = 1;	
		pAll.add(new JLabel(""), g);
		g.gridx = 4;			g.gridy = 0;		g.gridwidth = 1;		g.gridheight = 1;	
		pAll.add(bHome, g);
		g.gridx = 5;			g.gridy = 0;		g.gridwidth = 6;		g.gridheight = 1;		
		pAll.add(new JLabel(""), g);
		
		g.gridx = 0;			g.gridy = 1;		g.gridwidth = 6;		g.gridheight = 1;		
		pAll.add(new JLabel(""), g);
		g.gridx = 1;			g.gridy = 2;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(laEmpNo, g);
		g.gridx = 2;			g.gridy = 2;		g.gridwidth = 3;		g.gridheight = 1;		
		pAll.add(tfEmpNo, g);
		
		g.gridx = 1;			g.gridy = 3;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(laEmpName, g);
		g.gridx = 2;			g.gridy = 3;		g.gridwidth = 3;		g.gridheight = 1;		
		pAll.add(tfEmpName, g);
		
		g.gridx = 1;			g.gridy = 4;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(laEmpTel, g);
		g.gridx = 2;			g.gridy = 4;		g.gridwidth = 3;		g.gridheight = 1;		
		pAll.add(tfEmpTel, g);
		
		g.gridx = 1;			g.gridy = 5;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(laEmpEmail, g);
		g.gridx = 2;			g.gridy = 5;		g.gridwidth = 3;		g.gridheight = 1;		
		pAll.add(tfEmpEmail, g);
		
		g.gridx = 1;			g.gridy = 6;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(bSelectEmp, g);
		g.gridx = 2;			g.gridy = 6;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(bInsertEmp, g);
		g.gridx = 3;			g.gridy = 6;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(bModifyEmp, g);
		g.gridx = 4;			g.gridy = 6;		g.gridwidth = 1;		g.gridheight = 1;		
		pAll.add(bDeleteEmp, g);
		
		g.gridx = 0;			g.gridy = 2;		g.gridwidth = 1;		g.gridheight = 6;		
		pAll.add(new JLabel(""), g);
		
		
		g.gridx = 5;			g.gridy = 2;		g.gridwidth = 1;		g.gridheight = 6;
		pAll.add(new JLabel(""), g);
		
		
		g.gridx = 1;			g.gridy = 7;		g.gridwidth = 4;		g.gridheight = 1;
		pAll.add(new JLabel(""), g);
		
		// 전체 패널에 붙이기
		setLayout(new BorderLayout());
		add(pAll);

*/
		
		

	
//붙이기
		// title 패널
		JPanel ptitle = new JPanel();
		ptitle.setBorder(BorderFactory.createEmptyBorder(0,10,20,20));
		ptitle.setLayout(new BorderLayout());
		ptitle.add(bHome, "East");
		
		// textfield들 붙이는 패널 
		JPanel ptf_center = new JPanel();
		ptf_center.setBorder(BorderFactory.createEmptyBorder(0,10,20,100));
		ptf_center.setLayout(new GridLayout(4, 1, 15,15));
		
		/* 그리드 아님
		JPanel pEmpNo = new JPanel();
		pEmpNo.add(laEmpNo);
		pEmpNo.add(tfEmpNo);
		JPanel pEmpName = new JPanel();
		pEmpName.add(laEmpName);
		pEmpName.add(tfEmpName);
		JPanel pEmpTel = new JPanel();
		pEmpTel.add(laEmpTel);
		pEmpTel.add(tfEmpTel);
		JPanel pEmpEmail = new JPanel();
		pEmpEmail.add(laEmpEmail);
		pEmpEmail.add(tfEmpEmail);
		
		ptf_center.add(pEmpNo);
		ptf_center.add(pEmpName);
		ptf_center.add(pEmpTel);
		ptf_center.add(pEmpEmail);
		*/
		
		// 그리드
		ptf_center.add(laEmpNo);
		ptf_center.add(tfEmpNo);
		ptf_center.add(laEmpName);
		ptf_center.add(tfEmpName);
		ptf_center.add(laEmpTel);
		ptf_center.add(tfEmpTel);
		ptf_center.add(laEmpEmail);
		ptf_center.add(tfEmpEmail);
		
		JPanel ptf = new JPanel();
		ptf.setLayout(new BorderLayout());
		ptf.add(ptf_center, "Center");
		ptf.add(new JPanel(), "West");
		ptf.add(new JPanel(), "East");
		
		
		// button 붙이는 패널
		JPanel pb = new JPanel();
		pb.setLayout(new GridLayout(1, 4));
		pb.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10)); 

		pb.add(bSelectEmp);
		pb.add(bInsertEmp);
		pb.add(bModifyEmp);
		pb.add(bDeleteEmp);
		
		
		// 전체 패널에 붙이기
		JPanel pAll = new JPanel();
		pAll.setLayout(new BorderLayout());
		pAll.add(ptitle, "North");
		pAll.add(ptf, "Center");
		pAll.add(pb, "South");
		
		setLayout(new BorderLayout());
		TitledBorder tb = new TitledBorder("사원관리");
		tb.setTitleFont(titlefont);
		setBorder(tb);
		add("West", new JLabel(""));
		
		add("Center", pAll);

	}

	void connectDB() {
		try {
			model = new EmployeeModel();
			System.out.println("사원관리 DB 연결 성공");
		} catch (Exception e) {
			System.out.println("사원관리 DB 연결 ");
			e.printStackTrace();
		}
	}

	void eventProc() {
		ButtonEventHandler btnHandler = new ButtonEventHandler();
		
		//이벤트 등록
		bSelectEmp.addActionListener(btnHandler);
		bInsertEmp.addActionListener(btnHandler);
		bModifyEmp.addActionListener(btnHandler);
		bDeleteEmp.addActionListener(btnHandler);
		bHome.addActionListener(btnHandler);
	}
	
	//버튼 이벤트 핸들러 클래스 
	class ButtonEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			if(o==bSelectEmp){
				selectEmp();
			} else if (o==bInsertEmp){
				insertEmp();
			} else if (o==bModifyEmp){
				modifyEmp();
			} else if (o==bDeleteEmp){
				deleteEmp();
			} else if (o==bHome){
				ac.movecard("main");
				ac.originalFrame();
			}
		
		}
		
	}

	

	
	
	
// 버튼 메소드들 
	
	//조회
	void selectEmp() {

	}
	//입력
	void insertEmp() {

	}
	
	//수정
	void modifyEmp() {

	}
	//삭제
	void deleteEmp() {

	}

	
//초기화
	void clear() {

	}
	
	
}
