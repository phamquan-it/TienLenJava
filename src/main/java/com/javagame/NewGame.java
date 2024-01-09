package com.javagame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NewGame {
	public void show() {
		Stage stage =new Stage();
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(new Button("Button1"));
		Scene scene = new Scene(stackPane,150,100);
		stage.setTitle("New Game");
		stage.setScene(scene);
		stage.show();
	}
}
