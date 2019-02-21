package com.latidude99.sncxmlreader.app;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.latidude99.sncxmlreader.model.Address;
import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.ChartStatus;
import com.latidude99.sncxmlreader.model.ContactInfo;
import com.latidude99.sncxmlreader.model.GeographicLimit;
import com.latidude99.sncxmlreader.model.MD_PointOfContact;
import com.latidude99.sncxmlreader.model.Metadata;
import com.latidude99.sncxmlreader.model.NoticesToMariners;
import com.latidude99.sncxmlreader.model.Panel;
import com.latidude99.sncxmlreader.model.Paper;
import com.latidude99.sncxmlreader.model.Polygon;
import com.latidude99.sncxmlreader.model.Position;
import com.latidude99.sncxmlreader.model.Products;
import com.latidude99.sncxmlreader.model.ResponsibleParty;
import com.latidude99.sncxmlreader.model.StandardNavigationChart;
import com.latidude99.sncxmlreader.model.Status;
import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;



// java to xml
public class MarshalApp {
	
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		
		//base metadata part
		
		BaseFileMetadata baseFileMetadata = new BaseFileMetadata();
		Address address = new Address();
		ContactInfo contactInfo = new ContactInfo();
		MD_PointOfContact md_PointOfContact = new MD_PointOfContact();
		ResponsibleParty responsibleParty = new ResponsibleParty();
		
		baseFileMetadata.setMD_FileIdentifier("45643563");
		baseFileMetadata.setMD_CharacterSet("");
		address.setDeliveryPoint("Admiralty Way");
		address.setCity("Taunton");
		address.setAdministrativeArea("IMT");
		address.setPostalCode("TA1 2DN");
		address.setCountry("United Kingdom");
		address.setElectronicMailAddress("helpdesk@ukho.gov.uk");
		contactInfo.setAddress(address);
		contactInfo.setFax("+44 (0)1823 284077");
		contactInfo.setPhone("+44 (0)1823 337900");
		responsibleParty.setOrganisationName("The United Kingdom Hydrographic Office");
		responsibleParty.setContactInfo(contactInfo);
		md_PointOfContact.setResponsibleParty(responsibleParty);
		baseFileMetadata.setMD_PointOfContact(md_PointOfContact);
		baseFileMetadata.setMD_DateStamp("2019-01-22");
		
		// standard nauvigation chart 1
		
		StandardNavigationChart standardNavigationChart1 = new StandardNavigationChart();
		standardNavigationChart1.setShortName("2345");
		
		Metadata metadata1 = new Metadata();
		metadata1.setDatasetTitle("Approaches to Dakar  Baie de Goree");
		metadata1.setScale("25000");
				
		GeographicLimit geographicLimit1 = new GeographicLimit();
		Polygon polygon0 = new Polygon();
		Position position1 = new Position("14.5" ,"-17.93"); 
		Position position2 = new Position("15.1" ,"-14.87");
		Position position3 = new Position("24.8" ,"-16.32");
		Position position4 = new Position("34.1" ,"-18.45");
		Position position5 = new Position("16.6" ,"-20.78");
		Position position6 = new Position("17.4" ,"-21.23");
		polygon0.add(position1);
		polygon0.add(position2);
		polygon0.add(position3);
		polygon0.add(position4);
		polygon0.add(position5);
		polygon0.add(position6);
		geographicLimit1.add(polygon0);
		metadata1.setGeographicLimit(geographicLimit1);
		
		Status status1 = new Status();
		ChartStatus chartStatus1 = new ChartStatus();
		chartStatus1.setDate("2015-02-14");
		chartStatus1.setValue("Edition");
		status1.setChartStatus(chartStatus1);
		metadata1.setStatus(status1);
		
		metadata1.setChartID("2345");
		metadata1.setChartNumber("2345");
		metadata1.setChartInternationalNumber("1993");
		metadata1.setChartNewEditionDate("2009-03-05");
				
		Panel panel0 = new Panel("", "", "", "");
		metadata1.add(panel0);
		
