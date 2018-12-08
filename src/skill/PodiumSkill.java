package skill;

public class PodiumSkill extends Skill {

	public PodiumSkill() {
		super("Throw Podium", 120);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
	}
	
}
