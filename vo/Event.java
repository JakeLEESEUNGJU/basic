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

	public Event() {
		super();
	}

	public Event(int evtNo, String evtTitle, String evtStart, String evtEnd, String evtRating, int evtPrice,
			String evtDetail, int locNo) {
		super();
		this.evtNo = evtNo;
		this.evtTitle = evtTitle;
		this.evtStart = evtStart;
		this.evtEnd = evtEnd;
		this.evtRating = evtRating;
		this.evtPrice = evtPrice;
		this.evtDetail = evtDetail;
		this.locNo = locNo;
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
