package com.code.api.models;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;





@Entity
@Table(name="item")
public class Item implements Serializable{
	
	@Id
	@Column(name="ItemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	
	@Column(name="ItemName")
	private String itemName;
	
	
	@Column(name="ItemPrice")
	private int itemPrice;
	@Column(name = "filename")
	private String filename;   	
	//---------------------------------------item mapped to category------------------------------------------//
	@ManyToOne
	 @JoinColumn(name="categoryId")
	private Category category;
	//--------------------------------------------------------------------------------------------------------//
	
	public  Item()
	{
		this.itemId=0;
	}
	
	
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
}

