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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;


public class InputPaneController implements Initializable{
	
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
    private Line lineSeparator;
    @FXML
    private Button buttonCatInfo;
    @FXML
    private Hyperlink linkHelp;
    @FXML
    private Hyperlink linkAbout;
    
    @FXML
   
    
	public Label getLabelTitle() {
		return labelTitle;
	}
	public TextField getPathLoadFile() {
		return pathLoadFile;
	}
	public Button getButtonLoadFile() {
		return buttonLoadFile;
	}
	public TextField getPathSaveResult() {
		return pathSaveResult;
	}
	public Button getButtonSaveResult() {
		return buttonSaveResult;
	}
	public Label getLabelLoadedDate() {
		return labelLoadedDate;
	}
	public Label getLabelInputError() {
		return labelInputError;
	}
	public Line getLineSeparator() {
		return lineSeparator;
	}
	public Button getButtonCatInfo() {
		return buttonCatInfo;
	}
	public Hyperlink getLinkHelp() {
		return linkHelp;
	}
	public Hyperlink getLinkAbout() {
		return linkAbout;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

	
	
}









