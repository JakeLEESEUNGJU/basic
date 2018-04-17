package vo;

public class Employee {

	private int empNo; // 사번
	private String empName; // 사원명
	private String empTel;// 사원전화번호
	private String empEmail;// 사원 이메일

	public Employee() {
		super();
	}

	public Employee(int empNo, String empName, String empTel, String empEmail) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empTel = empTel;
		this.empEmail = empEmail;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

}
