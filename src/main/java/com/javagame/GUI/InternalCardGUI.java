package com.javagame.GUI;

import java.io.InputStream;

import com.javagame.entities.Card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InternalCardGUI  extends ImageView{
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
