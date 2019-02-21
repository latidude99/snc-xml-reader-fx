package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Paper")
public class Scale {
	
	
	StandardNavigationChart standardNavigationChart;

	public StandardNavigationChart getStandardNavigationChart() {
		return standardNavigationChart;
	}
	
	@XmlElement(name = "StandardNavigationChart")
	public void setStandardNavigationChart(StandardNavigationChart standardNavigationChart) {
		this.standardNavigationChart = standardNavigationChart;
	}
 	
   

}
