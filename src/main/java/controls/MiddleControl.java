package controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javagame.SpringContext;
import com.javagame.GUI.ListCardsChoose;
import com.javagame.GUI.TableGame;
import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.repositories.DataRepository;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MiddleControl implements SpringContext {
	public volatile TableGame tableGame;
	public volatile ListCardsChoose listCardsChoose;
	public volatile List<Player> listPlayers;
	public volatile List<Player> session;
	public volatile int turn = 0;
	public volatile int deathTime = 30;
	public volatile List<Card> currentListCard;

	public synchronized void showTablePlayer(Stage primaryStage) {
		currentListCard = new ArrayList<Card>();
		listCardsChoose = new ListCardsChoose(new DataRepository().getAllCards().subList(0, 13));
		listCardsChoose.pushCard.setOnAction((e) -> {
			if (lookUpData.getCardType(listCardsChoose.cardsChoose) != -1) {
				setCurentListCard(listCardsChoose.cardsChoose);
				tableGame.setListCardCenter(getCurrentListCard());

				listCardsChoose.listCard.removeAll(listCardsChoose.cardsChoose);
				listCardsChoose.cardsChoose.clear();
				listCardsChoose.updateCardChoose();
			}else {
				System.out.println("Không hợp lệ bé ơi");
			}
		});
		listPlayers = new ArrayList<Player>();
		listPlayers.add(new Player(1, "player1"));
		listPlayers.add(new Player(2, "player2"));
		Thread showTablePlayerThread = new Thread(() -> {
			tableGame = new TableGame(listCardsChoose);
			tableGame.skip.setOnAction((e) -> {
				turn++;
				// getTurn();
//            	listPlayers.add(new  Player(3, "player3"));
//            	showListPlayersSize();
				tableGame.setListCardCenter(new DataRepository().getAllCards().subList(0, 12));
			});

			Scene scene = new Scene(tableGame, 800, 600);

			Platform.runLater(() -> {
				primaryStage.setScene(scene);
				primaryStage.show();
			});
			countDowndDeatime();
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
				tableGame.setTextTop(getDeathTime());
			}));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		});
		showTablePlayerThread.start();
	}

	public synchronized void countDowndDeatime() {
		Thread coundDowndTime = new Thread(() -> {
			while (deathTime > 0) {
				try {
					deathTime--;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			resetDeathTime();
		});
		coundDowndTime.start();
	}

	public synchronized void setDeathTime() {
		deathTime = 0;
	}

	public synchronized void resetDeathTime() {
		deathTime = 30;
	}

	public synchronized int getDeathTime() {
		return deathTime;
	}

	public List<List<Card>> device() {
		List<Card> lst = new ArrayList<>(new DataRepository().getAllCards());
		Collections.shuffle(lst); // Shuffle the list

		List<List<Card>> result = new ArrayList();
		int size = lst.size() / 4; // Split the shuffled list into four parts

		for (int i = 0; i < 3; i++) {
			result.add(lst.subList(i * size, (i + 1) * size));
		}
		// Add the remaining cards to the last sublist
		result.add(lst.subList(3 * size, lst.size()));

		return result;
	}

	public void getTurn() {
		System.out.println(turn);
	}

	public void showListPlayersSize() {
		System.out.println(listPlayers.size());
	}

	public synchronized void setCurentListCard(List<Card> cardListInput) {
		currentListCard = cardListInput;
	}

	public List<Card> getCurrentListCard() {
		return currentListCard;
	}
}
