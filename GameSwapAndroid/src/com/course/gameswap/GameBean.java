package com.course.gameswap;

public class GameBean {
	int id;
	String name;
	String version;
	
	GameBean(){
		
	}
	
	GameBean(int id,String name,String phone_number){
		this.id=id;
		this.name=name;
		this.version=phone_number;
	}
	
	// constructor
    public GameBean(String name, String phone_number){
        this.name = name;
        this.version = phone_number;
    }
    
	void setID(int id){
		this.id=id;
	}
	int getID(){
		return this.id;
	}
	
	void setName(String name){
		this.name=name;
	}
	String getName(){
		return this.name;
	}
	
	void setPhoneNumber(String phoneNumber){
		this.version=phoneNumber;
	}
	String getPhoneNumber(){
		return this.version;
	}
}
