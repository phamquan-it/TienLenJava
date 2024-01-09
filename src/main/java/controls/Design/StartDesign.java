package controls.Design;
import com.javagame.entities.Player;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public interface StartDesign {
	default void exitApplication() {
		Alert confirmExit = new Alert(Alert.AlertType.CONFIRMATION);
		confirmExit.setTitle("Exit");
		confirmExit.setHeaderText("Do you want exit app?");
		confirmExit.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		java.util.Optional<ButtonType> result = confirmExit.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
			Platform.exit();
	}
	void playGame(Stage primaryStage);
	default void continueGame() {
		System.out.println("Continue game");
	}
	default void helpGame() {
		System.out.println("Help game");
	}
	default void aboutGame() {
		System.out.println("About game");
	}
	void addPlayer(Player player);
	void startGame(Stage primaryStage);
	
}
