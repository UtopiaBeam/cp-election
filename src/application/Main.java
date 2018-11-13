package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import ui.StartScene;


public class Main extends Application {
	
	private static Stage stage;
	private static StartScene startScene;
//	private static Scene gameScene = new GameScene();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			startScene = new StartScene();
			stage.setTitle("CP-Election4.0!");
			stage.setScene(startScene);
			stage.show();
			
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root, 900, 600);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
