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
import vo.Employee;



public class EmployeeView extends JPanel{
	String[] empDept = {"개발부", "기획부", "인사부", "판매부"};
	JComboBox cbEmpDept = new JComboBox<>(empDept);
	JLabel laEmpNo, laEmpName, laEmpTel, laEmpEmail, laEmpDept;
	JTextField tfEmpNo,tfEmpName, tfEmpTel, tfEmpEmail;
	JButton bSelectEmp, bInsertEmp, bModifyEmp, bDeleteEmp;
	
	JButton bHome, bClear;
	
	EmployeeModel model;
	ArtCenter ac;
	
	Font bfont, titlefont, lafont;
	

	
	public EmployeeView(ArtCenter ac) {
		this.ac = ac;
		addLayout();
		connectDB();
		eventProc();
		initStyle(); 
		
	}
	
	private void initStyle() {
		tfEmpNo.setEditable(false);
	}

	void addLayout() {
		bfont = new Font("포천 오성과 한음 Regular", Font.PLAIN, 20);
		titlefont = new Font("210 타임라인 R", Font.BOLD, 30);
		lafont = new Font("포천 오성과 한음 Regular", Font.PLAIN, 15);
		tfEmpNo = new JTextField(30);
		tfEmpName = new JTextField(30);
		tfEmpTel = new JTextField(30);
		tfEmpEmail = new JTextField(30);
		
		laEmpNo = new JLabel("직원번호", JLabel.CENTER);
		laEmpNo.setFont(lafont);
		laEmpName = new JLabel("이름", JLabel.CENTER);
		laEmpName.setFont(lafont);
		laEmpTel = new JLabel("전화번호", JLabel.CENTER);
		laEmpTel.setFont(lafont);
		laEmpEmail = new JLabel("이메일", JLabel.CENTER);
		laEmpEmail.setFont(lafont);
		laEmpDept = new JLabel("부서", JLabel.CENTER);
		laEmpDept.setFont(lafont);
		
		bSelectEmp = new JButton("조회");
		bSelectEmp.setFont(bfont);
		bInsertEmp = new JButton("입력");
		bInsertEmp.setFont(bfont);
		bModifyEmp = new JButton("수정");
		bModifyEmp.setFont(bfont);
		bDeleteEmp = new JButton("삭제");
		bDeleteEmp.setFont(bfont);
		
		bHome = new JButton("Home");
		bHome.setFont(lafont);
		bClear = new JButton("초기화");
		bClear.setFont(lafont);
	
//붙이기
		// title 패널
		JPanel ptitle = new JPanel();
		ptitle.setBorder(BorderFactory.createEmptyBorder(0,10,20,20));
		ptitle.setLayout(new BorderLayout());
		JPanel ptitle_b = new JPanel();
		ptitle_b.setLayout(new FlowLayout());
		ptitle_b.add(bClear);
		ptitle_b.add(bHome);
		ptitle.add(ptitle_b, "East");
		
		// textfield들 붙이는 패널 
		JPanel ptf_center = new JPanel();
		ptf_center.setBorder(BorderFactory.createEmptyBorder(0,10,20,100));
		ptf_center.setLayout(new GridLayout(5, 2, 15,15));
		
		// 그리드
		ptf_center.add(laEmpNo);
		ptf_center.add(tfEmpNo);
		ptf_center.add(laEmpDept);
		ptf_center.add(cbEmpDept);
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
		bClear.addActionListener(btnHandler);
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
				clear();
			} else if (o==bClear){
				clear();
			}
		
		}
		
	}

	

	
	
	
// 버튼 메소드들 
	
	//조회 -- 완성
	void selectEmp() {
		String tel = tfEmpTel.getText();
		
		try {
			Employee vo = model.selectEmp(tel);
			tfEmpNo.setText(String.valueOf(vo.getEmpNo()));
			tfEmpName.setText(vo.getEmpName());
			tfEmpTel.setText(vo.getEmpTel());
			tfEmpEmail.setText(vo.getEmpEmail());
			cbEmpDept.setSelectedItem(vo.getEmpDept());
		} catch (Exception e) {
			System.out.println("사원조회 실패");
			e.printStackTrace();
		}
		
		
		
	}
	//입력 -- 완성
	void insertEmp() {
		Employee vo = new Employee();
		
		vo.setEmpName(tfEmpName.getText());
		vo.setEmpTel(tfEmpTel.getText());
		vo.setEmpEmail(tfEmpEmail.getText());
		vo.setEmpDept(String.valueOf(cbEmpDept.getSelectedItem()));
		try {
			model.insertEmp(vo);
			JOptionPane.showMessageDialog(null, "사원입력성공");
			clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "사원입력실패:" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

	//수정 -- 완성
	void modifyEmp() {
		Employee vo = new Employee();
		vo.setEmpNo(Integer.parseInt(tfEmpNo.getText()));
		vo.setEmpName(tfEmpName.getText());
		vo.setEmpTel(tfEmpTel.getText());
		vo.setEmpEmail(tfEmpEmail.getText());
		vo.setEmpDept(String.valueOf(cbEmpDept.getSelectedItem()));
		try {
			model.modifyEmp(vo);
			JOptionPane.showMessageDialog(null, "수정완료");
			clear();
		} catch (Exception e) {
			System.out.println("사원수정성공");
			e.printStackTrace();
		}
	}
	//삭제 -- 완성
	void deleteEmp() {
		Employee vo = new Employee();
		vo.setEmpNo(Integer.parseInt(tfEmpNo.getText()));
		vo.setEmpName(tfEmpName.getText());
		vo.setEmpTel(tfEmpTel.getText());
		vo.setEmpEmail(tfEmpEmail.getText());
		vo.setEmpDept(String.valueOf(cbEmpDept.getSelectedItem()));
		try {
			model.deleteEmp(vo);
			JOptionPane.showMessageDialog(null, "삭제성공");
			clear();
		} catch (Exception e) {
			System.out.println("사원삭제실패");
			e.printStackTrace();
		}
	}

	
//초기화
	void clear() {
		tfEmpNo.setText(null);
		tfEmpName.setText(null);
		tfEmpTel.setText(null);
		tfEmpEmail.setText(null);
		cbEmpDept.setSelectedIndex(0);
	}
	
	
}
