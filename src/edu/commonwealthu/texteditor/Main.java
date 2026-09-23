package edu.commonwealthu.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Text Editor - Untitled");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}