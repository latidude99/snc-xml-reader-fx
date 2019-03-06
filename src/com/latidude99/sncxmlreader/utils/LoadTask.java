package com.latidude99.sncxmlreader.utils;

import java.io.File;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.StandardNavigationChart;
import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;

import javafx.concurrent.Task;

public class LoadTask extends Task<Void> {
	UKHOCatalogueFile ukhoCatalogueFile;
	String schemaVersion;
	BaseFileMetadata meta;
	Map<String, StandardNavigationChart> standardCharts;
	ChartUtils chartUtils = new ChartUtils();
	
	
	public LoadTask() {
    }

    @Override
    protected Void call() throws Exception {
       
 //       long fileLength = 19_858_062;  //connection.getContentLengthLong();
        
//        System.out.println("fileLength " + fileLength);
        try {
			File file = new File("snc_catalogue.xml");
	        JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(file);
	        schemaVersion = ukhoCatalogueFile.getSchemaVersion();
	        meta = ukhoCatalogueFile.getBaseFileMetadata();
	        ChartUtils.setUkhoCatalogueFile(ukhoCatalogueFile);
			Info.catalogueFull(meta, schemaVersion);
			ukhoCatalogueFile = null;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

        return null;
    }

    @Override
    protected void failed() {
        System.out.println("failed");
    }

    @Override
    protected void succeeded() {
        System.out.println("loaded");
    }
    
   
}

/*
 
 public void startup() {
		chartUtils = new ChartUtils();
		checkboxInfo.setSelected(false);
		try {
			File file = new File("snc_catalogue.xml");
	        JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(file);
	        schemaVersion = ukhoCatalogueFile.getSchemaVersion();
	        meta = ukhoCatalogueFile.getBaseFileMetadata();
	        textResult.setText(Info.catalogueFull(meta, schemaVersion));
			labelLoadedDate.setText(Info.catalogueBasic(meta, schemaVersion));
			setDateLabels(meta);
			standardCharts = chartUtils.getCharts(ukhoCatalogueFile);
			ukhoCatalogueFile = null;
		} catch (JAXBException e) {
			textResult.setText("Catalogue not loaded. Please load the file manually \n"
					+ "or \n"
					+ "copy the catalogue file into the application folder and start SncXmlReader again");
//			e.printStackTrace();
		}
	}
 
 */















