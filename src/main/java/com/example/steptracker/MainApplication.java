package com.example.steptracker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainApplication extends Application {

    private TextArea chatArea;
    private TextField stepCounter;
    private Label bluetoothStatus;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Step Tracker with AI Chat Interface");

        // Chat Interface
        Label chatLabel = new Label("AI Chat Interface:");
        chatArea = new TextArea();
        chatArea.setPrefHeight(200);
        chatArea.setEditable(false);
        TextField chatInput = new TextField();
        chatInput.setPromptText("Enter your message...");
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage(chatInput.getText()));
        
        HBox chatBox = new HBox(10, chatInput, sendButton);
        chatBox.setPadding(new Insets(10));

        // Step Counter
        Label stepLabel = new Label("Steps:");
        stepCounter = new TextField();
        stepCounter.setPromptText("0");
        stepCounter.setEditable(false);
        stepCounter.setPrefWidth(100);
        Button refreshSteps = new Button("Refresh Steps");
        refreshSteps.setOnAction(e -> refreshStepCount());

        HBox stepBox = new HBox(10, stepLabel, stepCounter, refreshSteps);
        stepBox.setPadding(new Insets(10));

        // Bluetooth Section
        bluetoothStatus = new Label("Bluetooth Status: Disconnected");
        Button connectBluetooth = new Button("Connect Bluetooth");
        connectBluetooth.setOnAction(e -> connectBluetooth());

        HBox bluetoothBox = new HBox(10, bluetoothStatus, connectBluetooth);
        bluetoothBox.setPadding(new Insets(10));

        // Import/Export Buttons
        Button importData = new Button("Import Data (XML/CSV)");
        importData.setOnAction(e -> importData(primaryStage));
        Button exportData = new Button("Export Data (XML/CSV)");
        exportData.setOnAction(e -> exportData(primaryStage));

        HBox importExportBox = new HBox(10, importData, exportData);
        importExportBox.setPadding(new Insets(10));

        // Main Layout
        VBox mainLayout = new VBox(10, chatLabel, chatArea, chatBox, stepBox, bluetoothBox, importExportBox);
        mainLayout.setPadding(new Insets(15));

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Chat Interface Logic
    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            chatArea.appendText("You: " + message + "\n");
            // Simulate AI response
            chatArea.appendText("AI: Processing your message...\n");
        }
    }

    // Step Counter Logic
    private void refreshStepCount() {
        // Simulate step count update
        int steps = (int) (Math.random() * 10000); // For testing, you would replace this with actual sensor data
        stepCounter.setText(String.valueOf(steps));
    }

    // Bluetooth Connection Logic
    private void connectBluetooth() {
        try {
            // This is where the Bluetooth connection code would go, using Bluecove or another Bluetooth library
            bluetoothStatus.setText("Bluetooth Status: Connected");
        } catch (Exception e) {
            bluetoothStatus.setText("Bluetooth Status: Connection Failed");
        }
    }

    // Import Data Logic
    private void importData(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Data");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            if (selectedFile.getName().endsWith(".xml")) {
                importXML(selectedFile);
            } else if (selectedFile.getName().endsWith(".csv")) {
                importCSV(selectedFile);
            }
        }
    }

    private void importXML(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(fis);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // Process XML event
            }

            chatArea.appendText("Data imported from XML\n");

        } catch (Exception e) {
            chatArea.appendText("Failed to import XML\n");
        }
    }

    private void importCSV(File file) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process CSV line
            }

            chatArea.appendText("Data imported from CSV\n");

        } catch (IOException e) {
            chatArea.appendText("Failed to import CSV\n");
        }
    }

    // Export Data Logic
    private void exportData(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Data");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            if (file.getName().endsWith(".xml")) {
                exportXML(file);
            } else if (file.getName().endsWith(".csv")) {
                exportCSV(file);
            }
        }
    }

    private void exportXML(File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write("<data>\n");
            writer.write("<steps>" + stepCounter.getText() + "</steps>\n");
            writer.write("</data>");
            chatArea.appendText("Data exported to XML\n");

        } catch (IOException e) {
            chatArea.appendText("Failed to export XML\n");
        }
    }

    private void exportCSV(File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write("Steps\n");
            writer.write(stepCounter.getText() + "\n");
            chatArea.appendText("Data exported to CSV\n");

        } catch (IOException e) {
            chatArea.appendText("Failed to export CSV\n");
        }
    }
}
