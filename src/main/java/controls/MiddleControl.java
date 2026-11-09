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
	/** Người chơi thật luôn ở ghế 1, 3 ghế còn lại là AI. */
	public static final int HUMAN_ID = 1;
	private static final long AI_THINK_MS = 700;

	public MiddleControl() {
		listPlayers = new ArrayList<Player>();
		listPlayers.add(new Player(1, "Bạn"));
		listPlayers.add(new Player(2, "Fun88 (AI)"));
		listPlayers.add(new Player(3, "Fb88 (AI)"));
		listPlayers.add(new Player(4, "M88 (AI)"));
		setSession(listPlayers);
	}
	public volatile TableGame tableGame;
	public volatile ListCardsChoose listCardsChoose;
	public volatile List<Player> listPlayers;
	public volatile List<Player> session;
	public volatile int turn = 1;
	public volatile int deathTime = 30;
	public volatile List<Card> currentListCard;

	// ---- trạng thái ván chơi dùng cho cả người và AI ----
	/** Bài người chơi thật đang giữ (ListCardsChoose đọc trực tiếp list này). */
	private List<Card> humanHand;
	/** Bài trên bàn: người gần nhất đã đánh. Rỗng = chưa ai đánh trong vòng. */
	private List<Card> tableCards;
	/** Người vừa đánh bài trên bàn; ai lấy được vòng thì thắng. */
	private int lastPlayerId;
	private volatile boolean gameOver;
	private volatile String status = "Bắt đầu ván mới";

	public List<Card> getHumanHand() {
		return humanHand;
	}
	public List<Card> getTableCards() {
		return tableCards;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public String getStatus() {
		return status;
	}
	public boolean isHumanTurn() {
		return !gameOver && getTurn() == HUMAN_ID;
	}
	public boolean isHumanWinner() {
		return gameOver && lastPlayerId == HUMAN_ID;
	}

	/**
	 * Chia 52 lá cho 4 ghế, tìm người có 3 bích đi trước.
	 * Giữ nguyên cách gọi DataRepository như bản gốc.
	 */
	public synchronized void startNewGame() {
		startNewGame(false);
	}

	/** @param withUI true khi có TableGame để hẹn giờ AI qua Timeline */
	private synchronized void startNewGame(boolean withUI) {
		List<Card> deck = new ArrayList<>(new DataRepository().getAllCards());
		Collections.shuffle(deck);
		for (int i = 0; i < listPlayers.size(); i++) {
			Player p = listPlayers.get(i);
			p.setListCards(new ArrayList<>(deck.subList(i * 13, (i + 1) * 13)));
		}
		humanHand = listPlayers.get(HUMAN_ID - 1).getListCards();
		tableCards = new ArrayList<>();
		currentListCard = new ArrayList<>();
		lastPlayerId = 0;
		gameOver = false;
		setSession(listPlayers);
		setTurn(firstTurnPlayerId());
		status = "Ván mới — lượt của " + nameOf(turn);
		resetDeathTime();
		if (withUI)
			aiLoop();
	}

	private int firstTurnPlayerId() {
		for (Player p : listPlayers) {
			for (Card c : p.getListCards()) {
				// id 3 + category 3 (Chuồn) = 3 bích
				if (c.getId() == 3 && c.getCategoryid() == 3)
					return p.getId();
			}
		}
		return listPlayers.getFirst().getId();
	}

	private String nameOf(int playerId) {
		for (Player p : listPlayers) {
			if (p.getId() == playerId)
				return p.getName();
		}
		return "?";
	}

	/** Người chơi thật bấm "Quất". Kiểm tra luật thật sự, không chỉ kiểm tra bộ bài. */
	public synchronized void playHuman() {
		if (gameOver)
			return;
		if (getTurn() != HUMAN_ID) {
			status = "Chưa đến lượt của bạn!";
			refreshUI();
			return;
		}
		List<Card> chosen = listCardsChoose.cardsChoose;
		if (chosen == null || chosen.isEmpty()) {
			status = "Chọn bài trước đã.";
			refreshUI();
			return;
		}
		if (!isLegalPlay(chosen)) {
			status = "Bộ bài không hợp lệ, hoặc đè không nổi bài trên bàn.";
			refreshUI();
			return;
		}
		commitPlay(HUMAN_ID, new ArrayList<>(chosen));
		humanHand.removeAll(chosen);
		listCardsChoose.cardsChoose.clear();
		listCardsChoose.updateCardChoose();
		refreshUI();
		if (!gameOver)
			aiLoop();
	}

	/** Người chơi thật bấm "Bỏ lượt". */
	public synchronized void skipHuman() {
		if (gameOver || getTurn() != HUMAN_ID)
			return;
		if (tableCards.isEmpty()) {
			status = "Bạn mở vòng, không thể bỏ lượt.";
			refreshUI();
			return;
		}
		passTurn(HUMAN_ID);
		refreshUI();
		if (!gameOver)
			aiLoop();
	}

	private boolean isLegalPlay(List<Card> chosen) {
		if (lookUpData.getCardType(chosen) == -1)
			return false;
		// canBeat gom toàn bộ luật chặt (tứ quý, 3 đôi thông, heo) và so bộ thường
		return cardsCompare.canBeat(chosen, tableCards);
	}

	/** Ghi nhận lượt đánh và kiểm tra thắng / kết thúc vòng. */
	private void commitPlay(int playerId, List<Card> cards) {
		tableCards = new ArrayList<>(cards);
		currentListCard = new ArrayList<>(cards);
		lastPlayerId = playerId;
		status = nameOf(playerId) + " đánh " + describe(cards);
		Player p = findPlayer(playerId);
		if (p != null)
			p.getListCards().removeAll(cards);
		resetDeathTime();
		if (p != null && p.getListCards().isEmpty()) {
			gameOver = true;
			status = nameOf(playerId) + " hết bài và thắng ván!";
			return;
		}
		advanceTurn();
	}

	/** Bỏ lượt: nếu là người đang mở vòng thì không bỏ được (đã chặn ở UI). */
	private void passTurn(int playerId) {
		status = nameOf(playerId) + " bỏ lượt.";
		resetDeathTime();
		advanceTurn();
		// Vòng đã về lại người đánh bài trên bàn -> mọi người bỏ lượt, bàn được dọn.
		if (tableCards != null && !tableCards.isEmpty() && getTurn() == lastPlayerId) {
			tableCards.clear();
			currentListCard = new ArrayList<>();
		}
	}

	private void advanceTurn() {
		List<Integer> order = new ArrayList<>();
		for (Player p : session)
			order.add(p.getId());
		int idx = order.indexOf(getTurn());
		if (idx < 0)
			idx = 0;
		setTurn(order.get((idx + 1) % order.size()));
		// AI cầm lượt -> hẹn giờ cho nó tự đi (chỉ khi đã có bàn chơi/UI)
		if (getTurn() != HUMAN_ID && tableGame != null)
			scheduleNextTurn();
	}

	/** Hẹn giờ cho AI đi, dùng Timeline của JavaFX thay vì để Thread tự chạy. */
	private void scheduleNextTurn() {
		Timeline delay = new Timeline(new KeyFrame(Duration.millis(AI_THINK_MS), e -> {
			if (!gameOver && getTurn() != HUMAN_ID)
				aiTurn();
		}));
		delay.setCycleCount(1);
		delay.play();
	}

	/** Giúp UI có dữ liệu mới: đẩy nhịp 100ms cập nhật bàn và bài người chơi. */
	private void refreshUI() {
		if (tableGame == null)
			return;
		// chụp bản sao trước, vì tableCards có thể bị sửa tiếp trong lúc chờ UI
		List<Card> snapshot = tableCards == null ? new ArrayList<>() : new ArrayList<>(tableCards);
		Platform.runLater(() -> tableGame.setListCardCenter(snapshot));
	}

	/** Vòng lặp AI: nếu lượt là AI thì tự chọn bài và đánh. */
	void aiTurn() {
		if (gameOver)
			return;
		int id = getTurn();
		Player bot = findPlayer(id);
		if (bot == null || bot.getId() == HUMAN_ID)
			return;
		List<Card> choice = new BotPlayer().chooseCards(bot.getListCards(), tableCards);
		if (choice.isEmpty()) {
			passTurn(id);
			refreshUI();
			return;
		}
		commitPlay(id, choice);
		refreshUI();
	}

	private void aiLoop() {
		// chưa có bàn chơi (test headless / mới khởi tạo) -> không dùng Timeline
		if (tableGame != null && !gameOver && getTurn() != HUMAN_ID)
			scheduleNextTurn();
	}

	/**
	 * Cho cả 4 ghế (kể cả ghế 1) tự chơi đến hết ván.
	 * Dùng để kiểm tra luật + AI mà không cần GUI; KHÔNG dùng khi đang chạy app.
	 */
	public synchronized void playAutomatic() {
		int guard = 2000;
		while (!gameOver && guard-- > 0) {
			int id = getTurn();
			Player p = findPlayer(id);
			if (p == null)
				break;
			List<Card> choice = new BotPlayer().chooseCards(p.getListCards(), tableCards);
			if (choice.isEmpty()) {
				passTurn(id);
			} else {
				commitPlay(id, choice);
			}
		}
	}

	private Player findPlayer(int playerId) {
		for (Player p : listPlayers) {
			if (p.getId() == playerId)
				return p;
		}
		return null;
	}

	private String describe(List<Card> cards) {
		StringBuilder sb = new StringBuilder();
		for (Card c : cards) {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(c.getName());
		}
		return sb.toString();
	}

	public synchronized void showTablePlayer(Stage primaryStage) {
		currentListCard = new ArrayList<Card>();
		startNewGame(true);
		listCardsChoose = new ListCardsChoose(humanHand);
		listCardsChoose.pushCard.setOnAction((e) -> playHuman());
		listCardsChoose.skip.setOnAction((e) -> skipHuman());

		Thread showTablePlayerThread = new Thread(() -> {
			tableGame = new TableGame(listCardsChoose);
			Scene scene = new Scene(tableGame, 800, 600);
			Platform.runLater(() -> {
				primaryStage.setScene(scene);
				primaryStage.show();
				tableGame.setListCardCenter(new ArrayList<>());
			});
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
				tableGame.setTextTop(getDeathTime());
				tableGame.setStatus(getStatus());
			}));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			// bàn đã dựng xong -> nếu lượt đầu là AI thì cho AI đi
			Platform.runLater(this::aiLoop);
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
					return;
				}
			}
			// Hết giờ -> người đang cầm lượt bị bỏ lượt tự động (chỉ khi không phải người thật)
			if (!gameOver && getTurn() != HUMAN_ID) {
				aiTurn();
			}
			resetDeathTime();
		});
		coundDowndTime.setDaemon(true);
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

	/** Chia bài cho các ghế (giữ nguyên hàm gốc, không còn người dùng nhưng để nguyên). */
	public List<List<Card>> device() {
		List<Card> lst = new ArrayList<>(new DataRepository().getAllCards());
		Collections.shuffle(lst);
		List<List<Card>> result = new ArrayList();
		int size = lst.size() / 4;
		for (int i = 0; i < 3; i++) {
			result.add(new ArrayList<>(lst.subList(i * size, (i + 1) * size)));
		}
		result.add(new ArrayList<>(lst.subList(3 * size, lst.size())));
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

	public synchronized void nextTurn() {
		advanceTurn();
	}

	/** API cũ giữ nguyên chữ ký để không vỡ chỗ khác gọi. */
	public synchronized void outSession(int playerId) {
		for (int i = 0; i < session.size(); i++) {
			if (session.get(i).getId() == playerId) {
				session.remove(i);
				break;
			}
		}
		advanceTurn();
	}

	public synchronized void setSession(List<Player> listPlayer) {
		session = new ArrayList<Player>();
		session.addAll(listPlayer);
	}
}
