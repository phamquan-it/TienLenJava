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
	/** Tứ quý: 4 lá cùng số. */
	public boolean isFourOfAKind(List<Card> cards) {
		return cardsValid.isFourCards(cards);
	}
	/**
	 * Ba đôi thông: đúng 3 đôi liên tiếp (6 lá, 3 cặp số liền kề).
	 * Tách riêng khỏi isDoubleChainCard vì hàm đó nhận cả 4-5 đôi thông.
	 */
	public boolean isThreePairsInARow(List<Card> cards) {
		return cards.size() == 6
				&& cardsValid.isDoubleChainCard(cards);
	}
	/**
	 * So hai tứ quý với nhau: tứ quý có số lớn hơn thắng.
	 * Dùng lại cardDataSupport.getMaxCard như các hàm compare khác.
	 */
	public boolean fourOfAKindBeats(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if (!isFourOfAKind(currentCardsPlayer) || !isFourOfAKind(oldCardsPlayer))
			return false;
		return singleCardCompare(
				Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
				Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer)));
	}
	/** So hai ba đôi thông: so đôi cao nhất. */
	public boolean threePairsInARowBeats(List<Card> currentCardsPlayer, List<Card> oldCardsPlayer) {
		if (!isThreePairsInARow(currentCardsPlayer) || !isThreePairsInARow(oldCardsPlayer))
			return false;
		return singleCardCompare(
				Arrays.asList(cardDataSupport.getMaxCard(currentCardsPlayer)),
				Arrays.asList(cardDataSupport.getMaxCard(oldCardsPlayer)));
	}
	/**
	 * Một bộ có được phép đè lên bộ trên bàn hay không.
	 * Gom toàn bộ luật chặt vào một chỗ để cả người chơi lẫn AI dùng chung.
	 */
	public boolean canBeat(List<Card> candidate, List<Card> onTable) {
		if (onTable == null || onTable.isEmpty())
			return true; // mở vòng mới, bộ nào hợp lệ cũng được
		if (candidate == null || candidate.isEmpty())
			return false;
		boolean four = isFourOfAKind(candidate);
		boolean threePairs = isThreePairsInARow(candidate);
		// Tứ quý chặt được: heo đơn, heo đôi, ba đôi thông, tứ quý nhỏ hơn
		if (four) {
			if (cardsValid.isSingleCard(onTable))
				return onTable.getFirst().getId() == 15;
			if (cardsValid.isDoubleCards(onTable))
				return onTable.getFirst().getId() == 15;
			if (isThreePairsInARow(onTable))
				return true;
			if (isFourOfAKind(onTable))
				return fourOfAKindBeats(candidate, onTable);
			return false;
		}
		// Ba đôi thông chặt được: heo đơn, ba đôi thông nhỏ hơn
		if (threePairs) {
			if (cardsValid.isSingleCard(onTable))
				return onTable.getFirst().getId() == 15;
			if (isThreePairsInARow(onTable))
				return threePairsInARowBeats(candidate, onTable);
			return false;
		}
		// Còn lại: bộ cùng loại, cùng số lá mới so sánh được
		if (candidate.size() != onTable.size())
			return false;
		return getResultCompare(candidate, onTable);
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
