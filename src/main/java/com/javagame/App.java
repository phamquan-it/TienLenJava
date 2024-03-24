package com.javagame;

import com.javagame.entities.Card;
import com.javagame.repositories.DataRepository;

import controls.MiddleControl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		TableGame tableGame = new TableGame();
//		StackPane gridPane = new StackPane();
//		gridPane.getChildren().add(tableGame);
//		Scene scene = new Scene(gridPane, 800, 600);
//		primaryStage.setScene(scene);
//		ClassLoader classLoader = getClass().getClassLoader();
//		InputStream inputStream = classLoader.getResourceAsStream("icon.png");
//		primaryStage.setOnCloseRequest(event -> {
//            System.exit(0);
//        });
//
//	    Image image = new Image(inputStream);
//	    primaryStage.setTitle("NA88");
//	    primaryStage.getIcons().add(image);
//	    primaryStage.show();
		MiddleControl middleControl = new MiddleControl();
		middleControl.showTablePlayer(primaryStage);
	
	}
	
}
