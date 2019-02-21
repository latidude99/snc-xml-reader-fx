package com.latidude99.sncxmlreader.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UKHOCatalogueFile") 
public class UKHOCatalogueFile {
	
	String schemaVersion;
	BaseFileMetadata baseFileMetadata;
	Products products;
	
    
    public String getSchemaVersion() {
		return schemaVersion;
	}

    @XmlAttribute(name = "SchemaVersion")
	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}

	
    public BaseFileMetadata getBaseFileMetadata() {
		return baseFileMetadata;
	}
    
    @XmlElement(name = "BaseFileMetadata")
	public void setBaseFileMetadata(BaseFileMetadata baseFileMetadata) {
		this.baseFileMetadata = baseFileMetadata;
	}

	public Products getProducts() {
		return products;
	}
    
    @XmlElement(name = "Products")
	public void setProducts(Products products) {
		this.products = products;
	}


}
