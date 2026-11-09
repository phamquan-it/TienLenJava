package controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.javagame.entities.Card;

/**
 * Mô phỏng cả ván chơi ở chế độ headless (không có JavaFX):
 * luật, chia bài, lượt đi, AI và điều kiện thắng.
 */
public class GameSimulationTest {

	@Test
	public void fourPlayersGetThirteenCardsEach() {
		MiddleControl game = new MiddleControl();
		game.startNewGame();
		int total = 0;
		for (com.javagame.entities.Player p : game.listPlayers) {
			assertEquals(13, p.getListCards().size());
			total += p.getListCards().size();
		}
		assertEquals(52, total);
	}

	@Test
	public void gameMustFinishAndAnnounceWinner() {
		MiddleControl game = new MiddleControl();
		game.startNewGame();
		game.playAutomatic();
		assertTrue("ván phải kết thúc", game.isGameOver());
		assertFalse("phải có người thắng", game.getStatus().isEmpty());
		System.out.println("Kết thúc: " + game.getStatus());
	}

	@Test
	public void threeBichStartsTheGame() {
		MiddleControl game = new MiddleControl();
		game.startNewGame();
		int firstTurn = game.getTurn();
		boolean holderHas3Bich = false;
		for (com.javagame.entities.Player p : game.listPlayers) {
			if (p.getId() != firstTurn)
				continue;
			for (Card c : p.getListCards()) {
				if (c.getId() == 3 && c.getCategoryid() == 3)
					holderHas3Bich = true;
			}
		}
		assertTrue("người đi đầu phải giữ 3 bích", holderHas3Bich);
	}
}
