/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik
* @version 2.0
* 
*  This file is part of SncXmlReader.
*  Subs Converter is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
* 
*  SncXmlReader is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
* 
*  You should have received a copy of the GNU General Public License
*  along with SncXmlReader.  If not, see <http://www.gnu.org/licenses/>
*  or write to: latidude99@gmail.com
*/

package com.latidude99.sncxmlreader.controller;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.utils.ChartUtils;
import com.latidude99.sncxmlreader.utils.FormatUtils;
import com.latidude99.sncxmlreader.utils.Info;
import com.latidude99.sncxmlreader.utils.MessageBox;
import com.latidude99.sncxmlreader.utils.MessageBoxOn;
import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.StandardNavigationChart;
import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;


public class MainController implements Initializable{
	
	@FXML
	InputPaneController inputPaneController;
	@FXML
	SearchPaneController searchPaneController;
	@FXML
	ContentPaneController contentPaneController;
	
 
    private File fileSave = null;
    private File fileLoad = null;
     
    
    Charset charset;   
    private FileChooser fileChooser = new FileChooser();
    UKHOCatalogueFile ukhoCatalogueFile;
    Map<String, StandardNavigationChart> standardCharts;
    BaseFileMetadata meta;
    String schemaVersion;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH.mm");
    ChartUtils chartUtils;
    
    TextArea textResult;
    Button buttonClearSearch;
    Label labelTitle;
    TextField pathLoadFile;
    Button buttonLoadFile;
    TextField pathSaveResult;
    Button buttonSaveResult;
    Label labelLoadedDate;
    Label labelInputError;
    TextField textSearchChart;
    Button buttonSearchChart;
    Line lineSeparator;
    Button buttonCatInfo;
    Hyperlink linkHelp;
    Hyperlink linkAbout;
    CheckBox checkboxInfo;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configureControls();	
		startup();
		configureIO();
		configureProcessing();
	}
	
	public void configureControls() {
		textResult = contentPaneController.getTextResult();
	    buttonClearSearch = contentPaneController.getButtonClearSearch();
	    labelTitle = inputPaneController.getLabelTitle();
	    pathLoadFile = inputPaneController.getPathLoadFile();
	    buttonLoadFile = inputPaneController.getButtonLoadFile();
	    pathSaveResult = inputPaneController.getPathSaveResult();
	    buttonSaveResult = inputPaneController.getButtonSaveResult();
	    labelLoadedDate = inputPaneController.getLabelLoadedDate();
	    labelInputError = inputPaneController.getLabelInputError();
	    textSearchChart = searchPaneController.getTextSearchChart();
	    buttonSearchChart = searchPaneController.getButtonSearchChart();
	    lineSeparator = inputPaneController.getLineSeparator();
	    buttonCatInfo = searchPaneController.getButtonCatInfo();
	    linkHelp = inputPaneController.getLinkHelp();
	    linkAbout = inputPaneController.getLinkAbout();
	    checkboxInfo = searchPaneController.getCheckboxInfo();
	}
	
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
			textResult.setText("No catalogue loaded. Please load the file manually \n"
					+ "or \n"
					+ "copy the catalogue file into the application folder and start SncXmlReader again");
