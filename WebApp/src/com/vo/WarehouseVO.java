package com.vo;

public class WarehouseVO {
	String wareid;
	String warename;
	String warecate;
	double warescale;
	String waretype;
	
	
	
	public WarehouseVO() {
		super();
	}
	public WarehouseVO(String wareid, String warename, String warecate, double warescale, String waretype) {
		super();
		this.wareid = wareid;
		this.warename = warename;
		this.warecate = warecate;
		this.warescale = warescale;
		this.waretype = waretype;
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
	public String getWarecate() {
		return warecate;
	}
	public void setWarecate(String warecate) {
		this.warecate = warecate;
	}
	public double getWarescale() {
		return warescale;
	}
	public void setWarescale(double warescale) {
		this.warescale = warescale;
	}
	public String getWaretype() {
		return waretype;
	}
	public void setWaretype(String waretype) {
		this.waretype = waretype;
	}
	@Override
	public String toString() {
		return "WarehouseVO [wareid=" + wareid + ", warename=" + warename + ", warecate=" + warecate + ", warescale="
				+ warescale + ", waretype=" + waretype + "]";
	}
	
	
}
