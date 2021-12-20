package com.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClothTypeModel {

	private int clothTypeID;
	private String clothType;
	private int flag = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getClothTypeID() {
		return clothTypeID;
	}
	public void setClothTypeID(int clothTypeID) {
		this.clothTypeID = clothTypeID;
	}
	public String getClothType() {
		return clothType;
	}
	public void setClothType(String clothType) {
		this.clothType = clothType;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
