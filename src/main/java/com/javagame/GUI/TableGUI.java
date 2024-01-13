package com.javagame.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javagame.GUIContext;
import com.javagame.SpringContext;
import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.repositories.DataRepository;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableGUI extends BorderPane implements SpringContext,GUIContext{
	private ListCardsChoose cardsChoose;
	public List<Card> cardsData;
	public BorderPane borderPane;
	public List<Player> listPlayers;
	public List<Card> listCurrentCard;

	public TableGUI(List<Player> listPlayers,Stage primaryStage) {
		middleControl.resetDeathTime(30);
		cardsData = new DataRepository().getAllCards();
		Collections.shuffle(cardsData);
		this.listPlayers = listPlayers;
		listCurrentCard = new ArrayList<>();
		// init middle Controlmi
		middleControl.initTableControl(this.listPlayers);
		
		borderPane = new BorderPane();
		Button button  = new Button("mainFrame");
		Label turnLabel = new Label();
		setBottom(turnLabel);
		setRight(button);
		setLeft(new Label("player4"));
		setFirstPlayer(this);
		setCenter(borderPane);
		Label deathtime = new Label();
		setTop(deathtime);
		Thread thread = new Thread(()->{
			while (true) {
				try {
					Thread.sleep(1000);
					System.out.print(middleControl.getDeathTime());
					Platform.runLater(() -> {
						deathtime.setText(middleControl.getDeathTime()+"");
						turnLabel.setText(middleControl.getTurn()+"");
					});
				} catch (InterruptedException e) {
					System.out.println("Thread interrupted. Exiting...");
		            break;
				}
				
			}
		});
		thread.start();
		button.setOnAction(event->{
			startGUI.start(primaryStage);
			thread.interrupt();
			middleControl.cowndownInterrupt();
		});
	}

	public void setFirstPlayer(TableGUI tableGUI) {
		// setFirst Player
		cardsChoose = new ListCardsChoose(listPlayers.getFirst(), tableGUI);
		List<Card> list = listPlayers.getFirst().getListCards();
		Collections.sort(list);
		cardsChoose.setListCards(list);
		borderPane.setBottom(cardsChoose);
	}

	public void setSecondPlayer(TableGUI tableGUI) {
		// TODO Auto-generated method stub

	}

	public boolean setCurrentCard(List<Card> listCard,Player player) {
		if (player.getId() != middleControl.getTurn()) {
			System.out.println("Chưa đến lượt");
			return false;
		}
		middleControl.nextTurn();
		System.out.print(listCard.getFirst().getName());
		Group group = new Group();
		int translatex = 0;
		for (Card card : listCard) {
			InternalCardGUI internalCardGUI = new InternalCardGUI(card);
			internalCardGUI.setTranslateX(translatex);
			translatex += 30;
			group.getChildren().add(internalCardGUI);
		}
		borderPane.setCenter(group);
//		for (Card card : cardsChoose.getListCards()) {
//			System.out.println(card.getName());
//		}
		return true;
	}
}
