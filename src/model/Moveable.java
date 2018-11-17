package model;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Moveable extends Frame {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	protected String name;
	protected double speed;
	protected double maxSpeed;
	protected double acc;
	protected int facing;
	
	private Image image, imageL, imageR;

	public Moveable(String name, Image image, double posX, double posY) {
		super(posX, posY, image.getWidth(), image.getHeight());
		this.setImage(image);
		this.imageL = null;
		this.imageR = null;
	}
	
	public Moveable(String name, Image imageL, Image imageR, double posX, double posY) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight());
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(RIGHT);
	}
	
	public Moveable(String name, Image imageL, Image imageR, double posX, double posY, int facing) {
		super(posX, posY, imageL.getWidth(), imageL.getHeight());
		this.imageL = imageL;
		this.imageR = imageR;
		setFacing(facing);
	}
	
	public void move() {
		this.posX += facing * speed;
	}
	
	public void move(double x, double y) {
		this.posX += x;
		this.posY += y;
	}
	
	public void accelerate() {
		this.speed += this.acc;
		if (this.speed > this.maxSpeed) {
			this.speed = this.maxSpeed;
		}
	}
	
	public abstract void update();
	
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), posX-GameManager.getInstance().getCurrentMap().posX, posY-GameManager.getInstance().getCurrentMap().posY);
	}
	
	// Getters & Setters
	
	public double getSpeed() {
		return speed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getAcc() {
		return acc;
	}

	public int getFacing() {
		return facing;
	}
	
	public Image getImage() {
		return image;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setAcc(double acc) {
		this.acc = acc;
	}

	public void setFacing(int facing) {
		this.facing = facing;
		this.image = (facing == LEFT) ? imageL : imageR;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
