package com.javagame.entities;

import java.util.List;

public class Player {
	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Player(int id, String name, List<Card> list) {
		this.id = id;
		this.name = name;
		listCards =  list;
	}
	private int id;
	private String name;
	private double monney;
	private boolean turn;
	private boolean isNextSession;
	public double getMonney() {
		return monney;
	}
	public void setMonney(double monney) {
		this.monney = monney;
	}
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
	public void setListCards(List<Card> cards) {
		this.listCards = cards;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
