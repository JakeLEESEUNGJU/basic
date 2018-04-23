package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ArtCenter;
import model.EmployeeModel;

public class LoginView extends JPanel implements ActionListener{
	
	ArtCenter ac;
	EmployeeModel model;
	JLabel laID,laPW;
	JButton bOk,bReset;
	JTextField tfLog, tfPW;
	
	
	public LoginView(){
		super();
	}
	
	public LoginView(ArtCenter ac){
		this.ac= ac;
		addLayout();
		connectDB();
		eventProc();
		ac.changeFrame(400, 100);
		
	}
	
	void addLayout() {
		laID = new JLabel("사용자 이메일",JLabel.CENTER);
		laPW = new JLabel("비 밀 번 호",JLabel.CENTER);
		bOk  = new JButton("로그인");
		bReset  = new JButton("재입력");
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 3));
		center.add(laID);
		center.add(tfLog = new JTextField());
		center.add(bOk);
		center.add(laPW);
		center.add(tfPW = new JTextField());
		center.add(bReset);
		
		
		
		
		
		add(center);
		
	}
	void connectDB() {
		try {
			model = new EmployeeModel();
					System.out.println("로그인 창 (DB 연결 성공)");
		} catch (Exception e) {
			System.out.println("로그인 창 DB 연결 실패");
			e.printStackTrace();
		}
	}
	void eventProc() {
		bOk.addActionListener(this);
		bReset.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ev= (Object)e.getSource();
		
		if( ev == bOk || ev == tfLog || ev == tfPW){
			if(tfLog.getText().equals("")||tfPW.getText().equals("")){
				JOptionPane.showMessageDialog(null, "아이디 혹은 이름이 없습니다.");
			}else{
				
				login(tfLog.getText(),tfPW.getText());
				
				
								
			}
		}else if(ev == bReset){
			tfLog.setText("");
			tfPW.setText("");
		}
	}

	void login(String ID, String PW) {
		try{
			ArrayList<String> temp = new ArrayList<String>();
			temp = model.loginDB(ID,PW); // 0 = empno , 1= empname , 2 = empdept
			JOptionPane.showMessageDialog(null, temp.get(1)+"님 접속("+temp.get(2)+")");
			
			tfLog.setText("");
			tfPW.setText("");
			ac.empInfoSending(temp);
			ac.changeFrame(900, 950);
			ac.movecard("main");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "로그인 실패" + e.getMessage());
		}
	}
		
	
}
	


