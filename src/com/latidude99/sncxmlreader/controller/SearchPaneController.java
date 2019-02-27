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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class SearchPaneController implements Initializable{
	
	
    @FXML
    private TextField textSearchChart;
    @FXML
    private Button buttonSearchChart;
    @FXML
    private Button buttonCatInfo;
    @FXML
    private CheckBox checkboxInfo;
    
           
	public TextField getTextSearchChart() {
		return textSearchChart;
	}
	public Button getButtonSearchChart() {
		return buttonSearchChart;
	}
	public Button getButtonCatInfo() {
		return buttonCatInfo;
	}
	public CheckBox getCheckboxInfo() {
		return checkboxInfo;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
		
}











