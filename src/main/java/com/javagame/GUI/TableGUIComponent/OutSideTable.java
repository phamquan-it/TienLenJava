package com.javagame.GUI.TableGUIComponent;

import com.javagame.GUI.TableGUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class OutSideTable extends BorderPane{
	Label countDownTimePlayer;
	public OutSideTable(TableGUI tableGUI) {
		// TODO Auto-generated constructor stub
		BackgroundFill backgroundFill = new BackgroundFill(Color.DARKSLATEGREY, null, null);
		Background background = new Background(backgroundFill);
		setBackground(background);
		countDownTimePlayer = new Label("30");
		countDownTimePlayer.setTextFill(Color.YELLOW);
		Button menu = new Button("Menu");
		Button push = new Button("Đánh bài");
		Button skip = new Button("Bỏ lượt");
		BorderPane topLayout = new BorderPane();
		topLayout.setRight(menu);
		topLayout.setLeft(countDownTimePlayer);
		BorderPane bottomLayout = new BorderPane();
		bottomLayout.setCenter(push);
		bottomLayout.setRight(skip);
		
		setTop(topLayout);
		setBottom(bottomLayout);
	}
	public void setContentCenter(InsideTable insideTable) {
		setCenter(insideTable);
	}
}