		NoticesToMariners notice1 = new NoticesToMariners("2017", "23", 2323, "ChartUpdate");
		NoticesToMariners notice2 = new NoticesToMariners("2018", "45", 2456, "ChartUpdate");
		NoticesToMariners notice3 = new NoticesToMariners("2019", "11", 2457, "ChartUpdate");
		metadata1.add(notice1);
		metadata1.add(notice2);
		metadata1.add(notice3);
		
		metadata1.setEditionNumber("");
		metadata1.setLastNMNumber("4224\\2016");
		metadata1.setLastNMDate("32-2016");
		metadata1.setPublicationDate("2009-03-05");
		
		standardNavigationChart1.setMetadata(metadata1);
				
		
		// standard navigation chart 2
		
		StandardNavigationChart standardNavigationChart2 = new StandardNavigationChart();
		standardNavigationChart1.setShortName("1017");
	
		Metadata metadata2 = new Metadata();
		metadata2.setDatasetTitle("Golfo de California");
//		metadata2.setScale("");
		
		Status status2 = new Status();
		ChartStatus chartStatus2 = new ChartStatus();
		chartStatus2.setDate("2019-12-24");
		chartStatus2.setValue("Edition");
		status2.setChartStatus(chartStatus2);
		metadata2.setStatus(status2);
		
		metadata2.setChartID("1017");
		metadata2.setChartNumber("1017");
//		metadata2.setChartInternationalNumber("1993");
		metadata2.setChartNewEditionDate("2018-09-13");
				
		Panel panel1 = new Panel("1", "A", "A", "750000");
		Polygon polygon1 = new Polygon();
		Position position1a = new Position("14.5" ,"-17.93"); 
		Position position2a = new Position("15.1" ,"-14.87");
		Position position3a = new Position("24.8" ,"-16.32");
		Position position4a = new Position("34.1" ,"-18.45");
		Position position5a = new Position("16.6" ,"-20.78");
		Position position6a = new Position("17.4" ,"-21.23");
		polygon1.add(position1a);
		polygon1.add(position2a);
		polygon1.add(position3a);
		polygon1.add(position4a);
		polygon1.add(position5a);
		polygon1.add(position6a);
		panel1.setPolygon(polygon1);
		
		Panel panel2 = new Panel("2", "B", "B", "550000");
		Polygon polygon2 = new Polygon();
		Position position1b = new Position("14.5" ,"-17.93"); 
		Position position2b = new Position("15.1" ,"-14.87");
		Position position3b = new Position("24.8" ,"-16.32");
		Position position4b = new Position("34.1" ,"-18.45");
		polygon2.add(position1b);
		polygon2.add(position2b);
		polygon2.add(position3b);
		polygon2.add(position4b);		
		panel2.setPolygon(polygon2);
		
		metadata2.add(panel1);
		metadata2.add(panel2);

		NoticesToMariners notice4 = new NoticesToMariners("2018", "53", 2326, "ChartUpdate");
		NoticesToMariners notice5 = new NoticesToMariners("2018", "55", 2465, "Temporary");
		NoticesToMariners notice6 = new NoticesToMariners("2019", "01", 2498, "ChartUpdate");
		metadata2.add(notice1);
		metadata2.add(notice2);
		metadata2.add(notice3);
		metadata2.add(notice4);
		metadata2.add(notice5);
		metadata2.add(notice6);
		
		metadata2.setEditionNumber("");
		metadata2.setLastNMNumber("5937\\2019");
		metadata2.setLastNMDate("49-2018");
		metadata2.setPublicationDate("2015-09-13");
		
		standardNavigationChart2.setMetadata(metadata2);
		
		
		
				
		// main part
		UKHOCatalogueFile ukhoCatalogueFile = new UKHOCatalogueFile();
		Products products = new Products();
		Paper paper = new Paper();
		paper.add(standardNavigationChart1);
		paper.add(standardNavigationChart2);
		products.setPaper(paper);
		ukhoCatalogueFile.setSchemaVersion("98.943.982");
		ukhoCatalogueFile.setProducts(products);
		baseFileMetadata.setMD_DateStamp("2018-11-11");
		ukhoCatalogueFile.setBaseFileMetadata(baseFileMetadata);
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(UKHOCatalogueFile.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(ukhoCatalogueFile, new File("snp4_generated1.xml"));
		marshaller.marshal(ukhoCatalogueFile, System.out);
	
		  
	    
	}
	

}
