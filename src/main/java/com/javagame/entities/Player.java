package com.javagame.entities;

import java.util.List;

public class Player {
	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private int id;
	private String name;
	private double monney;
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
	public void setListCards(List<Card> listCards) {
		this.listCards = listCards;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
