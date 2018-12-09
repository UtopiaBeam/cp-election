package skill;

import constants.CCType;
import controller.GameManager;
import model.player.Player;

public class WatchSkill extends Skill {

	public WatchSkill() {
		super("Shiny Watch", 480);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
		
		Player player = GameManager.getInstance().getPlayer();
		double random = Math.random();
		
		if (random <= 0.2) {
			player.setStatus(CCType.STUN);
			player.setCCedDuration(90);
		} else if (random <= 0.5) {
			player.setStatus(CCType.SLOW);
			player.setCCedDuration(120);
		} else {
			player.setStatus(CCType.SILENCE);
			player.setCCedDuration(180);
		}
	}

}
