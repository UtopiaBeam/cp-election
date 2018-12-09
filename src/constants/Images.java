package constants;

import javafx.scene.image.Image;

public class Images {

	public static final Image startscreen = new Image(ClassLoader.getSystemResource("ui/startscreen/startscene.png").toString());
	public static final Image playbutton_highlight = new Image(ClassLoader.getSystemResource("ui/startscreen/playbutton_highlight.png").toString(),100,30,false,false);
	public static final Image playbutton = new Image(ClassLoader.getSystemResource("ui/startscreen/playbutton.png").toString(),100,30,false,false);
	
	public static final Image instruction1 = new Image(ClassLoader.getSystemResource("ui/instructions/p1.png").toString());
	public static final Image instruction2 = new Image(ClassLoader.getSystemResource("ui/instructions/p2.png").toString());
	public static final Image button = new Image(ClassLoader.getSystemResource("ui/instructions/b1.png").toString(),100,66,false,false);
	public static final Image button_highlight = new Image(ClassLoader.getSystemResource("ui/instructions/b2.png").toString(),100,66,false,false);
	public static final Image info = new Image(ClassLoader.getSystemResource("ui/instructions/info.png").toString(),40,40,false,false);
	
	public static final Image deathScreen = new Image(ClassLoader.getSystemResource("ui/deathscreen.png").toString());
	public static final Image quit = new Image(ClassLoader.getSystemResource("ui/quit.png").toString());
	
	
	public static final Image playrerL = new Image(ClassLoader.getSystemResource("character/playerL.png").toString(),66,100,false,false);
	public static final Image playerR = new Image(ClassLoader.getSystemResource("character/playerR.png").toString(),66,100,false,false);
	public static final Image stage = new Image(ClassLoader.getSystemResource("ui/stage.png").toString(),1000,667,false,false);
	
	public static final Image soldierL = new Image(ClassLoader.getSystemResource("character/soldierL.png").toString(),66,100,false,false);
	public static final Image soldierR = new Image(ClassLoader.getSystemResource("character/soldierR.png").toString(),66,100,false,false);
	
	public static final Image prayutL = new Image(ClassLoader.getSystemResource("character/prayut.png").toString());
	public static final Image prayutR = new Image(ClassLoader.getSystemResource("character/prayut.png").toString());
	
	public static final Image podiumL = new Image(ClassLoader.getSystemResource("character/pL.png").toString());
	public static final Image podiumR = new Image(ClassLoader.getSystemResource("character/pR.png").toString());
	
	public static final Image statusbar = new Image(ClassLoader.getSystemResource("ui/statusbar/statusbar.png").toString());
	
	public static final Image heal = new Image(ClassLoader.getSystemResource("ui/statusbar/heal.png").toString(),40,40,false,false);
	public static final Image attack = new Image(ClassLoader.getSystemResource("ui/statusbar/attack.png").toString(),40,40,false,false);
	public static final Image cc = new Image(ClassLoader.getSystemResource("ui/statusbar/cc.png").toString(),40,40,false,false);
	public static final Image revive = new Image(ClassLoader.getSystemResource("ui/statusbar/revive.png").toString(),40,40,false,false);
	public static final Image immune = new Image(ClassLoader.getSystemResource("ui/statusbar/immune.png").toString(),60,60,false,false);
	
	public static final Image immuneEffect = new Image(ClassLoader.getSystemResource("effect/immune.png").toString(),110,110,false,false);
	
	public static final Image[] normalAttackEffect = new Image[10];
	public static final Image[] healEffect = new Image[2];
	public static final Image[] stunEffect = new Image[2];
	public static final Image[] slowEffect = new Image[3];
	public static final Image[] silenceEffect = new Image[3];
	public static final Image[] reviveEffect = new Image[20];
	
	static {
		for (int i=0; i<normalAttackEffect.length; i++) {
			normalAttackEffect[i] = new Image(ClassLoader.getSystemResource("effect/attack/"+i+".png").toString(),50,60,false,false);
		}
		for (int i=0; i<healEffect.length; i++) {
			healEffect[i] = new Image(ClassLoader.getSystemResource("effect/heal/"+i+".png").toString(),100,133,false,false);
		}
		for (int i=0; i<stunEffect.length; i++) {
			stunEffect[i] = new Image(ClassLoader.getSystemResource("effect/stun/"+i+".png").toString(),100,133,false,false);
		}
		for (int i=0; i<slowEffect.length; i++) {
			slowEffect[i] = new Image(ClassLoader.getSystemResource("effect/slow/"+i+".png").toString(),100,133,false,false);
		}
		for (int i=0; i<silenceEffect.length; i++) {
			silenceEffect[i] = new Image(ClassLoader.getSystemResource("effect/silence/"+i+".png").toString(),100,133,false,false);
		}
		for (int i=0; i<reviveEffect.length; i++) {
			reviveEffect[i] = new Image(ClassLoader.getSystemResource("effect/revive/"+i+".png").toString());
		}
		
		
	}
}
