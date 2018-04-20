package vo;

public class Event {

	private int evtNo;// 행사번호
	private String evtTitle;// 행사이름
	private String evtStart;// 행사 시작일
	private String evtEnd;// 행사종료일
	private String evtRating; // 행사 등급
	private int evtPrice; // 행사가격
	private String evtDetail; // 행사설명
	private int locNo; // 장소번호
	private String evtKind;//행사종류  p:공연 e:전시
	private String evt_flag;//행사 종료 여부
	private Location location; //장소객체

	public Event() {
		super();
		location = new Location();
	}
	
	public Event(int evtNo, String evtTitle, String evtStart, String evtEnd, String evtRating, int evtPrice,
			String evtDetail, int locNo,String evtKind,String evt_flag) {
		super();
		this.evtNo = evtNo;
		this.evtTitle = evtTitle;
		this.evtStart = evtStart;
		this.evtEnd = evtEnd;
		this.evtRating = evtRating;
		this.evtPrice = evtPrice;
		this.evtDetail = evtDetail;
		this.locNo = locNo;
		this.evtKind = evtKind;
		this.evt_flag = evt_flag;
		location = new Location();
	}

	
	public String getEvt_flag() {
		return evt_flag;
	}

	public void setEvt_flag(String evt_flag) {
		this.evt_flag = evt_flag;
	}

	public String getEvtKind() {
		return evtKind;
	}


	public void setEvtKind(String evtKind) {
		this.evtKind = evtKind;
	}


	public int getEvtNo() {
		return evtNo;
	}

	public void setEvtNo(int evtNo) {
		this.evtNo = evtNo;
	}

	public String getEvtTitle() {
		return evtTitle;
	}

	public void setEvtTitle(String evtTitle) {
		this.evtTitle = evtTitle;
	}

	public String getEvtStart() {
		return evtStart;
	}

	public void setEvtStart(String evtStart) {
		this.evtStart = evtStart;
	}

	public String getEvtEnd() {
		return evtEnd;
	}

	public void setEvtEnd(String evtEnd) {
		this.evtEnd = evtEnd;
	}

	public String getEvtRating() {
		return evtRating;
	}

	public void setEvtRating(String evtRating) {
		this.evtRating = evtRating;
	}

	public int getEvtPrice() {
		return evtPrice;
	}

	public void setEvtPrice(int evtPrice) {
		this.evtPrice = evtPrice;
	}

	public String getEvtDetail() {
		return evtDetail;
	}

	public void setEvtDetail(String evtDetail) {
		this.evtDetail = evtDetail;
	}

	public int getLocNo() {
		return locNo;
	}

	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}

}
