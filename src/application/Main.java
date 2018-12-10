package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.*;

public class Main extends Application {
	
	private static Stage stage;
	private static StartScene startScene;
	private static InstructionsScene instructionsScene = new InstructionsScene();
	private static GameScene gameScene = new GameScene();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			startScene = new StartScene();
			stage.setTitle("CP-Election4.0!");
			stage.setScene(startScene);
			stage.show();
			stage.setOnCloseRequest(e -> {
				stage.close();
				System.exit(0);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static StartScene getStartScene() {
		return startScene;
	}

	public static void setStartScene(StartScene startScene) {
		Main.startScene = startScene;
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getGameScene() {
		return gameScene;
	}
	
	public static Scene getInstructionsScene() {
		return instructionsScene;
	}
	
	public static void setCenter() {
		stage.centerOnScreen(); 
	}
}
