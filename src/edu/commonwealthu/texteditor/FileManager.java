package edu.commonwealthu.texteditor;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

import java.io.File;
import java.nio.file.Files;

public class FileManager {

    private final TextArea textArea;
    private final Stage stage;

    private File currentFile = null;
    private boolean modified = false;

    private final FileChooser chooser;

    public FileManager(TextArea textArea, Stage stage) {
        this.textArea = textArea;
        this.stage = stage;

        this.textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!modified) {
                modified = true;
                updateTitle();
            }
        });

        chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        chooser.setInitialFileName("Untitled.txt");
    }

    // Updates title and shows * if modified
    private void updateTitle() {
        String fileName, title;
        if(currentFile == null) fileName = "Untitled";
        else fileName = currentFile.getName();
        if(modified) title = "Text Editor - *" + fileName;
        else title = "Text Editor - " + fileName;
        stage.setTitle(title);
    }

    //Prompt user to save unsaved changes
    public boolean promptSaveIfNeeded() {
        if (!modified) return true;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText("You have unsaved changes.");
        alert.setContentText("Do you want to save before continuing?");

        ButtonType saveBtn = new ButtonType("Save");
        ButtonType dontSaveBtn = new ButtonType("Don't Save");
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

        ButtonType result = alert.showAndWait().orElse(cancelBtn);

        if (result == saveBtn) {
            return saveFile(); // true if saved, false if canceled
        }
        if (result == dontSaveBtn) {
            return true;
        }
        return false; // cancel
    }

    // New File
    public void newFile() {
        if (!promptSaveIfNeeded()) return;

        textArea.clear();
        currentFile = null;
        modified = false;
        updateTitle();
    }

    // Open File
    public void openFile() {
        if (!promptSaveIfNeeded()) return;

        File file = chooser.showOpenDialog(stage);
        if (file == null) return;

        try {
            String content = Files.readString(file.toPath());
            textArea.setText(content);
            currentFile = file;
            modified = false;
            updateTitle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Save File
    public boolean saveFile() {
        try {
            if (currentFile == null) {
                File file = chooser.showSaveDialog(stage);
                if (file == null) return false;
                currentFile = file;
            }

            Files.writeString(currentFile.toPath(), textArea.getText());
            modified = false;
            updateTitle();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Save As
    public void saveAs() {
        File file = chooser.showSaveDialog(stage);
        if (file == null) return;

        try {
            Files.writeString(file.toPath(), textArea.getText());
            currentFile = file;
            modified = false;
            updateTitle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Exit
    public void exit() {
        if (!promptSaveIfNeeded()) return;
        stage.close();
    }
}