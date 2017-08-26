package com.StudentManageSystem.model;
public class Student {
	private int id;
	private String code;//电子监管码
	private String name;//药名
	private String no;//生产批号
	private String date;//生产日期
	private String term;//有效期
	private String place;// 产地
	private String type;// 种类   中药   西药
	private String amount;//数量
	private String price;// 价格

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getno() {
		return no;
	}

	public void setno(String no) {
		this.no = no;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getterm() {
		return term;
	}

	public void setterm(String term) {
		this.term = term;
	}

	public String getplace() {
		return place;
	}

	public void setplace(String place) {
		this.place = place;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getamount() {
		return amount;
	}

	public void setamount(String amount) {
		this.amount = amount;
	}

	public String getprice() {
		return price;
	}

	public void setprice(String price) {
		this.price = price;
	}
}
