package com.latidude99.sncxmlreader.controller;

import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
//import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;

import com.latidude99.sncxmlreader.utils.ConnectionUtils;
import com.latidude99.sncxmlreader.utils.DownloadTask;
import com.latidude99.sncxmlreader.utils.Downloader2;
import com.latidude99.sncxmlreader.utils.LoadTask;
import com.latidude99.sncxmlreader.utils.MessageBox;
import com.latidude99.sncxmlreader.utils.ProgressBarBox;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
//import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebPaneController implements Initializable{
	
	@FXML
    private WebView webView;
	@FXML
	private Button buttonDownload;
	@FXML
	private Button buttonSignIn;
	@FXML
	private TextField textUsername;
	@FXML
	private TextField textPassword;
	@FXML
	private CheckBox checkboxDontRememeber;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private Label labelDownloaded;
	@FXML
	private Button buttonCancel;
	@FXML
	Button buttonUpdate;
	@FXML
	Button buttonDelete;
	@FXML
	MainPaneController mainPaneController;
/*	
	public MainPaneController getMainPaneController() {
		return mainPaneController;
	}
	public void setMainPaneController(MainPaneController mainPaneController) {
		this.mainPaneController = mainPaneController;
	}
*/	
	public final String UKHO_HOME = "https://enavigator.ukho.gov.uk/";
	public final String UKHO_LOGIN = "https://enavigator.ukho.gov.uk/Login";
	public final String UKHO_DOWNLOAD = "https://enavigator.ukho.gov.uk/Download";
	public final String FILE_PARAM = "?file=";
	
	
    String setUsername =  "document.getElementsByName('un')[0].value='" + "PiotrC" + "'";
    String setPassword = "document.getElementsByName('pw')[0].value='" + "flamenco10ST" + "';";
	
    WebEngine webEngine;
    final BooleanProperty loginAttempted = new SimpleBooleanProperty(false);
    Downloader2 downloader = new Downloader2();
    Preferences userPreferences = Preferences.userRoot().node("/sncxmlreader");
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    Task<Void> downloadTask;
    Task<Void> loadTask;
    File file;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		configureCredentials();
		connectionConfig();	
		configureWebView();
		configureButtons();            		
	}
	
	
	private void configureCredentials() {
		checkboxDontRememeber.setSelected(false);
		if(!userPreferences.get("username", "").equals("") && !userPreferences.get("password", "").equals("")) {
        	System.out.println(userPreferences.toString() +
        			", " + userPreferences.get("username", "") + 
        			", " + userPreferences.get("password", ""));
        	textUsername.setText(userPreferences.get("username", ""));
            textPassword.setText(userPreferences.get("password", ""));
        }
	}
	
	private void configureWebView() {
		progressIndicator.setVisible(false);
		labelDownloaded.setVisible(false);
		buttonUpdate.setVisible(false);
		buttonDelete.setVisible(false);
        webEngine = webView.getEngine();
		webEngine.setJavaScriptEnabled(true);
		
		webEngine.documentProperty().addListener(new ChangeListener<Document>() {
            @Override
            public void changed(ObservableValue<? extends Document> ov, Document oldDoc, Document doc) {
                if (doc != null && !loginAttempted.get()) {
                    if (doc.getElementsByTagName("form").getLength() > 0) {
                        HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(0);
                        
                            HTMLInputElement username = null;
                            HTMLInputElement password = null;
                            NodeList nodes = form.getElementsByTagName("input");
                            for (int i = 0; i < nodes.getLength(); i++) {
                                HTMLInputElement input = (HTMLInputElement) nodes.item(i);
                                switch (input.getId()) {
                                    case "un":
                                        username = input;
                                        break;
                                    case "pw":
                                        password = input;
                                        break;
                                }
                            }

                            if (username != null && password != null) {
                                loginAttempted.set(true);
                                username.setValue(textUsername.getText());
                                password.setValue(textPassword.getText());
                                form.submit();
                            }
                        
                    }
                }
            }
        });
		webEngine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
            @Override
            public void changed(ObservableValue<? extends Throwable> ov, Throwable oldException, Throwable exception) {
                System.out.println("Load Exception: " + exception);
            }
        });
	}
	
	private void configureButtons() {
				
		buttonDownload.setDisable(true);
		buttonCancel.setDisable(true);
		
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
		     @Override
		     public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
		    	 String newURL = webEngine.getLocation();
		         if (newState == Worker.State.SUCCEEDED) {
		        	 System.out.println("after login new URL: " + newURL);
		        	 afterLogin();
		         }		         
		     }
		});
		
		buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
	         public void handle(ActionEvent t) {
				 progressBar.progressProperty().bind(webView.getEngine().getLoadWorker().progressProperty());
				 progressBar.visibleProperty().bind(webView.getEngine().getLoadWorker().runningProperty());
				 if (notEmpty(textPassword.getText()) && notEmpty(textPassword.getText())) {
	            	 loginAttempted.set(false);
	                 webEngine.load(UKHO_HOME);
	             }else {
	             	MessageBox.show("Please enter your Username  and Password.", "Info");
	             }

	         }
			 
	    });

	    
	    buttonDownload.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){	
				buttonDownload.setDisable(true);
				buttonCancel.setDisable(false);
				webView.setOpacity(0.3);
								
				downloadTask = new DownloadTask(UKHO_DOWNLOAD);
				progressIndicator.setVisible(true);
				labelDownloaded.setVisible(true);
				progressIndicator.progressProperty().bind(downloadTask.progressProperty());
				labelDownloaded.textProperty().bind(downloadTask.messageProperty());
				
				downloadTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, 
	                       						new EventHandler<WorkerStateEvent>() {
	                           @Override
	                           public void handle(WorkerStateEvent t) {
	                        	   labelDownloaded.textProperty().unbind();
	                        	   labelDownloaded.setText("Download complete.");
	                        	   buttonUpdate.setVisible(true);
	                           }
	                       });
				downloadTask.addEventHandler(WorkerStateEvent.WORKER_STATE_FAILED, 
   												new EventHandler<WorkerStateEvent>() {
						       @Override
						       public void handle(WorkerStateEvent t) {
						    	   buttonDownload.setDisable(false);
						    	   buttonCancel.setDisable(true);
						    	   buttonUpdate.setVisible(false);
						    	   webView.setOpacity(1);
						    	   downloadTask.cancel(true);
						    	   progressIndicator.progressProperty().unbind();
						    	   labelDownloaded.textProperty().unbind();
						    	   progressIndicator.setVisible(false);
						    	   labelDownloaded.setVisible(false);
						    	   
						    	   file = new File("snc_catalogue.xml");
						    	   buttonDelete.setText("Delete file: " + file.getName());
						    	   buttonDelete.setVisible(true);
						    	   MessageBox.show("Could not save the file. Delete the old file and try again. ", "Error");
						       }
						   });
				Thread thread = new Thread(downloadTask);
	            thread.setDaemon(true);
	            thread.start();
			}
		});
	    
	    buttonCancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){		
				buttonDownload.setDisable(false);
				buttonCancel.setDisable(true);
				buttonUpdate.setVisible(false);
				webView.setOpacity(1);
				downloadTask.cancel(true);
				progressIndicator.progressProperty().unbind();
				labelDownloaded.textProperty().unbind();
				progressIndicator.setVisible(false);
				labelDownloaded.setVisible(false);
			}
		});
	    
	    buttonUpdate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){	
//				buttonUpdate.setText("Close the browser window and refresh catalogue");
				Stage stage = (Stage) buttonUpdate.getScene().getWindow();
				stage.close();
			}
		});
	    
	    buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){	
				try {
					Files.delete(file.toPath());
					buttonDownload.setDisable(false);
					buttonCancel.setDisable(true);
					buttonUpdate.setVisible(false);
					webView.setOpacity(1);
					downloadTask.cancel(true);
					progressIndicator.progressProperty().unbind();
					labelDownloaded.textProperty().unbind();
					progressIndicator.setVisible(false);
					labelDownloaded.setVisible(false);
					buttonDelete.setVisible(false);
					MessageBox.show("File deleted. Try downloading again. ", "Info");
					 
				} catch (IOException eio) {
					eio.printStackTrace();
					buttonDownload.setDisable(false);
					buttonCancel.setDisable(true);
					buttonUpdate.setVisible(false);
					webView.setOpacity(1);
					downloadTask.cancel(true);
					progressIndicator.progressProperty().unbind();
					labelDownloaded.textProperty().unbind();
					progressIndicator.setVisible(false);
					labelDownloaded.setVisible(false);
					buttonDelete.setVisible(false);
					MessageBox.show("AccessDenied. Change file name or delete it manually (" +file.getAbsolutePath() + ")" , "Error");
				}
			}
		});
	        
	}
	
	private void connectionConfig() {
		ConnectionUtils connUtils = new ConnectionUtils();
		connUtils.sslOff();
	}
	
	private boolean notEmpty(String s) {
        return s != null && !"".equals(s);
    }
	
	// not used
	Runnable afterLoginTask = new Runnable() {
        public void run() {
            afterLogin();
        }
    };
    
    
    private void afterLogin() {
    	if(downloader.loginCheck(UKHO_DOWNLOAD)) {
       	 System.out.println("file link: " + downloader.getUrlByIdJSOUP(UKHO_DOWNLOAD) + 
       			 " contains: "  + FILE_PARAM); 
       	 buttonDownload.setDisable(false);
       	 
       	 if(checkboxDontRememeber.isSelected()){
	        	 try {
					userPreferences.clear();
					MessageBox.show("Your credentials have been cleared.", "Info");
				} catch (BackingStoreException e) {
					MessageBox.show("An error occurred when clearing your credentials.", "Info");
					e.getMessage();
				}
            }else {
           	 userPreferences.put("username",textUsername.getText());
           	 userPreferences.put("password",textPassword.getText());
            }
        }
    }

    
	
	
}
	
	
	
	

//	https://enavigator.ukho.gov.uk  MDSPages_linkDlPaperXml













