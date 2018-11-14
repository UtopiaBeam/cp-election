package model.item;

import javafx.scene.image.Image;
import model.Frame;

public abstract class Item extends Frame {
	
	protected String name;
	protected int count = 1;
	protected int maxCount;
	
	private Image image;

	public Item(double posX, double posY, double width, double height, String name, int maxCount, Image image) {
		super(posX, posY, width, height);
		this.name = name;
		this.maxCount = maxCount;
		this.image = image;
	}
	
	public Item(String name, int maxCount, Image image) {
		super(0, 0, 0, 0);
		this.name = name;
		this.maxCount = maxCount;
		this.image = image;
	}
	
	public boolean isSame(Item item) {
		return name == item.getName();
	}
	
	public boolean addCount(int count) {
		if (this.count + count > this.maxCount) {
			return false;
		}
		this.count += count;
		return true;
	}
	
	public void use() {
		if (count > 0) {
			activate();
			count--;
		}
	}
	
	public abstract void activate();
	
	// Getters & Setters
	
	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public Image getImage() {
		return image;
	}
	
}
