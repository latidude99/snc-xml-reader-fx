package com.latidude99.sncxmlreader.utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.controller.ContentPaneController;
import com.latidude99.sncxmlreader.controller.InputPaneController;
import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LoadXML {
/*	
	@FXML
	InputPaneController inputPaneController;
	@FXML
	ContentPaneController contentPaneController;
	
	
	
	public void readXMLAtStart() {
		TextArea textResult = contentPaneController.getTextResult();
	    Label labelLoadedDate = inputPaneController.getLabelLoadedDate();  
	    UKHOCatalogueFile ukhoCatalogueFile;
	    InfoLabel infoLabel = new InfoLabel();
		try {
			File file = new File("snc_catalogue.xml");
	        JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(file);
	        textResult.setText(Catalogue.fullInfo(ukhoCatalogueFile));
			labelLoadedDate.setText(Catalogue.basicInfo(ukhoCatalogueFile));
			infoLabel.setInfo(ukhoCatalogueFile);
		} catch (JAXBException e) {
			textResult.setText("No catalogue loaded. Please load the file manually \n"
					+ "or \n"
					+ "copy the catalogue file into the application folder and start SncXmlReader again");
//			e.printStackTrace();
		}
	}
	
*/	
	
	

}
/*
 
 	TextArea textResult = contentPaneController.getTextResult();
    Button buttonClearSearch = contentPaneController.getButtonClearSearch();
    Label labelTitle = inputPaneController.getLabelTitle();
    TextField pathLoadFile = inputPaneController.getPathLoadFile();
    Button buttonLoadFile = inputPaneController.getButtonLoadFile();
    TextField pathSaveResult = inputPaneController.getPathSaveResult();
    Button buttonSaveResult = inputPaneController.getButtonSaveResult();
    Label labelLoadedDate = inputPaneController.getLabelLoadedDate();
    Label labelInputError = inputPaneController.getLabelInputError();
    TextField textSearchChart = searchPaneController.getTextSearchChart();
    Button buttonSearchChart = searchPaneController.getButtonSearchChart();
    Line lineSeparator = inputPaneController.getLineSeparator();
    Button buttonCatInfo = searchPaneController.getButtonCatInfo();
    Hyperlink linkHelp = inputPaneController.getLinkHelp();
    Hyperlink linkAbout = inputPaneController.getLinkAbout();
    CheckBox checkboxInfo = searchPaneController.getCheckboxInfo();
 
 
 
 */












