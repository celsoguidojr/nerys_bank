/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Felip
 */
public class frmController extends Application{
    
    String nomeForm, nomeCSS;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        StringBuilder s = new StringBuilder();
        s.append("/fxml/").append(nomeForm).append(".fxml");
        Parent root = FXMLLoader.load(getClass().getResource(s.toString()));
        
        s = new StringBuilder();
        s.append("/styles/").append(nomeCSS).append(".css");
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(s.toString());
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    public void setFXML(String nomeForm, String nomeCSS){
        this.nomeForm = nomeForm;
        this.nomeCSS = nomeCSS;
        
    }
}

