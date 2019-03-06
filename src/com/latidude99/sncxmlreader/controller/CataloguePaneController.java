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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.latidude99.sncxmlreader.model.UKHOCatalogueFile;
import com.latidude99.sncxmlreader.utils.Info;
import com.latidude99.sncxmlreader.utils.LoadTask;
import com.latidude99.sncxmlreader.utils.MessageBox;
import com.latidude99.sncxmlreader.utils.MessageBoxOn;

import javafx.fxml.Initializable;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CataloguePaneController implements Initializable{
	
	
	@FXML
	private Button buttonWebView;
	@FXML
	private Button buttonRefresh;
	@FXML
	private Label labelWebAddress;
	@FXML
    private Hyperlink linkHelp;
    @FXML
    private Hyperlink linkAbout;
    @FXML
    MainPaneController mainController;
    @FXML
    WebPaneController webPaneController;
    
    public Hyperlink getLinkHelp() {
		return linkHelp;
	}
	public Hyperlink getLinkAbout() {
		return linkAbout;
	}	
	public Button getButtonWebView() {
		return buttonWebView;
	}
	public Label getLabelWebAddress() {
		return labelWebAddress;
	}
	public Button getButtonRefresh() {
		return buttonRefresh;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		configureButtons();
		configureLinks();
		
	}
	
	
	private void configureButtons() {
		buttonWebView.setOnAction(new EventHandler<ActionEvent>(){
			@Override
            public void handle(ActionEvent event) {
 
				try {
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/latidude99/sncxmlreader/pane/webPane.fxml"));
			        Parent root1 = (Parent) fxmlLoader.load();
			        Stage stage = new Stage();
			        stage.setScene(new Scene(root1));  
			        stage.show();
			    } catch(Exception e) {
			        e.printStackTrace();
			    }
            }
        });
		
		
	        
	}
		
		
 
	
	
	
	private void configureLinks() {
		
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
		
}











