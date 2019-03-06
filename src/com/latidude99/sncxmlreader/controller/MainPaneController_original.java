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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.utils.MessageBox;
import com.latidude99.sncxmlreader.utils.MessageBoxOn;
import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.ContactInfo;
import com.latidude99.sncxmlreader.model.Metadata;
import com.latidude99.sncxmlreader.model.NoticesToMariners;
import com.latidude99.sncxmlreader.model.Panel;
import com.latidude99.sncxmlreader.model.Polygon;
import com.latidude99.sncxmlreader.model.Position;
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


public class MainPaneController_original implements Initializable{
	
	@FXML
    private TextArea textResult;
    @FXML
    private Button buttonClearSearch;
    @FXML
    private Label labelTitle;
    @FXML
    private TextField pathLoadFile;
    @FXML
    private Button buttonLoadFile;
    @FXML
    private TextField pathSaveResult;
    @FXML
    private Button buttonSaveResult;
    @FXML
    private Label labelLoadedDate;
    @FXML
    private Label labelInputError;
    @FXML
    private TextField textSearchChart;
    @FXML
    private Button buttonSearchChart;
    @FXML
    private Line lineSeparator;
    @FXML
    private Button buttonCatInfo;
    @FXML
    private Hyperlink linkHelp;
    @FXML
    private Hyperlink linkAbout;
    @FXML
    private CheckBox checkboxInfo;
    
    private File fileSave = null;
    private File fileLoad = null;
     
