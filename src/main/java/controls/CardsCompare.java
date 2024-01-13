package controls;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;
import com.javagame.entities.Card;
@Component
public class CardsCompare implements SpringContext{
	public boolean singleCardCompare(List<Card> currentPlayer,List<Card>  oldPlayer) {
		// TODO Auto-generated method stub
		if(cardsValid.isSingleCard(oldPlayer)
				&& cardsValid.isSingleCard(currentPlayer)) {
			if(currentPlayer.getFirst().getId()
					> oldPlayer.getFirst().getId())
				return true;
			else if(currentPlayer.getFirst().getId() == oldPlayer.getFirst().getId())
				if(currentPlayer.getFirst().getCategoryid()
						< oldPlayer.getFirst().getCategoryid())
					return true;
		}
		return false;
	}
	public boolean doubleCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(cardsValid.isDoubleCards(oldCardsPlayer) && cardsValid.isDoubleCards(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean threeCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(cardsValid.isThreeCards(oldCardsPlayer) && cardsValid.isThreeCards(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean fourCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(cardsValid.isFourCards(oldCardsPlayer) && cardsValid.isFourCards(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean chainCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(currentCardsPlayer.size() != oldCardsPlayer.size()) return false;
		if(cardsValid.isChainCards(oldCardsPlayer) && cardsValid.isChainCards(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean doubleChainCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(currentCardsPlayer.size() != oldCardsPlayer.size()) return false;
		if(cardsValid.isDoubleChainCard(oldCardsPlayer) && cardsValid.isDoubleChainCard(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean threeChainCardCompare(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if(currentCardsPlayer.size() != oldCardsPlayer.size()) return false;
		if(cardsValid.isThreeChainCard(oldCardsPlayer) && cardsValid.isThreeChainCard(currentCardsPlayer)) {
			return cardsCompare.singleCardCompare(
					Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
					Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer))
					); 
		}
		return false;
	}
	public boolean isMinimumPower(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		//Chặn heo đơn
		if(cardsValid.isFourCards(currentCardsPlayer)
				&& cardsValid.isSingleCard(oldCardsPlayer)
				&& oldCardsPlayer.getFirst().getId() ==15
				|| 
				cardsValid.isDoubleChainCard(currentCardsPlayer)
				&& cardsValid.isSingleCard(oldCardsPlayer)
				&& oldCardsPlayer.getFirst().getId() ==15
				||
				cardsValid.isThreeChainCard(currentCardsPlayer)
				&& cardsValid.isSingleCard(oldCardsPlayer)
				&& oldCardsPlayer.getFirst().getId() ==15
				)
			return true;
		return false;
	}
	public boolean isMediumPower(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		//Chặn heo đôi
		if(cardsValid.isFourCards(currentCardsPlayer) 
				&& cardsValid.isDoubleCards(oldCardsPlayer)
				&& oldCardsPlayer.getFirst().getId() == 15
				||
				cardsValid.isDoubleChainCard(currentCardsPlayer)
				&& currentCardsPlayer.size()>= 10
				&& cardsValid.isDoubleCards(oldCardsPlayer)
				&& oldCardsPlayer.getFirst().getId() == 15)
			return true;
		return false;
	}
	public boolean isUntilmatePower(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		//Chặn tứ quý
		if(cardsValid.isFourCards(oldCardsPlayer)
				&& cardsValid.isDoubleChainCard(currentCardsPlayer)
				&& currentCardsPlayer.size() >= 10)
			return true;
		return false;
	}
	public boolean getResultCompare(List<Card> currentCardsPlayer
			, List<Card> oldCardsPlayer) {
		if(cardsValid.isSingleCard(currentCardsPlayer)
				&& cardsValid.isSingleCard(oldCardsPlayer))
			//single  compare
			return singleCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isDoubleCards(currentCardsPlayer)
				&& cardsValid.isDoubleCards(oldCardsPlayer))
			//double compare
			return doubleCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isThreeCards(currentCardsPlayer)
				&& cardsValid.isThreeCards(oldCardsPlayer))
			//three compare
			return threeCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isFourCards(currentCardsPlayer)
				&& cardsValid.isFourCards(oldCardsPlayer))
			//four compare
			return fourCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isChainCards(currentCardsPlayer)
				&& cardsValid.isChainCards(oldCardsPlayer))
			//chain compare
			return chainCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isDoubleChainCard(currentCardsPlayer)
				&& cardsValid.isDoubleChainCard(oldCardsPlayer))
			//chain double compare
			return doubleChainCardCompare(currentCardsPlayer, oldCardsPlayer);
		else if(cardsValid.isThreeChainCard(currentCardsPlayer)
				&& cardsValid.isThreeChainCard(oldCardsPlayer))
			//chain three compare
			return threeChainCardCompare(currentCardsPlayer, oldCardsPlayer);
		if(cardsValid.isDoubleCards(oldCardsPlayer) 
				&& oldCardsPlayer.getFirst().getId()==15) {
			if (cardsValid.isFourCards(currentCardsPlayer)
					|| cardsValid.isDoubleChainCard(currentCardsPlayer) 
					&& currentCardsPlayer.size()>=10) {
				return isMediumPower(currentCardsPlayer, oldCardsPlayer);
			}
		}
		if(cardsValid.isSingleCard(oldCardsPlayer) 
				&& oldCardsPlayer.getFirst().getId()==15) {
			if (cardsValid.isFourCards(currentCardsPlayer)
					|| cardsValid.isDoubleChainCard(currentCardsPlayer)
					|| cardsValid.isThreeChainCard(currentCardsPlayer)) {
				
				return isMinimumPower(currentCardsPlayer, oldCardsPlayer);
			}
		}
		if(cardsValid.isFourCards(oldCardsPlayer)
				&& cardsValid.isDoubleChainCard(currentCardsPlayer)
				&& currentCardsPlayer.size() >= 10) {
			return isUntilmatePower(currentCardsPlayer, oldCardsPlayer);
		}	
		return false;
	}
}
