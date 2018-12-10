package ui;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Character;
import model.player.Player;

public class HpBar {
	
	public static final double WIDTH = 60;
	public static final double HEIGHT = 6;
	private Character character;
	
	public HpBar(Character character) {
		this.character = character;
	}
	
	public void render(GraphicsContext gc) {
		
		double mapX = GameManager.getInstance().getCurrentMap().getPosX();
		double mapY = GameManager.getInstance().getCurrentMap().getPosY();
		
		double centerX = character.getPosX() + character.getWidth() / 2;
		double startX = centerX - WIDTH / 2;
		gc.setFill(Color.GRAY);
		gc.fillRect(startX-mapX, character.getPosY()+100+HEIGHT-mapY, WIDTH, HEIGHT);
		gc.setFill(Color.GREEN.	brighter());
		gc.fillRect(startX-mapX, character.getPosY()+100+HEIGHT-mapY, WIDTH * character.getHp() / character.getMaxHp(), HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(startX-mapX, character.getPosY()+100+HEIGHT-mapY, WIDTH, HEIGHT);
		
		if (character instanceof Player && ((Player) character).isRevivable()) {
			gc.drawImage(Images.revive, startX-mapX+WIDTH+5, character.getPosY()+100+HEIGHT-mapY-7, 20, 20);
		}
	}
	
}
