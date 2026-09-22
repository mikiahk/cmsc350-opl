package edu.commonwealthu.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Text Editor - Untitled");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}