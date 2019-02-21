package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "StandardNavigationChart")
@XmlType(propOrder={"shortName","metadata"}) 
public class StandardNavigationChart {
	
	
    String shortName;
	Metadata metadata;
	
 	
    public String getShortName() {
		return shortName;
	}
    
    @XmlElement(name = "ShortName")
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Metadata getMetadata() {
		return metadata;
	}
    
    @XmlElement(name = "Metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}
