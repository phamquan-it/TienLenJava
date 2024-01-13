package controls;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;
import com.javagame.entities.Card;

@Component
public class LookUpData implements SpringContext{
	public int getCardType(List<Card> cards) {
		//CHiếu theo định luật nhìn ta thấy :)
		if (cardsValid.isSingleCard(cards))
			return 1;
		else if(cardsValid.isDoubleCards(cards))
			return 2;
		else if (cardsValid.isThreeCards(cards))
			return 3;
		else if (cardsValid.isFourCards(cards)) 
			return 4;
		else if(cardsValid.isChainCards(cards))
			return 5;
		else if (cardsValid.isDoubleChainCard(cards))
			return 6;
		else if(cardsValid.isThreeChainCard(cards))
			return 7;
		else 
			return -1;
	}
}
