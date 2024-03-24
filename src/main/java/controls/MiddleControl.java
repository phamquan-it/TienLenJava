package controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
	public MiddleControl() {
		listPlayers = new ArrayList<Player>();
		listPlayers.add(new Player(1, "player1"));
		listPlayers.add(new Player(2, "player2"));
		listPlayers.add(new Player(3, "player3"));
		listPlayers.add(new Player(4, "player4"));
		setSession(listPlayers);
	}
	public volatile TableGame tableGame;
	public volatile ListCardsChoose listCardsChoose;
	public volatile List<Player> listPlayers;
	public volatile List<Player> session;
	public volatile int turn = 1;
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

		Thread showTablePlayerThread = new Thread(() -> {
			tableGame = new TableGame(listCardsChoose);
			tableGame.skip.setOnAction((e) -> {
				
				// getTurn();
//            	listPlayers.add(new  Player(3, "player3"));
//            	showListPlayersSize();
				System.out.println(session);
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
			outSession(turn);
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

	public int getTurn() {
		return turn;
	}
	public synchronized void setTurn(int turnPlayer) {
		turn = turnPlayer;
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
	//next turn
	public synchronized void nextTurn() {
		
		Iterator<Player> iterator = session.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (player.getId()== getTurn() && iterator.hasNext()) {
				setTurn(iterator.next().getId());
			}else {
				setTurn(session.getFirst().getId());
			}
		}
		if (session.size()==1) {
			setSession(listPlayers);
		}
		System.out.println("Lượt:"+getTurn());
		System.out.println("Số người chơi còn  lại:"+session.size());
		countDowndDeatime();
	}
	public synchronized void outSession(int playerId) {
		for(int i =0; i< session.size();i++) {
			if(session.get(i).getId() == playerId && session.size()>1) {
				session.remove(session.remove(i));
				break;
			}
		}
		nextTurn();
		
	}
	public synchronized void setSession(List<Player> listPlayer) {
		session = new ArrayList<Player>();
		session.addAll(listPlayer);
	}
}
