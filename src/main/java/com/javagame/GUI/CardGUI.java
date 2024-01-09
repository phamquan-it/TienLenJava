package com.javagame.GUI;

import java.io.InputStream;

import com.javagame.entities.Card;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardGUI extends Group{
	private Card card;
	private boolean cardChoose;
	private boolean cartHover;
	private InternalCardGUI internalCardGUI;
	public CardGUI(Card card) {
		internalCardGUI = new InternalCardGUI(card);
		Button cardButton = new Button("choose");
		getChildren().add(cardButton);
		//getChildren().add(internalCardGUI);
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
}

class InternalCardGUI  extends ImageView{
	public InternalCardGUI(Card cardEntity) {
		Card card = cardEntity;
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(card.getImage_link());
	    Image image = new Image(inputStream);
	    setImage(image);
	    setFitHeight(200);
	    setFitWidth(130);
		// TODO Auto-generated constructor stub
	}
	public void setSize(int height, int width) {
		setFitHeight(height);
		setFitWidth(width);
	}
	
}
