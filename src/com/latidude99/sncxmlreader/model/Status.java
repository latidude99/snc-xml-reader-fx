package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Status")
public class Status {
	
	
	ChartStatus chartStatus;
	
	public ChartStatus getChartStatus() {
		return chartStatus;
	}
	@XmlElement(name = "ChartStatus")
	public void setChartStatus(ChartStatus chartStatus) {
		this.chartStatus = chartStatus;
	}

	  

}
