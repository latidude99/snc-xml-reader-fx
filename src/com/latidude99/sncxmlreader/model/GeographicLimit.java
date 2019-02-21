package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "GeographicLimit")
public class GeographicLimit {
	
	List<Polygon> polygons;
    
        
    
   
    public List<Polygon> getPolygons() {
		return polygons;
	}
    
    @XmlElement(name = "Polygon")
	public void setPolygons(List<Polygon> polygons) {
		this.polygons = polygons;
	}
	
	
	public void add(Polygon polygon) {
        if (this.polygons == null) {
            this.polygons = new ArrayList<Polygon>();
        }
        this.polygons.add(polygon);
    }

 
}
