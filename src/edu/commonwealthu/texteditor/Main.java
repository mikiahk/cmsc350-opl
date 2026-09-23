package edu.commonwealthu.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        TextArea textArea = createTextArea();
        root.setCenter(textArea);

        root.setTop(createMenuBar(textArea));

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Text Editor - Untitled");
        stage.setScene(scene);
        stage.show();
    }

    private TextArea createTextArea(){
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        return textArea;
    }

    private MenuBar createMenuBar(TextArea textArea){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = createFileMenu();
        Menu editMenu = createEditMenu(textArea);
        menuBar.getMenus().addAll(fileMenu, editMenu);
        return menuBar;
    }

    private Menu createFileMenu(){
        Menu fileMenu = new Menu("File");

        // add ur functionality here
        // add arguments as needed

        return fileMenu;
    }

    private Menu createEditMenu(TextArea textArea){
        Menu editMenu = new Menu("Edit");

        MenuItem undoItem = new MenuItem("Undo");
        MenuItem redoItem = new MenuItem("Redo");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");

        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        redoItem.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        undoItem.setOnAction(actionEvent -> textArea.undo());
        redoItem.setOnAction(actionEvent -> textArea.redo());
        cutItem.setOnAction(actionEvent -> textArea.cut());
        copyItem.setOnAction(actionEvent -> textArea.copy());
        pasteItem.setOnAction(actionEvent -> textArea.paste());

        editMenu.getItems().addAll(undoItem, redoItem, cutItem, copyItem, pasteItem);

        return editMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}