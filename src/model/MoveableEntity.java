package model;

import javafx.scene.image.Image;

public abstract class MoveableEntity extends Entity implements IMoveable {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	protected double speedX = 0;
	protected double speedY = 0;
	protected int facing;
	
	private Image imageL;
	private Image imageR;
	
	public MoveableEntity(double posX, double posY, String name, Image imageL, Image imageR) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight(), name, null);
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(RIGHT);
	}
	
	@Override
	public void move() {
		this.posX += facing * speedX;
		this.posY += speedY;
	}
	
	// Getters & Setters
	
	public double getSpeedX() {
		return speedX;
	}
	
	public double getSpeedY() {
		return speedY;
	}

	public int getFacing() {
		return facing;
	}

	public void setSpeedX(double speed) {
		this.speedX = speed;
	}
	
	public void setSpeedY(double speed) {
		this.speedY = speed;
	}
	
	public void setFacing(int facing) {
		this.facing = facing;
		this.setImage((facing == LEFT) ? imageL : imageR);
	}
	
}
