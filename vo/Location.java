package vo;

public class Location {

	private int locNo;
	private String locSeat;
	private int locCnt;
	
	public Location() {
		super();
	}

	public Location(int locNo, String locSeat, int locCnt) {
		super();
		this.locNo = locNo;
		this.locSeat = locSeat;
		this.locCnt = locCnt;
	}

	public int getLocNo() {
		return locNo;
	}

	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}

	public String getLocSeat() {
		return locSeat;
	}

	public void setLocSeat(String locSeat) {
		this.locSeat = locSeat;
	}

	public int getLocCnt() {
		return locCnt;
	}

	public void setLocCnt(int locCnt) {
		this.locCnt = locCnt;
	}
	
	
	
	
	
}
