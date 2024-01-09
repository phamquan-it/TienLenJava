package com.javagame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.javagame.GUI.GUIConfig;

public interface GUIContext {
	ApplicationContext guiContext  = new AnnotationConfigApplicationContext(GUIConfig.class);
}
