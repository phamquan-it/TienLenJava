package com.javagame.GUI;

import com.javagame.entities.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class PlayerGUI extends Group{
	private Player _player;
	Label name;
	public PlayerGUI(Player player) {
		_player = player;
		name = new Label(_player.getName());
		name.setTextFill(Color.WHITE);
		getChildren().add(name);
	}
	public void setMainPlayer() {
		name.setTextFill(Color.YELLOW);
	}
	public void setCommonPlayer() {
		name.setTextFill(Color.WHITE);
	}
}