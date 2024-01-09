package controls;
import org.springframework.stereotype.Component;

import com.javagame.GUIContext;
import com.javagame.GUI.CreateNewGame;
import com.javagame.GUI.TableGUI;
import com.javagame.entities.Player;
import com.javagame.entities.Table;

import controls.Design.StartDesign;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

@Component
public class MainControl extends ValidData implements StartDesign,GUIContext{
	public MainControl() {
		createNewGame = guiContext.getBean(CreateNewGame.class);
		_table = new Table();
		_table.setListPlayers(createNewGame.getListPlayers());
	}
	private TableGUI tableGUI;
	private Table _table;
	private CreateNewGame createNewGame;
	@Override
	public void playGame(Stage primaryStage) {
		if(numberValidOfPlayers(_table.getListPlayers())) {
			tableGUI = guiContext.getBean(TableGUI.class);
			tableGUI.setListPlayers(_table.getListPlayers());
			tableGUI.show(primaryStage);
		}else {
			Popup popup = new Popup();
			Label label = new Label("Number of players is not valid!");
			label.setStyle(" -fx-background-color: yellow;");
			label.setTextFill(Color.RED);
			popup.getContent().add(label);
			 // set size of label 
	        label.setMinWidth(80); 
	        label.setMinHeight(50); 
			popup.show(primaryStage);
			popup.setAutoHide(true);
			
			System.out.println("not valid");
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame(Stage primaryStage) {
		// TODO Auto-generated method stub
		createNewGame.show(primaryStage);
		playGame(primaryStage);
	}


	
}
