package controls;

import java.util.List;

import com.javagame.entities.Player;
public class ValidData {
	public boolean numberValidOfPlayers(List<Player> players){
		int count = 0;
		for (Player player: players)
			if(player != null)	count++;
		if(count > 1 && count < 5)
			return true;
		return false;
	}

}
