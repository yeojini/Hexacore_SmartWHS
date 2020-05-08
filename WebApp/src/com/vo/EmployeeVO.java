package com.vo;

public class EmployeeVO {
	String empno;
	String empname;
	String emppw;
	String empjob;
	String empemail;
	String empphone;
	String wareid;
	String warename;
	
	
	public EmployeeVO() {
	}

	public EmployeeVO(String empno, String empname, String emppw, String empjob, String empemail, String empphone,
			String wareid, String warename) {
		this.empno = empno;
		this.empname = empname;
		this.emppw = emppw;
		this.empjob = empjob;
		this.empemail = empemail;
		this.empphone = empphone;
		this.wareid = wareid;
		this.warename = warename;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmppw() {
		return emppw;
	}

	public void setEmppw(String emppw) {
		this.emppw = emppw;
	}

	public String getEmpjob() {
		return empjob;
	}

	public void setEmpjob(String empjob) {
		this.empjob = empjob;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public String getEmpphone() {
		return empphone;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public String getWareid() {
		return wareid;
	}

	public void setWareid(String wareid) {
		this.wareid = wareid;
	}

	public String getWarename() {
		return warename;
	}

	public void setWarename(String warename) {
		this.warename = warename;
	}

	@Override
	public String toString() {
		return "EmployeeVO [empno=" + empno + ", empname=" + empname + ", emppw=" + emppw + ", empjob=" + empjob
				+ ", empemail=" + empemail + ", empphone=" + empphone + ", wareid=" + wareid + ", warename=" + warename
				+ "]";
	}
	
	
	

}
