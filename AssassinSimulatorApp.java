package org.example.assassinsimulatorapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AssassinSimulatorApp extends Application {

    private Assassin game = new Assassin(); // Your game logic class

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assassin Simulator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Input and button for finding a target
        TextField searchField = new TextField();
        searchField.setPromptText("Enter name to find target");
        GridPane.setConstraints(searchField, 0, 0);
        Button searchButton = new Button("Find Target");
        GridPane.setConstraints(searchButton, 1, 0);
        searchButton.setOnAction(e -> {
            String target = game.findTarget(searchField.getText());
            showAlert("Target Found", searchField.getText() + "'s target is " + target);
        });

        // Fields and button for assassination
        TextField assassinField = new TextField();
        assassinField.setPromptText("Assassin's name");
        GridPane.setConstraints(assassinField, 0, 1);
        TextField targetField = new TextField();
        targetField.setPromptText("Target's name");
        GridPane.setConstraints(targetField, 1, 1);
        Button assassinateButton = new Button("Assassinate");
        GridPane.setConstraints(assassinateButton, 2, 1);
        assassinateButton.setOnAction(e -> {
            String result = game.assassination(assassinField.getText(), targetField.getText());
            showAlert("Assassination Attempt", result);
        });

        // Button for displaying competitors
        Button competitorsButton = new Button("Show Competitors");
        GridPane.setConstraints(competitorsButton, 0, 2);
        competitorsButton.setOnAction(e -> showAlert("Competitors", game.competitorsLeft()));

        // Button for displaying competitors with targets
        Button competitorsWithTargetsButton = new Button("Show Competitors And Targets");
        GridPane.setConstraints(competitorsWithTargetsButton, 2, 0);
        competitorsButton.setOnAction(e -> showAlert("Competitors And Targets", game.competitorsAndTargetsLeft()));

        // Button for displaying leaderboard
        Button leaderboardButton = new Button("Show Leaderboard");
        GridPane.setConstraints(leaderboardButton, 1, 2);
        leaderboardButton.setOnAction(e -> showAlert("Leaderboard", game.leaderBoard()));

        grid.getChildren().addAll(searchField, searchButton, assassinField, targetField, assassinateButton, competitorsButton, leaderboardButton);

        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
