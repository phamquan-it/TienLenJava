package com.javagame.entities;

import java.util.List;

public class Person {
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private int id;
	private String name;
	private List<Card> listCards;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getListCards() {
		return listCards;
	}
	public void setListCards(List<Card> listCards) {
		this.listCards = listCards;
	}
	
}
