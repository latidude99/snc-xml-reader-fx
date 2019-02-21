package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "StandardNavigationChart")
public class Products {
	
	
	Paper paper;

	public Paper getPaper() {
		return paper;
	}
	
	@XmlElement(name = "Paper")
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
 	
    

}
