package com.javagame;

import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.image.Image;
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
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("icon.png");
		primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

	    Image image = new Image(inputStream);
	    primaryStage.setTitle("NA88");
	    primaryStage.getIcons().add(image);

		startGUI.start(primaryStage);
	}
	
}