    boolean fullInfo;
    Charset charset;   
    private FileChooser fileChooser = new FileChooser();
    UKHOCatalogueFile ukhoCatalogueFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH.mm");
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startup();
		configureIO();
		configureProcessing();
	}
	
	public void startup() {
		
		checkboxInfo.setSelected(false);
		
		try {
			File file = new File("snc_catalogue.xml");
	        JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(file);
	        textResult.setText(displayInfo(ukhoCatalogueFile));
			labelLoadedDate.setText(displayBasicInfo(ukhoCatalogueFile));
			setDateLabels(ukhoCatalogueFile);
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
				//fileChooser.setInitialDirectory(File.listRoots()[0]);
				if(fileLoad != null){
					pathLoadFile.setText(fileLoad.getAbsolutePath());
//					try {
//						getFileEncoding(fileIn);
//					} catch (IOException e1) {
//						MessageBox.show("Error reading encoding type", "Error");
//					}
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				    ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(fileLoad);
					textResult.setText(displayInfo(ukhoCatalogueFile));
					setDateLabels(ukhoCatalogueFile);
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
				fileChooser.setTitle("Choose location for converted subtitles");
				//fileChooser.setInitialDirectory(File.listRoots()[0]);
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
				if(ukhoCatalogueFile != null) {
					textResult.clear();
					textResult.setText(displayInfo(ukhoCatalogueFile));	
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
				if(ukhoCatalogueFile == null) {
					MessageBox.show("The UKHO Standard Navigation ChartUtils catalogue has not been loaded yet.\n"
							  + "          Load the catalogue first and then search for charts!", "Info");
					return;
				}
				fullInfo = checkboxInfo.isSelected();
				textResult.clear();
				String searchInput = textSearchChart.getText().trim();
				textResult.setText(displayChartRange(ukhoCatalogueFile, searchInput));
			}
		});
		
		textSearchChart.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					if(ukhoCatalogueFile == null) {
						MessageBox.show("The UKHO Standard Navigation ChartUtils catalogue has not been loaded yet.\n"
								  + "          Load the catalogue first and then search for charts!", "Info");
						return;
					}
					fullInfo = checkboxInfo.isSelected();
					textResult.clear();
					String searchInput = textSearchChart.getText().trim();
					textResult.setText(displayChartRange(ukhoCatalogueFile, searchInput));
				}
			}
		});
		
		linkAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StringBuilder sb = new StringBuilder();
				sb.append("Standard Navigation ChartUtils XML Reader (SncXmlReader) v.0.1 beta\r\n");
				sb.append("--------------------------------------------------------------------------\r\n\n");
				sb.append("The goal of SncXmlReader is to present a UKHO Standard Navigation ChartUtils catalogue\r\n"
						+ "(available for registered eNavigator users to download in XML file format) \r\n"
						+ "in a more user friendly way. SncXmlReader allows simple search for charts \r\n"
						+ "using their official catalogue number.\r\n"
						+ "\r\n"
						+ "Displayed chart information include:\r\n"
						+ "- chart's catalogue number\r\n"
						+ "- chart's international number (if exist)\r\n"
						+ "- chart's title\r\n"
						+ "- chart's scale\r\n"
						+ "- chart's geographical limits (coverage)\r\n "
						+ "- additional panels/insets (if exist) and their:\r\n"
						+ "                                               - name\r\n"
						+ "                                               - number\r\n"
						+ "                                               - scale\r\n"
						+ "                                               - geographical limits\r\n"
						+ "- Notices To Mariners with Year/Week/Number and Type for each notice\r\n\n");
				sb.append("--------------------------------------------------------------------------\r\n\n");
				
				sb.append("Copyright (C) 2019  Piotr Czapik.\r\n" + 
						"  author Piotr Czapik\r\n" + 
						"  version 0.1 (beta)\r\n" + 
						" \r\n" + 
						"  SncXmlReader is free software: you can redistribute it and/or modify\r\n" + 
						"  it under the terms of the GNU General Public License as published by\r\n" + 
						"  the Free Software Foundation, either version 3 of the License, or\r\n" + 
						"  (at your option) any later version.\r\n" + 
						"  SncXmlReader is distributed in the hope that it will be useful,\r\n" + 
						"  but WITHOUT ANY WARRANTY; without even the implied warranty of\r\n" + 
						"  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\r\n" + 
						"  GNU General Public License for more details.\r\n" + 
						"  You should have received a copy of the GNU General Public License\r\n" + 
						"  along with SncXmlReader.  If not, see <http://www.gnu.org/licenses/>\r\n" + 
						"  or write to: latidude99@gmail.com");
				MessageBox.show(sb.toString(), "About");
			}
		});
		
		linkHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StringBuilder sb = new StringBuilder();
				sb.append("Standard Navigation ChartUtils XML Reader \r\n");
				sb.append("-------------------------------------------------------------------------------------------------------------------------------\r\n");
				sb.append("To use ScnXmlReader you need to download a UKHO SNC catalogue file first and save it on your computer/network drive, \r\n"
						+ "optimally in the ScnXmlReader folder.\r\n");
				sb.append("Assuming the catalogue file is in the same folder as ScnXmlReader it will be loaded each time when the ScnXmlReader starts.\r\n"
						+ "The catalogue has to have the \"snc_catalogue.xml\" name exactly.\r\n"
						+ "If the catalogue file is not present or has different name it will not be loaded at the start of ScnXmlReader.\r\n"
						+ "A message \"no catalogue loaded\" will display under the the application title.\r\n"
						+ "\r\n"
						+ "When ScnXmlReader is up and running you can load a catalogue manually from any location. It does not have to have have \r\n"
						+ "any specific name as long as it conforms to the same UKHO XML Schema (but it has to have .xml extention) .\r\n"
						+ "\r\n"
						+ "You can load a catalogue as many times as you want, the new one will simply replace the old one\r\n");
				sb.append("-------------------------------------------------------------------------------------------------------------------------------\r\n");
				sb.append("UKHO issues new SNC XML catalogue every week.\r\n"
						+ "If the loaded catalogue is up-to-date the catalogue date and schema vrsion (under the app title) will display in green colour. \r\n"
						+ "It will also say when the new catalogue edition is due (in days)\r\n"
						+ "If the loaded catalogue is out-of-date the colour will change to dark orange (less than 30 days) or red (more than 30 days) \r\n"
						+ "It will also say how many days it is out-of-date.\r\n\n\"");
				sb.append("-------------------------------------------------------------------------------------------------------------------------------\r\n\n");
				sb.append("SEARCHING FOR CHARTS\r\n\n"
						+ "In the search field you can enter:\r\n"
						+ "  - a single chart number, without any letters, just 0-9 digits\\r\n"
						+ "  - a few chart numbers separated with \",\" (comma) \r\n"
						+ "  - a range of charts separated with \"-\" (hyphen)\r\n"
						+ "  - any combimation of the above\r\n"
						+ "Spaces between digits will be removed during processing, numbers joined and trated as one chart number");
				
				MessageBoxOn.show(sb.toString(), "Help");
			}
		});
	
		
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	
	private String displayBasicInfo(UKHOCatalogueFile catalogue) {
		return "catalogue date: " + catalogue.getBaseFileMetadata().getMD_DateStamp() + 
				",  schema version: " + catalogue.getSchemaVersion();
	}
	
	private String displayInfo(UKHOCatalogueFile catalogue) {
		BaseFileMetadata base = catalogue.getBaseFileMetadata();
		ContactInfo contact = base.getMD_PointOfContact().getResponsibleParty().getContactInfo();
		StringBuilder sb = new StringBuilder();
		
		sb.append("UKHO Standard Navigation Charts Catalogue, loaded from XML file\n");
		sb.append("Schema Version: " + catalogue.getSchemaVersion());
		sb.append("Catalogue Date: " + base.getMD_DateStamp() + "\n");
		sb.append("MD FileIdentifier: " + base.getMD_FileIdentifier() + "\n");
		sb.append("MD CharacterSet: " + base.getMD_CharacterSet() + "\n");
		sb.append("Organisation Name: " + base.getMD_PointOfContact().getResponsibleParty().getOrganisationName() + "\n");
		sb.append("contactInfo: " + "\n");
		sb.append("     Fax: " + contact.getFax() + "\n");
		sb.append("     Phone: " + contact.getPhone() + "\n");
		sb.append("     Address: " + contact.getAddress() + "\n");
		sb.append("================================================= \n");
		
		return sb.toString();
	}
	
	private void setDateLabels(UKHOCatalogueFile catalogue){
		String catalogueDateString = catalogue.getBaseFileMetadata().getMD_DateStamp();
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
			labelLoadedDate.setText("catalogue date: " + catalogue.getBaseFileMetadata().getMD_DateStamp() + 
					",  schema version: " + catalogue.getSchemaVersion() +
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
			labelLoadedDate.setText("catalogue date: " + catalogue.getBaseFileMetadata().getMD_DateStamp() + 
					",  schema version: " + catalogue.getSchemaVersion() +
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
				for(String str: textToList(textResult.getText())){
					bw.write(str);
					bw.newLine();
				}
				pathSaveResult.setText(file.getAbsolutePath());
				MessageBox.show("File saved!", "Confirmation");
			} catch (Exception ex){
				MessageBox.show("File not saved", "Error");
			}
	}
	
	private List<String>  textToList(String content){
		List<String> textResultList = new ArrayList<>();
		String newline = System.getProperty("line.separator");
		boolean hasNewLine = content.contains(newline);
		if((content.trim().length() > 0) && (!hasNewLine)){
			String[] lines = textResult.getText().split("\n");
			textResultList = new ArrayList<String>(Arrays.asList(lines));
		} else {
			MessageBox.show("The text area is empty!", "Input error");
		}
		return textResultList;
	}
	
	// not used
	public String diplaySingleChart(UKHOCatalogueFile catalogue, String chartNumber) {
		List<StandardNavigationChart> chartsFound = 
				catalogue.getProducts().getPaper().getCharts().stream()
															.filter(n -> n.getMetadata().getCatalogueNumber().equals(chartNumber))
															.collect(Collectors.toList());
		if(!chartsFound.isEmpty() && chartsFound.get(0).getMetadata().getCatalogueNumber().equals(chartNumber))
			if(fullInfo)
				return displayChartFullInfo(chartsFound.get(0));
			else
				return displayChartBasicInfo(chartsFound.get(0));
		else {
			String message = "ChartUtils number " + chartNumber + " has not been found in the catalogue.";
			MessageBox.show(message, "Info");
		}
		return ""; 
	}
	
	
	public String displayChartRange(UKHOCatalogueFile catalogue, String input) {
		StringBuilder sb = new StringBuilder();
//		String numbersAsString = "2, 4, 5,,67,7 6,32,3, 5,7-9, 8-17,15, 17, e9 0, 9-q9, 1 2-14";
//		System.out.println("getUserDefinedId() ->");
        List<String> numbersListString = new ArrayList<>();
        Set<Integer> numbersSetFinal = new TreeSet<>();
        int notValid = 0;
//        System.out.println("Input string: " + input);
        String[] numbers = input.split(",");
        for(String s: numbers) {
	        s= s.trim().replaceAll(" ", "").replaceAll("\u00A0", "");
//	        System.out.println("after trim() and replace() s =" + s);
	        notValid = 0;
	        for(int i = 0; i < s.length(); i++){
	          	if((s.charAt(i) != '0')
	               && (s.charAt(i) != '1')
	               && (s.charAt(i) != '2')
	               && (s.charAt(i) != '3')
	               && (s.charAt(i) != '4')
	               && (s.charAt(i) != '5')
	               && (s.charAt(i) != '6')
	               && (s.charAt(i) != '7')
	               && (s.charAt(i) != '8')
	               && (s.charAt(i) != '9')
	               && (s.charAt(i) != '-'))
	            notValid++;
//	            System.out.print(s.charAt(i) + ", ");
//	            System.out.println("notValid for the char: " + notValid);
	        }
//	        System.out.println("notValid for the string: " + notValid);
	        if(!s.isEmpty() && notValid < 1)
	        	numbersListString.add(s);
        }
//        System.out.println(numbersListString);
        for(String n: numbersListString) {
        	if(n.contains("-")) {
        		String[] range = n.split("-");
        		int rangeMin = Integer.parseInt(range[0]);
        		int rangeMax = Integer.parseInt(range[1]);
        		if( rangeMin < rangeMax) {
        			for(int i = rangeMin; i <rangeMax + 1; i++) {
        				numbersSetFinal.add(i);
        			}
        		}else {
        			for(int i = rangeMax; i < rangeMin + 1; i++) {
            			numbersSetFinal.add(i);
        			}
        		}
        	}else {
        		numbersSetFinal.add( Integer.parseInt(n));
        	}
        }
//      	System.out.println("numbersSetFinal, int: " + numbersSetFinal);
        List<StandardNavigationChart> chartsFound = new ArrayList<>();
        for(Integer chartSearched : numbersSetFinal) {
        	for(StandardNavigationChart chartInCatalog : catalogue.getProducts().getPaper().getCharts())
        		if(("" + chartSearched).equals(chartInCatalog.getMetadata().getCatalogueNumber()))
        			chartsFound.add(chartInCatalog);
        }
        
        List<String> chartsFoundNumbers = new ArrayList<>();
        for(StandardNavigationChart chart : chartsFound) {
        	chartsFoundNumbers.add(chart.getMetadata().getCatalogueNumber());
        }
        
        String chartsEntered = " chart";
        String chartsListed = " chart";
        if(numbersSetFinal.size() > 1)
        	chartsEntered = " charts";
        if(chartsFoundNumbers.size() > 1)
        	chartsListed = " charts";
        
        sb.append("Searching for " + numbersSetFinal.size() + chartsEntered + "\n");
        sb.append(printSet20Cols(numbersSetFinal));
        sb.append("\n");
        sb.append("\nFound " + chartsFoundNumbers.size() + chartsListed + "\n");
        sb.append(printList20Cols(chartsFoundNumbers));
        sb.append("\n-------------------------------------------------");
        
        if(!chartsFound.isEmpty()) {
        	for(int i = 0; i < chartsFound.size(); i++)
        		if(fullInfo)
        			sb.append(displayChartFullInfo(chartsFound.get(i)));
				else
					sb.append(displayChartBasicInfo(chartsFound.get(i)));
 //       	sb.append("\n-------------------------------------------------\n");
        } else {
			sb.append("\nNo charts have been found"); 
//			sb.append("\n-------------------------------------------------\n");
        }
        return sb.toString();
	}
	
	public static String displayChartBasicInfo(StandardNavigationChart chart) {
		Metadata meta = chart.getMetadata();
      	String international = "";
      	StringBuilder sb = new StringBuilder();
      	
      	sb.append("\n\nChart Number: " + meta.getCatalogueNumber());
      	
      	if(meta.getChartInternationalNumber() != null)
      		international = meta.getChartInternationalNumber();
      	sb.append("\nChart International Number: " + international); 
      	
      	if((chart.getMetadata().getScale()) != null) {
      		int scaleNumber = Integer.parseInt(chart.getMetadata().getScale());
      		String scaleFormatted = String.format("%,d", scaleNumber);
      		sb.append("\nChart Scale: 1:" + scaleFormatted);
      	}
      	sb.append("\nChart Title: " + meta.getDatasetTitle());
      	sb.append("\nChart Status: " + meta.getStatus().getChartStatus().getValue()
					+ ", Date: " + meta.getStatus().getChartStatus().getDate());
      	
      	String additionalPanels = "none";
      	if(meta.getPanels() != null && meta.getPanels().size() > 1) {
      		additionalPanels = "";
      		for(Panel panel : meta.getPanels()) {
      			additionalPanels = additionalPanels + "\n" + 
      					"                         Panel " + panel.getPanelID() + " scale: 1:" + panel.getPanelScale();
      		}
      	}
      	sb.append("\n\nAdditional panels: " + additionalPanels + "\n");
          
       
        sb.append("\n=================================================");
        return sb.toString();
	}
	
	public static String displayChartFullInfo(StandardNavigationChart chart) {
		Metadata meta = chart.getMetadata();
      	String international = "";
      	StringBuilder sb = new StringBuilder();
      	
      	sb.append("\n\nChart Number: " + meta.getCatalogueNumber());
      	
      	if(meta.getChartInternationalNumber() != null)
      		international = meta.getChartInternationalNumber();
      	sb.append("\nChart International Number: " + international); 
      	
      	if((chart.getMetadata().getScale()) != null) {
      		int scaleNumber = Integer.parseInt(chart.getMetadata().getScale());
      		String scaleFormatted = String.format("%,d", scaleNumber);
      		sb.append("\nChart Scale: 1:" + scaleFormatted);
      	}
      	sb.append("\nChart Title: " + meta.getDatasetTitle());
      	sb.append("\nChart Status: " + meta.getStatus().getChartStatus().getValue()
					+ ", Date: " + meta.getStatus().getChartStatus().getDate());
      	
      	String additionalPanels = "none";
      	if(meta.getPanels() != null && meta.getPanels().size() > 1) {
      		additionalPanels = "";
      		for(Panel panel : meta.getPanels()) {
      			additionalPanels = additionalPanels + "\n" + 
      					"                         Panel " + panel.getPanelID() + " scale: 1:" + panel.getPanelScale();
      		}
      	}
      	sb.append("\n\nAdditional panels: " + additionalPanels + "\n");
          
        if(meta.getGeographicLimit() != null && meta.getGeographicLimit().getPolygons() != null) {
        	sb.append("\n\nGeographic Limits: ");
          	for(Polygon polygon : meta.getGeographicLimit().getPolygons()) {
          		for(Position position : polygon.getPositions())
          			sb.append("\n     Position -- "
          									+ " latitude = " + position.getLatitude()
          									+ " longitude = " + position.getLongitude());
          	}
        }
                      
        if(meta.getPanels() != null && meta.getPanels().size() >1) {
        	sb.append("\n\nPanels: ");
          	for(Panel panel : meta.getPanels()) {
          		sb.append("\n     Panel Area Name: " + panel.getPanelAreaName());
          		sb.append("\n     Panel ID: " + panel.getPanelID());
          		sb.append("\n     Panel Name: " + panel.getPanelName());
          		int scaleNumber = Integer.parseInt(panel.getPanelScale());
          		String scaleFormatted = String.format("%,d", scaleNumber);
          		sb.append("\n     Panel Scale: " + scaleFormatted);
          		for(Position position : panel.getPolygon().getPositions())
          			sb.append("\n          Position -- "
             									+ " latitude = " + position.getLatitude()
             									+ " longitude = " + position.getLongitude());
          	}	
        }
         
        if(chart.getMetadata().getNotices() != null) {
        	sb.append("\n\nNotices To Mariners: ");
          	for(NoticesToMariners notice : chart.getMetadata().getNotices()) {
          		sb.append("\n" + notice);
          	}
        } else {
        	sb.append("\n\nThere is no NMs for this chart yet.");
        }
        sb.append("\n=================================================");
        return sb.toString();
	}
	
	private String printList20Cols(List<String> list) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(String s : list) {
			sb.append(s + "  ");
			count++;
			if(count == 5 || count == 10 || count ==15) {
				sb.append("   ");
			}
			if(count == 20) {
				sb.append("\n");
				count = 0;
			}
		}
		return sb.toString();
	}
	
	private String printSet20Cols(Set<Integer> set) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(Integer s : set) {
			sb.append(s + "  ");
			count++;
			if(count == 5 || count == 10 || count ==15) {
				sb.append("   ");
			}
			if(count == 20) {
				sb.append("\n");
				count = 0;
			}
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
}
		

		
		
