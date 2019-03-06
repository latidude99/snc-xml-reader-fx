package com.latidude99.sncxmlreader.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Downloader {
	
	public final String DOWNLOAD_LINK_BASE = "https://enavigator.ukho.gov.uk/";
	public final String FILE_NAME = "snc_catalogue.xml";

	
	
	
	public String downloadXML(String url) {
		URL downloadUrl = null;
		try {
			downloadUrl = new URL(getUrlByIdJSOUP(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("Downloading file ...  " + downloadUrl);
		File file = new File(FILE_NAME);
		download(downloadUrl, file);
		return "File saved in " + file.getAbsolutePath();
	}
	
	private String getUrlByIdJSOUP(String downloadPageString) {
		String url = "";
		Element link = null;
		try {
			URL downloadPageUrl = new URL(downloadPageString);
			Document doc = Jsoup.parse(downloadPageUrl, 5000);
			link = doc.getElementById("MDSPages_linkDlPaperXml");
			url = link.attr("href");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(link.text());
		System.out.println(url);
		return DOWNLOAD_LINK_BASE + url;	
	}
	
	private File download(URL url, File downFile) {        
        try {
        	FileUtils.copyURLToFile(url, downFile); 			
    	} catch (Exception e) {
    		throw new IllegalStateException(e);
    	}
        return downFile;
	}
	
	
	
}










