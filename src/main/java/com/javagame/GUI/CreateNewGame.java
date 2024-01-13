package com.javagame.GUI;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.javagame.entities.Player;

import controls.MainControl;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
@Component
public class CreateNewGame{

	public void show(Stage parentStage, MainControl mainControl){
		//init Stage
		Stage modalStage = new Stage();
		modalStage.initModality(Modality.APPLICATION_MODAL);
		modalStage.initStyle(StageStyle.UNDECORATED);
		modalStage.setTitle("Modal Window");
		modalStage.setResizable(false);
		modalStage.setMinWidth(300);
		VBox hboxInitPlayers = new VBox(4);
		Player computer = new Player(-1,"None");
		Player bao = new Player(1,"Bao lasvegas");
		ComboBox<Player> player1 = new ComboBox<>(
				FXCollections.observableArrayList(Arrays.asList(computer, bao)));
		player1.setValue(computer);
		player1.setOnAction(event -> {
			mainControl.setPlayer(0,player1.getValue());
		});
		ComboBox<Player> player2 = new ComboBox<>(
				FXCollections.observableArrayList(Arrays.asList(computer, new Player(2, "Fun88"))));
		player2.setValue(computer);
		player2.setOnAction(event -> {
			mainControl.setPlayer(1,player2.getValue());
		});
		ComboBox<Player> player3 = new ComboBox<>(
				FXCollections.observableArrayList(Arrays.asList(computer, new Player(3,"Fb88"))));
		player3.setValue(computer);
		player3.setOnAction(event -> {
			mainControl.setPlayer(2,player3.getValue());
		});
		ComboBox<Player> player4 = new ComboBox<>(
				FXCollections.observableArrayList(Arrays.asList(computer, new Player(4, "M88"))));
		player4.setValue(computer);
		player4.setOnAction(event -> {
			mainControl.setPlayer(3,player4.getValue());
		});
		hboxInitPlayers.getChildren().add(player1);
		hboxInitPlayers.getChildren().add(player2);
		hboxInitPlayers.getChildren().add(player3);
		hboxInitPlayers.getChildren().add(player4);
		Button closeButton = new Button("Ok");
		closeButton.setOnAction(e -> {
			modalStage.close();
		});
		Label label = new Label("Create New Game");
		// init layout
		BorderPane modalLayout = new BorderPane();
		modalLayout.setTop(label);
		modalLayout.setCenter(hboxInitPlayers);
		modalLayout.setBottom(closeButton);
		modalLayout.setPadding(new Insets(20));
		// setup  model  stage
		modalStage.setScene(new Scene(modalLayout, 400, 300));
		modalStage.setX(parentStage.getX()+50);
		modalStage.setY(parentStage.getY()+100);
		modalStage.initOwner(parentStage);
		modalStage.showAndWait();
	}
}
