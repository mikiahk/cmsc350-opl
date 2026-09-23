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
        FileManager fileManager = new FileManager(textArea, stage);
        root.setTop(createMenuBar(textArea, fileManager));

        Scene scene = new Scene(root, 900, 600);

        stage.setOnCloseRequest(event -> {
            event.consume();      // prevent automatic closing
            fileManager.exit();   // run your unsaved-changes logic
        });

        root.setTop(createMenuBar(textArea, fileManager));

        stage.setTitle("Text Editor - Untitled");
        stage.setScene(scene);
        stage.show();
    }

    private TextArea createTextArea(){
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        return textArea;
    }

    private MenuBar createMenuBar(TextArea textArea, FileManager fileManager) {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                createFileMenu(fileManager),
                createEditMenu(textArea)
        );
        return menuBar;
    }


    private Menu createFileMenu(FileManager fm){
        Menu fileMenu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.setOnAction(e -> fm.newFile());
        openItem.setOnAction(e -> fm.openFile());
        saveItem.setOnAction(e -> fm.saveFile());
        saveAsItem.setOnAction(e -> fm.saveAs());
        exitItem.setOnAction(e -> fm.exit());

        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);
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