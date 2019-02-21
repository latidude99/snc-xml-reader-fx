package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Metadata")
@XmlType(propOrder={"datasetTitle", "scale", "geographicLimit", "catalogueNumber", 
					"status", "chartID", "chartNumber", "chartInternationalNumber", 
					"chartNewEditionDate", "panels", "notices", "editionNumber", 
					"lastNMNumber", "lastNMDate", "publicationDate"})
public class Metadata {
	
	String datasetTitle;
	String scale;
	GeographicLimit geographicLimit;
	String catalogueNumber;
	Status status;
	String chartID;
	String chartNumber;
	String chartInternationalNumber;
	String chartNewEditionDate;
	List<Panel> panels;
	List<NoticesToMariners> notices;
	String editionNumber;
	String lastNMNumber;
	String lastNMDate;
	String publicationDate;
    
    
        
    public String getDatasetTitle() {
		return datasetTitle;
	}
    @XmlElement(name = "DatasetTitle")
	public void setDatasetTitle(String datasetTitle) {
		this.datasetTitle = datasetTitle;
	}

	public String getScale() {
		return scale;
	}
    
    @XmlElement(name = "Scale")
	public void setScale(String scale) {
		this.scale = scale;
	}
    public GeographicLimit getGeographicLimit() {
		return geographicLimit;
	}
	@XmlElement(name = "GeographicLimit")
	public void setGeographicLimit(GeographicLimit geographicLimit) {
		this.geographicLimit = geographicLimit;
	}
	public List<NoticesToMariners> getNotices() {
        return notices;
    }
    @XmlElement(name = "NoticesToMariners")
    public void setNotices(List<NoticesToMariners> notices) {
        this.notices = notices;
    }
    public void add(NoticesToMariners notice) {
        if (this.notices == null) {
            this.notices = new ArrayList<NoticesToMariners>();
        }
        this.notices.add(notice);
    }
	public String getCatalogueNumber() {
		return catalogueNumber;
	}
	@XmlElement(name = "CatalogueNumber")
	public void setCatalogueNumber(String catalogueNumber) {
		this.catalogueNumber = catalogueNumber;
	}
	public Status getStatus() {
		return status;
	}
	@XmlElement(name = "Status")
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getChartID() {
		return chartID;
	}
	@XmlElement(name = "ChartID")
	public void setChartID(String chartID) {
		this.chartID = chartID;
	}
	public String getChartNumber() {
		return chartNumber;
	}
	@XmlElement(name = "ChartNumber")
	public void setChartNumber(String chartNumber) {
		this.chartNumber = chartNumber;
	}
	public String getChartNewEditionDate() {
		return chartNewEditionDate;
	}
	@XmlElement(name = "ChartNewEditionDate")
	public void setChartNewEditionDate(String chartNewEditionDate) {
		this.chartNewEditionDate = chartNewEditionDate;
	}
	public List<Panel> getPanels() {
		return panels;
	}
	@XmlElement(name = "Panel")
	public void setPanels(List<Panel> panels) {
		this.panels = panels;
	}
	
	public void add(Panel panel) {
        if (this.panels == null) {
            this.panels = new ArrayList<Panel>();
        }
        this.panels.add(panel);
    }
	public String getEditionNumber() {
		return editionNumber;
	}
	@XmlElement(name = "EditionNumber")
	public void setEditionNumber(String editionNumber) {
		this.editionNumber = editionNumber;
	}
	public String getLastNMNumber() {
		return lastNMNumber;
	}
	@XmlElement(name = "LastNMNumber")
	public void setLastNMNumber(String lastNMNumber) {
		this.lastNMNumber = lastNMNumber;
	}
	public String getLastNMDate() {
		return lastNMDate;
	}
	@XmlElement(name = "LastNMDate")
	public void setLastNMDate(String lastNMDate) {
		this.lastNMDate = lastNMDate;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	@XmlElement(name = "PublicationDate")
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getChartInternationalNumber() {
		return chartInternationalNumber;
	}
	@XmlElement(name = "ChartInternationalNumber")
	public void setChartInternationalNumber(String chartInternationalNumber) {
		this.chartInternationalNumber = chartInternationalNumber;
	}
    

 
}
