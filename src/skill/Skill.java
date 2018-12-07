package skill;

public abstract class Skill {

	private String name;
	private int coolDownTime;
	private int coolDownTick;
	private boolean isCoolingDown;
	
	public Skill(String name, int coolDownTime) {
		this.name = name;
		this.coolDownTime = coolDownTime;
		this.isCoolingDown = false;
	}
	
	public void resetCoolDownTick() {
		coolDownTick = 0;
	}
	
	public void addCoolDownTick() {
		if (coolDownTick < coolDownTime) {
			coolDownTick++;
		}
		if (coolDownTick == coolDownTime) {
			resetCoolDownTick();
			setCoolingDown(false);
		}
	}
	
	// Getters & Setters
	
	public String getName() {
		return name;
	}
	
	public int getCoolDownTime() {
		return coolDownTime;
	}
	
	public boolean isCoolingDown() {
		return isCoolingDown;
	}
	
	public void setCoolingDown(boolean isCoolingDown) {
		this.isCoolingDown = isCoolingDown;
	}
}
