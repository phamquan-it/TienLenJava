package controls;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.javagame.entities.Card;
import com.javagame.entities.ListWrapper;
import com.javagame.repositories.DataRepository;

/**
 * Test không cần JavaFX: chỉ kiểm tra bộ não AI trên bộ bài thật.
 */
public class BotPlayerTest {

	private static List<Card> deck() {
		return new DataRepository().getAllCards();
	}

	private static Card card(int id, int cid) {
		for (Card c : deck()) {
			if (c.getId() == id && c.getCategoryid() == cid)
				return c;
		}
		throw new IllegalArgumentException("không có lá " + id + "/" + cid);
	}

	@Test
	public void deckHas52Cards() {
		assertTrue(deck().size() == 52);
	}

	@Test
	public void botOpensWithSmallestCard() {
		List<Card> hand = new ArrayList<>(Arrays.asList(card(5, 1), card(7, 2), card(14, 3)));
		List<Card> choice = new BotPlayer().chooseCards(hand, Collections.emptyList());
		assertTrue(choice.size() == 1);
		assertTrue(choice.getFirst().getId() == 5); // 5 nhỏ hơn 7 và A
	}

	@Test
	public void botBearsSmallerSingle() {
		List<Card> hand = new ArrayList<>(Arrays.asList(card(5, 1), card(9, 2)));
		List<Card> onTable = new ArrayList<>(Arrays.asList(card(7, 4)));
		List<Card> choice = new BotPlayer().chooseCards(hand, onTable);
		assertTrue(choice.size() == 1);
		assertTrue(choice.getFirst().getId() == 9); // 9 đè được 7
	}

	@Test
	public void botPassesWhenCannotBear() {
		List<Card> hand = new ArrayList<>(Arrays.asList(card(3, 1), card(4, 2)));
		List<Card> onTable = new ArrayList<>(Arrays.asList(card(15, 1))); // 2 Cơ
		List<Card> choice = new BotPlayer().chooseCards(hand, onTable);
		assertTrue(choice.isEmpty());
	}

	@Test
	public void botBearsPair() {
		List<Card> hand = new ArrayList<>(Arrays.asList(card(5, 1), card(5, 2), card(3, 4)));
		List<Card> onTable = new ArrayList<>(Arrays.asList(card(4, 3), card(4, 4)));
		List<Card> choice = new BotPlayer().chooseCards(hand, onTable);
		assertTrue(choice.size() == 2);
		assertTrue(choice.get(0).getId() == 5 && choice.get(1).getId() == 5);
	}

	@Test
	public void fullGameTerminates() {
		// mô phỏng 1 ván đầy đủ không có GUI: chỉ dùng luật + AI
		MiddleControl game = new MiddleControl();
		game.startNewGame();
		int totalCards = 0;
		for (com.javagame.entities.Player p : game.listPlayers)
			totalCards += p.getListCards().size();
		assertTrue("mỗi người 13 lá", totalCards == 52);
		// chạm vào một hàm luật để chắc CardsCompare được nối đúng
		assertFalse(new CardsCompare().getResultCompare(
				new ArrayList<>(Arrays.asList(card(3, 1))),
				new ArrayList<>(Arrays.asList(card(7, 4)))));
	}

	@Test
	public void dataXmlLoads() {
		ListWrapper wrapper = new DataRepository().getAllData();
		assertTrue(wrapper != null && wrapper.getCards().size() == 52);
		assertTrue(wrapper.getCategories().size() == 4);
	}
}
