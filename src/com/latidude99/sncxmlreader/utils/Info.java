package com.latidude99.sncxmlreader.utils;

import com.latidude99.sncxmlreader.model.BaseFileMetadata;
import com.latidude99.sncxmlreader.model.ContactInfo;

public class Info {
	
	public static String about() {
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
		return sb.toString();
	}
	
	public static String help() {
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
		return sb.toString();
	}
	
	
	public static String catalogueBasic(BaseFileMetadata meta, String schemaVersion) {
		return "catalogue date: " + meta.getMD_DateStamp() + 
				",  schema version: " + schemaVersion;
	}
	
	public static String catalogueFull(BaseFileMetadata meta, String schemaVersion) {
		ContactInfo contact = meta.getMD_PointOfContact().getResponsibleParty().getContactInfo();
		StringBuilder sb = new StringBuilder();
		
		sb.append("UKHO Standard Navigation Charts Catalogue, loaded from XML file\n");
		sb.append("Schema Version: " + schemaVersion);
		sb.append("Catalogue Date: " + meta.getMD_DateStamp() + "\n");
		sb.append("MD FileIdentifier: " + meta.getMD_FileIdentifier() + "\n");
		sb.append("MD CharacterSet: " + meta.getMD_CharacterSet() + "\n");
		sb.append("Organisation Name: " + meta.getMD_PointOfContact().getResponsibleParty().getOrganisationName() + "\n");
		sb.append("contactInfo: " + "\n");
		sb.append("     Fax: " + contact.getFax() + "\n");
		sb.append("     Phone: " + contact.getPhone() + "\n");
		sb.append("     Address: " + contact.getAddress() + "\n");
		sb.append("================================================= \n");
		
		return sb.toString();
	}

}



