/*

		
	
	
		
		buttonReplace.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//if(contentText.getText() != null){
				contentToTextIn();
				if(textIn != null){
					labelDoneContent.setText("");
					labelSaved.setText("");
					contentToTextIn();
					contentText.clear();
					contentText.setText(textInToContent(replaceAll(textIn)));
					labelDone.setText("Replaced!");
				}
				//}else{
				//	MessageBox.show("No text to process", "Entry data error");
				//}
			}
		});
		
		buttonClear.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				contentText.clear();
				labelSaved.setText("");
				labelDone.setText("");
				labelDoneContent.setText("");
				textReplace.clear();
				textWith.clear();
				pathOpenFile.clear();
				pathSaveFile.clear();
			}
		});
		
		buttonApplyContent.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				labelSaved.setText("");
				labelDone.setText("");
				contentToTextIn();
				contentText.clear();
				if(textIn != null)
					contentText.setText(textInToContent(textIn));
			}
		});
	}
	
	private void configureMouseOnText(){
		
		pathOpenFile.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				clear();
			}
		});
		
		pathSaveFile.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				clear();
			}
		});
		
		contentText.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				clear();
			}
		});
		
	}
	
	private void clear(){
		labelDone.setText("");
		labelDoneContent.setText("");
		labelSaved.setText("");
		textReplace.clear();
		textWith.clear();
		
	}
		
	private ArrayList<String> readFile(File file){
			textIn = new ArrayList<String>();
			chIn = Charset.forName(getInEncoding());
			try(InputStream is = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, chIn));) {
				
					String line = null;
					while((line = br.readLine()) != null){
						textIn.add(line);
						//System.out.println(line);
					}
							
			}catch(UnsupportedEncodingException e){
				MessageBox.show("Encoding not supported", "Error");
			}catch(IOException e){
				MessageBox.show("e.getMessage()", "Input Error");
			}catch(Exception e){
				MessageBox.show("e.getMessage()", "Input Error");
			}
			return textIn;
	}
	
	private void saveFile(File file) {
		if(textIn == null){
			MessageBox.show("Nothing to save!", "Output error");
			return;
		}
		chOut = Charset.forName(getOutEncoding());
		try(OutputStream os = new FileOutputStream(file);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, chOut));) {
				for(String str: textIn){
					bw.write(str);
					bw.newLine();
				}
				pathSaveFile.setText(file.getAbsolutePath());
				labelSaved.setText("File saved!");
			} catch (Exception ex){
				MessageBox.show("File not saved", "Output error");
			}
	}
	
	private ArrayList<String> replaceAll(ArrayList<String> list){
		//contentToTextIn();
		if(textIn == null)
			return null;
		textIn = (ArrayList<String>) list.stream()
			.map(s-> s.replaceAll("ą", "a").replaceAll("ę", "e")
			.replaceAll("ó", "o").replaceAll("ł", "l")
			.replaceAll("ń", "n").replaceAll("ż", "z")
			.replaceAll("ź", "z").replaceAll("ć", "c")
			.replaceAll("ś", "s").replaceAll("�?", "L")
			.replaceAll("Ż", "Z").replaceAll("Ź", "Z")
			.replaceAll("Ć", "C").replaceAll("Ś", "S")
			.replaceAll("Ó", "O"))
			//.peek(s-> System.out.println(s))
			.collect(Collectors.toList());	
		return textIn;
	}
	
	private void replaceSingle(ArrayList<String> list){
		//contentToTextIn();
		if(textIn != null)
		textIn = (ArrayList<String>) list.stream()
			.map(s-> s.replaceAll(textReplace.getText(), textWith.getText()))
			//.peek(s-> System.out.println(s))
			.collect(Collectors.toList());
		labelDone.setText("Done!");
	}
			
	private String textInToContent(List<String> list){
		StringBuilder content = new StringBuilder();
		for(String s: list){
			content.append(s);
			content.append("\n");
		}
		labelDoneContent.setText("Done!");
		return content.toString();
	}
		
	private void contentToTextIn(){
		String newline = System.getProperty("line.separator");
		String content = contentText.getText();
		boolean hasNewLine = content.contains(newline);
		if((content.trim().length() > 0) && (!hasNewLine)){
			String[] lines = contentText.getText().split("\n");
			textIn = new ArrayList<String>(Arrays.asList(lines));
		} else {
			MessageBox.show("The text area is empty!", "Input error");
		}
	}
		
	public void getFileEncoding(File file) throws IOException{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		String ch = null;
		try{
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			ch = isr.getEncoding();
			radioInFile.setText(ch);
			radioOutFile.setText(ch);
		}catch(IOException e){
			MessageBox.show("e.getMessage()", "Encoding reading error");
		}catch(Exception e){
			MessageBox.show("e.getMessage()", "Encoding reading error");
		}finally{
			fis.close();
			isr.close();
		}
	}
	
	private String getInEncoding(){
		if(radioInGroup.getSelectedToggle() == null)
			return "UTF-8";
		RadioButton radioInSelected = (RadioButton) radioInGroup.getSelectedToggle();
		return radioInSelected.getText();
	}
	
	private String getOutEncoding(){
		if(radioOutGroup.getSelectedToggle() == null)
			return getInEncoding();
		RadioButton radioOutSelected = (RadioButton) radioOutGroup.getSelectedToggle();
		return radioOutSelected.getText();
	}


*/

