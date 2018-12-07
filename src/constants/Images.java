package constants;

import javafx.scene.image.Image;

public class Images {

	public static final Image startscreen = new Image(ClassLoader.getSystemResource("startscene.png").toString());
	public static final Image playbutton_highlight = new Image(ClassLoader.getSystemResource("playbutton_highlight.png").toString(),100,30,false,false);
	public static final Image playbutton = new Image(ClassLoader.getSystemResource("playbutton.png").toString(),100,30,false,false);
	
	public static final Image playrerL = new Image(ClassLoader.getSystemResource("playerL.png").toString());
	public static final Image playerR = new Image(ClassLoader.getSystemResource("playerR.png").toString());
	public static final Image stage1 = new Image(ClassLoader.getSystemResource("stage1.png").toString(),1000,667,false,false);
	
	public static final Image soldierL = new Image(ClassLoader.getSystemResource("soldierL.png").toString(),66,100,false,false);
	public static final Image soldierR = new Image(ClassLoader.getSystemResource("soldierR.png").toString(),66,100,false,false);
	
	public static final Image statusbar = new Image(ClassLoader.getSystemResource("icon/statusbar.png").toString());
	
	public static final Image[] normalAttackEffect = new Image[10];
	
	public static final Image heal = new Image(ClassLoader.getSystemResource("icon/heal.png").toString(),40,40,false,false);
	public static final Image attack = new Image(ClassLoader.getSystemResource("icon/attack.png").toString(),40,40,false,false);
	public static final Image cc = new Image(ClassLoader.getSystemResource("icon/cc.png").toString(),40,40,false,false);
	public static final Image revive = new Image(ClassLoader.getSystemResource("icon/revive.png").toString(),40,40,false,false);
	public static final Image protect = new Image(ClassLoader.getSystemResource("icon/protect.png").toString(),60,60,false,false);
	
	
	static {
		for (int i=0; i<normalAttackEffect.length; i++) {
			normalAttackEffect[i] = new Image("attack/"+i+".png");
		}
	}
}
