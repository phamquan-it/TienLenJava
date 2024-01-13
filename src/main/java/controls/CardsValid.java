package controls;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;
import com.javagame.entities.Card;
@Component
public class CardsValid implements SpringContext{
	public boolean isSingleCard(List<Card> listCards) {
		if(listCards.size() == 1)
			return true;
		return false;
	}
	public boolean isDoubleCards(List<Card> listCards) {
		if(listCards.size() == 2 && 
				listCards.getFirst().getId() == listCards.get(1).getId())
			return true;
		return false;
	}
	public boolean isThreeCards(List<Card> listCards) {
		if(listCards.size() == 3 && 
				listCards.getFirst().getId() == listCards.get(1).getId() &&
				listCards.getFirst().getId() == listCards.get(2).getId())
			return true;
		return false;
	}
	public boolean isFourCards(List<Card> listCards) {
		if(listCards.size() == 4 && 
				listCards.getFirst().getId() == listCards.get(1).getId() &&
				listCards.getFirst().getId() == listCards.get(2).getId() &&
				listCards.getFirst().getId() == listCards.get(3).getId())
			return true;
		return false;
	}
	public boolean isChainCards(List<Card> listCards) {
		if(listCards.size() < 3) return false;
		Collections.sort(listCards);
		for (int i = 0; i < listCards.size()-1; i++) {
			if(listCards.get(i).getId()+1!=listCards.get(i+1).getId())
				return false;
		}
		return true;
	}
	public boolean isDoubleChainCard(List<Card> listCard) {
		//Kiểm tra liên kết các đôi
		if(listCard.size()< 6 || listCard.size()%2!=0) return false;
		Collections.sort(listCard);
		for (int i = 0; i < listCard.size(); i+=2) {
			if(listCard.get(i).getId() == listCard.get(i+1).getId()) {
				if(i>0)
					if(listCard.get(i-2).getId()+1 != listCard.get(i).getId())
						return false;
			}else return false;
		}
		return true;
	}
	public boolean isThreeChainCard(List<Card> listCard) {
		//Kiểm tra liên kết bộ ba
		if(listCard.size()< 9 || listCard.size()%3!=0) return false;
		Collections.sort(listCard);
		for (int i = 0; i < listCard.size(); i+=3) {
			if(listCard.get(i).getId() == listCard.get(i+1).getId()
					&& listCard.get(i).getId() == listCard.get(i+2).getId()) {
				if(i>0)
					if(listCard.get(i-3).getId()+1 != listCard.get(i).getId())
						return false;
			}else return false;
		}
		return true;
	}
}
