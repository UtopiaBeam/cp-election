package ui;


import constants.Images;
import controller.GameManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.player.Player;



public class StatusBar {
	
	private static Player player;
	
	public static final double WINDOW_HEIGHT = 600;
	public static final double HEIGHT = 50;
	public static final double HP_WIDTH = 200;
	public static final double HP_HEIGHT = 16;
	public static final double HP_X = 100;
	public static final double HP_Y = 10;
	

	private static final Color HP_COLOR = Color.GREEN.brighter();
	
	private static final Font HP_BAR_FONT = Font.font("Tahoma", FontWeight.BOLD, 12);
	
	private static double hpWidth = 0;
	
	private static Image img = Images.statusbar;
	
	public void render(GraphicsContext gc) {
		player = GameManager.getInstance().getPlayer();
		
		hpWidth = HP_WIDTH*player.getHp()*0.2/player.getMaxHp() + 0.8*hpWidth;
		
		//HP bar
		gc.setFill(Color.GRAY);
		gc.fillRoundRect(HP_X, HEIGHT+HP_Y, HP_WIDTH, HP_HEIGHT, 5, 5);
		gc.setFill(HP_COLOR);
		gc.fillRoundRect(HP_X, HEIGHT+HP_Y, hpWidth, HP_HEIGHT, 5, 5);
		
		gc.drawImage(img, 0, 0);
		gc.drawImage(Images.heal, 50, 100);
		gc.drawImage(Images.attack, 50, 160);
		gc.drawImage(Images.immune, 180, 90);
		gc.drawImage(Images.cc, 180, 160);
		gc.drawImage(Images.revive, 310, 100);
		
		//Draw text
				
		// HP MP Value
		gc.setFill(Color.WHITE);
		gc.setFont(HP_BAR_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(String.format("%d / %d", player.getHp(), player.getMaxHp()), HP_X+HP_WIDTH/2, HEIGHT+HP_Y+HP_HEIGHT/2);
		

	}
}
