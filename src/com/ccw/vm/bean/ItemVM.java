package com.ccw.vm.bean;

import com.ccw.bean.Item;

public class ItemVM {
	private String toptitle;
	private String bottomfoot;
	private Item item;
	
	public String getToptitle() {
		return toptitle;
	}
	public void setToptitle(String toptitle) {
		this.toptitle = toptitle;
	}
	public String getBottomfoot() {
		return bottomfoot;
	}
	public void setBottomfoot(String bottomfoot) {
		this.bottomfoot = bottomfoot;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}
