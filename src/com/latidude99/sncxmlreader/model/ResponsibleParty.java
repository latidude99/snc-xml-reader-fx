package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ResponsibleParty")
@XmlType(propOrder={"organisationName", "contactInfo"})
public class ResponsibleParty {
	
	String organisationName;
	ContactInfo contactInfo;
	
	public String getOrganisationName() {
		return organisationName;
	}
	@XmlElement(name = "organisationName")
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	@XmlElement(name = "contactInfo")
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	

}
