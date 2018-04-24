package vo;

public class Receipt {

	private int recNo;			//예매번호
	private int exiNo;			//전시코드
	private int perNo;			//공연코드
	private int empNo;			//직원번호
	private String recDate;		//예매일시
	private String seeDate;		//관람일시
	private String startTime; 	//시작시간
	private String finishTime; //시작시간
	private String recSeat;		//예매한 좌석
	private String recMethod;	//결제수단
	private int adultCnt;		//성인인원
	private int childCnt;		//아동인원
	private int oldCnt;			//우대인원
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
		this.oldCnt = oldCnt;
		this.recPrice = recPrice;
	}
	
	


		
	

	
}
