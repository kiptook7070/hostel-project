package myhostelproject.hostel.hstlmodels;

public class attModel {
	
	
	String stud,absnam[],prsntname[],mnth,attstat;
	int atid,absntId[],day,year,presntId[];
	////////================================================
	
	
	public String getAttstat() {
		return attstat;
	}
	public void setAttstat(String attstat) {
		this.attstat = attstat;
	}
	
	public String getMnth() {
		return mnth;
	}
	public void setMnth(String mnth) {
		this.mnth = mnth;
	}
	public String[] getPrsntname() {
		return prsntname;
	}

	public void setPrsntname(String[] prsntname) {
		this.prsntname = prsntname;
	}

	public int[] getPresntId() {
		return presntId;
	}

	public void setPresntId(int[] presntId) {
		this.presntId = presntId;
	}

	public String[] getAbsnam() {
		return absnam;
	}

	public void setAbsnam(String[] absnam) {
		this.absnam = absnam;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public int[] getAbsntId() {
		return absntId;
	}

	public void setAbsntId(int[] absntId) {
		this.absntId = absntId;
	}

	public int getAtid() {
		return atid;
	}

	public void setAtid(int atid) {
		this.atid = atid;
	}

	public String getStud() {
		return stud;
	}

	public void setStud(String stud) {
		this.stud = stud;
	}

	
}
