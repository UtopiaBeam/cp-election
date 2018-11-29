package model;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class MoveableEntity extends Frame {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	protected String name;
	protected double speedX = 3.5;
	protected double speedY = 0;
	protected double maxSpeed;
	protected int facing;
	
	private Image image, imageL, imageR;

	public MoveableEntity(String name, Image image, double posX, double posY) {
		super(posX, posY, image.getWidth(), image.getHeight());
		this.setImage(image);
		this.imageL = image;
		this.imageR = image;
	}
	
	public MoveableEntity(String name, Image imageL, Image imageR, double posX, double posY) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight());
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(RIGHT);
	}
	
	public MoveableEntity(String name, Image imageL, Image imageR, double posX, double posY, int facing) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight());
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
	
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().posX, posY-GameManager.getInstance().getCurrentMap().posY);
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
	
	public Image getImage() {
		return image;
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
		this.image = (facing == LEFT) ? imageL : imageR;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
