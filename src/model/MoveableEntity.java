package model;

import java.util.List;
import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.map.Map;

public abstract class MoveableEntity extends Entity {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	protected List<Image> imageWalk;
	protected List<Image> imageAttack;
	protected List<Image> imageStunned;
	protected List<Image> imageSlowed;
	
	protected double speedX = 0;
	protected double speedY = 0;
	protected double maxSpeed;
	protected int facing;
	
	private Image imageL;
	private Image imageR;
	
	private int atkTime = 0;
	private int maxAtkTime = 30;

	public MoveableEntity(String name, Image image, double posX, double posY) {
		super(posX, posY, image.getWidth(), image.getHeight(), name, image);
		this.imageL = image;
		this.imageR = image;
	}
	
	public MoveableEntity(String name, Image imageL, Image imageR, double posX, double posY) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight(), name, null);
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(RIGHT);
	}
	
	public MoveableEntity(String name, Image imageL, Image imageR, double posX, double posY, int facing) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight(), name, null);
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(facing);
	}
	
	public void move() {
		this.posX += facing * speedX;
		this.posY += speedY;
	}
	
	public void move(double x, double y) {
		this.posX += x;
		this.posY += y;
	}
	
	public abstract void update();
	
	public void renderNormalAtk(GraphicsContext gc) {
		Map map = GameManager.getInstance().getCurrentMap();
		Image img = Images.normalAttackEffect[atkTime/3];
		if (this.getFacing() == LEFT) {
			gc.drawImage(img, this.getPosX()-img.getWidth()-map.getPosX()+10, this.getPosY()-map.getPosY()+20);
		}
		else {
			gc.drawImage(img, this.getPosX()+this.getWidth()+img.getWidth()-map.getPosX()-10, this.getPosY()-map.getPosY()+20, -img.getWidth(), img.getHeight());
		}
		atkTime++;
		if (atkTime >= maxAtkTime) {
			atkTime = 0;
		}
	}
	
	
	// Getters & Setters
	
	public double getSpeedX() {
		return speedX;
	}
	
	public double getSpeedY() {
		return speedY;
	}

	public double getMaxSpeed() {
		return maxSpeed;
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

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setFacing(int facing) {
		this.facing = facing;
		this.setImage((facing == LEFT) ? imageL : imageR);
	}
	
	
	
}
