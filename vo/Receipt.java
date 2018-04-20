package vo;

public class Receipt {

	private int recNo;//예매번호
	private int exiNo;//전시코드
	private int perNo;//공연코드
	private int empNo;//전시번호
	private String recDate;//예매일시
	private String seeDate;//관람일시
	private String recSeat;//예매한 좌석
	private String recMethod;//결제수단
	private int recCnt;//관람인원
	private int recPrice;//금액
	private int[] kindpep = new int[3];
		public Receipt() {
		
		super();
	}

	public Receipt(int recNo, int exiNo, int perNo, int empNo, String recDate, String seeDate,String recSeat, String recMethod,
			int recCnt, int recPrice) {
		super();
		this.recNo = recNo;
		this.exiNo = exiNo;
		this.perNo = perNo;
		this.empNo = empNo;
		this.recDate = recDate;
		this.seeDate = seeDate;
		this.recSeat = recSeat;
		this.recMethod = recMethod;
		this.recCnt =recCnt;
		this.recPrice = recPrice;
	}


	public String getRecSeat() {
		return recSeat;
	}

	public void setRecSeat(String recSeat) {
		this.recSeat = recSeat;
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

	public String getRecMethod() {
		return recMethod;
	}

	public void setRecMethod(String recMethod) {
		this.recMethod = recMethod;
	}

	public int getRecCnt() {
		return recCnt;
	}

	public void setRecCnt(int recCnt) {
		this.recCnt = recCnt;
	}

	public int getRecPrice() {
		return recPrice;
	}

	public void setRecPrice(int recPrice) {
		this.recPrice = recPrice;
	}
	public int[] getKindpep() {
		return kindpep;
	}

	public void setKindpep(int[] kindpep) {
		this.kindpep = kindpep;
	}


	
}
