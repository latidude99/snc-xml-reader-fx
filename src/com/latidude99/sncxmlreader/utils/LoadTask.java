package com.latidude99.sncxmlreader.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;

import javafx.concurrent.Task;

public class LoadTask extends Task<Void> {
	File file;
	UKHOCatalogueFile ukhoCatalogueFile;
	ChartUtils chartUtils = new ChartUtils();
	String fileName;
	
	public LoadTask(String fileName) {
		this.fileName = fileName;
    }

    @Override
    protected Void call() throws Exception {
//       long fileLength = 19_858_062;  //connection.getContentLengthLong();
//    	Thread.sleep(3000);
//        System.out.println("fileLength " + fileLength);
        try {
        	File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
//            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            System.out.println("Read text file using InputStreamReader");
//            while((line = br.readLine()) != null){
                //process the line
//                System.out.println(line);
//            }
//            br.close();
            
			long fileSize = file.length();
			System.out.println(fileSize);
			
	        JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(fis);
	        ChartUtils.setUkhoCatalogueFile(ukhoCatalogueFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

        return null;
    }

    @Override
    protected void failed() {
        System.out.println("Could not load the catalogue file");
    }

    @Override
    protected void succeeded() {
        System.out.println("Catalogue file loaded");
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















