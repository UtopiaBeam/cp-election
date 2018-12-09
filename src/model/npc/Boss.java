package model.npc;

import constants.Images;
import controller.GameManager;
import exception.CannotAttackException;
import model.player.Player;
import skill.*;

public class Boss extends NPC {
	
	private Skill[] skills = { new PodiumSkill(), new WatchSkill() , new StandySkill() };

	public Boss(double posX, double posY) {
		super(posX, posY, "Prayut", Images.prayutL, Images.prayutR, 10000, 100, 500, 100, 120);
		setAttacking(true);
	}
	
	@Override
	public void attack() throws CannotAttackException {
		if (isAttacking()) {
			throw new CannotAttackException();
		}
		
		Player player = GameManager.getInstance().getPlayer();
		
		if (isCollideWith(player)) {
			setAttacking(true);
			player.takeDamage(getDamage());
		}
		
		for (Skill s: skills) {
			if (s.isCoolingDown()) {
				continue;
			}
			setAttacking(true);
			s.use();
			return;
		}		
	}

	@Override
	public void dead() {
		if (!isDead()) {
			return;
		}
	}
	
	// Getters & Setters

	public Skill[] getSkills() {
		return skills;
	}
	
}
