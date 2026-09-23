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

    private double zoom = 14;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        TextArea textArea = createTextArea();
        root.setCenter(textArea);
        FileManager fileManager = new FileManager(textArea, stage);

        stage.setOnCloseRequest(event -> {
            event.consume();      // prevent automatic closing
            fileManager.exit();   // run unsaved-changes logic
        });

        Scene scene = new Scene(root, 900, 600);
        root.setTop(createMenuBar(textArea, fileManager));
        stage.setTitle("Text Editor - Untitled");
        stage.setScene(scene);
        stage.show();
    }

    // Creates the editable text area
    private TextArea createTextArea(){
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        return textArea;
    }

    // Creates the menu bar and adds specified tabs
    private MenuBar createMenuBar(TextArea textArea, FileManager fileManager) {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                createFileMenu(fileManager),
                createEditMenu(textArea),
                createViewMenu(textArea)
        );
        return menuBar;
    }

    // Creates the file menu item
    private Menu createFileMenu(FileManager fm){
        Menu fileMenu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN));
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));

        newItem.setOnAction(e -> fm.newFile());
        openItem.setOnAction(e -> fm.openFile());
        saveItem.setOnAction(e -> fm.saveFile());
        saveAsItem.setOnAction(e -> fm.saveAs());
        exitItem.setOnAction(e -> fm.exit());

        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);
        return fileMenu;
    }

    // Creates the edit menu item
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

    // Creates the view menu item
    private Menu createViewMenu(TextArea textArea){
        Menu viewMenu = new Menu("View");

        MenuItem zoomInItem = new MenuItem("Zoom In");
        MenuItem zoomOutItem = new MenuItem("Zoom Out");
        MenuItem resetZoomItem = new MenuItem("Reset Zoom");

        zoomInItem.setAccelerator(new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        zoomOutItem.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));

        zoomInItem.setOnAction(ActionEvent -> zoomIn(textArea));
        zoomOutItem.setOnAction(ActionEvent -> zoomOut(textArea));
        resetZoomItem.setOnAction(ActionEvent -> resetZoom(textArea));

        viewMenu.getItems().addAll(zoomInItem, zoomOutItem, resetZoomItem);

        return viewMenu;
    }

    // Zooms the text area in
    private void zoomIn(TextArea textArea){
        if(zoom < 100){
            zoom += 2;
        }else return;
        textArea.setStyle("-fx-font-size: " + zoom + "px;");
    }

    // Zooms the text area out
    private void zoomOut(TextArea textArea){
        if(zoom > 2){
            zoom -= 2;
        }else return;
        textArea.setStyle("-fx-font-size: " + zoom + "px;");
    }

    // Resets the zoom to specified default
    private void resetZoom(TextArea textArea){
        zoom = 14;
        textArea.setStyle("-fx-font-size: " + zoom + "px;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}