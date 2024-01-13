package com.javagame;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.javagame.entities.Card;
import com.javagame.entities.Player;
import com.javagame.repositories.DataRepository;

import controls.AppConfig;
import controls.CardDataSupport;
import controls.CardsCompare;
import controls.CardsValid;
import controls.DataStorage;
import controls.GUIControl;
import controls.LookUpData;
import controls.MainControl;
import controls.MiddleControl;


public interface SpringContext {
	ApplicationContext context  = new AnnotationConfigApplicationContext(AppConfig.class);
	MainControl mainControl = context.getBean(MainControl.class);
	CardsValid cardsValid = context.getBean(CardsValid.class);
	CardsCompare cardsCompare = context.getBean(CardsCompare.class);
	CardDataSupport cardDataSupport = context.getBean(CardDataSupport.class);
	LookUpData lookUpData = context.getBean(LookUpData.class);
	GUIControl guiControl = context.getBean(GUIControl.class);
	DataStorage dataStorage = context.getBean(DataStorage.class);
	//List<Card> listCards = new DataRepository().getAllCards();
	MiddleControl middleControl =  context.getBean(MiddleControl.class);
}
