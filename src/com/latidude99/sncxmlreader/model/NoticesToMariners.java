package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "NoticesToMariners")
//@XmlAccessorType(XmlAccessType.FIELD)
public class NoticesToMariners {
	
//    @XmlAttribute(name = "id")
//    private String productId;
    
    @XmlElement(name = "Year")
    private String year;
    
    @XmlElement(name = "Week")
    private String week;
    
    @XmlElement(name = "Number")
    private int number;
    
    @XmlElement(name = "Type")
    private String type;
    
    public NoticesToMariners(){}
    
    public NoticesToMariners(String year, String week,
                   int number, String type) {
        this.year = year;
        this.week = week;
        this.number = number;
        this.type = type;
    }
    @Override
    public String toString() {
        return "     notice -- Year: " + year + ", " + "Week: " + week + ", " + "Number: " + number + ", " +"Type: " + type;
    }
}