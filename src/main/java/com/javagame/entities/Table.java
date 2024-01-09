package com.javagame.entities;

import java.util.List;

public class Table {
	private int id;
	private int turn;
	private boolean status;
	private List<Integer> session;
	private List<Player> listPlayers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Integer> getSession() {
		return session;
	}

	public void setSession(List<Integer> session) {
		this.session = session;
	}

	public List<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}

	public void next() {
		if (turn >= listPlayers.size() - 1)
			turn = 0;
		else
			++turn;
	}
}
