package com.code.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int catid;
	@Column(name="catname",length=40,nullable = false,unique = true)
	private String catname;
	@Column(name="catdesc",length=100,nullable = false)
	private String catdesc;

public Category(String catname, String catdesc) {
	super();
	this.catname = catname;
	this.catdesc = catdesc;
}


}
