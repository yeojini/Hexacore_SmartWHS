package com.vo;

import java.sql.Date;
import java.util.ArrayList;

public class InvoiceVO {
	int invoiceid;
	String empno;
	String empname;
	String invoicedate;
	String startdate;
	String enddate;
	ArrayList<InvoicedetailVO> dtllist;
	
	
	public InvoiceVO() {
	}
	
	public InvoiceVO(String empno, String empname, String invoicedate, String startdate, String enddate,
			ArrayList<InvoicedetailVO> dtllist) {
		super();
		this.empno = empno;
		this.empname = empname;
		this.invoicedate = invoicedate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.dtllist = dtllist;
	}
	

	public InvoiceVO(int invoiceid, String empno, String empname, String invoicedate) {
		this.invoiceid = invoiceid;
		this.empno = empno;
		this.empname = empname;
		this.invoicedate = invoicedate;
	}


	public InvoiceVO(int invoiceid, String empno, String empname, String invoicedate, String startdate, String enddate,
			ArrayList<InvoicedetailVO> dtllist) {
		this.invoiceid = invoiceid;
		this.empno = empno;
		this.empname = empname;
		this.invoicedate = invoicedate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.dtllist = dtllist;
	}
	
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
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
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public ArrayList<InvoicedetailVO> getDtllist() {
		return dtllist;
	}

	public void setDtllist(ArrayList<InvoicedetailVO> dtllist) {
		this.dtllist = dtllist;
	}

	@Override
	public String toString() {
		return "InvoiceVO [invoiceid=" + invoiceid + ", empno=" + empno + ", empname=" + empname + ", invoicedate="
				+ invoicedate + ", startdate=" + startdate + ", enddate=" + enddate + ", dtllist=" + dtllist.toString() + "]";
	}

	
	

}
