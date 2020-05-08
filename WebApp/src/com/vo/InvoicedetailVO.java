package com.vo;

import java.sql.Date;

public class InvoicedetailVO {
	int invoicedtlid;
	String itemid;
	String itemname;
	String wareid;
	String warename;
	String invoicestat;
	int invoicedtlqty;
	String empno;
	String empname;
	int invoiceid;
	Date invoicedtldate;
	String startdate;
	String enddate;
	
	public InvoicedetailVO() {
	}

	public InvoicedetailVO(String itemid, String itemname, String wareid, String warename, String invoicestat,
			int invoicedtlqty, String empno, String empname) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.wareid = wareid;
		this.warename = warename;
		this.invoicestat = invoicestat;
		this.invoicedtlqty = invoicedtlqty;
		this.empno = empno;
		this.empname = empname;
	}
	
	

	public InvoicedetailVO(String itemid, String itemname, String wareid, String warename, String invoicestat,
			int invoicedtlqty, String empno, String empname, String startdate,
			String enddate) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.wareid = wareid;
		this.warename = warename;
		this.invoicestat = invoicestat;
		this.invoicedtlqty = invoicedtlqty;
		this.empno = empno;
		this.empname = empname;
		this.startdate = startdate;
		this.enddate = enddate;
	}



	public InvoicedetailVO(int invoicedtlid, String itemid, String itemname, String wareid, String warename,
			String invoicestat, int invoicedtlqty, String empno, String empname, int invoiceid, Date invoicedtldate) {
		this.invoicedtlid = invoicedtlid;
		this.itemid = itemid;
		this.itemname = itemname;
		this.wareid = wareid;
		this.warename = warename;
		this.invoicestat = invoicestat;
		this.invoicedtlqty = invoicedtlqty;
		this.empno = empno;
		this.empname = empname;
		this.invoiceid = invoiceid;
		this.invoicedtldate = invoicedtldate;
	}
	
	

	public int getInvoicedtlid() {
		return invoicedtlid;
	}

	public void setInvoicedtlid(int invoicedtlid) {
		this.invoicedtlid = invoicedtlid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
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

	public String getInvoicestat() {
		return invoicestat;
	}

	public void setInvoicestat(String invoicestat) {
		this.invoicestat = invoicestat;
	}

	public int getInvoicedtlqty() {
		return invoicedtlqty;
	}

	public void setInvoicedtlqty(int invoicedtlqty) {
		this.invoicedtlqty = invoicedtlqty;
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

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public Date getInvoicedtldate() {
		return invoicedtldate;
	}

	public void setInvoicedtldate(Date invoicedtldate) {
		this.invoicedtldate = invoicedtldate;
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

	@Override
	public String toString() {
		return "InvoicedetailVO [invoicedtlid=" + invoicedtlid + ", itemid=" + itemid + ", itemname=" + itemname
				+ ", wareid=" + wareid + ", warename=" + warename + ", invoicestat=" + invoicestat + ", invoicedtlqty="
				+ invoicedtlqty + ", empno=" + empno + ", empname=" + empname + ", invoiceid=" + invoiceid
				+ ", invoicedtldate=" + invoicedtldate + "]";
	}
	
	
	
}
