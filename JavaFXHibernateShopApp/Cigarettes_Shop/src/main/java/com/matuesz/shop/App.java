package com.matuesz.shop;

import com.matuesz.shop.Hibernate.DatabaseServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("ConstantConditions")
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/main.fxml"));
        primaryStage.setTitle("Shop Service");
        primaryStage.setScene(new Scene(root,800,700));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        DatabaseServer.get().closeFactory();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
