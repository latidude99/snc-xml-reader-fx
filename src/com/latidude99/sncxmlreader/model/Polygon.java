package com.latidude99.sncxmlreader.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Polygon")
public class Polygon {
	
	List<Position> positions;

	public List<Position> getPositions() {
		return positions;
	}
	
	@XmlElement(name = "Position")
	public void setPositions(List<Position> position) {
		this.positions = position;
	}
	
	public void add(Position position) {
        if (this.positions == null) {
            this.positions = new ArrayList<Position>();
        }
        this.positions.add(position);
	}
	
}
