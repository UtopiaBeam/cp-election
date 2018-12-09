package skill;

import controller.GameManager;
import model.effect.Podium;
import ui.GameScene;

public class PodiumSkill extends Skill {

	public PodiumSkill() {
		super("Podium Throwing", 120);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
		
		double x = (Math.random() <= 0.5) ? 0 : GameScene.WINDOW_WIDTH;
		double y = GameManager.getInstance().getPlayer().getPosY();
		
		GameManager.getInstance().getCurrentMap().addPodium(new Podium(x, y));
	}
	
}
