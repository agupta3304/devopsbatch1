package com.code.api.models;

import java.util.List;

public class OrderRequestDTO {
	private String orderdate;
    private double totalamount;
    private int userid;
    private List<CartItems> items;
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public List<CartItems> getItems() {
		return items;
	}
	public void setItems(List<CartItems> items) {
		this.items = items;
	}
    
}
