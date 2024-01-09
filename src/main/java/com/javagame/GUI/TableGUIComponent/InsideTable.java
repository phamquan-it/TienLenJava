package com.javagame.GUI.TableGUIComponent;

import java.io.InputStream;
import java.util.List;

import com.javagame.GUI.PlayerGUI;
import com.javagame.GUI.TableGUI;
import com.javagame.entities.Card;
import com.javagame.repositories.DataRepository;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InsideTable extends BorderPane{
	public InsideTable(TableGUI tableGUI) {
		PlayerGUI playerGUI = new PlayerGUI(
				(tableGUI.getPlayers().get(0) !=null)?
						tableGUI.getPlayers().get(0) : TableGUI.nullPlayer);
		playerGUI.setMainPlayer();
		PlayerGUI player1 = new PlayerGUI(
				(tableGUI.getPlayers().get(1) !=null)?
						tableGUI.getPlayers().get(1) : TableGUI.nullPlayer);
		PlayerGUI player2 = new PlayerGUI(
				(tableGUI.getPlayers().get(2) !=null)?
						tableGUI.getPlayers().get(2) : TableGUI.nullPlayer);
		PlayerGUI player4 = new PlayerGUI(
				(tableGUI.getPlayers().get(3) !=null)?
						tableGUI.getPlayers().get(3) : TableGUI.nullPlayer);
		setTop(player1);
		BorderPane.setAlignment(player1, Pos.CENTER);
		BorderPane.setAlignment(player2, Pos.CENTER);
		BorderPane.setAlignment(playerGUI, Pos.CENTER);
		BorderPane.setAlignment(player4, Pos.CENTER);
		setRight(player2);
		setBottom(playerGUI);
		setLeft(player4);
	}
	public void setDataTableContent(TableGUI tableGUI) {
		Group group = new Group();
		Label label = new Label("Turn"+tableGUI.getTurn());
		Button button = new Button("Next Turn");
		button.setTranslateY(100);
		button.setOnAction(e -> {
			tableGUI.next(); label.setText("Turn"+tableGUI.getTurn());
		});
		group.getChildren().add(centerCardsFromPlayer(tableGUI.getInCurrentCards()));
		group.getChildren().add(label);
		group.getChildren().add(button);
		setCenter(group);
	}
	public Group centerCardsFromPlayer(List<Card> currentCards) {
		List<Card> cards = currentCards;
		Group group = new Group();
		Label label = new Label("12345");
		group.getChildren().add(label);
		
		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(cards.getFirst().getId());
		int translate = 20;
		for (Card card : cards) {
			InputStream inputStream = classLoader.getResourceAsStream(card.getImage_link());
		    Image image = new Image(inputStream);
		    ImageView imageView = new ImageView(image);
		    imageView.setFitHeight(200);
		    imageView.setFitWidth(130);
		    imageView.setTranslateX(translate);
		    group.getChildren().add(imageView);
		    translate+=30;
		}
		return group;
	}
}
