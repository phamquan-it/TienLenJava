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
	public Label status;
	public Button menu;
	public TableGame(ListCardsChoose listCardsChoose) {
		// nút Bỏ lượt dùng chung với nút trên ListCardsChoose
		skip = listCardsChoose.skip;
		BorderPane topPane = new BorderPane();
		top = new Label("Top Player");
		status = new Label("");
		menu = new Button("Menu");
		topPane.setLeft(top);
		topPane.setCenter(status);
		topPane.setRight(menu);
		setTop(topPane);
		right = new Label("Right Player");
		setRight(right);
		BorderPane bottomPane = new BorderPane();
		bottomPane.setTop(listCardsChoose);
		bottom = new Label("Bottom Player");
		setBottom(bottomPane);
		setAlignment(bottom, Pos.CENTER);
		left = new Label("Left Player");
		setLeft(left);
		setAlignment(left, Pos.CENTER);
	}
	public void setTextTop(int second) {
		top.setText(String.valueOf(second));
	}
	public void setStatus(String text) {
		status.setText(text);
	}
	public void setListCardCenter(List<Card> listCard) {
		setCenter(new ListCardCenter(listCard));
	}
}
class ListCardCenter extends Group{
	public ListCardCenter(List<Card> listCard) {
		Collections.sort(listCard);
		// bài trên bàn thường ít lá -> đặt giữa màn hình
		int width = Math.min(130 + Math.max(0, listCard.size() - 1) * 30, 800);
		setLayoutX((800 - width) / 2.0);
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
