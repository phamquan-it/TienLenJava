package controls;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.javagame.entities.Card;
import com.javagame.repositories.DataRepository;

/** Kiểm tra luật chặt: tứ quý, ba đôi thông, heo. */
public class ChopTest {

	private final CardsCompare compare = new CardsCompare();

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

	private static List<Card> four(int id) {
		return new ArrayList<>(Arrays.asList(card(id, 1), card(id, 2), card(id, 3), card(id, 4)));
	}

	/** Ví dụ 3 đôi thông 4-5-6 (số 4,5,6 phải liền nhau). */
	private static List<Card> threePairs(int low) {
		return new ArrayList<>(Arrays.asList(
				card(low, 1), card(low, 2),
				card(low + 1, 1), card(low + 1, 2),
				card(low + 2, 3), card(low + 2, 4)));
	}

	private static List<Card> single(int id) {
		return new ArrayList<>(Arrays.asList(card(id, 3)));
	}

	private static List<Card> pair(int id) {
		return new ArrayList<>(Arrays.asList(card(id, 1), card(id, 2)));
	}

	// ---------- tứ quý ----------

	@Test
	public void fourOfAKindBeatsSingleTwo() {
		// 2 Chuồn (id 15) là heo đơn -> tứ quý 5 chặt được
		assertTrue(compare.canBeat(four(5), single(15)));
	}

	@Test
	public void fourOfAKindBeatsPairOfTwo() {
		assertTrue(compare.canBeat(four(5), pair(15)));
	}

	@Test
	public void fourOfAKindDoesNotBeatNormalSingle() {
		// tứ quý không dùng để chặn lá thường
		assertFalse(compare.canBeat(four(5), single(9)));
	}

	@Test
	public void biggerFourBeatsSmallerFour() {
		assertTrue(compare.canBeat(four(9), four(5)));
		assertFalse(compare.canBeat(four(5), four(9)));
	}

	// ---------- ba đôi thông ----------

	@Test
	public void threePairsBeatSingleTwo() {
		assertTrue(compare.canBeat(threePairs(6), single(15)));
	}

	@Test
	public void threePairsDoNotBeatPair() {
		// ba đôi thông không chặt được heo đôi
		assertFalse(compare.canBeat(threePairs(6), pair(15)));
	}

	@Test
	public void biggerThreePairsBeatSmaller() {
		assertTrue(compare.canBeat(threePairs(7), threePairs(4)));
		assertFalse(compare.canBeat(threePairs(4), threePairs(7)));
	}

	@Test
	public void threePairsBeatNothingElse() {
		// không chặt được lá thường
		assertFalse(compare.canBeat(threePairs(6), single(9)));
	}

	// ---------- luật thường vẫn phải giữ ----------

	@Test
	public void normalSingleStillCompares() {
		assertTrue(compare.canBeat(single(9), single(7)));
		assertFalse(compare.canBeat(single(3), single(7)));
	}

	@Test
	public void sameSuitOrderStillWorks() {
		// 9 Bích không đè được 9 Cơ (♥ > ♠)
		List<Card> spade = new ArrayList<>(Arrays.asList(card(9, 4)));
		List<Card> heart = new ArrayList<>(Arrays.asList(card(9, 1)));
		assertFalse(compare.canBeat(spade, heart));
		assertTrue(compare.canBeat(heart, spade));
	}

	// ---------- AI có biết chặt ----------

	@Test
	public void botChopsSingleTwoWithFourOfAKind() {
		List<Card> hand = new ArrayList<>();
		hand.add(card(3, 1));
		hand.addAll(four(6));
		hand.add(card(10, 2));
		// trên bàn là 2 Cơ
		List<Card> choice = new BotPlayer().chooseCards(hand, single(15));
		assertTrue("AI phải chặt bằng tứ quý", choice.size() == 4);
		for (Card c : choice)
			assertTrue(c.getId() == 6);
	}

	@Test
	public void botChopsSingleTwoWithThreePairs() {
		List<Card> hand = new ArrayList<>();
		hand.addAll(threePairs(4));
		List<Card> choice = new BotPlayer().chooseCards(hand, single(15));
		assertTrue("AI phải chặt bằng ba đôi thông", choice.size() == 6);
	}

	@Test
	public void botPrefersFourOverThreePairs() {
		List<Card> hand = new ArrayList<>();
		hand.addAll(threePairs(4)); // 4,5,6
		hand.addAll(four(9));       // tứ quý 9
		List<Card> choice = new BotPlayer().chooseCards(hand, single(15));
		assertTrue("AI ưu tiên tứ quý (rẻ hơn) khi chặt heo đơn", choice.size() == 4);
	}
}
