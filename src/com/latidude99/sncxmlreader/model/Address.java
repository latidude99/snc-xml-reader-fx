package com.latidude99.sncxmlreader.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "address")
@XmlType(propOrder={"deliveryPoint", "city", "administrativeArea", "postalCode", "country", "electronicMailAddress"})
public class Address {
	
	String deliveryPoint;
	String city;
	String administrativeArea;
	String postalCode;
	String country;
	String electronicMailAddress;
	
	public String getDeliveryPoint() {
		return deliveryPoint;
	}
	@XmlElement(name = "deliveryPoint")
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}
	public String getCity() {
		return city;
	}
	@XmlElement(name = "city")
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdministrativeArea() {
		return administrativeArea;
	}
	@XmlElement(name = "administrativeArea")
	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}
	public String getPostalCode() {
		return postalCode;
	}
	@XmlElement(name = "postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	@XmlElement(name = "country")
	public void setCountry(String country) {
		this.country = country;
	}
	public String getElectronicMailAddress() {
		return electronicMailAddress;
	}
	@XmlElement(name = "electronicMailAddress")
	public void setElectronicMailAddress(String electronicMailAddress) {
		this.electronicMailAddress = electronicMailAddress;
	}
	@Override
	public String toString() {
		return "     Delivery Point: " + deliveryPoint + 
				"\n City: " + city + 
				"\n Administrative Area: " + administrativeArea + 
				"\n Postal Code: " + postalCode + 
				"\n Country: " + country	+ 
				"\n Email: " + electronicMailAddress;
	}
	
	
	
	

}
