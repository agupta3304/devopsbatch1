package com.code.api.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="itemorder")
public class ItemOrder {
	//member variable
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderid")
	int orderid;
	@Column(name="orderdate")
	String orderdate;
	@Column(name="totalamount")
	double totalamount;
	@Column(name="razorpayOrderId")
	 private String razorpayOrderId;
	@Column(name="status")
	 private String status="in-Process";
	private LocalDateTime createdAt = LocalDateTime.now();
	//@ManyToOne
	@ManyToOne
	 @JoinColumn(name="userid")
	Users users;
	@OneToMany(mappedBy = "itemorder")
	List<ItemOrderDetails> itemOrderDetails;
	public ItemOrder() {
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<ItemOrderDetails> getItemOrderDetails() {
		return itemOrderDetails;
	}
	public void setItemOrderDetails(List<ItemOrderDetails> itemOrderDetails) {
		this.itemOrderDetails = itemOrderDetails;
	}
	
	
}
