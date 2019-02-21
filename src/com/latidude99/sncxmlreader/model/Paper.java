package com.latidude99.sncxmlreader.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Paper")
public class Paper {
	
	
	List<StandardNavigationChart> charts;

	public List<StandardNavigationChart> getCharts() {
		return charts;
	}

	@XmlElement(name = "StandardNavigationChart")
	public void setCharts(List<StandardNavigationChart> charts) {
		this.charts = charts;
	}

	public void add(StandardNavigationChart chart) {
        if (this.charts == null) {
            this.charts = new ArrayList<StandardNavigationChart>();
        }
        this.charts.add(chart);
    }
 	
   

}
