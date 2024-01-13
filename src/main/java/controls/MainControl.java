package controls;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.GUIContext;
import com.javagame.SpringContext;
import com.javagame.GUI.CreateNewGame;
import com.javagame.GUI.TableGUI;
import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.repositories.DataRepository;

import controls.Design.StartDesign;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

@Component
public class MainControl extends ValidData implements StartDesign,GUIContext,SpringContext{
	private CreateNewGame createNewGame;
	private TableGUI tableGUI;
	private List<Player> listInitPlayers;
	private List<Player> listPlayers;
	public MainControl() {
		listInitPlayers = Arrays.asList(null, null, null, null);
		createNewGame = new CreateNewGame();
		
	}
	@Override
	public void playGame(Stage primaryStage) {
		updateListPlayersValue();
		if(listPlayers.size()>1) {
			tableGUI = new TableGUI(listPlayers,primaryStage);
			Scene scene = new Scene(tableGUI, 800,600);
			primaryStage.setScene(scene);
			primaryStage.show();
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
//		// TODO Auto-generated method stub
	}


	@Override
	public void startGame(Stage primaryStage) {
		// TODO Auto-generated method stub
		createNewGame.show(primaryStage, this);
		playGame(primaryStage);
	}
	public void cardsDevice() {
		System.out.println("Chia  bài");
	}
	public void setPlayer(int index,Player player) {
		listInitPlayers.set(index, player);
	}
	public void updateListPlayersValue() {
		
		List<Card> list = new DataRepository().getAllCards();
		Collections.shuffle(list);
	    List<Player> lisPlayers = listInitPlayers;
	    List<Player> lisPlayers2 = new ArrayList<>();
	    for (Player player2 : lisPlayers) {
	    	if (player2 != null) {
	    		List<Card> w = new ArrayList<>();
			    w.addAll(list.subList(0, 13));
			    player2.setListCards(w);
			    lisPlayers2.add(player2);
			    list.removeAll(list.subList(0, 13));
	    	}
		}
	    this.listPlayers = lisPlayers2;
	}
	public void showSize() {
		System.out.println(listPlayers.getLast().getListCards().size());
	}
}
