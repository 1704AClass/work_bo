package com.health.entity;

import java.io.Serializable;

public class WanNeng implements Serializable{

	private Object data;
	private Object data1;
	private Object data2;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData1() {
		return data1;
	}
	public void setData1(Object data1) {
		this.data1 = data1;
	}
	public Object getData2() {
		return data2;
	}
	public void setData2(Object data2) {
		this.data2 = data2;
	}
	public WanNeng(Object data, Object data1, Object data2) {
		super();
		this.data = data;
		this.data1 = data1;
		this.data2 = data2;
	}
	public WanNeng(Object data, Object data1) {
		super();
		this.data = data;
		this.data1 = data1;
	}
	public WanNeng() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WanNeng [data=" + data + ", data1=" + data1 + ", data2="
				+ data2 + "]";
	}
	
	
	
}
