package com.vipapp.appmark2.items;

public class ClassItem{
	private String name;
	private String fullname;
	private int type;

	public ClassItem(String name, String fullname, int type){
		this.name = name;
		this.fullname = fullname;
		this.type = type;
	}
	

	public void setType(int type){
		this.type = type;
	}
	public int getType(){
		return type;
	}
	public void setFullname(String fullname){
		this.fullname = fullname;
	}
	public String getFullname(){
		return fullname;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
