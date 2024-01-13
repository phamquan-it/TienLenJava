package com.javagame.GUI;

import com.javagame.entities.Card;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CardGUI extends VBox{
	private Card card;
	private boolean cardChoose;
	private InternalCardGUI internalCardGUI;
	private Label label;
	Button cardButton;
	public CardGUI(Card card ) {
		this.card = card;
		label = new Label("");
		label.setTranslateX(10);
		label.setTextFill(Color.ORANGE);
		internalCardGUI = new InternalCardGUI(card);
		cardButton = new Button();
		cardButton.setGraphic(internalCardGUI);
		cardButton.setStyle(
                "-fx-background-color: transparent; " +
                "-fx-border-color: transparent; " +
                "-fx-border-width: 0; " +
                "-fx-border-radius: 0; " +
                "-fx-background-radius: 0; " +
                "-fx-padding: 0; " +
                "-fx-margin: 0;"
        );
		cardButton.setOnAction((event)->{
			if(isCardChoose()) unChoose();
			else choose();
		});
		
		getChildren().add(label);
		getChildren().add(cardButton);
		//getChildren().add(internalCardGUI);
		setMaxHeight(200);
		setMaxWidth(130);
		setOnMouseEntered(event->{
			label.setText("*");
		});
		setOnMouseExited(event->{
			label.setText("");
		});
	}
	public void setImageSize(int width,int height) {
		internalCardGUI.setSize(height, width);
	}
	public void choose() {
		cardChoose = true;
		setTranslateY(-30);
	}
	public void unChoose() {
		cardChoose = false;
		setTranslateY(0);
	}
	public boolean isCardChoose() {
		return cardChoose;
	}
	public Card getCard() {
		return card;
	}
}