/* characters's codes
												.map(s-> s.replaceAll("\u0105", "\u0061").replaceAll("\u0119", "\u0065")
												.replaceAll("\u00F3", "\u006F").replaceAll("\u0142", "\u006C")
												.replaceAll("\u0144", "\u006E").replaceAll("\u017C", "\u007A")
												.replaceAll("\u017A", "\u007A").replaceAll("\u0107", "\u0063")
												.replaceAll("\u015B", "\u0073").replaceAll("\u0141", "\u004C")
												.replaceAll("\u017B", "\u005A").replaceAll("\u0179", "\u005A")
												.replaceAll("\u0106", "\u0043").replaceAll("\u015A", "\u0053")
												.replaceAll("\u00D3", "\u004F"))
												.peek(s-> System.out.println(s))
												.collect(Collectors.toList());	
ą \u0105   a \u0061
ę \u0119   e \u0065
ó \u00F3   o \u006F
ł \u0142   l \u006C
ń \u0144   n \u006E
ż \u017C   z \u007A
ź \u017A   z \u007A
ć \u0107   c \u0063
ś \u015B   s \u0073

�? \u0141   L \u004C
Ż \u017B   Z \u005A
Ź \u0179   Z \u005A
Ć \u0106   C \u0043
Ś \u015A   S \u0053
Ó \u00D3   O \u004F

Ę \u0118   E \u0045
Ą \u0104   A \u0041
Ń \u0143   N \u004E


*/









