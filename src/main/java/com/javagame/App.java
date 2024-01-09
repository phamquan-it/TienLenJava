package com.javagame;

import java.io.InputStream;
import java.util.List;

import com.javagame.GUI.CardGUI;
import com.javagame.GUI.StartGUI;
import com.javagame.entities.Card;
import com.javagame.repositories.DataRepository;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application implements GUIContext{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
		//guiContext.getBean(StartGUI.class).start(primaryStage);
		Group group = new Group();
		Card card = new DataRepository().getAllCards().getFirst();
		CardGUI cardGUI = new CardGUI(card);
		group.getChildren().add(cardGUI);
		cardGUI.choose();
		cardGUI.unChoose();
		Scene scene = new Scene(group,600,400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
