package vo;

public class Employee {

	private int empNo; // 사번
	private String empName; // 사원명
	private String empTel;// 사원전화번호
	private String empEmail;// 사원 이메일
	private String empDept; // 사원 부서
	private String empPw; //사원 비밀번호
	
	public Employee() {
		super();
	}

	public Employee(int empNo, String empName, String empTel, String empEmail, String empDept, String empPw) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empTel = empTel;
		this.empEmail = empEmail;
		this.empDept = empDept;
		this.empPw = empPw;
	}

	public String getEmpPw() {
		return empPw;
	}

	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
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
