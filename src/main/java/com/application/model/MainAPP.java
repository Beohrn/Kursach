package com.application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by Alexander on 20.04.2015.
 */
public class MainAPP extends Application{

    public static void MAIN(String[] args) throws Exception {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/first.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("ddddd");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
