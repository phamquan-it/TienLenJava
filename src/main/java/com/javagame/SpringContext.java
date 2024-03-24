package com.javagame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controls.AppConfig;
import controls.CardDataSupport;
import controls.CardsCompare;
import controls.CardsValid;
import controls.DataStorage;
import controls.LookUpData;


public interface SpringContext {
	ApplicationContext context  = new AnnotationConfigApplicationContext(AppConfig.class);
	CardsValid cardsValid = context.getBean(CardsValid.class);
	CardsCompare cardsCompare = context.getBean(CardsCompare.class);
	CardDataSupport cardDataSupport = context.getBean(CardDataSupport.class);
	LookUpData lookUpData = context.getBean(LookUpData.class);
	DataStorage dataStorage = context.getBean(DataStorage.class);
}
