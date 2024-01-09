package com.javagame.GUI;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.javagame.GUI.TableGUIComponent.InsideTable;
import com.javagame.GUI.TableGUIComponent.OutSideTable;
import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.entities.Table;
import com.javagame.repositories.DataRepository;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
@Component
public class TableGUI extends Table{
	public static final Player nullPlayer = new Player(-1, "");
	private List<Player> players;
	private List<Card> inCurrentCards;
	public void show(Stage primaryStage) {
		players = getListPlayers();
		OutSideTable outSideTable  = new OutSideTable(this);
		//
		InsideTable insideTable = new InsideTable(this);
		outSideTable.setContentCenter(insideTable);
		//setup logic data
		inCurrentCards = new DataRepository().getAllCards();
		insideTable.setDataTableContent(this);
		//
		Scene scene = new Scene(outSideTable,800,600,Color.BLACK);
		primaryStage.setTitle("Tiến lên");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public List<Card> getInCurrentCards() {
		return inCurrentCards;
	}

	public void setInCurrentCards(List<Card> inCurrentCards) {
		this.inCurrentCards = inCurrentCards;
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public void countdown(int seconds){
		final CountDownLatch latch = new CountDownLatch(1);
		Runnable countdownTask = new Runnable() {
			@Override
			public void run() {
				for (int i = seconds; i >= 0; i--) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				latch.countDown();
			}
		};
		Thread countdownThread = new Thread(countdownTask);
		countdownThread.start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hết thời gian đếm ngược!");
		next();
		System.out.println("Player:"+getTurn());

	}
	
}