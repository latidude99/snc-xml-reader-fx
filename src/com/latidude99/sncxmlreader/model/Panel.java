package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Paper")
public class Panel {
	
	String panelID;
	String panelAreaName;
 	String panelName;
 	String panelScale;
 	Polygon polygon;
 	
 	public Panel() {}
 	 	
	public Panel(String panelID, String panelAreaName, String panelName, String panelScale) {
		this.panelID = panelID;
		this.panelAreaName = panelAreaName;
		this.panelName = panelName;
		this.panelScale = panelScale;
	}


	public String getPanelID() {
		return panelID;
	}
	@XmlElement(name = "PanelID")
	public void setPanelID(String panelID) {
		this.panelID = panelID;
	}
	public String getPanelAreaName() {
		return panelAreaName;
	}
	@XmlElement(name = "PanelAreaName")
	public void setPanelAreaName(String panelAreaName) {
		this.panelAreaName = panelAreaName;
	}
	public String getPanelName() {
		return panelName;
	}
	@XmlElement(name = "PanelName")
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public String getPanelScale() {
		return panelScale;
	}
	@XmlElement(name = "PanelScale")
	public void setPanelScale(String panelScale) {
		this.panelScale = panelScale;
	}
	public Polygon getPolygon() {
		return polygon;
	}
	@XmlElement(name = "Polygon")
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
 	
 	

}
