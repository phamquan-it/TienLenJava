package com.javagame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controls.AppConfig;
import controls.MainControl;


public interface SpringContext {
	ApplicationContext context  = new AnnotationConfigApplicationContext(AppConfig.class);
	MainControl mainControl = context.getBean(MainControl.class);
}
