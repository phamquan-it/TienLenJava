package com.javagame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.javagame.GUI.GUIConfig;
import com.javagame.GUI.StartGUI;

public interface GUIContext {
	ApplicationContext guiContext  = new AnnotationConfigApplicationContext(GUIConfig.class);
	StartGUI startGUI = guiContext.getBean(StartGUI.class);
}
