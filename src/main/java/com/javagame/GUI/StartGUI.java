package com.javagame.GUI;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@Component
public class StartGUI implements SpringContext{
	public void start(Stage primaryStage) {
		BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, null, null);
		Background background = new Background(backgroundFill);
		BorderPane root = new BorderPane();
		root.setBackground(background);
		Group group = new Group();
		Button play = new Button("Play");
		play.setOnAction(event ->{
			
		});
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(play);
		group.getChildren().add(vBox);
		root.setCenter(group);
		Scene scene = new Scene(root, 800, 600, Color.GRAY);
	    primaryStage.setTitle("NA88");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
