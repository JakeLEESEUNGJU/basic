package vo;

public class Receipt {

	private int recNo;			//예매번호
	private int exiNo;			//전시코드
	private int perNo;			//공연코드
	private int empNo;			//직원번호
	private String recDate;		//예매일시
	private String seeDate;		//관람일시
	private String startTime; 	//시작시간
	private String finishTime; //종료시간
	private String recSeat;		//예매한 좌석
	private String recMethod;	//결제수단
	private int adultCnt;		//성인인원
	private int childCnt;		//아동인원
	private int advCnt;			//우대인원
	private int recPrice;		//금액
	
	
	public Receipt() {
		super();
	}


	public Receipt(int recNo, int exiNo, int perNo, int empNo, String recDate, String seeDate, String startTime,
			String finishTime, String recSeat, String recMethod, int adultCnt, int childCnt, int oldCnt, int recPrice) {
		super();
		this.recNo = recNo;
		this.exiNo = exiNo;
		this.perNo = perNo;
		this.empNo = empNo;
		this.recDate = recDate;
		this.seeDate = seeDate;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.recSeat = recSeat;
		this.recMethod = recMethod;
		this.adultCnt = adultCnt;
		this.childCnt = childCnt;
		this.advCnt = oldCnt;
		this.recPrice = recPrice;
	}


	public int getRecNo() {
		return recNo;
	}


	public void setRecNo(int recNo) {
		this.recNo = recNo;
	}


	public int getExiNo() {
		return exiNo;
	}


	public void setExiNo(int exiNo) {
		this.exiNo = exiNo;
	}


	public int getPerNo() {
		return perNo;
	}


	public void setPerNo(int perNo) {
		this.perNo = perNo;
	}


	public int getEmpNo() {
		return empNo;
	}


	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}


	public String getRecDate() {
		return recDate;
	}


	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}


	public String getSeeDate() {
		return seeDate;
	}


	public void setSeeDate(String seeDate) {
		this.seeDate = seeDate;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getFinishTime() {
		return finishTime;
	}


	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}


	public String getRecSeat() {
		return recSeat;
	}


	public void setRecSeat(String recSeat) {
		this.recSeat = recSeat;
	}


	public String getRecMethod() {
		return recMethod;
	}


	public void setRecMethod(String recMethod) {
		this.recMethod = recMethod;
	}


	public int getAdultCnt() {
		return adultCnt;
	}


	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}


	public int getChildCnt() {
		return childCnt;
	}


	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}


	public int getAdvCnt() {
		return advCnt;
	}


	public void setAdvCnt(int advCnt) {
		this.advCnt = advCnt;
	}


	public int getRecPrice() {
		return recPrice;
	}


	public void setRecPrice(int recPrice) {
		this.recPrice = recPrice;
	}
	

	
}
