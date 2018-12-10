package skill;

import model.IUpdatable;
import model.IUseable;

public abstract class Skill implements IUseable, IUpdatable {

	private int cooldownTime;
	private int cooldownTick;
	private boolean isCoolingDown;
	
	public Skill(int cooldownTime) {
		this.cooldownTime = cooldownTime;
		this.isCoolingDown = false;
	}
	
	public void resetCooldownTick() {
		cooldownTick = 0;
	}
	
	public void addCooldownTick() {
		if (cooldownTick < cooldownTime) {
			cooldownTick++;
		}
		if (cooldownTick == cooldownTime) {
			resetCooldownTick();
			setCoolingDown(false);
		}
	}
	
	@Override
	public void update() {
		addCooldownTick();
	}
	
	// Getters & Setters
	
	public boolean isCoolingDown() {
		return isCoolingDown;
	}
	
	public void setCoolingDown(boolean isCoolingDown) {
		this.isCoolingDown = isCoolingDown;
	}
}