//			e.printStackTrace();
		}
	}
		
		
	public void configureIO(){
				
		buttonLoadFile.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				fileLoad = fileChooser.showOpenDialog(null);
				fileChooser.setTitle("Load SNC Catalogue from XML File (.xml)");
				fileChooser.setInitialDirectory(File.listRoots()[0]);
				if(fileLoad != null){
					pathLoadFile.setText(fileLoad.getAbsolutePath());
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				    ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(fileLoad);
				    schemaVersion = ukhoCatalogueFile.getSchemaVersion();
			        meta = ukhoCatalogueFile.getBaseFileMetadata();
			        textResult.setText(Info.catalogueFull(meta, schemaVersion));
					labelLoadedDate.setText(Info.catalogueBasic(meta, schemaVersion));
					setDateLabels(meta);
					standardCharts = chartUtils.getCharts(ukhoCatalogueFile);
					ukhoCatalogueFile = null;
				} catch (JAXBException exJAXB) {
					MessageBox.show("Loading XML file unsuccessful", "Error");
//					exJAXB.printStackTrace();
				}
			    
				}
			}
		});
		
		buttonSaveResult.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				fileChooser.setTitle("Choose location for saved search");
				fileChooser.setInitialDirectory(File.listRoots()[0]);
				if(fileLoad != null){
					String name = fileLoad.getName();
					fileChooser.setInitialFileName(name.substring(0, name.lastIndexOf(".")) + 
							"_chart search_" + LocalDateTime.now().format(formatter)	+ ".txt");
					fileSave = fileChooser.showSaveDialog(null);
					//}
					saveSearch(fileSave);
				} else {
					fileChooser.setInitialFileName("_chart search_" + LocalDateTime.now().format(formatter)	+ ".txt");
					saveSearch(fileSave);
				}
				fileSave = null;
				
			}
		});
		
		buttonCatInfo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(meta != null && schemaVersion != null) {
					textResult.clear();
					textResult.setText(Info.catalogueFull(meta, schemaVersion));	
				} else {
					MessageBox.show("The UKHO Standard Navigation ChartUtils catalogue has not been loaded yet.", "Info");
				}
				
			}
		});
		
	}
	
	
	private void configureProcessing() {
						
		buttonClearSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				textResult.clear();
			}
		});
		
		buttonSearchChart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(meta == null) {
					MessageBox.show("The UKHO Standard Navigation ChartUtils catalogue has not been loaded yet.\n"
							  + "          Load the catalogue first and then search for charts!", "Info");
					return;
				}
				boolean fullInfo = checkboxInfo.isSelected();
				textResult.clear();
				String searchInput = textSearchChart.getText().trim();
				textResult.setText(chartUtils.displayChartRange(standardCharts, searchInput, fullInfo));
			}
		});
		
		textSearchChart.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					if(meta == null) {
						MessageBox.show("The UKHO Standard Navigation ChartUtils catalogue has not been loaded yet.\n"
								  + "          Load the catalogue first and then search for charts!", "Info");
						return;
					}
					boolean fullInfo = checkboxInfo.isSelected();
					textResult.clear();
					String searchInput = textSearchChart.getText().trim();
					textResult.setText(chartUtils.displayChartRange(standardCharts, searchInput, fullInfo));
				}
			}
		});
		
		linkAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MessageBox.show(Info.about(), "About");
			}
		});
		
		linkHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MessageBoxOn.show(Info.help(), "Help");
			}
		});
	
		
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	
	
	
	private void setDateLabels(BaseFileMetadata meta){
		String catalogueDateString = meta.getMD_DateStamp();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate catalogueDate = LocalDate.parse(catalogueDateString, formatter);
		LocalDate currentDate = LocalDate.now();
		long daysBetween = Duration.between(catalogueDate.atStartOfDay(), currentDate.atStartOfDay()).toDays();
		long daysToNew = daysBetween -7;
		daysToNew = daysToNew > 0 ? daysToNew : -daysToNew;
//		System.out.println(daysBetween + ", " + daysToNew);
		String days = "";
		if(daysBetween < 8) {
			if(daysToNew == 0) {
				days = "today";
				labelLoadedDate.setTextFill(Color.LIGHTGREEN);
				lineSeparator.setStroke(Color.LIGHTGREEN);
			}
			else if(daysToNew == 1) {
				days = "tomorrow";
				labelLoadedDate.setTextFill(Color.LIGHTGREEN);
				lineSeparator.setStroke(Color.LIGHTGREEN);
			}
			else {
				days = "in " + daysToNew +" days";
				labelLoadedDate.setTextFill(Color.GREEN);
				lineSeparator.setStroke(Color.GREEN);
			}
			labelLoadedDate.setText("catalogue date: " + meta.getMD_DateStamp() + 
					",  schema version: " + schemaVersion +
					", next edition expected " + days);
		} else {
			if(daysToNew > 30) {
				days = daysToNew +" days";
				labelLoadedDate.setTextFill(Color.RED);
				lineSeparator.setStroke(Color.RED);
			}
			else if(daysToNew > 7) {
				days = daysToNew +" days";
				labelLoadedDate.setTextFill(Color.ORANGERED);
				lineSeparator.setStroke(Color.ORANGERED);
			}
			labelLoadedDate.setText("catalogue date: " + meta.getMD_DateStamp() + 
					",  schema version: " + schemaVersion +
					", catalogue out-of-date " + days +" !");
			
		}		
	}
	

	
	private void saveSearch(File file) {
		if(textResult.getText() == null){
			MessageBox.show("Nothing to save!", "Warning");
			return;
		}
		try(OutputStream os = new FileOutputStream(file);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));) {
				for(String str: FormatUtils.stringToList(textResult.getText())){
					bw.write(str);
					bw.newLine();
				}
				pathSaveResult.setText(file.getAbsolutePath());
				MessageBox.show("File saved!", "Confirmation");
			} catch (Exception ex){
				MessageBox.show("File not saved", "Error");
			}
	}
	
	
	

	
	
	

}
		

		
	