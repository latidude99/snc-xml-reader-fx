package com.latidude99.sncxmlreader.utils;

import java.io.File;
import java.util.List;
 
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class ProgressBarBox {
	
    
	public static void show(Task<Void> task) {
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("ProgressBar");
        stage.setMinWidth(250);
 
       final Label label = new Label("Copy files:");
       final ProgressBar progressBar = new ProgressBar(0);
       final ProgressIndicator progressIndicator = new ProgressIndicator(0);
 
       final Button startButton = new Button("Start");
       final Button cancelButton = new Button("Cancel");
 
       final Label statusLabel = new Label();
       statusLabel.setMinWidth(250);
       statusLabel.setTextFill(Color.BLUE);
 
       // Start Button.
       startButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
        	   
               progressBar.setProgress(0);
               
               // Unbind progress property
               progressBar.progressProperty().unbind();
 
               // Bind progress property
               progressBar.progressProperty().bind(task.progressProperty());
 
               // Unbind text property for Label.
               statusLabel.textProperty().unbind();
 
               // Bind the text property of Label
                // with message property of Task
               statusLabel.textProperty().bind(task.messageProperty());
 
               // When completed tasks
               task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                       new EventHandler<WorkerStateEvent>() {
 
                           @Override
                           public void handle(WorkerStateEvent t) {
                               statusLabel.textProperty().unbind();
 //                              statusLabel.setText("Downloaded: " + downloaded);
                           }
                       });
 
               // Start the Task.
               new Thread(task).start();
 
           }
       });
 
       // Cancel
       
 
       FlowPane root = new FlowPane();
       root.setPadding(new Insets(10));
       root.setHgap(10);
 
       root.getChildren().addAll(label, progressBar, progressIndicator, //
               statusLabel, startButton, cancelButton);
 
       Scene scene = new Scene(root, 500, 120, Color.WHITE);
       stage.setTitle("ProgressBar & ProgressIndicator");
       stage.setScene(scene);
       stage.show();
   }
 
   public static void main(String[] args) {
       Application.launch(args);
   }
 
}
















