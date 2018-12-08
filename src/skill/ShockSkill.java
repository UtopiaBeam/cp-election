package skill;

public class ShockSkill extends Skill {

	public ShockSkill() {
		super("Shock", 300);
	}
	
	@Override
	public void use() {
		if (isCoolingDown()) {
			return;
		}
	}
	
}
