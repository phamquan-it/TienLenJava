package com.javagame.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.javagame.SpringContext;
import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.repositories.DataRepository;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ListCardsChoose extends BorderPane implements SpringContext{
	public List<Card> listCard;
	public List<Card> cardsChoose;
	public Button pushCard;
	public Button skip;
	public ListCardsChoose(List<Card>  listCardPlayer) {
		cardsChoose  = new ArrayList<>();
		// listCard là chính list bài trên tay trong MiddleControl (cùng object),
		// để ván sau chia lại vẫn hiển thị đúng mà không cần setListCards.
		listCard = listCardPlayer;
		updateCardChoose();
		// TODO Auto-generated constructor stub
		pushCard = new Button("Quất");
//		button.setOnAction(event->{
//			if(cardsChoose.size()!=0) {
//				System.out.println(
//						cardDataSupport
//						.getNameCardType(lookUpData.getCardType(cardsChoose)));
//				if(lookUpData.getCardType(cardsChoose) != -1 &&
//						tableGUI.setCurrentCard(cardsChoose,player)) {
//
//					listCard.removeAll(cardsChoose);
//					cardsChoose.clear();
//					updateCardChoose();
//					tableGUI.listPlayers.getFirst().setListCards(listCard);
//				}
//			}
//		});
		skip = new Button("Bỏ lượt");
		setMaxHeight(300);
		setBottom(new HBox(8, pushCard, skip));
	}
	public void updateCardChoose() {
		Group group = new Group();
		int translatex =0;
		Collections.sort(listCard);
		for (Card card : listCard) {
			CardGUI cardGUI = new CardGUI(card);
			cardGUI.cardButton.setOnAction(event->{
				if(cardGUI.isCardChoose()) {
					cardGUI.unChoose();
				}else {
					cardGUI.choose();
				}
				if(cardsChoose.contains(card)) {
					cardsChoose.remove(card);
				}else {
					cardsChoose.add(card);
				}
//				for(Card cardw: cardsChoose) {
//					System.out.println(cardw.getName());
//				}
			});
			cardGUI.setTranslateX(translatex);
			group.getChildren().add(cardGUI);
			translatex+=30;
		}
		setCenter(group);
	}
	/** Ván mới: trỏ sang list bài mới trên tay rồi vẽ lại. */
	public void setListCards(List<Card> listCards) {
		listCard = listCards;
		cardsChoose.clear();
		updateCardChoose();
	}
	public List<Card> getListCards(){
		return listCard;
	}
}
