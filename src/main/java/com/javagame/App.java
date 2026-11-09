package com.javagame;

import java.io.InputStream;

import com.javagame.GUI.StartGUI;

import javafx.application.Application;
import javafx.scene.image.Image;
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
		// icon + tiêu đề giữ như bản gốc
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("icon.png");
		if (inputStream != null)
			primaryStage.getIcons().add(new Image(inputStream));
		primaryStage.setTitle("NA88");
		// đóng cửa sổ thì dọn thread/Timeline đang chạy
		primaryStage.setOnCloseRequest(event -> {
			javafx.application.Platform.exit();
			System.exit(0);
		});
		new StartGUI().start(primaryStage);
	}
	
}
