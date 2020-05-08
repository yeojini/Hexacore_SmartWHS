package com.vo;

public class ItemVO {
	String itemid;
	String wareid;
	String itemname;
	String warename;
	String itemcate;
	double itemprice;
	int itemstock;
	double itemweightpb;
	int itemqtypb;
	String itemloc;
	
	
	public ItemVO() {
	}
	
	public ItemVO(String itemid) {
		this.itemid = itemid;
	}

	public ItemVO(String itemid, String wareid, String itemname, String warename, String itemcate, double itemprice,
			int itemstock, double itemweightpb, int itemqtypb, String itemloc) {
		this.itemid = itemid;
		this.wareid = wareid;
		this.itemname = itemname;
		this.warename = warename;
		this.itemcate = itemcate;
		this.itemprice = itemprice;
		this.itemstock = itemstock;
		this.itemweightpb = itemweightpb;
		this.itemqtypb = itemqtypb;
		this.itemloc = itemloc;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getWareid() {
		return wareid;
	}
	public void setWareid(String wareid) {
		this.wareid = wareid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getWarename() {
		return warename;
	}
	public void setWarename(String warename) {
		this.warename = warename;
	}
	public String getItemcate() {
		return itemcate;
	}
	public void setItemcate(String itemcate) {
		this.itemcate = itemcate;
	}
	public double getItemprice() {
		return itemprice;
	}
	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}
	public int getItemstock() {
		return itemstock;
	}
	public void setItemstock(int itemstock) {
		this.itemstock = itemstock;
	}
	public double getItemweightpb() {
		return itemweightpb;
	}
	public void setItemweightpb(double itemweightpb) {
		this.itemweightpb = itemweightpb;
	}
	public int getItemqtypb() {
		return itemqtypb;
	}
	public void setItemqtypb(int itemqtypb) {
		this.itemqtypb = itemqtypb;
	}
	public String getItemloc() {
		return itemloc;
	}
	public void setItemloc(String itemloc) {
		this.itemloc = itemloc;
	}
	@Override
	public String toString() {
		return "ItemMapper [itemid=" + itemid + ", wareid=" + wareid + ", itemname=" + itemname + ", warename="
				+ warename + ", itemcate=" + itemcate + ", itemprice=" + itemprice + ", itemstock=" + itemstock
				+ ", itemweightpb=" + itemweightpb + ", itemqtypb=" + itemqtypb + ", itemloc=" + itemloc + "]";
	}
	
}
