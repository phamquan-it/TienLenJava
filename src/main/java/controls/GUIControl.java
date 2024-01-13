package controls;

import org.springframework.stereotype.Component;

import com.javagame.GUIContext;
import com.javagame.GUI.StartGUI;

import javafx.stage.Stage;

@Component
public class GUIControl implements GUIContext{
	private StartGUI startGUI;
	public void showStartGUI(Stage primaryState) {
		startGUI = guiContext.getBean(StartGUI.class);
		startGUI.start(primaryState);
	}

}
