package skill;

public class StandySkill extends Skill {

	public StandySkill() {
		super("Standy Summon", 300);
	}

	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
	}

}
