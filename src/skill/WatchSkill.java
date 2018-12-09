package skill;

public class WatchSkill extends Skill {

	public WatchSkill() {
		super("Shiny Watch", 480);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
	}

}
