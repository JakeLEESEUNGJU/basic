package vo;

public class Exhibition extends Event {

	private int exiNo;// 전시 번호
	private int evtNo; // 행사번호
	private String exiDir;// 전시 작가명

	public Exhibition() {
		super();
	}

	public Exhibition(int exiNo, int evtNo, String exiDir) {
		super();
		this.exiNo = exiNo;
		this.evtNo = evtNo;
		this.exiDir = exiDir;
	}

	public Exhibition(int exiNo, int evtNo, String exiDir, String evtTitle, String evtStart, String evtEnd,
			String evtRating, int evtPrice, String evtDetail, int locNo,String evtKind,String evtFlag) {
		super(evtNo, evtTitle, evtStart, evtEnd, evtRating, evtPrice, evtDetail, locNo, evtKind, evtFlag);
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
