package vo;

public class Exhibition extends Event {

	private int exiNo;// 전시 번호
	private int evtNo; // 행사번호
	private String exiDir;// 전시 작가명

	public Exhibition() {
		super();
	}

	public Exhibition(int exiNo, int evtNo, String exiDir, String evtTitle, String evtStart, String evtEnd,
			String evtRating, int evt_price, String evtDetail, int locNo) {
		super(evtNo, evtTitle, evtStart, evtEnd, evtRating, evt_price, evtDetail, locNo);
		this.exiNo = exiNo;
		this.evtNo = evtNo;
		this.exiDir = exiDir;
	}

	public int getExiNo() {
		return exiNo;
	}

	public void setExiNo(int exiNo) {
		this.exiNo = exiNo;
	}

	public int getEvtNo() {
		return evtNo;
	}

	public void setEvtNo(int evtNo) {
		this.evtNo = evtNo;
	}

	public String getExiDir() {
		return exiDir;
	}

	public void setExiDir(String exiDir) {
		this.exiDir = exiDir;
	}

}
