package constants;

import javafx.scene.image.Image;

public class Images {

	public static final Image startscreen = new Image(ClassLoader.getSystemResource("startscene.png").toString());
	public static final Image playbutton_highlight = new Image(ClassLoader.getSystemResource("playbutton_highlight.png").toString(),100,30,false,false);
	public static final Image playbutton = new Image(ClassLoader.getSystemResource("playbutton.png").toString(),100,30,false,false);
	
	public static final Image playrerL = new Image(ClassLoader.getSystemResource("playerL.png").toString());
	public static final Image playerR = new Image(ClassLoader.getSystemResource("playerR.png").toString());
	public static final Image stage1 = new Image(ClassLoader.getSystemResource("stage1.jpg").toString());
	
}
