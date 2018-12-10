package ui;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Character;
import model.player.Player;

public class HpBar {
	
	public static final int WIDTH = 60;
	public static final int HEIGHT = 6;
	private Character entity;
	
	public HpBar(Character entity) {
		this.entity = entity;
	}
	
	public void render(GraphicsContext gc) {
		
		double mapX = GameManager.getInstance().getCurrentMap().getPosX();
		double mapY = GameManager.getInstance().getCurrentMap().getPosY();
		
		double centerX = entity.getPosX() + entity.getWidth() / 2;
		double startX = centerX - WIDTH / 2;
		gc.setFill(Color.GRAY);
		gc.fillRect(startX-mapX, entity.getPosY()+100+HEIGHT-mapY, WIDTH, HEIGHT);
		gc.setFill(Color.GREEN.	brighter());
		gc.fillRect(startX-mapX, entity.getPosY()+100+HEIGHT-mapY, WIDTH * entity.getHp() / entity.getMaxHp(), HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(startX-mapX, entity.getPosY()+100+HEIGHT-mapY, WIDTH, HEIGHT);
		
		if (entity instanceof Player && ((Player) entity).isRevivable()) {
			gc.drawImage(Images.revive, startX-mapX+WIDTH+5, entity.getPosY()+100+HEIGHT-mapY-7, 20, 20);
		}
	}
	
}
