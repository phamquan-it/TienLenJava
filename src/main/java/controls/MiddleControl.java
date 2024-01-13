package controls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javagame.entities.Card;
import com.javagame.entities.Player;

import javafx.application.Platform;

@Component
public class MiddleControl {
	public int deathTime = 30;
	private Thread thread;
	private List<Player> listPlayers;
	private List<Player> session;
	private int turn;
	public void initTableControl(List<Player> players) {
		
		turn = 0;
		session = new ArrayList<>();
		listPlayers = players;
		setFirstTurn();
		countDownDeathTime(this);
	}
	public List<Player> getListPlayers() {
		return listPlayers;
	}
	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}
	public void makeNewSession() {
		session.clear();
		session.addAll(listPlayers);
	}
	public void nextTurn() {
		resetDeathTime(30);
		for (int i = 0; i < session.size(); i++) {
            if (session.get(i).getId() == turn) {
                if (i == session.size() - 1) {
                    turn = session.getFirst().getId();
                    return;
                } else if(session.size()!=1){
                    turn = session.get(i + 1).getId();
                    return;
                }
            }
        }
	}
	public void outSession(Player playerOut) {
		if(playerOut == null) return;
		for (int i = 0; i < session.size(); i++) {
			if(session.get(i).getId() == playerOut.getId()) {
				if(playerOut.getId() == turn) nextTurn();
				session.remove(i);	
			}
		}
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public List<Player> getSession(){
		return session;
	}
	public void setFirstTurn() {
		for (Player player : session) {
			for (Card card : player.getListCards()) {
				if(card.getId()==3 && card.getCategoryid() == 3) {
					setTurn(player.getId());
					return;
				}
			}
		}
		setTurn(listPlayers.getFirst().getId());
	}
	public void countDownDeathTime(MiddleControl middleControl) {
		thread = new Thread(()->{
			
			while (true) {
				try {
					if(deathTime == 0) {
						Platform.runLater(()->{
//							for (Player ses : session) {
//								if(ses.getId() != turn)
//									this.outSession(ses);
//							}
//							System.out.println("sizeSession:"+session.size());
						});
						nextTurn();
						System.out.println(turn);
					}
				//	System.out.println("Time:"+deathTime);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Cowndown. Exiting...");
		            break;
				}
				deathTime--;
			}
		});
		thread.start();
	}
	public void resetDeathTime(int newDeathTime) {
	    synchronized (this) {
	        deathTime = newDeathTime;
	    }
	}
	public int getDeathTime() {
		return deathTime;
	}
	public void cowndownInterrupt() {
		thread.interrupt();
	}
	public void size() {
		System.out.println(session.size());
	}
	public void setSession(List<Player> session) {
		this.session = session;
	}
	
}
