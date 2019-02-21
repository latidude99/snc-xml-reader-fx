package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BaseFileMetadata")
@XmlType(propOrder={"MD_FileIdentifier", "MD_CharacterSet", "MD_PointOfContact", "MD_DateStamp"})
public class BaseFileMetadata {
	
	String mD_FileIdentifier;
	String mD_CharacterSet;
	MD_PointOfContact mD_PointOfContact;
	String mD_DateStamp;

	
	public String getMD_FileIdentifier() {
		return mD_FileIdentifier;
	}
	@XmlElement(name = "MD_FileIdentifier")
	public void setMD_FileIdentifier(String mD_FileIdentifier) {
		this.mD_FileIdentifier = mD_FileIdentifier;
	}

	public String getMD_CharacterSet() {
		return mD_CharacterSet;
	}
	@XmlElement(name = "MD_CharacterSet")
	public void setMD_CharacterSet(String mD_CharacterSet) {
		this.mD_CharacterSet = mD_CharacterSet;
	}

	public MD_PointOfContact getMD_PointOfContact() {
		return mD_PointOfContact;
	}
	@XmlElement(name = "MD_PointOfContact")
	public void setMD_PointOfContact(MD_PointOfContact mD_PointOfContact) {
		this.mD_PointOfContact = mD_PointOfContact;
	}

	public String getMD_DateStamp() {
		return mD_DateStamp;
	}

	@XmlElement(name = "MD_DateStamp")
	public void setMD_DateStamp(String mD_DateStamp) {
		this.mD_DateStamp = mD_DateStamp;
	}
    

}
