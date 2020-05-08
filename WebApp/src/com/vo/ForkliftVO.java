package com.vo;

import java.sql.Date;

public class ForkliftVO {
	String forkid;
	String wareid;
	Date forkpurdate;
	String forkmodel;
	Date forklastcheckdate;
	long forkdist;
	
	public ForkliftVO() {}
	
	public ForkliftVO(String forkid, String wareid, Date forkpurdate, String forkmodel, Date forklastcheckdate,
			long forkdist) {
		this.forkid = forkid;
		this.wareid = wareid;
		this.forkpurdate = forkpurdate;
		this.forkmodel = forkmodel;
		this.forklastcheckdate = forklastcheckdate;
		this.forkdist = forkdist;
	}
	public String getForkid() {
		return forkid;
	}
	public void setForkid(String forkid) {
		this.forkid = forkid;
	}
	public String getWareid() {
		return wareid;
	}
	public void setWareid(String wareid) {
		this.wareid = wareid;
	}
	public Date getForkpurdate() {
		return forkpurdate;
	}
	public void setForkpurdate(Date forkpurdate) {
		this.forkpurdate = forkpurdate;
	}
	public String getForkmodel() {
		return forkmodel;
	}
	public void setForkmodel(String forkmodel) {
		this.forkmodel = forkmodel;
	}
	public Date getForklastcheckdate() {
		return forklastcheckdate;
	}
	public void setForklastcheckdate(Date forklastcheckdate) {
		this.forklastcheckdate = forklastcheckdate;
	}
	public long getForkdist() {
		return forkdist;
	}
	public void setForkdist(long forkdist) {
		this.forkdist = forkdist;
	}
	@Override
	public String toString() {
		return "ForkliftVO [forkid=" + forkid + ", wareid=" + wareid + ", forkpurdate=" + forkpurdate + ", forkmodel="
				+ forkmodel + ", forklastcheckdate=" + forklastcheckdate + ", forkdist=" + forkdist + "]";
	}
	
	
	
	
}

