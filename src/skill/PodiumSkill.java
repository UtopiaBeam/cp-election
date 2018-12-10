package skill;

import controller.GameManager;
import model.effect.Podium;
import ui.GameScene;

public class PodiumSkill extends Skill {

	public PodiumSkill() {
		super("Podium Throwing", 240);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
		double x = (Math.random() <= 0.5) ? 0 : GameScene.WINDOW_WIDTH;
		double y = 400 + (int) (Math.random() * (GameScene.WINDOW_HEIGHT - 400));
		
		GameManager.getInstance().getCurrentMap().addPodium(new Podium(x, y));
		setCoolingDown(true);
	}
	
}
