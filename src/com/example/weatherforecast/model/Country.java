package com.example.weatherforecast.model;

public class Country {
	private int id;
	private String countryName;
	private String countryCode;
	private int cityId;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getCountryName(){
		return countryName;
	}
	public void setcountryName(String countryName){
		this.countryName=countryName;
	}
	public String getCountryCode(){
		return countryCode;
	}
	public void setcountryCode(String countryCode){
		this.countryCode=countryCode;
	}
	public int getcityId(){
		return cityId;
	}
	public void setcityId(int cityId){
		this.cityId=cityId;
	}
}
