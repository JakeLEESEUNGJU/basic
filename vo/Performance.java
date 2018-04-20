package vo;

public class Performance extends Event {

	private int perNo; // 공연번호
	private int evtNo;// 행사번호
	private String perActor;// 공연 배우
	private String perDir;// 공연 감독
	private String perStart;// 공연시작시간
	private String perEnd;// 공연 종료시간
	private String perReady;// 좌석 예매여부
	
	public Performance() {
		super();
	}
	
	public Performance(int perNo, int evtNo, String perActor, String perDir, String perStart, String perEnd,
			String perReady) {
		super();
		this.perNo = perNo;
		this.evtNo = evtNo;
		this.perActor = perActor;
		this.perDir = perDir;
		this.perStart = perStart;
		this.perEnd = perEnd;
		this.perReady = perReady;
	}

	public Performance(int perNo, int evtNo, String perActor, String perDir, String perStart, String perEnd,
			String perReady,String evtTitle, String evtStart, String evtEnd, String evtRating, int evtPrice,
			String evtDetail, int locNo,String evtKind,String evtFlag) {
		super( evtNo,  evtTitle,  evtStart,  evtEnd,  evtRating,  evtPrice,
				 evtDetail,  locNo, evtKind,evtFlag);
		this.perNo = perNo;
		this.evtNo = evtNo;
		this.perActor = perActor;
		this.perDir = perDir;
		this.perStart = perStart;
		this.perEnd = perEnd;
		this.perReady = perReady;
	}
	
	public int getPerNo() {
		return perNo;
	}
	public void setPerNo(int perNo) {
		this.perNo = perNo;
	}
	public int getEvtNo() {
		return evtNo;
	}
	public void setEvtNo(int evtNo) {
		this.evtNo = evtNo;
	}
	public String getPerActor() {
		return perActor;
	}
	public void setPerActor(String perActor) {
		this.perActor = perActor;
	}
	public String getPerDir() {
		return perDir;
	}
	public void setPerDir(String perDir) {
		this.perDir = perDir;
	}
	public String getPerStart() {
		return perStart;
	}
	public void setPerStart(String perStart) {
		this.perStart = perStart;
	}
	public String getPerEnd() {
		return perEnd;
	}
	public void setPerEnd(String perEnd) {
		this.perEnd = perEnd;
	}
	public String getPerReady() {
		return perReady;
	}
	public void setPerReady(String perReady) {
		this.perReady = perReady;
	}

	

}
