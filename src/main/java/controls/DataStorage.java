package controls;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.entities.Card;
import com.javagame.entities.Player;
@Component
public class DataStorage {
	private List<Player> listPlayers;
	private List<Card> listCards;
	public List<Player> getListPlayers() {
		return listPlayers;
	}
	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}
	public List<Card> getListCards() {
		return listCards;
	}
	public void setListCards(List<Card> listCards) {
		this.listCards = listCards;
	}
}
