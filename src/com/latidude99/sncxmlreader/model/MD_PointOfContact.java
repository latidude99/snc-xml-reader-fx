package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MD_PointOfContact")
public class MD_PointOfContact {
	
	ResponsibleParty responsibleParty;
	
	public ResponsibleParty getResponsibleParty() {
		return responsibleParty;
	}
	@XmlElement(name = "ResponsibleParty")
	public void setResponsibleParty(ResponsibleParty responsibleParty) {
		this.responsibleParty = responsibleParty;
	}
    
	

}
