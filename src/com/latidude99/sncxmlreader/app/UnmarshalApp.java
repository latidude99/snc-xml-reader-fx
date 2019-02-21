package com.latidude99.sncxmlreader.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.ContactInfo;
import com.latidude99.sncxmlreader.model.Metadata;
import com.latidude99.sncxmlreader.model.NoticesToMariners;
import com.latidude99.sncxmlreader.model.Panel;
import com.latidude99.sncxmlreader.model.Polygon;
import com.latidude99.sncxmlreader.model.Position;
import com.latidude99.sncxmlreader.model.StandardNavigationChart;
import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;




// xml to java
public class UnmarshalApp {
	
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		
		UKHOCatalogueFile ukhoCatalogueFile;
		
		File file = new File("snc_catalogue.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ukhoCatalogueFile = (UKHOCatalogueFile) unmarshaller.unmarshal(file);
        
        
        displayInfo(ukhoCatalogueFile);
        displayChartStats(ukhoCatalogueFile);
//        displayOnlyChartNums(ukhoCatalogueFile);
//        displayAllCharts(ukhoCatalogueFile);
        displayChartRange(ukhoCatalogueFile, "2, 18,1002-1005, 3432-3435");
//        diplaySingleChart(ukhoCatalogueFile, "1017");
//        diplaySingleChart(ukhoCatalogueFile, "3465");
//        diplaySingleChart(ukhoCatalogueFile, "1000");
        
    }
	
	
		public static void displayInfo(UKHOCatalogueFile catalog) {
			
			System.out.println("UKHO Standard Navigation Charts Catalogue, loaded from XML file\n");
			System.out.println("Schema Version: " + catalog.getSchemaVersion());
			BaseFileMetadata base = catalog.getBaseFileMetadata();
			ContactInfo contact = base.getMD_PointOfContact().getResponsibleParty().getContactInfo();
			
			System.out.println("Catalog Date: " + base.getMD_DateStamp());
			System.out.println("MD FileIdentifier: " + base.getMD_FileIdentifier());
			System.out.println("MD CharacterSet: " + base.getMD_CharacterSet());
			System.out.println("Organisation Name: " + base.getMD_PointOfContact().getResponsibleParty().getOrganisationName());
			System.out.println("contactInfo: ");
			System.out.println("     Fax: " + contact.getFax());
			System.out.println("     Phone: " + contact.getPhone());
			System.out.println("     Address: " + contact.getAddress());
	        
	        System.out.println("================================================= \n");
			
		}
		
		
		public static void displayChartStats(UKHOCatalogueFile catalog) {
			int numOfCharts = catalog.getProducts().getPaper().getCharts().size();
			System.out.println("Number of Standard Charts in the catalogue: " + numOfCharts);
			System.out.println("================================================= \n");
		}
		
		public static void displayOnlyChartNums(UKHOCatalogueFile catalogue) {
    		int number = 0;
    		System.out.println("Standard Charts Numbers included in the catalogue: "); 
    		for(StandardNavigationChart chart : catalogue.getProducts().getPaper().getCharts()) {
    			System.out.print(chart.getMetadata().getChartNumber() + ", ");
    			number++;
    			if(number > 9) {
    				System.out.print("\n");
    				number = 0;
    			}
    		}
    		System.out.println("\n");
    	}
        
		
		public static void displayAllCharts(UKHOCatalogueFile ukhoCatalogueFile) {
			List<StandardNavigationChart>  charts = ukhoCatalogueFile.getProducts().getPaper().getCharts();
	        for(StandardNavigationChart chart :charts) {
	        	displayChart(chart);
	        	System.out.println("\n-------------------------------------------------\n");
	        }
		}
		
		public static void diplaySingleChart(UKHOCatalogueFile catalogue, String chartNumber) {
			List<StandardNavigationChart> chartsFound = catalogue.getProducts().getPaper().getCharts().stream()
																		.filter(n -> n.getMetadata().getCatalogueNumber().equals(chartNumber))
																		.collect(Collectors.toList());
			if(!chartsFound.isEmpty() && chartsFound.get(0).getMetadata().getCatalogueNumber().equals(chartNumber))
				displayChart(chartsFound.get(0));
			else
				System.out.println("Chart number " + chartNumber + " has not been found in the catalogue."); 
			System.out.println("\n-------------------------------------------------\n");
		}
		
