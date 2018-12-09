package skill;

public class PodiumSkill extends Skill {

	public PodiumSkill() {
		super("Podium Throwing", 120);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
	}
	
}
