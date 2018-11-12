package model.item;

import javafx.scene.image.Image;

public abstract class Item {
	
	private String name;
	private String description;
	private Image image;
	
	protected int dropSpan;
	protected int count;
	protected int maxCount;
	
	public Item(String name, String description, Image image, int dropSpan, int maxCount) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.dropSpan = dropSpan;
		this.count = 1;
		this.maxCount = maxCount;
	}
	
	public boolean isSameType(Item item) {
		return getClass() == item.getClass();
	}
	
	public boolean addCount() {
		if (this.count < this.maxCount) {
			this.count++;
			return true;
		}
		return false;
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
	
	public String getDescription() {
		return description;
	}
	
	public Image getImage() {
		return image;
	}

	public int getDropSpan() {
		return dropSpan;
	}

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
}
