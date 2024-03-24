package com.javagame.GUI;

import java.util.Collections;
import java.util.List;

import com.javagame.entities.Card;
import com.javagame.repositories.DataRepository;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TableGame extends BorderPane{
	public Button skip;
	public Label top;
	public Label right;
	public Label bottom;
	public Label left;
	public TableGame(ListCardsChoose listCardsChoose) {
		skip = new Button("Bỏ  lượt");
		BorderPane topPane = new BorderPane();
		top = new Label("Top Player");
		topPane.setLeft(top);
		topPane.setRight(new Button("Menu"));
		setTop(topPane);
		right = new Label("Right Player");
		setRight(right);
		BorderPane bottomPane = new BorderPane();
		bottomPane.setTop(listCardsChoose);
		bottomPane.setRight(skip);
		bottom = new Label("Bottom Player");
		bottomPane.setCenter(bottom);
		setBottom(bottomPane);
		setAlignment(bottom, Pos.CENTER);
		left = new Label("Left Player");
		setLeft(left);
		setAlignment(left, Pos.CENTER);
	}
	public void setTextTop(int second) {
		top.setText(String.valueOf(second));
	}
	public void setListCardCenter(List<Card> listCard) {
		setCenter(new ListCardCenter(listCard));
	}
}
class ListCardCenter extends Group{
	public ListCardCenter(List<Card> listCard) {
		Collections.sort(listCard);
		int translatex = 0;
		// TODO Auto-generated constructor stub
		for(Card card: listCard) {
			InternalCardGUI internalCardGUI = new InternalCardGUI(card);
			internalCardGUI.setTranslateX(translatex);
			getChildren().add(internalCardGUI);
			translatex+=30;
		}
	}
}
