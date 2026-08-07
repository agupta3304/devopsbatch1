package com.code.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="itemorderdetails")
public class ItemOrderDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="itemorderid")
	int itemorderid;
	@Column(name="productname")
	String productname;
	@Column(name="categoryname")
	String categoryname;
	@Column(name="price")
	double price;
	@Column(name="qty")
	int qty;
	@Column(name="itemvalue")
	double itemvalue;
	@ManyToOne
	@JoinColumn(name="orderid")
	@JsonIgnore
	ItemOrder itemorder;
	public  ItemOrderDetails() {
		
	}
	public int getItemorderid() {
		return itemorderid;
	}
	public void setItemorderid(int itemorderid) {
		this.itemorderid = itemorderid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getItemvalue() {
		return itemvalue;
	}
	public void setItemvalue(double itemvalue) {
		this.itemvalue = itemvalue;
	}
	public ItemOrder getItemorder() {
		return itemorder;
	}
	public void setItemorder(ItemOrder itemorder) {
		this.itemorder = itemorder;
	}
	
}
