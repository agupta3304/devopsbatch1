package com.code.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int catid;
	@Column(name="catname",length=40,nullable = false,unique = true)
	private String catname;
	@Column(name="catdesc",length=100,nullable = false)
	private String catdesc;

	public Category() {
	}

	public Category(String catname, String catdesc) {
		super();
		this.catname = catname;
		this.catdesc = catdesc;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getCatdesc() {
		return catdesc;
	}

	public void setCatdesc(String catdesc) {
		this.catdesc = catdesc;
	}

	@Override
	public String toString() {
		return "Category{" +
				"catid=" + catid +
				", catname='" + catname + '\'' +
				", catdesc='" + catdesc + '\'' +
				'}';
	}
}
