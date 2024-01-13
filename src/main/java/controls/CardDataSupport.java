package controls;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.entities.Card;

@Component
public class CardDataSupport {
	public Card getMinCard(List<Card> cards) {
		Card cardresult = cards.getFirst();
		for (Card card : cards) {
			if(card.getId() < cardresult.getId())
				cardresult = card;
			else if(card.getId() == cardresult.getId() &&
					card.getCategoryid() > cardresult.getCategoryid())
				cardresult = card;
		}
		return cardresult;
	}
	public Card getMaxCard(List<Card> cards) {
		Card cardresult = cards.getFirst();
		for (Card card : cards) {
			if(card.getId() > cardresult.getId())
				cardresult = card;
			else if(card.getId() == cardresult.getId() &&
					card.getCategoryid() < cardresult.getCategoryid())
				cardresult = card;
		}
		return cardresult;
	}
	public String getNameCardType(int cardType) {
		switch (cardType) {
		case 1: 
			return "Cóc";
		case 2: 
			return "Đôi";
		case 3: 
			return "Sam";
		case 4: 
			return "Tứ quý";
		case 5:
			return "Liên kết đơn";
		case 6:
			return "Liên kết đôi";
		case 7:
			return "Liên kết ba";
		default:
			return "Không hợp lệ";
		}
	};
}