		public static  void displayChartRange(UKHOCatalogueFile catalogue, String input) {
//			String numbersAsString = "2, 4, 5,,67,7 6,32,3, 5,7-9, 8-17,15, 17, e9 0, 9-q9, 1 2-14";
//			System.out.println("getUserDefinedId() ->");
	        List<String> numbersListString = new ArrayList<>();
	        Set<Integer> numbersSetFinal = new TreeSet<>();
	        int notValid = 0;
//	        System.out.println("Input string: " + input);
	        String[] numbers = input.split(",");
	        for(String s: numbers) {
		        s= s.trim().replaceAll(" ", "").replaceAll("\u00A0", "");
//		        System.out.println("after trim() and replace() s =" + s);
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
//		            System.out.print(s.charAt(i) + ", ");
//		            System.out.println("notValid for the char: " + notValid);
		        }
//		        System.out.println("notValid for the string: " + notValid);
		        if(!s.isEmpty() && notValid < 1)
		        	numbersListString.add(s);
	        }
//	        System.out.println(numbersListString);
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
//	      	System.out.println("numbersSetFinal, int: " + numbersSetFinal);
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
	        
	        System.out.println("Searching for charts:" + numbersSetFinal + 
	        		" \n........................................................." +
	        		"\nFound charts: " + chartsFoundNumbers + "\n");
	        
	        for(int i = 0; i < chartsFound.size(); i++)
	        	displayChart(chartsFound.get(i));
			if(chartsFound.isEmpty())
				System.out.println("No charts have been found"); 
			System.out.println("\n-------------------------------------------------\n");
	       
		}
		
		
		
        public static void displayChart(StandardNavigationChart chart) {
        	Metadata meta = chart.getMetadata();
        	String international = "";
        	
        	System.out.println("Chart Number: " + meta.getCatalogueNumber());
        	
        	if(meta.getChartInternationalNumber() != null)
        		international = meta.getChartInternationalNumber();
        	System.out.println("Chart International Number: " + international); 
        	
        	if((chart.getMetadata().getScale()) != null) {
        		int scaleNumber = Integer.parseInt(chart.getMetadata().getScale());
        		String scaleFormatted = String.format("%,d", scaleNumber);
        		System.out.println("Chart Scale: 1:" + scaleFormatted);
        	}
        	
        	System.out.println("Chart Title: " + meta.getDatasetTitle());
        	
        	System.out.println("Chart Status: " + meta.getStatus().getChartStatus().getValue()
					+ ", Date: " + meta.getStatus().getChartStatus().getDate());
            
            if(meta.getGeographicLimit() != null && meta.getGeographicLimit().getPolygons() != null) {
            	System.out.println("\nGeographic Limits: ");
            	for(Polygon polygon : meta.getGeographicLimit().getPolygons()) {
            		for(Position position : polygon.getPositions())
            			System.out.println("     Position -- "
            									+ " latitude = " + position.getLatitude()
            									+ " longitude = " + position.getLongitude());
            	}
            }
                        
            if(meta.getPanels() != null && meta.getPanels().size() >1) {
            	System.out.println("\nPanels: ");
            	for(Panel panel : meta.getPanels()) {
            		System.out.println("     Panel Area Name: " + panel.getPanelAreaName());
            		System.out.println("     Panel ID: " + panel.getPanelID());
            		System.out.println("     Panel Name: " + panel.getPanelName());
            		int scaleNumber = Integer.parseInt(panel.getPanelScale());
            		String scaleFormatted = String.format("%,d", scaleNumber);
            		System.out.println("     Panel Scale: " + scaleFormatted);
            		for(Position position : panel.getPolygon().getPositions())
                		System.out.println("          Position -- "
               									+ " latitude = " + position.getLatitude()
               									+ " longitude = " + position.getLongitude());
            		System.out.print("\n");
            	}	
           	}
           
            
            if(chart.getMetadata().getNotices() != null) {
            	System.out.println("Notices To Mariners: ");
            	for(NoticesToMariners notice : chart.getMetadata().getNotices()) {
            		System.out.println(notice);
            	}
            } else {
            	System.out.println("There is no NMs for this chart yet.");
            }
    
        }
	

}






















